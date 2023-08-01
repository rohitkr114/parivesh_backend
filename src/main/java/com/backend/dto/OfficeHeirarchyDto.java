package com.backend.dto;

import lombok.Data;

@Data
public class OfficeHeirarchyDto {

	private Integer office_id;

	private String office_name;

	private String office_type;

	private Integer parent_office;
}
