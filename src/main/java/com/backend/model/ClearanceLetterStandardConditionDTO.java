package com.backend.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class ClearanceLetterStandardConditionDTO {

	@Id
	private Integer id;
	
	private Integer proposal_no ;
	
	private String condition_id;
	
	private String condition_type;
	
	private String condition_description;
}
