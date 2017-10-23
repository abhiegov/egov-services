package org.egov.lams.common.web.request;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.egov.lams.common.web.contract.EstateRegister;
import org.egov.lams.common.web.contract.RequestInfo;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by ramki on 23/10/17.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EstateRegisterRequest {
    @JsonProperty("RequestInfo")
    private RequestInfo requestInfo = null;

    @JsonProperty("landRegisters")
    @Valid
    private List<EstateRegister> landRegisters = new ArrayList<EstateRegister>();
}
