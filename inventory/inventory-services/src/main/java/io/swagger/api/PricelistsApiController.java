package io.swagger.api;

import io.swagger.annotations.ApiParam;
import io.swagger.model.Pagination;
import io.swagger.model.PriceList;
import io.swagger.model.PriceListRequest;
import io.swagger.model.PriceListResponse;
import io.swagger.model.PriceListSearchRequest;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.egov.inv.domain.service.InventoryUtilityService;
import org.egov.inv.domain.service.PriceListService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.ObjectMapper;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-11-08T06:34:49.872Z")

@Controller
public class PricelistsApiController implements PriceListsApi {

    private final ObjectMapper objectMapper;

    private PriceListService priceListService;

    private InventoryUtilityService inventoryUtilityService;

    public PricelistsApiController(ObjectMapper objectMapper, PriceListService priceListService, InventoryUtilityService inventoryUtilityService) {
		this.objectMapper = objectMapper;
		this.priceListService = priceListService;
		this.inventoryUtilityService = inventoryUtilityService;
    }

    public ResponseEntity<PriceListResponse> pricelistsCreatePost( @NotNull@ApiParam(value = "Unique id for a tenant.", required = true) @RequestParam(value = "tenantId", required = true) String tenantId,
        @ApiParam(value = "Create new pricelist"  )  @Valid @RequestBody PriceListRequest priceListRequest,
        @RequestHeader(value = "Accept", required = false) String accept) throws Exception {

    	List<PriceList> priceLists = priceListService.save(priceListRequest, tenantId);

//        if (accept != null && accept.contains("application/json")) {
//            return new ResponseEntity<PriceListResponse>(objectMapper.readValue("{  \"priceLists\" : [ {    \"code\" : \"code\",    \"maxQuantity\" : 7.061401241503109105224211816675961017608642578125,    \"description\" : \"description\",    \"reorderQuantity\" : 3.61607674925191080461672754609026014804840087890625,    \"reorderLevel\" : 9.301444243932575517419536481611430644989013671875,    \"techincalSpecs\" : \"techincalSpecs\",    \"termsOfDelivery\" : \"termsOfDelivery\",    \"minQuantity\" : 2.3021358869347654518833223846741020679473876953125,    \"auditDetails\" : {      \"lastModifiedTime\" : 1,      \"createdBy\" : \"createdBy\",      \"lastModifiedBy\" : \"lastModifiedBy\",      \"createdTime\" : 6    },    \"priceListControlType\" : \"LOTControl\",    \"manufacturePartNo\" : \"manufacturePartNo\",    \"model\" : \"model\",    \"id\" : \"id\",    \"staockingUom\" : {      \"code\" : \"code\",      \"auditDetails\" : {        \"lastModifiedTime\" : 1,        \"createdBy\" : \"createdBy\",        \"lastModifiedBy\" : \"lastModifiedBy\",        \"createdTime\" : 6      },      \"tenantId\" : \"tenantId\",      \"name\" : \"name\",      \"id\" : \"id\"    },    \"inventoryType\" : \"Asset\",    \"priceListClass\" : \"HighUsage\",    \"priceListType\" : {      \"parent\" : 5,      \"code\" : \"code\",      \"auditDetails\" : {        \"lastModifiedTime\" : 1,        \"createdBy\" : \"createdBy\",        \"lastModifiedBy\" : \"lastModifiedBy\",        \"createdTime\" : 6      },      \"tenantId\" : \"tenantId\",      \"name\" : \"name\",      \"id\" : \"id\"    },    \"purchaseUom\" : {      \"code\" : \"code\",      \"auditDetails\" : {        \"lastModifiedTime\" : 1,        \"createdBy\" : \"createdBy\",        \"lastModifiedBy\" : \"lastModifiedBy\",        \"createdTime\" : 6      },      \"tenantId\" : \"tenantId\",      \"name\" : \"name\",      \"id\" : \"id\"    },    \"baseUom\" : {      \"code\" : \"code\",      \"auditDetails\" : {        \"lastModifiedTime\" : 1,        \"createdBy\" : \"createdBy\",        \"lastModifiedBy\" : \"lastModifiedBy\",        \"createdTime\" : 6      },      \"tenantId\" : \"tenantId\",      \"name\" : \"name\",      \"id\" : \"id\"    },    \"oldCode\" : \"oldCode\",    \"tenantId\" : \"tenantId\",    \"name\" : \"name\",    \"expenseAccount\" : {      \"glCode\" : \"glCode\",      \"auditDetails\" : {        \"lastModifiedTime\" : 1,        \"createdBy\" : \"createdBy\",        \"lastModifiedBy\" : \"lastModifiedBy\",        \"createdTime\" : 6      },      \"tenantId\" : \"tenantId\",      \"name\" : \"name\",      \"id\" : \"id\"    },    \"overridePriceListControlType\" : true,    \"status\" : \"Active\"  }, {    \"code\" : \"code\",    \"maxQuantity\" : 7.061401241503109105224211816675961017608642578125,    \"description\" : \"description\",    \"reorderQuantity\" : 3.61607674925191080461672754609026014804840087890625,    \"reorderLevel\" : 9.301444243932575517419536481611430644989013671875,    \"techincalSpecs\" : \"techincalSpecs\",    \"termsOfDelivery\" : \"termsOfDelivery\",    \"minQuantity\" : 2.3021358869347654518833223846741020679473876953125,    \"auditDetails\" : {      \"lastModifiedTime\" : 1,      \"createdBy\" : \"createdBy\",      \"lastModifiedBy\" : \"lastModifiedBy\",      \"createdTime\" : 6    },    \"priceListControlType\" : \"LOTControl\",    \"manufacturePartNo\" : \"manufacturePartNo\",    \"model\" : \"model\",    \"id\" : \"id\",    \"staockingUom\" : {      \"code\" : \"code\",      \"auditDetails\" : {        \"lastModifiedTime\" : 1,        \"createdBy\" : \"createdBy\",        \"lastModifiedBy\" : \"lastModifiedBy\",        \"createdTime\" : 6      },      \"tenantId\" : \"tenantId\",      \"name\" : \"name\",      \"id\" : \"id\"    },    \"inventoryType\" : \"Asset\",    \"priceListClass\" : \"HighUsage\",    \"priceListType\" : {      \"parent\" : 5,      \"code\" : \"code\",      \"auditDetails\" : {        \"lastModifiedTime\" : 1,        \"createdBy\" : \"createdBy\",        \"lastModifiedBy\" : \"lastModifiedBy\",        \"createdTime\" : 6      },      \"tenantId\" : \"tenantId\",      \"name\" : \"name\",      \"id\" : \"id\"    },    \"purchaseUom\" : {      \"code\" : \"code\",      \"auditDetails\" : {        \"lastModifiedTime\" : 1,        \"createdBy\" : \"createdBy\",        \"lastModifiedBy\" : \"lastModifiedBy\",        \"createdTime\" : 6      },      \"tenantId\" : \"tenantId\",      \"name\" : \"name\",      \"id\" : \"id\"    },    \"baseUom\" : {      \"code\" : \"code\",      \"auditDetails\" : {        \"lastModifiedTime\" : 1,        \"createdBy\" : \"createdBy\",        \"lastModifiedBy\" : \"lastModifiedBy\",        \"createdTime\" : 6      },      \"tenantId\" : \"tenantId\",      \"name\" : \"name\",      \"id\" : \"id\"    },    \"oldCode\" : \"oldCode\",    \"tenantId\" : \"tenantId\",    \"name\" : \"name\",    \"expenseAccount\" : {      \"glCode\" : \"glCode\",      \"auditDetails\" : {        \"lastModifiedTime\" : 1,        \"createdBy\" : \"createdBy\",        \"lastModifiedBy\" : \"lastModifiedBy\",        \"createdTime\" : 6      },      \"tenantId\" : \"tenantId\",      \"name\" : \"name\",      \"id\" : \"id\"    },    \"overridePriceListControlType\" : true,    \"status\" : \"Active\"  } ],  \"page\" : {    \"totalResults\" : 1,    \"offSet\" : 1,    \"totalPages\" : 1,    \"pageSize\" : 6,    \"currentPage\" : 7  },  \"responseInfo\" : {    \"ver\" : \"ver\",    \"resMsgId\" : \"resMsgId\",    \"msgId\" : \"msgId\",    \"apiId\" : \"apiId\",    \"ts\" : 0,    \"status\" : \"SUCCESSFUL\"  }}", PriceListResponse.class), HttpStatus.OK);
//        }

        PriceListResponse priceListResponse = buildPriceListResponse(priceLists, priceListRequest.getRequestInfo());
    	
    	return new ResponseEntity<PriceListResponse>(HttpStatus.OK);
    }

