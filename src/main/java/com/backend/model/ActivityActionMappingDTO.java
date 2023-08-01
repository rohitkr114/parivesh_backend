package com.backend.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class ActivityActionMappingDTO {

	@Id
	private Integer id;
	
	private Integer activity_id;
	
	private String name;
	
	private Integer sector_id;
	
	private String item_no;
}
