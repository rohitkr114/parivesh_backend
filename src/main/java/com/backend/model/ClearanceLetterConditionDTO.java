package com.backend.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class ClearanceLetterConditionDTO {
	
	@Id
	private Integer id;

}
