package org.egov.pgr.web.contract;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class Error {
    private String code;
    private String message;
    private String description;
    private List<ErrorField> fields;
}
