/*
 * eGov suite of products aim to improve the internal efficiency,transparency,
 * accountability and the service delivery of the government  organizations.
 *
 *  Copyright (C) 2016  eGovernments Foundation
 *
 *  The updated version of eGov suite of products as by eGovernments Foundation
 *  is available at http://www.egovernments.org
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program. If not, see http://www.gnu.org/licenses/ or
 *  http://www.gnu.org/licenses/gpl.html .
 *
 *  In addition to the terms of the GPL license to be adhered to in using this
 *  program, the following additional terms are to be complied with:
 *
 *      1) All versions of this program, verbatim or modified must carry this
 *         Legal Notice.
 *
 *      2) Any misrepresentation of the origin of the material is prohibited. It
 *         is required that all modified versions of this material be marked in
 *         reasonable ways as different from the original version.
 *
 *      3) This license does not grant any rights to any user of the program
 *         with regards to rights under trademark law for use of the trade names
 *         or trademarks of eGovernments Foundation.
 *
 *  In case of any queries, you can reach eGovernments Foundation at contact@egovernments.org.
 */

$(document).ready(function()
{
	
	$('.recovrbtn').click(function(){
		if($('#emailOrMobileNum').val()===""){
			$('#emailOrMobileNoReq').show();
		}else{
			$('#originURL').val(location.origin);
			if($(this).attr("id") == "recoveryotpbtn")
				$("#byOtp").val(true);
            else
                $("#byOtp").val(false);
			$('#forgotPasswordForm').attr('action', '/egi/login/password/recover').trigger('submit');
		}
		return false;
	});

    $("#otprecoverybtn").click( function () {
            if ($("#token").val() != "") {
                window.location = '/egi/login/password/reset?token='+$("#token").val();
            }
        }
    );

	$('#compsearch').click(function() {
		var compnum=$.trim($('#compsearchtxt').val());
		if (compnum !== "") {
			$('.search-error-msg').addClass('display-hide');
			window.open('templates/view-complaint.html?srn='+compnum, '_blank');
		}else{
			$('.search-error-msg').removeClass('display-hide');
		}
	});
	
	var checklocation = false;
	
	$('#j_username').blur(function(){
		$('#locationId').empty();
		if(!$.trim($(this).val())){
			//console.log('Trimmed - value is not there');
		}else{
			$.ajax({
			      url: "requiredlocations?username="+this.value,
			      dataType: "json",
			      success: function(data) { 
			    	  checklocation = true;
			    	  //console.log(JSON.stringify(data));
			    	  if(data.length > 0){
			    		  $('#locationId').append('<option value="">select location</option>');
			    		  $.each(data, function (key,value) {
							  //console.log(value.id+"<-->"+value.name);
							  var opt = "<option value=" + value.id + ">" + value.name + "</option>";
							  $('#locationId').append(opt);
							  $("#locationId").attr('required', true);
						  });	
			    		  $('#counter-section').removeClass('display-hide');
			    		  loaddpdown_value();
			    	  }else{
			    		  $('#locationId').empty();
			    		  $('#locationId').attr('required', false);
			    		  $('#counter-section').addClass('display-hide');
			    	  }
		    	  },
		    	  error: function() {
	    	         //console.log('Error method');
		          }
			});
		}
		//ajax call to load counter
		
	});
	
	function loaddpdown_value(){
		$("#locationId").each(function() { 
			//console.log($(this).children('option').length);
			if($(this).children('option').length == 2)
			{
			  $(this).find('option').eq(1).prop('selected', true);
			}
		});
	}
	
	$("#signin-action").click(function(e){
		var obj = $(this);
		obj.attr("disabled", "disabled");
		if($('#signform').valid()){
			//console.log('Form valid');
			if(!checklocation){
				$('#j_username').trigger('blur');
				e.preventDefault();
			}
			$.ajax({
				url : '/user/_login?jurisdiction_id=ap.public',
				type: 'POST',
				beforeSend : function(){
					showLoader();
				},
				headers : {
					"Content-Type": "application/x-www-form-urlencoded",
					"Authorization": "Basic ZWdvdi11c2VyLWNsaWVudDplZ292LXVzZXItc2VjcmV0"
				},
				data:{
					username : $('#username').val(),
					password : $('#password').val(),
					grant_type: 'password',
					scope : 'read'
				},
				success : function(response){
					localStorage.setItem("auth", response.access_token);
					localStorage.setItem("type", response.User.type);
					localStorage.setItem("id", response.User.id);
					if(localStorage.getItem("type") == 'CITIZEN')
						window.open("templates/homepagecitizen.html","_self")
					else if(localStorage.getItem("type") == 'EMPLOYEE')
						window.open("templates/homepage.html","_self")
				},
				error : function(){
					bootbox.alert('User Auth failed!');
				},
				complete : function(){
					obj.removeAttr("disabled")
					hideLoader();
				}
			});
		}else{
			//console.log('Form not valid');
			e.preventDefault();
		}
	});
	
	$("#signform").validate({
	    rules: {
	    	j_username: "required",
	    	j_password: "required"
	    },
	    messages: {
	    	j_username: "Please enter your Mobile Number / Login ID",
	    	j_password: "Please enter your password"
	    }
	});
	
	if(navigator.cookieEnabled){
		
	}else{
		$('#cookieornoscript').modal('show', {backdrop: 'static'});
	}	
	
});
