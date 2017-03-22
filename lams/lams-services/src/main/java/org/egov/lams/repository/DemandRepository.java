package org.egov.lams.repository;

import java.util.ArrayList;
import java.util.List;

import org.egov.lams.config.PropertiesManager;
import org.egov.lams.contract.AgreementRequest;
import org.egov.lams.contract.DemandReasonResponse;
import org.egov.lams.contract.DemandRequest;
import org.egov.lams.contract.DemandResponse;
import org.egov.lams.model.Demand;
import org.egov.lams.model.DemandDetails;
import org.egov.lams.model.DemandReason;
import org.egov.lams.model.RequestInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class DemandRepository {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private PropertiesManager propertiesManager;
	
	public List<DemandReason> getDemandReason(AgreementRequest agreementRequest){
		String url = propertiesManager.getDemandServiceHostName()
				+propertiesManager.getDemandReasonSearchService();
		
		DemandReasonResponse demandReasonResponse = restTemplate.postForObject(url, agreementRequest.getRequestInfo(), DemandReasonResponse.class);
		//Todo if api returns exception object
		return demandReasonResponse.getDemandReasons();
	}
	
	public List<Demand> getDemandList(AgreementRequest agreementRequest, List<DemandReason> demandReasons) {
		
		List<Demand> demands = new ArrayList<Demand>();
		List<DemandDetails> demandDetails = new ArrayList<DemandDetails>();
		Demand demand=new Demand();
		demand.setInstallment(demandReasons.get(0).getTaxPeriod());
		demand.setModuleName("Leases And Agreements");
		
		DemandDetails demandDetail = null;
		for(DemandReason demandReason: demandReasons){
			demandDetail = new DemandDetails();
			//rent has to be not null
			demandDetail.setTaxAmount(agreementRequest.getAgreement().getRent().toString());
			demandDetail.setCollectionAmount("0");
			demandDetail.setRebateAmount("0");
			demandDetail.setTaxReason("RENT");
			demandDetail.setTaxPeriod(demandReason.getTaxPeriod());
			
			demandDetails.add(demandDetail);
		}
		demand.setDemandDetails(demandDetails);
		demands.add(demand);
	 
		return demands;
	}
	
	public DemandResponse createDemand(List<Demand> demands, RequestInfo requestInfo){
		
		DemandRequest demandRequest = new DemandRequest();
		demandRequest.setRequestInfo(requestInfo);
		demandRequest.setDemand(demands);
		
		String url = propertiesManager.getDemandServiceHostName()
				+propertiesManager.getCreateDemandSevice();
		
		return restTemplate.postForObject(url, demandRequest, DemandResponse.class);
	}
}
