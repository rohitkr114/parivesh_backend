package com.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProjectListDto {
	
	
	@JsonProperty("id")
	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ProjectListDto(Integer id) {
		super();
		this.id = id;
	}

	public ProjectListDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
