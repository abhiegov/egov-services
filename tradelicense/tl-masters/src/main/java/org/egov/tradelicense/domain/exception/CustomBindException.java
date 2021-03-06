package org.egov.tradelicense.domain.exception;

import org.egov.tl.commons.web.contract.RequestInfo;
import org.springframework.validation.BindingResult;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomBindException extends RuntimeException {

	private static final long serialVersionUID = 8861914629969408745L;

	private BindingResult errors;

	private RequestInfo requestInfo;
}
