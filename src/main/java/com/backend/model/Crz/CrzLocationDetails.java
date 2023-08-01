package com.backend.model.Crz;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.backend.audit.Auditable;

import lombok.Data;

@Data
@Entity
@Table(name="crz_location_details",schema = "master")
public class CrzLocationDetails extends Auditable<Integer>{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name = "project_located_in", nullable = true)
	private String project_located_in;

	private Boolean is_active;
	
	private Boolean is_deleted;
	
	public CrzLocationDetails() {
		this.is_active=true;
		this.is_deleted=false;
	}
}
