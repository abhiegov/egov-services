import React, {Component} from 'react';
import {connect} from 'react-redux';
import SelectField from 'material-ui/SelectField';
import MenuItem from 'material-ui/MenuItem';
import Api from '../../../api/api';
import jp from "jsonpath";
import _ from 'lodash';
import { withRouter } from 'react-router'



class UiSelectField extends Component {
	constructor(props) {
        super(props);
		this.state = {
			dropDownData: []
		}
   	}

  initData(props) {
   		let {item, setDropDownData, useTimestamp} = props;
		if(item.hasOwnProperty("url") && item.url && item.url.search("\\|")>-1 && item.url.search("{")==-1) {
			let splitArray=item.url.split("?");
			let context="";
			let id={};
			
			for (var j = 0; j < splitArray[0].split("/").length; j++) {
				if(j==(splitArray[0].split("/").length-1)){
						context+=splitArray[0].split("/")[j];
				}else{
					context+=splitArray[0].split("/")[j]+"/";
				}
			}


			let queryStringObject=splitArray[1].split("|")[0].split("&");
			for (var i = 0; i < queryStringObject.length; i++) {
				if (i) {
					id[queryStringObject[i].split("=")[0]]=queryStringObject[i].split("=")[1];
				}
			}

			var response = Api.commonApiPost(context, id, {}, "", useTimestamp || false).then(function(response) {
				if(response) {
					let keys=jp.query(response,splitArray[1].split("|")[1]);
					let values=jp.query(response,splitArray[1].split("|")[2]);
					let dropDownData=[];
					for (var k = 0; k < keys.length; k++) {
						let obj={};
						obj["key"]= item.convertToString ? keys[k].toString() : (item.convertToNumber ? Number(keys[k]) : keys[k]);
						obj["value"]= values[k];
						if (item.hasOwnProperty("isKeyValuePair") && item.isKeyValuePair) {
							obj["value"]=keys[k]+"-"+values[k]
						}
						dropDownData.push(obj);
					}

					dropDownData.sort(function(s1, s2) {
						return (s1.value < s2.value) ? -1 : (s1.value > s2.value) ? 1 : 0;
					});

					dropDownData.unshift({key: null, value: "-- Please Select --"});
					setDropDownData(item.jsonPath, dropDownData);
				}
			},function(err) {
				console.log(err);
			});
		}
		else if (item.hasOwnProperty("defaultValue") && typeof(item.defaultValue)=="object") {
			setDropDownData(item.jsonPath, item.defaultValue);
		}
   }

	componentDidMount() {
		this.initData(this.props);
	}

	componentWillReceiveProps(nextProps) {
		if(this.props.location.pathname != nextProps.history.location.pathname) {
			this.initData(nextProps);
		}
	}





	renderSelect =(item) => {
		let {dropDownData}=this.props;
		switch (this.props.ui) {
			case 'google':
				return (
						<SelectField
							id={item.jsonPath.split(".").join("-")}
							floatingLabelStyle={{"color": item.isDisabled ? "#A9A9A9" : "#696969", "fontSize": "20px", "white-space": "nowrap"}}
							labelStyle={{"color": "#5F5C57"}}
							floatingLabelFixed={true}
							dropDownMenuProps={{animated: false, targetOrigin: {horizontal: 'left', vertical: 'bottom'}}}
							style={{"display": (item.hide ? 'none' : 'inline-block')}}
							errorStyle={{"float":"left"}}
							fullWidth={true}
							hintText="Please Select"
							floatingLabelText={<span>{item.label} <span style={{"color": "#FF0000"}}>{item.isRequired ? " *" : ""}</span></span>}
							value={this.props.getVal(item.jsonPath)}
							onChange={(event, key, value) =>{
								this.props.handler({target: {value: value}}, item.jsonPath, item.isRequired ? true : false, '', item.requiredErrMsg, item.patternErrMsg, item.expression, item.expressionMsg)
							}}
							disabled={item.isDisabled}
							errorText={this.props.fieldErrors[item.jsonPath]}
							maxHeight={200}>
					            {dropDownData.hasOwnProperty(item.jsonPath) && dropDownData[item.jsonPath].map((dd, index) => (
					                <MenuItem value={dd.key} key={index} primaryText={dd.value} />
					            ))}
			            </SelectField>

				);
		}
	}

	render () {
		return (
	      <div>
	        {this.renderSelect(this.props.item)}
	      </div>
	    );
	}
}

const mapStateToProps = state => ({
	dropDownData: state.framework.dropDownData, 
	formData: state.frameworkForm.form
});

const mapDispatchToProps = dispatch => ({
  setDropDownData:(fieldName,dropDownData)=>{
    dispatch({type:"SET_DROPDWON_DATA", fieldName, dropDownData})
  }
});
export default withRouter(connect(mapStateToProps, mapDispatchToProps)(UiSelectField));
