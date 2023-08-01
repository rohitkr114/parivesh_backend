package com.backend.model.FcFormEPartB;

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
@Table(name= "fc_form_e_part_b_legal_status", schema="master")
public class ForestClearanceELegalStatus extends Auditable<Integer>{
		
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(length = 100)
	private String forest_land_legal_status;
		
	private Double forest_land;

	private Boolean is_active;
	
	private Boolean is_deleted;
	
	public ForestClearanceELegalStatus() {
		this.is_active=true;
		this.is_deleted=false;
	}
	@Column(length = 100,nullable = true)
	private String please_specify_legal;
}