var dat = {
  "vehiclefuellingdetails.search": {
    "numCols": 4,
    "useTimestamp": true,
    "objectName": "",
    "url": "/swm-services/vehiclefuellingdetails/_search",
    "groups": [
      {
        "name": "search",
        "label": "vehiclefuellingdetails.search.title",
        "fields": [
          {
            "name": "transactionNo",
            "jsonPath": "transactionNo",
            "label": "vehiclefuellingdetails.create.transactionNo",
            "type": "text",
            "isDisabled": false,
            "maxLength": 265,
            "patternErrorMsg": "vehiclefuellingdetails.create.field.message.transactionNo"
          },
          {
            "name": "transactionDate",
            "jsonPath": "transactionDate",
            "label": "vehiclefuellingdetails.create.transactionDate",
            "type": "datePicker",
            "isDisabled": false,
            "patternErrorMsg": "vehiclefuellingdetails.create.field.message.transactionDate"
          },
          {
            "name": "vehicleType.name",
            "jsonPath": "vehicleType.name",
            "label": "vehiclefuellingdetails.create.vehicleType",
            "type": "singleValueList",
            "isDisabled": false,
            "maxLength": 265,
            "patternErrorMsg": "vehiclefuellingdetails.create.field.message.vehicleType.name",
            "url": "/egov-mdms-service/v1/_get?&moduleName=SWM&masterName=VehicleType|$..name|$..name"
          },
          {
            "name": "vehicleRegNo.regNumber",
            "jsonPath": "vehicleRegNo.regNumber",
            "label": "vehiclefuellingdetails.create.regNumber",
            "type": "singleValueList",
            "isDisabled": false,
            "maxLength": 265,
            "patternErrorMsg": "vehiclefuellingdetails.create.field.message.vehicleRegNo.regNumber",
            "url": "/egov-mdms-service/v1/_get?&moduleName=SWM&masterName=Vehicle|$..regNumber|$..regNumber"
          },
          {
            "name": "vehicleReadingDuringFuelling",
            "jsonPath": "vehicleReadingDuringFuelling",
            "label": "vehiclefuellingdetails.create.vehicleReadingDuringFuelling",
            "type": "text",
            "isDisabled": false,
            "patternErrorMsg": "vehiclefuellingdetails.create.field.message.vehicleReadingDuringFuelling"
          },
          {
            "name": "refuellingStation.name",
            "jsonPath": "refuellingStation.name",
            "label": "vehiclefuellingdetails.create.refuellingStation",
            "type": "singleValueList",
            "isDisabled": false,
            "maxLength": 265,
            "patternErrorMsg": "vehiclefuellingdetails.create.field.message.refuellingStation.name",
            "url": "/egov-mdms-service/v1/_get?&moduleName=SWM&masterName=RefillingPumpStation|$..name|$..name"
          },
          {
            "name": "fuelFilled",
            "jsonPath": "fuelFilled",
            "label": "vehiclefuellingdetails.create.fuelFilled",
            "type": "text",
            "isDisabled": false,
            "maxLength": 265,
            "patternErrorMsg": "vehiclefuellingdetails.create.field.message.fuelFilled"
          },
          {
            "name": "typeOfFuel",
            "jsonPath": "typeOfFuel",
            "label": "vehiclefuellingdetails.create.typeOfFuel",
            "type": "text",
            "isDisabled": false,
            "maxLength": 265,
            "patternErrorMsg": "vehiclefuellingdetails.create.field.message.typeOfFuel"
          },
          {
            "name": "totalCostIncurred",
            "jsonPath": "totalCostIncurred",
            "label": "vehiclefuellingdetails.create.totalCostIncurred",
            "type": "text",
            "isDisabled": false,
            "patternErrorMsg": "vehiclefuellingdetails.create.field.message.totalCostIncurred"
          },
          {
            "name": "receiptNo",
            "jsonPath": "receiptNo",
            "label": "vehiclefuellingdetails.create.receiptNo",
            "type": "text",
            "isDisabled": false,
            "maxLength": 265,
            "patternErrorMsg": "vehiclefuellingdetails.create.field.message.receiptNo"
          },
          {
            "name": "receiptDate",
            "jsonPath": "receiptDate",
            "label": "vehiclefuellingdetails.create.receiptDate",
            "type": "datePicker",
            "isDisabled": false,
            "patternErrorMsg": "vehiclefuellingdetails.create.field.message.receiptDate"
          }
        ]
      }
    ],
    "result": {
      "header": [
        {
          "label": "vehiclefuellingdetails.search.result.transactionNo"
        },
        {
          "label": "vehiclefuellingdetails.search.result.transactionDate"
        },
        {
          "label": "vehiclefuellingdetails.search.result.vehicleType"
        },
        {
          "label": "vehiclefuellingdetails.search.result.vehicleRegNo"
        },
        {
          "label": "vehiclefuellingdetails.search.result.vehicleReadingDuringFuelling"
        },
        {
          "label": "vehiclefuellingdetails.search.result.refuellingStation"
        },
        {
          "label": "vehiclefuellingdetails.search.result.fuelFilled"
        },
        {
          "label": "vehiclefuellingdetails.search.result.typeOfFuel"
        },
        {
          "label": "vehiclefuellingdetails.search.result.totalCostIncurred"
        },
        {
          "label": "vehiclefuellingdetails.search.result.receiptNo"
        },
        {
          "label": "vehiclefuellingdetails.search.result.receiptDate"
        }
      ],
      "values": [
        "transactionNo",
        "transactionDate",
        "vehicleType.name",
        "vehicleRegNo.regNumber",
        "vehicleReadingDuringFuelling",
        "refuellingStation.name",
        "fuelFilled",
        "typeOfFuel",
        "totalCostIncurred",
        "receiptNo",
        "receiptDate"
      ],
      "resultPath": "vehicleFuellingDetails",
      "rowClickUrlUpdate": "/update/vehiclefuellingdetails/{transactionNo}",
      "rowClickUrlView": "/view/vehiclefuellingdetails/{transactionNo}"
    }
  },
  "vehiclefuellingdetails.create": {
    "numCols": 4,
    "useTimestamp": true,
    "objectName": "vehicleFuellingDetails",
    "groups": [
      {
        "name": "VehicleDetails1",
        "label": "vehiclefuellingdetails.create.group.title.VehicleDetails1",
        "fields": [
          {
            "name": "transactionDate",
            "jsonPath": "vehicleFuellingDetails[0].transactionDate",
            "label": "vehiclefuellingdetails.create.transactionDate",
            "type": "datePicker",
            "isRequired": true,
            "isDisabled": false,
            "patternErrorMsg": ""
          }
        ]
      },
      {
        "name": "VehicleDetails2",
        "label": "vehiclefuellingdetails.create.group.title.VehicleDetails2",
        "fields": [
          {
            "name": "name",
            "jsonPath": "vehicleFuellingDetails[0].vehicleType.name",
            "label": "vehiclefuellingdetails.create.vehicleType",
            "type": "singleValueList",
            "isRequired": true,
            "isDisabled": false,
            "maxLength": 128,
            "minLength": 4,
            "patternErrorMsg": "",
            "url": "/egov-mdms-service/v1/_get?&moduleName=SWM&masterName=VehicleType|$..name|$..name"
          },
          {
            "name": "regNumber",
            "jsonPath": "vehicleFuellingDetails[0].vehicleRegNo.regNumber",
            "label": "vehiclefuellingdetails.create.regNumber",
            "type": "singleValueList",
            "isRequired": true,
            "isDisabled": false,
            "maxLength": 12,
            "minLength": 6,
            "patternErrorMsg": "",
            "url": "/egov-mdms-service/v1/_get?&moduleName=SWM&masterName=Vehicle|$..regNumber|$..regNumber"
          },
          {
            "name": "vehicleReadingDuringFuelling",
            "jsonPath": "vehicleFuellingDetails[0].vehicleReadingDuringFuelling",
            "label": "vehiclefuellingdetails.create.vehicleReadingDuringFuelling",
            "type": "number",
            "isRequired": true,
            "isDisabled": false,
            "patternErrorMsg": ""
          }
        ]
      },
      {
        "name": "VehicleDetails3",
        "label": "vehiclefuellingdetails.create.group.title.VehicleDetails3",
        "fields": [
          {
            "name": "name",
            "jsonPath": "vehicleFuellingDetails[0].refuellingStation.name",
            "label": "vehiclefuellingdetails.create.refuellingStation",
            "type": "singleValueList",
            "isRequired": true,
            "isDisabled": false,
            "maxLength": 256,
            "minLength": 1,
            "patternErrorMsg": "",
            "url": "/egov-mdms-service/v1/_get?&moduleName=SWM&masterName=RefillingPumpStation|$..name|$..name"
          },
          {
            "name": "fuelFilled",
            "jsonPath": "vehicleFuellingDetails[0].fuelFilled",
            "label": "vehiclefuellingdetails.create.fuelFilled",
            "type": "number",
            "isRequired": true,
            "isDisabled": false,
            "maxLength": 256,
            "minLength": 1,
            "patternErrorMsg": ""
          },
          {
            "name": "typeOfFuel",
            "jsonPath": "vehicleFuellingDetails[0].typeOfFuel",
            "label": "vehiclefuellingdetails.create.typeOfFuel",
            "type": "text",
            "isRequired": true,
            "isDisabled": false,
            "maxLength": 256,
            "minLength": 1,
            "patternErrorMsg": ""
          },
          {
            "name": "totalCostIncurred",
            "jsonPath": "vehicleFuellingDetails[0].totalCostIncurred",
            "label": "vehiclefuellingdetails.create.totalCostIncurred",
            "type": "number",
            "isRequired": true,
            "isDisabled": false,
            "patternErrorMsg": ""
          },
          {
            "name": "receiptNo",
            "jsonPath": "vehicleFuellingDetails[0].receiptNo",
            "label": "vehiclefuellingdetails.create.receiptNo",
            "type": "text",
            "isRequired": true,
            "isDisabled": false,
            "maxLength": 256,
            "minLength": 1,
            "patternErrorMsg": ""
          },
          {
            "name": "receiptDate",
            "jsonPath": "vehicleFuellingDetails[0].receiptDate",
            "label": "vehiclefuellingdetails.create.receiptDate",
            "type": "datePicker",
            "isRequired": true,
            "isDisabled": false,
            "patternErrorMsg": ""
          }
        ]
      }
    ],
    "url": "/swm-services/vehiclefuellingdetails/_create",
    "tenantIdRequired": true
  },
  "vehiclefuellingdetails.view": {
    "numCols": 4,
    "useTimestamp": true,
    "objectName": "vehicleFuellingDetails",
    "groups": [
      {
        "name": "VehicleDetails1",
        "label": "vehiclefuellingdetails.create.group.title.VehicleDetails1",
        "fields": [
	  {
            "name": "transactionNo",
            "jsonPath": "vehicleFuellingDetails[0].transactionNo",
            "label": "vehiclefuellingdetails.create.transactionNo",
            "type": "text",
            "isRequired": true,
            "isDisabled": false,
            "patternErrorMsg": ""
          },
          {
            "name": "transactionDate",
            "jsonPath": "vehicleFuellingDetails[0].transactionDate",
            "label": "vehiclefuellingdetails.create.transactionDate",
            "type": "datePicker",
            "isRequired": true,
            "isDisabled": false,
            "patternErrorMsg": ""
          }
	 
        ]
      },
      {
        "name": "VehicleDetails2",
        "label": "vehiclefuellingdetails.create.group.title.VehicleDetails2",
        "fields": [
          {
            "name": "name",
            "jsonPath": "vehicleFuellingDetails[0].vehicleType.name",
            "label": "vehiclefuellingdetails.create.vehicleType",
            "type": "singleValueList",
            "isRequired": true,
            "isDisabled": false,
            "maxLength": 128,
            "minLength": 4,
            "patternErrorMsg": "",
            "url": "/egov-mdms-service/v1/_get?&moduleName=SWM&masterName=VehicleType|$..name|$..name"
          },
          {
            "name": "regNumber",
            "jsonPath": "vehicleFuellingDetails[0].vehicleRegNo.regNumber",
            "label": "vehiclefuellingdetails.create.regNumber",
            "type": "singleValueList",
            "isRequired": true,
            "isDisabled": false,
            "maxLength": 12,
            "minLength": 6,
            "patternErrorMsg": "",
            "url": "/egov-mdms-service/v1/_get?&moduleName=SWM&masterName=Vehicle|$..regNumber|$..regNumber"
          },
          {
            "name": "vehicleReadingDuringFuelling",
            "jsonPath": "vehicleFuellingDetails[0].vehicleReadingDuringFuelling",
            "label": "vehiclefuellingdetails.create.vehicleReadingDuringFuelling",
            "type": "number",
            "isRequired": true,
            "isDisabled": false,
            "patternErrorMsg": ""
          }
        ]
      },
      {
        "name": "VehicleDetails3",
        "label": "vehiclefuellingdetails.create.group.title.VehicleDetails3",
        "fields": [
          {
            "name": "name",
            "jsonPath": "vehicleFuellingDetails[0].refuellingStation.name",
            "label": "vehiclefuellingdetails.create.refuellingStation",
            "type": "singleValueList",
            "isRequired": true,
            "isDisabled": false,
            "maxLength": 256,
            "minLength": 1,
            "patternErrorMsg": "",
            "url": "/egov-mdms-service/v1/_get?&moduleName=SWM&masterName=RefillingPumpStation|$..name|$..name"
          },
          {
            "name": "fuelFilled",
            "jsonPath": "vehicleFuellingDetails[0].fuelFilled",
            "label": "vehiclefuellingdetails.create.fuelFilled",
            "type": "number",
            "isRequired": true,
            "isDisabled": false,
            "maxLength": 256,
            "minLength": 1,
            "patternErrorMsg": ""
          },
          {
            "name": "typeOfFuel",
            "jsonPath": "vehicleFuellingDetails[0].typeOfFuel",
            "label": "vehiclefuellingdetails.create.typeOfFuel",
            "type": "text",
            "isRequired": true,
            "isDisabled": false,
            "maxLength": 256,
            "minLength": 1,
            "patternErrorMsg": ""
          },
          {
            "name": "totalCostIncurred",
            "jsonPath": "vehicleFuellingDetails[0].totalCostIncurred",
            "label": "vehiclefuellingdetails.create.totalCostIncurred",
            "type": "number",
            "isRequired": true,
            "isDisabled": false,
            "patternErrorMsg": ""
          },
          {
            "name": "receiptNo",
            "jsonPath": "vehicleFuellingDetails[0].receiptNo",
            "label": "vehiclefuellingdetails.create.receiptNo",
            "type": "text",
            "isRequired": true,
            "isDisabled": false,
            "maxLength": 256,
            "minLength": 1,
            "patternErrorMsg": ""
          },
          {
            "name": "receiptDate",
            "jsonPath": "vehicleFuellingDetails[0].receiptDate",
            "label": "vehiclefuellingdetails.create.receiptDate",
            "type": "datePicker",
            "isRequired": true,
            "isDisabled": false,
            "patternErrorMsg": ""
          }
        ]
      }
    ],
    "tenantIdRequired": true,
    "url": "/swm-services/vehiclefuellingdetails/_search?transactionNo={transactionNo}"
  },
  "vehiclefuellingdetails.update": {
    "numCols": 4,
    "useTimestamp": true,
    "objectName": "vehicleFuellingDetails",
    "groups": [
      {
        "name": "VehicleDetails1",
        "label": "vehiclefuellingdetails.create.group.title.VehicleDetails1",
        "fields": [
          {
            "name": "transactionDate",
            "jsonPath": "vehicleFuellingDetails[0].transactionDate",
            "label": "vehiclefuellingdetails.create.transactionDate",
            "type": "datePicker",
            "isRequired": true,
            "isDisabled": false,
            "patternErrorMsg": ""
          }
        ]
      },
      {
        "name": "VehicleDetails2",
        "label": "vehiclefuellingdetails.create.group.title.VehicleDetails2",
        "fields": [
          {
            "name": "name",
            "jsonPath": "vehicleFuellingDetails[0].vehicleType.name",
            "label": "vehiclefuellingdetails.create.vehicleType",
            "type": "singleValueList",
            "isRequired": true,
            "isDisabled": false,
            "maxLength": 128,
            "minLength": 4,
            "patternErrorMsg": "",
            "url": "/egov-mdms-service/v1/_get?&moduleName=SWM&masterName=VehicleType|$..name|$..name"
          },
          {
            "name": "regNumber",
            "jsonPath": "vehicleFuellingDetails[0].vehicleRegNo.regNumber",
            "label": "vehiclefuellingdetails.create.regNumber",
            "type": "singleValueList",
            "isRequired": true,
            "isDisabled": false,
            "maxLength": 12,
            "minLength": 6,
            "patternErrorMsg": "",
            "url": "/egov-mdms-service/v1/_get?&moduleName=SWM&masterName=Vehicle|$..regNumber|$..regNumber"
          },
          {
            "name": "vehicleReadingDuringFuelling",
            "jsonPath": "vehicleFuellingDetails[0].vehicleReadingDuringFuelling",
            "label": "vehiclefuellingdetails.create.vehicleReadingDuringFuelling",
            "type": "number",
            "isRequired": true,
            "isDisabled": false,
            "patternErrorMsg": ""
          }
        ]
      },
      {
        "name": "VehicleDetails3",
        "label": "vehiclefuellingdetails.create.group.title.VehicleDetails3",
        "fields": [
          {
            "name": "name",
            "jsonPath": "vehicleFuellingDetails[0].refuellingStation.name",
            "label": "vehiclefuellingdetails.create.refuellingStation",
            "type": "singleValueList",
            "isRequired": true,
            "isDisabled": false,
            "maxLength": 256,
            "minLength": 1,
            "patternErrorMsg": "",
            "url": "/egov-mdms-service/v1/_get?&moduleName=SWM&masterName=RefillingPumpStation|$..name|$..name"
          },
          {
            "name": "fuelFilled",
            "jsonPath": "vehicleFuellingDetails[0].fuelFilled",
            "label": "vehiclefuellingdetails.create.fuelFilled",
            "type": "number",
            "isRequired": true,
            "isDisabled": false,
            "maxLength": 256,
            "minLength": 1,
            "patternErrorMsg": ""
          },
          {
            "name": "typeOfFuel",
            "jsonPath": "vehicleFuellingDetails[0].typeOfFuel",
            "label": "vehiclefuellingdetails.create.typeOfFuel",
            "type": "text",
            "isRequired": true,
            "isDisabled": false,
            "maxLength": 256,
            "minLength": 1,
            "patternErrorMsg": ""
          },
          {
            "name": "totalCostIncurred",
            "jsonPath": "vehicleFuellingDetails[0].totalCostIncurred",
            "label": "vehiclefuellingdetails.create.totalCostIncurred",
            "type": "number",
            "isRequired": true,
            "isDisabled": false,
            "patternErrorMsg": ""
          },
          {
            "name": "receiptNo",
            "jsonPath": "vehicleFuellingDetails[0].receiptNo",
            "label": "vehiclefuellingdetails.create.receiptNo",
            "type": "text",
            "isRequired": true,
            "isDisabled": false,
            "maxLength": 256,
            "minLength": 1,
            "patternErrorMsg": ""
          },
          {
            "name": "receiptDate",
            "jsonPath": "vehicleFuellingDetails[0].receiptDate",
            "label": "vehiclefuellingdetails.create.receiptDate",
            "type": "datePicker",
            "isRequired": true,
            "isDisabled": false,
            "patternErrorMsg": ""
          }
        ]
      }
    ],
    "url": "/swm-services/vehiclefuellingdetails/_update",
    "tenantIdRequired": true,
    "searchUrl": "/swm-services/vehiclefuellingdetails/_search?transactionNo={transactionNo}"
  }
}
 export default dat;
