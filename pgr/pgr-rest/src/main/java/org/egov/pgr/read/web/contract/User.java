package org.egov.pgr.read.web.contract;

import java.util.Set;

import org.egov.pgr.common.model.Role;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

	@JsonProperty("id")
	private Long id;

	@JsonProperty("userName")
	private String userName;

	@JsonProperty("name")
	private String name;

	@JsonProperty("mobileNumber")
	private String mobileNumber;

	@JsonProperty("emailId")
	private String emailId;

	@JsonProperty("type")
	private String type;

    @JsonProperty("permanentAddress")
    private String permanentAddress;
    
    @JsonProperty("tenantId")
    private String tenantId;

    @JsonProperty("roles")
	private Set<Role> roles = null;
}
