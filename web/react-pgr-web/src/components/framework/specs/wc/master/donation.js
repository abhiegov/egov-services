var dat = {
	"wc.create": {
		"numCols": 12/3,
		"url": "/wcms/masters/donations/_create",
		"tenantIdRequired": true,
		"idJsonPath": "Donations[0].code",
		"useTimestamp": true,
		"objectName": "Donations",
		"groups": [
			{
				"label": "wc.create.donation.title",
				"name": "donation",
				"fields": [
					{
						"name": "UsageType",
						"jsonPath": "Donations[0].usageTypeCode",
						"label": "wc.create.groups.connectionDetails.usageType",
						"pattern": "",
						"type": "singleValueList",
						"url": "/wcms/masters/usagetypes/_search?&active=true|$..code|$..name",
						"isRequired": true,
						"isDisabled": false,
						"requiredErrMsg": "",
						"patternErrMsg": "",
						"depedants": [{
                "jsonPath": "Donations[0].subUsageTypeCode",
                "type": "dropDown",
                "pattern": "/wcms/masters/usagetypes/_search?&isSubUsageType=true&parent={Donations[0].usageTypeCode}|$..code|$..name"
              }]
					},
          {
            "name": "SubUsageType",
            "jsonPath": "Donations[0].subUsageTypeCode",
            "label": "wc.create.groups.connectionDetails.subUsageType",
            "pattern": "",
            "type": "singleValueList",
            "isRequired": true,
            "isDisabled": false,
            "requiredErrMsg": "",
            "patternErrMsg": ""
          },
        {
						"name": "MinHscPipeSizeType",
						"jsonPath": "Donations[0].minPipeSize",
						"label": "wc.create.minPipeSize",
						"pattern": "",
						"type": "singleValueList",
						"isRequired": true,
						"isDisabled": false,
						"url": "/wcms/masters/pipesizes/_search?&active=true|$..sizeInMilimeter|$..sizeInInch",
						"requiredErrMsg": "",
						"patternErrMsg": ""
					},
          {
						"name": "MaxHscPipeSizeType",
						"jsonPath": "Donations[0].maxPipeSize",
						"label": "wc.create.maxPipeSize",
						"pattern": "",
						"type": "singleValueList",
						"isRequired": true,
						"isDisabled": false,
						"url": "/wcms/masters/pipesizes/_search?&active=true|$..sizeInMilimeter|$..sizeInInch",
						"requiredErrMsg": "",
						"patternErrMsg": ""
					},
          {
            "name": "donationAmount",
            "jsonPath": "Donations[0].donationAmount",
            "label": "wc.create.donationAmount",
            "pattern": "^\\d+(\\.\\d+)?$",
            "type": "number",
            "isRequired": true,
            "isDisabled": false,
            "requiredErrMsg": "",
            "patternErrMsg": ""
          },
					{
            "name": "FromDate",
            "jsonPath": "Donations[0].fromDate",
            "label": "wc.create.fromDate",
            "pattern": "",
            "type": "datePicker",
            "isRequired": true,
            "isDisabled": false,
            "requiredErrMsg": "",
            "patternErrMsg": "",
						"expression":"$Donations[0].fromDate < $Donations[0].toDate",
						"expressionMsg": "From Date should be less than To Date"
          },
					{
            "name": "ToDate",
            "jsonPath": "Donations[0].toDate",
            "label": "wc.create.toDate",
            "pattern": "",
            "type": "datePicker",
            "isRequired": true,
            "isDisabled": false,
            "requiredErrMsg": "",
            "patternErrMsg": "",
						"expression":"$Donations[0].fromDate < $Donations[0].toDate",
						"expressionMsg": "From Date should be less than To Date"
          },
					{
						"name": "Active",
						"jsonPath": "Donations[0].active",
						"label": "wc.create.active",
						"pattern": "",
						"type": "checkbox",
						"isRequired": false,
						"isDisabled": false,
						"defaultValue":true,
						"requiredErrMsg": "",
						"patternErrMsg": ""
					},
					{
            "name": "Outside ULB",
            "jsonPath": "Donations[0].outsideUlb",
            "label": "wc.create.groups.connectionDetails.fields.outSide",
            "pattern": "",
            "type": "checkbox",
            "isRequired": false,
            "isDisabled": false,
            "requiredErrMsg": "",
            "patternErrMsg": "",
            "defaultValue":false
          }
				]
			}
		]
	},
	"wc.search": {
		"numCols": 12/3,
		"url": "/wcms/masters/donations/_search",
		"tenantIdRequired": true,
		"useTimestamp": true,
		"objectName": "Donations",
		"groups": [
			{
				"label": "wc.search.Donation.title",
				"name": "createCategoryType",
				"fields": [
					{
						"name": "UsageType",
						"jsonPath": "Donations[0].usageTypeCode",
						"label": "wc.create.groups.connectionDetails.usageType",
						"pattern": "",
						"type": "singleValueList",
						"url": "/wcms/masters/usagetypes/_search?&active=true|$..code|$..name",
						"isRequired": false,
						"isDisabled": false,
						"requiredErrMsg": "",
						"patternErrMsg": "",
						"depedants": [{
                "jsonPath": "Donations[0].subUsageTypeCode",
                "type": "dropDown",
                "pattern": "/wcms/masters/usagetypes/_search?&isSubUsageType=true&parent={Donations[0].usageTypeCode}|$..code|$..name"
              }]
					},
          {
            "name": "SubUsageType",
            "jsonPath": "Donations[0].subUsageTypeCode",
            "label": "wc.create.groups.connectionDetails.subUsageType",
            "pattern": "",
            "type": "singleValueList",
            "isRequired": false,
            "isDisabled": false,
            "requiredErrMsg": "",
            "patternErrMsg": ""
          },
        {
						"name": "hscPipeSizeType",
						"jsonPath": "Donations[0].minPipeSize",
						"label": "wc.create.minPipeSize",
						"pattern": "",
						"type": "singleValueList",
						"isRequired": false,
						"isDisabled": false,
						"url": "/wcms/masters/pipesizes/_search?&active=true|$..sizeInMilimeter|$..sizeInInch",
						"requiredErrMsg": "",
						"patternErrMsg": ""
					}
				]
			}
		],
		"result": {
			"header": [{label: "wc.create.groups.connectionDetails.usageType"},{label: "wc.create.groups.connectionDetails.subUsageType"}, {label: "wc.create.minPipeSize"},{label: "wc.create.maxPipeSize"},{label: "wc.create.donationAmount"},{label: "wc.create.active"}],
			"values": ["usageTypeName","subUsageTypeName","minPipeSizeInInch","maxPipeSizeInInch","donationAmount","active"],
			"resultPath": "Donations",
			"rowClickUrlUpdate": "/update/wc/donation/{id}",
			"rowClickUrlView": "/view/wc/donation/{id}"
			}
	},
	"wc.view": {
		"numCols": 12/3,
		"url": "/wcms/masters/donations/_search?ids={id}",
		"tenantIdRequired": true,
		"useTimestamp": true,
		"objectName": "Donations",
		"groups": [
			{
				"label": "wc.view.Donations.title",
				"name": "viewDonations",
				"fields": [
					{
						"name": "UsageType",
						"jsonPath": "Donations[0].usageTypeName",
						"label": "wc.create.groups.connectionDetails.usageType",
						"pattern": "",
						"type": "singleValueList",
						"url": "/wcms/masters/usagetypes/_search?&active=true|$..code|$..name",
						"isRequired": true,
						"isDisabled": false,
						"requiredErrMsg": "",
						"patternErrMsg": "",
						"depedants": [{
                "jsonPath": "Donations[0].subUsageTypeCode",
                "type": "dropDown",
                "pattern": "/wcms/masters/usagetypes/_search?&isSubUsageType=true&parent={Donations[0].usageTypeCode}|$..code|$..name"
              }]
					},
          {
            "name": "SubUsageType",
            "jsonPath": "Donations[0].subUsageTypeName",
            "label": "wc.create.groups.connectionDetails.subUsageType",
            "pattern": "",
            "type": "singleValueList",
            "isRequired": true,
            "isDisabled": false,
            "requiredErrMsg": "",
            "patternErrMsg": ""
          },
        	{
						"name": "MinHscPipeSizeType",
						"jsonPath": "Donations[0].minPipeSize",
						"label": "wc.create.minPipeSize",
						"pattern": "",
						"type": "singleValueList",
						"isRequired": true,
						"isDisabled": false,
						"url": "/wcms/masters/pipesizes/_search?&active=true|$..sizeInMilimeter|$..sizeInInch",
						"requiredErrMsg": "",
						"patternErrMsg": ""
					},
          {
						"name": "MaxHscPipeSizeType",
						"jsonPath": "Donations[0].maxPipeSize",
						"label": "wc.create.maxPipeSize",
						"pattern": "",
						"type": "singleValueList",
						"isRequired": true,
						"isDisabled": false,
						"url": "/wcms/masters/pipesizes/_search?&active=true|$..sizeInMilimeter|$..sizeInInch",
						"requiredErrMsg": "",
						"patternErrMsg": "",
						"convertToString":true
					},
					{
						"name": "donationAmount",
						"jsonPath": "Donations[0].donationAmount",
						"label": "wc.create.donationAmount",
						"pattern": "^\\d+(\\.\\d+)?$",
						"type": "number",
						"isRequired": true,
						"isDisabled": false,
						"requiredErrMsg": "",
						"patternErrMsg": ""
					},
					{
						"name": "FromDate",
						"jsonPath": "Donations[0].fromDate",
						"label": "wc.create.fromDate",
						"pattern": "",
						"type": "datePicker",
						"isRequired": true,
						"isDisabled": false,
						"requiredErrMsg": "",
						"patternErrMsg": "",
						"expression":"$Donations[0].fromDate < $Donations[0].toDate",
						"expressionMsg": "From Date should be less than To Date"
					},
					{
						"name": "ToDate",
						"jsonPath": "Donations[0].toDate",
						"label": "wc.create.toDate",
						"pattern": "",
						"type": "datePicker",
						"isRequired": true,
						"isDisabled": false,
						"requiredErrMsg": "",
						"patternErrMsg": "",
						"expression":"$Donations[0].fromDate < $Donations[0].toDate",
						"expressionMsg": "From Date should be less than To Date"
					},
					{
						"name": "Active",
						"jsonPath": "Donations[0].active",
						"label": "wc.create.active",
						"pattern": "",
						"type": "checkbox",
						"isRequired": false,
						"isDisabled": false,
						"defaultValue":true,
						"requiredErrMsg": "",
						"patternErrMsg": ""
					},
					{
						"name": "Outside ULB",
						"jsonPath": "Donations[0].outsideUlb",
						"label": "wc.create.groups.connectionDetails.fields.outSide",
						"pattern": "",
						"type": "checkbox",
						"isRequired": false,
						"isDisabled": false,
						"requiredErrMsg": "",
						"patternErrMsg": "",
						"defaultValue":false
					}
				]
			}
		]
	},
	"wc.update": {
		"numCols": 12/3,
		"searchUrl": "/wcms/masters/donations/_search?ids={id}",
		"url":"/wcms/masters/donations/_update",
		"tenantIdRequired": true,
		"useTimestamp": true,
		"objectName": "Donations",
		"groups": [
			{
				"label": "wc.update.Donation.title",
				"name": "updateDonation",
				"fields": [
					{
						"name": "UsageType",
						"jsonPath": "Donations[0].usageTypeCode",
						"label": "wc.create.groups.connectionDetails.usageType",
						"pattern": "",
						"type": "singleValueList",
						"url": "/wcms/masters/usagetypes/_search?&active=true|$..code|$..name",
						"isRequired": true,
						"isDisabled": false,
						"requiredErrMsg": "",
						"patternErrMsg": "",

						"depedants": [{
                "jsonPath": "Donations[0].subUsageTypeCode",
                "type": "dropDown",
                "pattern": "/wcms/masters/usagetypes/_search?&isSubUsageType=true&parent={Donations[0].usageTypeCode}|$..code|$..name"
              }]
					},
          {
            "name": "SubUsageType",
            "jsonPath": "Donations[0].subUsageTypeCode",
            "label": "wc.create.groups.connectionDetails.subUsageType",
            "pattern": "",
            "type": "singleValueList",
            "isRequired": true,
            "isDisabled": false,
            "requiredErrMsg": "",
            "patternErrMsg": ""
          },
        {
						"name": "MinHscPipeSizeType",
						"jsonPath": "Donations[0].minPipeSize",
						"label": "wc.create.minPipeSize",
						"pattern": "",
						"type": "singleValueList",
						"isRequired": true,
						"isDisabled": false,
						"url": "/wcms/masters/pipesizes/_search?&active=true|$..sizeInMilimeter|$..sizeInInch",
						"requiredErrMsg": "",
						"patternErrMsg": ""
					},
          {
						"name": "MaxHscPipeSizeType",
						"jsonPath": "Donations[0].maxPipeSize",
						"label": "wc.create.maxPipeSize",
						"pattern": "",
						"type": "singleValueList",
						"isRequired": true,
						"isDisabled": false,
						"url": "/wcms/masters/pipesizes/_search?&active=true|$..sizeInMilimeter|$..sizeInInch",
						"requiredErrMsg": "",
						"patternErrMsg": ""
					},
          {
            "name": "donationAmount",
            "jsonPath": "Donations[0].donationAmount",
            "label": "wc.create.donationAmount",
            "pattern": "^\\d+(\\.\\d+)?$",
            "type": "number",
            "isRequired": true,
            "isDisabled": false,
            "requiredErrMsg": "",
            "patternErrMsg": ""
          },
					{
            "name": "FromDate",
            "jsonPath": "Donations[0].fromDate",
            "label": "wc.create.fromDate",
            "pattern": "",
            "type": "datePicker",
            "isRequired": true,
            "isDisabled": false,
            "requiredErrMsg": "",
            "patternErrMsg": "",
						"expression":"$Donations[0].fromDate < $Donations[0].toDate",
						"expressionMsg": "From Date should be less than To Date"
          },
					{
            "name": "ToDate",
            "jsonPath": "Donations[0].toDate",
            "label": "wc.create.toDate",
            "pattern": "",
            "type": "datePicker",
            "isRequired": true,
            "isDisabled": false,
            "requiredErrMsg": "",
            "patternErrMsg": "",
						"expression":"$Donations[0].fromDate < $Donations[0].toDate",
						"expressionMsg": "From Date should be less than To Date"
          },
					{
						"name": "Active",
						"jsonPath": "Donations[0].active",
						"label": "wc.create.active",
						"pattern": "",
						"type": "checkbox",
						"isRequired": false,
						"isDisabled": false,
						"defaultValue":true,
						"requiredErrMsg": "",
						"patternErrMsg": ""
					},
					{
            "name": "Outside ULB",
            "jsonPath": "Donations[0].outsideUlb",
            "label": "wc.create.groups.connectionDetails.fields.outSide",
            "pattern": "",
            "type": "checkbox",
            "isRequired": false,
            "isDisabled": false,
            "requiredErrMsg": "",
            "patternErrMsg": "",
            "defaultValue":false
          }
				]
			}
		]
	}
}

export default dat;
