package  org.egov.egf.master.web.requests;
import java.util.ArrayList;
import java.util.List;

import org.egov.common.contract.request.RequestInfo;
import org.egov.egf.master.web.contract.BankBranchContract;

import lombok.Data;
public @Data class BankBranchRequest {
private RequestInfo requestInfo = new RequestInfo();
private List<BankBranchContract> bankBranches =new ArrayList<BankBranchContract>() ;
}