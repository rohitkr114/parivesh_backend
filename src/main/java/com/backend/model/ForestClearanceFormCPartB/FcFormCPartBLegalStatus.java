package com.backend.model.ForestClearanceFormCPartB;

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
@Table(name= "fc_form_c_part_b_legal_status", schema="master")
public class FcFormCPartBLegalStatus extends Auditable<Integer>{
		
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(length = 100,nullable = true)
	private String forest_land_legal_status;
		
	@Column(nullable = true)
	private Double forest_land;

	private Boolean is_active;
	
	private Boolean is_deleted;
	
	@Column(length = 100,nullable = true)
	private String please_specify_legal;
	

	public FcFormCPartBLegalStatus() {
		this.is_active=true;
		this.is_deleted=false;
	}
}