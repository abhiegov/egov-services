var dat = {
    'swm.search': {
      numCols: 4,
      useTimestamp: true,
      objectName: 'Shift',
      url: '/egov-mdms-service/v1/_search',
      groups: [
        {
          name:'ShiftSelection',
          label: 'swm.shift.create.group.title.ShiftSelection',
          fields: [
            {
              name: 'shiftType',
              jsonPath: 'shiftType.code',
              label: 'swm.Shift.create.shiftType',
              pattern: '',
              type: 'autoCompelete',
              isRequired: false,
              isDisabled: false,
              defaultValue: '',
              maxLength: 12,
              minLength: 6,
              url: '/egov-mdms-service/v1/_get?&moduleName=swm&masterName=ShiftType|$..ShiftType.*.code|$..ShiftType.*.name',
            },
            {
              name: 'designationName',
              jsonPath: 'designation.id',
              label: 'swm.Shift.create.designation',
              pattern: '',
              type: 'autoCompelete',
              isRequired: false,
              isDisabled: false,
              defaultValue: '',
              maxLength: 128,
              minLength: 1,
              patternErrorMsg: '',
              url: '/hr-masters/designations/_search?tenantId=default|$..id|$..name',
            },
            /*{
              name: 'code',
              jsonPath: 'MasterMetaData.masterData[0].code',
              type: 'text',
            },*/
           /* {
              name: 'moduleName',
              jsonPath: 'moduleName',
              type: 'text',             
              defaultValue: 'swm',
              hide: true
            },
            {
              name: 'masterName',
              jsonPath: 'masterName',              
              type: 'text',
              defaultValue: 'Shift',
              hide: true
            },*/
          ]
        },
      ],
      result: {
        header: [
          {
            label: 'swm.Shift.create.shiftType',
          },
          {
            label: 'swm.Shift.create.designation',
          },
          {
            label: 'swm.Shift.create.shiftStartTime',
          },
          {
            label: 'swm.Shift.create.shiftEndTime',
          },
        ],
        values: [
          'shiftType.name',
          'designation.name',
          'shiftStartTime',
          'shiftEndTime',
        ],
        resultPath: 'MdmsRes.swm.Shift',
        rowClickUrlUpdate: '/update/swm/shiftmasters/{code}',
        rowClickUrlView: '/view/swm/shiftmasters/{code}',
        isMasterScreen: true
      },
    },
    'swm.create': {
      numCols: 3,
      useTimestamp: true,
      objectName: 'MasterMetaData',
      idJsonPath: 'MasterMetaData.masterData[0].code',
      title: 'swm.create.page.title.shiftmasters',
      groups: [
        {
          name:'ShiftSelection',
          label: 'swm.shift.create.group.title.ShiftSelection',
          fields: [
            {
              name: 'shiftType',
              jsonPath: 'MasterMetaData.masterData[0].shiftType.code',
              label: 'swm.Shift.create.shiftType',
              pattern: '',
              type: 'singleValueList',
              isRequired: true,
              isDisabled: false,
              defaultValue: '',
              maxLength: 12,
              minLength: 6,
              url: '/egov-mdms-service/v1/_get?&moduleName=swm&masterName=ShiftType|$..ShiftType.*.code|$..ShiftType.*.name',
            },
            {
              name: 'departmentName',
              jsonPath: 'MasterMetaData.masterData[0].department.code',
              label: 'swm.Shift.create.department',
              pattern: '',
              type: 'autoCompelete',
              isRequired: true,
              isDisabled: false,
              defaultValue: '',
              maxLength: 128,
              minLength: 1,
              patternErrorMsg: '',
              url: "/egov-mdms-service/v1/_get?&moduleName=common-masters&masterName=Department|$..code|$..name",
              hasIdConverion: true,
            },
            {
              name: 'designationName',
              jsonPath: 'MasterMetaData.masterData[0].designation.code',
              label: 'swm.Shift.create.designation',
              pattern: '',
              type: 'autoCompelete',
              isRequired: true,
              isDisabled: false,
              defaultValue: '',
              maxLength: 128,
              minLength: 1,
              patternErrorMsg: '',
              url: '/hr-masters/designations/_search?tenantId=default|$..id|$..name',
            },
            {
              name: 'code',
              jsonPath: 'MasterMetaData.masterData[0].code',
              defaultValue: 'Shift-' + new Date().getTime(),
              isRequired : true,
              type: 'text',
              hide: true,
            },
            {
              name: 'tenantId',
              jsonPath: 'MasterMetaData.masterData[0].tenantId',
              type: 'text',             
              defaultValue: localStorage.getItem("tenantId"),
              hide: true
            },
            {
              name: 'moduleName',
              jsonPath: 'MasterMetaData.moduleName',
              type: 'text',             
              defaultValue: 'swm',
              hide: true
            },
            {
              name: 'masterName',
              jsonPath: 'MasterMetaData.masterName',              
              type: 'text',
              defaultValue: 'Shift',
              hide: true
            },
          ]
        },
        {
          name:'SiftDetails',
          label: 'swm.shift.create.group.title.SiftDetails',
          fields: [
            {
              name: 'shiftStartTime',
              jsonPath: 'MasterMetaData.masterData[0].shiftStartTime',
              label: 'swm.Shift.create.shiftStartTime',
              pattern: '',
              type: 'timePicker',
              isDisabled: false,
              defaultValue: '',
              maxLength: 12,
              minLength: 6,
              patternErrorMsg: '',
              url: '',
            },
            {
              name: 'shiftEndTime',
              jsonPath: 'MasterMetaData.masterData[0].shiftEndTime',
              label: 'swm.Shift.create.shiftEndTime',
              pattern: '',
              type: 'timePicker',
              isDisabled: false,
              defaultValue: '',
              patternErrorMsg: '',
              url: '',
            },
            {
              name: 'lunchStartTime',
              jsonPath: 'MasterMetaData.masterData[0].lunchStartTime',
              label: 'swm.Shift.create.lunchStartTime',
              pattern: '',
              type: 'timePicker',
              isDisabled: false,
              defaultValue: '',
              maxLength: 12,
              minLength: 6,
              patternErrorMsg: '',
              url: '',
            },
            {
              name: 'lunchEndTime',
              jsonPath: 'MasterMetaData.masterData[0].lunchEndTime',
              label: 'swm.Shift.create.lunchEndTime',
              pattern: '',
              type: 'timePicker',
              isDisabled: false,
              defaultValue: '',
              patternErrorMsg: '',
              url: '',
            },
            {
              name: 'graceTimeFrom',
              jsonPath: 'MasterMetaData.masterData[0].graceTimeFrom',
              label: 'swm.Shift.create.graceTimeFrom',
              pattern: '',
              type: 'timePicker',
              isDisabled: false,
              defaultValue: '',
              maxLength: 12,
              minLength: 6,
              patternErrorMsg: '',
              url: '',
            },
            {
              name: 'graceTimeTo',
              jsonPath: 'MasterMetaData.masterData[0].graceTimeTo',
              label: 'swm.Shift.create.graceTimeTo',
              pattern: '',
              type: 'timePicker',
              isDisabled: false,
              defaultValue: '',
              patternErrorMsg: '',
              url: '',
            },
            {
              name: 'remarks',
              jsonPath: 'MasterMetaData.masterData[0].remarks',
              label: 'swm.Shift.create.remarks',
              pattern: '',
              type: 'textarea',
              isDisabled: false,
              defaultValue: '',
              patternErrorMsg: '',
              url: '',
            },
          ],
        },
      ],
      url: '/egov-mdms-create/v1/_create',
      tenantIdRequired: true
    },
    'swm.view': {
      numCols: 4,
      useTimestamp: true,
      objectName: 'Shift',
      searchUrl: '/egov-mdms-service/v1/_search?code={code}',
      groups: [
        {
          name:'ShiftSelection',
          label: 'swm.shift.create.group.title.ShiftSelection',
          fields: [
            {
              name: 'shiftType',
              jsonPath: 'MdmsRes.swm.Shift[0].shiftType.code',
              label: 'swm.Shift.create.shiftType',
              pattern: '',
              type: 'singleValueList',
              isRequired: true,
              isDisabled: false,
              defaultValue: '',
              maxLength: 12,
              minLength: 6,
              url: '/egov-mdms-service/v1/_get?&moduleName=swm&masterName=ShiftType|$..ShiftType.*.code|$..ShiftType.*.name',
            },
            {
              name: 'departmentName',
              jsonPath: 'MdmsRes.swm.Shift[0].department.code',
              label: 'swm.Shift.create.department',
              pattern: '',
              type: 'autoCompelete',
              isRequired: true,
              isDisabled: false,
              defaultValue: '',
              maxLength: 128,
              minLength: 1,
              patternErrorMsg: '',
              url: "/egov-mdms-service/v1/_get?&moduleName=common-masters&masterName=Department|$..code|$..name",
              hasIdConverion: true,
            },
            {
              name: 'designationName',
              jsonPath: 'MdmsRes.swm.Shift[0].designation.code',
              label: 'swm.Shift.create.designation',
              pattern: '',
              type: 'autoCompelete',
              isRequired: true,
              isDisabled: false,
              defaultValue: '',
              maxLength: 128,
              minLength: 1,
              patternErrorMsg: '',
              url: '/hr-masters/designations/_search?tenantId=default|$..id|$..name',
            },
            {
              name: 'code',
              jsonPath: 'MdmsRes.swm.Shift[0].code',
              defaultValue: 'Shift-' + new Date().getTime(),
              isRequired : true,
              type: 'text',
              hide: true,
            },
            /*{
              name: 'tenantId',
              jsonPath: 'MdmsRes.swm.Shift[0].tenantId',
              type: 'text',             
              defaultValue: localStorage.getItem("tenantId"),
              hide: true
            },
            {
              name: 'moduleName',
              jsonPath: 'MasterMetaData.moduleName',
              type: 'text',             
              defaultValue: 'swm',
              hide: true
            },
            {
              name: 'masterName',
              jsonPath: 'MasterMetaData.masterName',              
              type: 'text',
              defaultValue: 'Shift',
              hide: true
            },*/
          ]
        },
        {
          name:'SiftDetails',
          label: 'swm.shift.create.group.title.SiftDetails',
          fields: [
            {
              name: 'shiftStartTime',
              jsonPath: 'MdmsRes.swm.ShiftType[0].shiftStartTime',
              label: 'swm.Shift.create.shiftStartTime',
              pattern: '',
              type: 'timePicker',
              isDisabled: false,
              defaultValue: '',
              maxLength: 12,
              minLength: 6,
              patternErrorMsg: '',
              url: '',
            },
            {
              name: 'shiftEndTime',
              jsonPath: 'MdmsRes.swm.Shift[0].shiftEndTime',
              label: 'swm.Shift.create.shiftEndTime',
              pattern: '',
              type: 'timePicker',
              isDisabled: false,
              defaultValue: '',
              patternErrorMsg: '',
              url: '',
            },
            {
              name: 'lunchStartTime',
              jsonPath: 'MdmsRes.swm.Shift[0].lunchStartTime',
              label: 'swm.Shift.create.lunchStartTime',
              pattern: '',
              type: 'timePicker',
              isDisabled: false,
              defaultValue: '',
              maxLength: 12,
              minLength: 6,
              patternErrorMsg: '',
              url: '',
            },
            {
              name: 'lunchEndTime',
              jsonPath: 'MdmsRes.swm.Shift[0].lunchEndTime',
              label: 'swm.Shift.create.lunchEndTime',
              pattern: '',
              type: 'timePicker',
              isDisabled: false,
              defaultValue: '',
              patternErrorMsg: '',
              url: '',
            },
            {
              name: 'graceTimeFrom',
              jsonPath: 'MdmsRes.swm.Shift[0].graceTimeFrom',
              label: 'swm.Shift.create.graceTimeFrom',
              pattern: '',
              type: 'timePicker',
              isDisabled: false,
              defaultValue: '',
              maxLength: 12,
              minLength: 6,
              patternErrorMsg: '',
              url: '',
            },
            {
              name: 'graceTimeTo',
              jsonPath: 'MdmsRes.swm.Shift[0].graceTimeTo',
              label: 'swm.Shift.create.graceTimeTo',
              pattern: '',
              type: 'timePicker',
              isDisabled: false,
              defaultValue: '',
              patternErrorMsg: '',
              url: '',
            },
            {
              name: 'remarks',
              jsonPath: 'MdmsRes.swm.Shift[0].remarks',
              label: 'swm.Shift.create.remarks',
              pattern: '',
              type: 'textarea',
              isDisabled: false,
              defaultValue: '',
              patternErrorMsg: '',
              url: '',
            },
          ],
        },
      ],
      tenantIdRequired: true,
      url: '/egov-mdms-service/v1/_search?code={code}',
    },
    'swm.update': {
      numCols: 3,
      useTimestamp: true,
      objectName: 'MasterMetaData',
      searchUrl: '/egov-mdms-update/v1/_update',
      idJsonPath: 'MdmsRes.swm.Shift[*].code',
      groups: [
        {
          name:'ShiftSelection',
          label: 'swm.shift.create.group.title.ShiftSelection',
          fields: [
            {
              name: 'shiftType',
              jsonPath: 'MasterMetaData.masterData[0].shiftType.code',
              label: 'swm.Shift.create.shiftType',
              pattern: '',
              type: 'singleValueList',
              isRequired: true,
              isDisabled: false,
              defaultValue: '',
              maxLength: 12,
              minLength: 6,
              url: '/egov-mdms-service/v1/_get?&moduleName=swm&masterName=ShiftType|$..ShiftType.*.code|$..ShiftType.*.name',
            },
            {
              name: 'departmentName',
              jsonPath: 'MasterMetaData.masterData[0].department.code',
              label: 'swm.Shift.create.department',
              pattern: '',
              type: 'autoCompelete',
              isRequired: true,
              isDisabled: false,
              defaultValue: '',
              maxLength: 128,
              minLength: 1,
              patternErrorMsg: '',
              url: "/egov-mdms-service/v1/_get?&moduleName=common-masters&masterName=Department|$..code|$..name",
              hasIdConverion: true,
            },
            {
              name: 'designationName',
              jsonPath: 'MasterMetaData.masterData[0].designation.code',
              label: 'swm.Shift.create.designation',
              pattern: '',
              type: 'autoCompelete',
              isRequired: true,
              isDisabled: false,
              defaultValue: '',
              maxLength: 128,
              minLength: 1,
              patternErrorMsg: '',
              url: '/hr-masters/designations/_search?tenantId=default|$..id|$..name',
            },
            {
              name: 'code',
              jsonPath: 'MasterMetaData.masterData[0].code',
              defaultValue: 'Shift-' + new Date().getTime(),
              isRequired : true,
              type: 'text',
              hide: true,
            },
            {
              name: 'tenantId',
              jsonPath: 'MasterMetaData.masterData[0].tenantId',
              type: 'text',             
              defaultValue: localStorage.getItem("tenantId"),
              hide: true
            },
            {
              name: 'moduleName',
              jsonPath: 'MasterMetaData.moduleName',
              type: 'text',             
              defaultValue: 'swm',
              hide: true
            },
            {
              name: 'masterName',
              jsonPath: 'MasterMetaData.masterName',              
              type: 'text',
              defaultValue: 'Shift',
              hide: true
            },
          ]
        },
        {
          name:'SiftDetails',
          label: 'swm.shift.create.group.title.SiftDetails',
          fields: [
            {
              name: 'shiftStartTime',
              jsonPath: 'MasterMetaData.masterData[0].shiftStartTime',
              label: 'swm.Shift.create.shiftStartTime',
              pattern: '',
              type: 'timePicker',
              isDisabled: false,
              defaultValue: '',
              maxLength: 12,
              minLength: 6,
              patternErrorMsg: '',
              url: '',
            },
            {
              name: 'shiftEndTime',
              jsonPath: 'MasterMetaData.masterData[0].shiftEndTime',
              label: 'swm.Shift.create.shiftEndTime',
              pattern: '',
              type: 'timePicker',
              isDisabled: false,
              defaultValue: '',
              patternErrorMsg: '',
              url: '',
            },
            {
              name: 'lunchStartTime',
              jsonPath: 'MasterMetaData.masterData[0].lunchStartTime',
              label: 'swm.Shift.create.lunchStartTime',
              pattern: '',
              type: 'timePicker',
              isDisabled: false,
              defaultValue: '',
              maxLength: 12,
              minLength: 6,
              patternErrorMsg: '',
              url: '',
            },
            {
              name: 'lunchEndTime',
              jsonPath: 'MasterMetaData.masterData[0].lunchEndTime',
              label: 'swm.Shift.create.lunchEndTime',
              pattern: '',
              type: 'timePicker',
              isDisabled: false,
              defaultValue: '',
              patternErrorMsg: '',
              url: '',
            },
            {
              name: 'graceTimeFrom',
              jsonPath: 'MasterMetaData.masterData[0].graceTimeFrom',
              label: 'swm.Shift.create.graceTimeFrom',
              pattern: '',
              type: 'timePicker',
              isDisabled: false,
              defaultValue: '',
              maxLength: 12,
              minLength: 6,
              patternErrorMsg: '',
              url: '',
            },
            {
              name: 'graceTimeTo',
              jsonPath: 'MasterMetaData.masterData[0].graceTimeTo',
              label: 'swm.Shift.create.graceTimeTo',
              pattern: '',
              type: 'timePicker',
              isDisabled: false,
              defaultValue: '',
              patternErrorMsg: '',
              url: '',
            },
            {
              name: 'remarks',
              jsonPath: 'MasterMetaData.masterData[0].remarks',
              label: 'swm.Shift.create.remarks',
              pattern: '',
              type: 'textarea',
              isDisabled: false,
              defaultValue: '',
              patternErrorMsg: '',
              url: '',
            },
          ],
        },
      ],
      url: '/egov-mdms-update/shiftmasters/_update',
      tenantIdRequired: true,
      searchUrl: '/egov-mdms-service/v1/_search?code={code}',
    },
  };
  export default dat;