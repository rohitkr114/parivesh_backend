package com.backend.dto;

import lombok.Data;

@Data
public class HomePageMasterResponse {

	public Integer getMappingId() {
		return mappingId;
	}
	public void setMappingId(Integer mappingId) {
		this.mappingId = mappingId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	private Integer mappingId;
	private String type;
}
