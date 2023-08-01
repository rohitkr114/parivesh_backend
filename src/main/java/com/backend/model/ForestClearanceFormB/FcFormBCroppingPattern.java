package com.backend.model.ForestClearanceFormB;

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
@Table(name = "fc_form_b_cropping_pattern", schema = "master")
public class FcFormBCroppingPattern extends Auditable<Integer>{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer Id;
	
	@Column(nullable = true)
	private String crop;
	
	@Column(nullable = true)
	private Double existing_area;
	
	@Column(nullable = true)
	private Double existing_productivity;
	
	@Column(nullable = true)
	private Double proposed_area;
	
	@Column(nullable = true)
	private Double proposed_productivity;
	
	@Column(nullable = true)
	private Double total_production;
	
	private Boolean is_active;

	private Boolean is_deleted;

	public FcFormBCroppingPattern() {
		this.is_active=true;
		this.is_deleted=false;
	}
	
}
