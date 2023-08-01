package com.backend.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.backend.audit.Auditable;

import lombok.Data;

@Data
@Entity
@Table(name="activity_subactivity_sector",schema = "master")
public class ActivitySubActivitySector{
	
	@Id
	private Integer subactivity_id;
	
	private Integer activity_id;
	
	private String sector_code;
	

}