    public ResponseEntity<PriceListResponse> pricelistsSearchPost( @NotNull@ApiParam(value = "Unique id for a tenant.", required = true) @RequestParam(value = "tenantId", required = true) String tenantId,
        @ApiParam(value = "Parameter to carry Request metadata in the request body"  )  @Valid @RequestBody org.egov.common.contract.request.RequestInfo requestInfo,
        @ApiParam(value = "Name of the vendor supplying priceLists required ") @RequestParam(value = "supplierName", required = false) List<String> supplierName,
        @ApiParam(value = "reference no of the priceList contract from the supplier ") @RequestParam(value = "rateContractNumber", required = false) String rateContractNumber,
        @ApiParam(value = "Agreement no with the supplier of priceLists ") @RequestParam(value = "agreementNumber", required = false) List<String> agreementNumber,
        @ApiParam(value = "contract date of the rate for item with the supplier.Date in epoc format. ") @RequestParam(value = "rateContractDate", required = false) Long rateContractDate,
        @ApiParam(value = "Date on which agreement done with supplier ") @RequestParam(value = "agreementDate", required = false) Long agreementDate,
        @ApiParam(value = "Date from which the agreement is valid with supplier ") @RequestParam(value = "agreementStartDate", required = false) Long agreementStartDate,
        @ApiParam(value = "Date on which the agreement expires with the supplier ") @RequestParam(value = "agreementEndDate", required = false) Long agreementEndDate,
        @ApiParam(value = "type of the information about the priceList we are getting from the supplier ", allowableValues = "DGSC_RATECONTRACT, ULB_RATE_CONTRACT, ONE_TIME_TENDER, QUOTATION") @RequestParam(value = "rateType", required = false) String rateType,
         @Min(0) @Max(100)@ApiParam(value = "Number of records returned.", defaultValue = "20") @RequestParam(value = "pageSize", required = false, defaultValue="20") Integer pageSize,
         @ApiParam(value = "offset") @Valid @RequestParam(value = "offset", required = false) Integer offset,
        @ApiParam(value = "Page number", defaultValue = "1") @RequestParam(value = "pageNumber", required = false, defaultValue="1") Integer pageNumber,
        @ApiParam(value = "This takes any field from the Object seperated by comma and asc,desc keywords. example name asc,code desc or name,code or name,code desc", defaultValue = "id") @RequestParam(value = "sortBy", required = false, defaultValue="id") String sortBy,
        @RequestHeader(value = "Accept", required = false) String accept) throws Exception {
    	
//        if (accept != null && accept.contains("application/json")) {
//            return new ResponseEntity<PriceListResponse>(objectMapper.readValue("{  \"priceLists\" : [ {    \"code\" : \"code\",    \"maxQuantity\" : 7.061401241503109105224211816675961017608642578125,    \"description\" : \"description\",    \"reorderQuantity\" : 3.61607674925191080461672754609026014804840087890625,    \"reorderLevel\" : 9.301444243932575517419536481611430644989013671875,    \"techincalSpecs\" : \"techincalSpecs\",    \"termsOfDelivery\" : \"termsOfDelivery\",    \"minQuantity\" : 2.3021358869347654518833223846741020679473876953125,    \"auditDetails\" : {      \"lastModifiedTime\" : 1,      \"createdBy\" : \"createdBy\",      \"lastModifiedBy\" : \"lastModifiedBy\",      \"createdTime\" : 6    },    \"priceListControlType\" : \"LOTControl\",    \"manufacturePartNo\" : \"manufacturePartNo\",    \"model\" : \"model\",    \"id\" : \"id\",    \"staockingUom\" : {      \"code\" : \"code\",      \"auditDetails\" : {        \"lastModifiedTime\" : 1,        \"createdBy\" : \"createdBy\",        \"lastModifiedBy\" : \"lastModifiedBy\",        \"createdTime\" : 6      },      \"tenantId\" : \"tenantId\",      \"name\" : \"name\",      \"id\" : \"id\"    },    \"inventoryType\" : \"Asset\",    \"priceListClass\" : \"HighUsage\",    \"priceListType\" : {      \"parent\" : 5,      \"code\" : \"code\",      \"auditDetails\" : {        \"lastModifiedTime\" : 1,        \"createdBy\" : \"createdBy\",        \"lastModifiedBy\" : \"lastModifiedBy\",        \"createdTime\" : 6      },      \"tenantId\" : \"tenantId\",      \"name\" : \"name\",      \"id\" : \"id\"    },    \"purchaseUom\" : {      \"code\" : \"code\",      \"auditDetails\" : {        \"lastModifiedTime\" : 1,        \"createdBy\" : \"createdBy\",        \"lastModifiedBy\" : \"lastModifiedBy\",        \"createdTime\" : 6      },      \"tenantId\" : \"tenantId\",      \"name\" : \"name\",      \"id\" : \"id\"    },    \"baseUom\" : {      \"code\" : \"code\",      \"auditDetails\" : {        \"lastModifiedTime\" : 1,        \"createdBy\" : \"createdBy\",        \"lastModifiedBy\" : \"lastModifiedBy\",        \"createdTime\" : 6      },      \"tenantId\" : \"tenantId\",      \"name\" : \"name\",      \"id\" : \"id\"    },    \"oldCode\" : \"oldCode\",    \"tenantId\" : \"tenantId\",    \"name\" : \"name\",    \"expenseAccount\" : {      \"glCode\" : \"glCode\",      \"auditDetails\" : {        \"lastModifiedTime\" : 1,        \"createdBy\" : \"createdBy\",        \"lastModifiedBy\" : \"lastModifiedBy\",        \"createdTime\" : 6      },      \"tenantId\" : \"tenantId\",      \"name\" : \"name\",      \"id\" : \"id\"    },    \"overrideMaterialControlType\" : true,    \"status\" : \"Active\"  }, {    \"code\" : \"code\",    \"maxQuantity\" : 7.061401241503109105224211816675961017608642578125,    \"description\" : \"description\",    \"reorderQuantity\" : 3.61607674925191080461672754609026014804840087890625,    \"reorderLevel\" : 9.301444243932575517419536481611430644989013671875,    \"techincalSpecs\" : \"techincalSpecs\",    \"termsOfDelivery\" : \"termsOfDelivery\",    \"minQuantity\" : 2.3021358869347654518833223846741020679473876953125,    \"auditDetails\" : {      \"lastModifiedTime\" : 1,      \"createdBy\" : \"createdBy\",      \"lastModifiedBy\" : \"lastModifiedBy\",      \"createdTime\" : 6    },    \"priceListControlType\" : \"LOTControl\",    \"manufacturePartNo\" : \"manufacturePartNo\",    \"model\" : \"model\",    \"id\" : \"id\",    \"staockingUom\" : {      \"code\" : \"code\",      \"auditDetails\" : {        \"lastModifiedTime\" : 1,        \"createdBy\" : \"createdBy\",        \"lastModifiedBy\" : \"lastModifiedBy\",        \"createdTime\" : 6      },      \"tenantId\" : \"tenantId\",      \"name\" : \"name\",      \"id\" : \"id\"    },    \"inventoryType\" : \"Asset\",    \"priceListClass\" : \"HighUsage\",    \"priceListType\" : {      \"parent\" : 5,      \"code\" : \"code\",      \"auditDetails\" : {        \"lastModifiedTime\" : 1,        \"createdBy\" : \"createdBy\",        \"lastModifiedBy\" : \"lastModifiedBy\",        \"createdTime\" : 6      },      \"tenantId\" : \"tenantId\",      \"name\" : \"name\",      \"id\" : \"id\"    },    \"purchaseUom\" : {      \"code\" : \"code\",      \"auditDetails\" : {        \"lastModifiedTime\" : 1,        \"createdBy\" : \"createdBy\",        \"lastModifiedBy\" : \"lastModifiedBy\",        \"createdTime\" : 6      },      \"tenantId\" : \"tenantId\",      \"name\" : \"name\",      \"id\" : \"id\"    },    \"baseUom\" : {      \"code\" : \"code\",      \"auditDetails\" : {        \"lastModifiedTime\" : 1,        \"createdBy\" : \"createdBy\",        \"lastModifiedBy\" : \"lastModifiedBy\",        \"createdTime\" : 6      },      \"tenantId\" : \"tenantId\",      \"name\" : \"name\",      \"id\" : \"id\"    },    \"oldCode\" : \"oldCode\",    \"tenantId\" : \"tenantId\",    \"name\" : \"name\",    \"expenseAccount\" : {      \"glCode\" : \"glCode\",      \"auditDetails\" : {        \"lastModifiedTime\" : 1,        \"createdBy\" : \"createdBy\",        \"lastModifiedBy\" : \"lastModifiedBy\",        \"createdTime\" : 6      },      \"tenantId\" : \"tenantId\",      \"name\" : \"name\",      \"id\" : \"id\"    },    \"overrideMaterialControlType\" : true,    \"status\" : \"Active\"  } ],  \"page\" : {    \"totalResults\" : 1,    \"offSet\" : 1,    \"totalPages\" : 1,    \"pageSize\" : 6,    \"currentPage\" : 7  },  \"responseInfo\" : {    \"ver\" : \"ver\",    \"resMsgId\" : \"resMsgId\",    \"msgId\" : \"msgId\",    \"apiId\" : \"apiId\",    \"ts\" : 0,    \"status\" : \"SUCCESSFUL\"  }}", PriceListResponse.class), HttpStatus.OK);
//        }


        PriceListSearchRequest priceListSearchRequest = PriceListSearchRequest.builder()
                .tenantId(tenantId)
                .rateContractNumber(rateContractNumber)
                .rateContractDate(rateContractDate)
                .agreementDate(agreementDate)
                .agreementStartDate(agreementStartDate)
                .agreementEndDate(agreementEndDate)
                .rateType(rateType.toUpperCase())
                .pageSize(pageSize)
                .offSet(offset)
                .sortBy(sortBy)
                .build();

        Pagination<PriceList> priceListList = priceListService
                .search(priceListSearchRequest);

        return new ResponseEntity<PriceListResponse>(buildPriceListResponse(priceListList.getPagedData(), requestInfo), HttpStatus.OK);
    }

    public ResponseEntity<PriceListResponse> pricelistsUpdatePost( @NotNull@ApiParam(value = "Unique id for a tenant.", required = true) @RequestParam(value = "tenantId", required = true) String tenantId,
        @ApiParam(value = "common Request info"  )  @Valid @RequestBody PriceListRequest pricelistRequest,
        @RequestHeader(value = "Accept", required = false) String accept) throws Exception {

    	List<PriceList> priceLists = priceListService.update(pricelistRequest, tenantId);
    	
        return new ResponseEntity<PriceListResponse>(buildPriceListResponse(priceLists, pricelistRequest.getRequestInfo()), HttpStatus.OK);
    }

    private PriceListResponse buildPriceListResponse(List<PriceList> priceLists, org.egov.common.contract.request.RequestInfo requestInfo) {
        return PriceListResponse.builder()
                .responseInfo(inventoryUtilityService.getResponseInfo(requestInfo))
                .priceLists(priceLists)
                .build();
    }
    
}