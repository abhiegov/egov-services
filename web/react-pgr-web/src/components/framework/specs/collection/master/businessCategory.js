var dat = {
	"collection.create": {
		"numCols": 12/3,
		"url":  "/egov-common-masters/businessCategory/_create",
		"tenantIdRequired": true,
		// "idJsonPath": "BusinessCategoryInfo[0].id",
		"useTimestamp": true,
		"objectName": "BusinessCategoryInfo",
		"groups": [
			{
				"label": "wc.create.businessCategoryType.title",
				"name": "businessCategoryType",
				"fields": [
						{
							"name": "name",
							"jsonPath": "BusinessCategoryInfo.name",
							"label": "wc.create.group.fields.businessName",
							"pattern": "^[\s.]*([^\s.][\s.]*){0,100}$",
							"type": "text",
							"isRequired": true,
							"isDisabled": false,
							"requiredErrMsg": "",
							"patternErrMsg": "Length minimum is 3 and maximum is 100"
						},
            {
							"name": "code",
							"jsonPath": "BusinessCategoryInfo.code",
							"label": "wc.create.group.fields.businessCode",
							"pattern": "^[\s.]*([^\s.][\s.]*){0,100}$",
							"type": "text",
							"isRequired": true,
							"isDisabled": false,
							"requiredErrMsg": "",
							"patternErrMsg": "Length minimum is 3 and maximum is 100"
						},
						{
							"name": "Active",
							"jsonPath": "BusinessCategoryInfo.active",
							"label": "wc.create.active",
							"pattern": "",
							"type": "checkbox",
							"isRequired": false,
							"defaultValue":true,
							"isDisabled": false,
							"requiredErrMsg": "",
							"patternErrMsg": ""
						}
				]
			}
		]
	},
	"collection.search": {
		"numCols": 12/3,
		"url": "/egov-common-masters/businessCategory/_search",
		"tenantIdRequired": true,
		"useTimestamp": true,
		"objectName": "BusinessCategoryInfo",
		"groups": [
			{
				"label": "wc.search.businessCategoryType.title",
				"name": "businessCategoryType",
				"fields": [
          {
            "name": "name",
            "jsonPath": "BusinessCategoryInfo.name",
            "label": "wc.create.group.fields.businessName",
            "pattern": "^[\s.]*([^\s.][\s.]*){0,100}$",
            "type": "text",
            "isRequired": false,
            "isDisabled": false,
            "requiredErrMsg": "",
            "patternErrMsg": "Length minimum is 3 and maximum is 100"
          },
						{
							"name": "Active",
							"jsonPath": "BusinessCategoryInfo.active",
							"label": "wc.create.active",
							"pattern": "",
							"type": "checkbox",
							"isRequired": false,
							"isDisabled": false,
							"requiredErrMsg": "",
							"patternErrMsg": ""
						}
				]
			}
		],
		"result": {
			"header": [{label: "wc.search.result.businessName"}, {label: "wc.search.result.businessCode"}, {label: "wc.search.result.active"}],
			"values": ["name", "code", "active"],
			"resultPath": "BusinessCategoryInfo",
			"rowClickUrlUpdate": "/update/collection/businessCategory/{id}",
			"rowClickUrlView": "/view/collection/businessCategory/{id}"
			}
	},
	"collection.view": {
		"numCols": 12/3,
		"url":  "/egov-common-masters/businessCategory/_search?ids={id}",
		"tenantIdRequired": true,
		"idJsonPath": "BusinessCategoryInfo[0].id",
		"useTimestamp": true,
		"objectName": "BusinessCategoryInfo",
		"groups": [
			{
				"label": "wc.view.businessCategoryType.title",
				"name": "businessCategoryType",
				"fields": [
						{
							"name": "name",
							"jsonPath": "BusinessCategoryInfo.name",
							"label": "wc.create.group.fields.businessName",
							"pattern": "^[\s.]*([^\s.][\s.]*){0,100}$",
							"type": "text",
							"isRequired": true,
							"isDisabled": false,
							"requiredErrMsg": "",
							"patternErrMsg": "Length minimum is 3 and maximum is 100"
						},
            {
							"name": "code",
							"jsonPath": "BusinessCategoryInfo.code",
							"label": "wc.create.group.fields.businessCode",
							"pattern": "^[\s.]*([^\s.][\s.]*){0,100}$",
							"type": "text",
							"isRequired": true,
							"isDisabled": false,
							"requiredErrMsg": "",
							"patternErrMsg": "Length minimum is 3 and maximum is 100"
						},
						{
							"name": "Active",
							"jsonPath": "documentType.active",
							"label": "wc.create.active",
							"pattern": "",
							"type": "checkbox",
							"isRequired": false,
							"defaultValue":true,
							"isDisabled": false,
							"requiredErrMsg": "",
							"patternErrMsg": ""
						}
				]
			}
		]
	},
	"collection.update": {
		"numCols": 12/3,
		"searchUrl":  "/egov-common-masters/businessCategory/_search?ids={id}",
    "url":"/egov-common-masters/businessCategory/{BusinessCategoryInfo[0].id}/_update",
		"tenantIdRequired": true,
		"useTimestamp": true,
		"objectName": "BusinessCategoryInfo",
		"groups": [
			{
				"label": "wc.update.businessCategoryType.title",
				"name": "businessCategoryType",
				"fields": [
						{
							"name": "name",
							"jsonPath": "BusinessCategoryInfo[0].name",
							"label": "wc.create.group.fields.businessName",
							"pattern": "^[\s.]*([^\s.][\s.]*){0,100}$",
							"type": "text",
							"isRequired": true,
							"isDisabled": false,
							"requiredErrMsg": "",
							"patternErrMsg": "Length minimum is 3 and maximum is 100"
						},
            {
							"name": "code",
							"jsonPath": "BusinessCategoryInfo[0].code",
							"label": "wc.create.group.fields.businessCode",
							"pattern": "^[\s.]*([^\s.][\s.]*){0,100}$",
							"type": "text",
							"isRequired": true,
							"isDisabled": false,
							"requiredErrMsg": "",
							"patternErrMsg": "Length minimum is 3 and maximum is 100"
						},
						{
							"name": "Active",
							"jsonPath": "documentType[0].active",
							"label": "wc.create.active",
							"pattern": "",
							"type": "checkbox",
							"isRequired": false,
							"defaultValue":true,
							"isDisabled": false,
							"requiredErrMsg": "",
							"patternErrMsg": ""
						}
				]
			}
		]
	}
}

export default dat;
