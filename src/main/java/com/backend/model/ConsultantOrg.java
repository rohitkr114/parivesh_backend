package com.backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ConsultantOrg {

	@JsonProperty("OrgId")
	private String OrgId;
	
}
