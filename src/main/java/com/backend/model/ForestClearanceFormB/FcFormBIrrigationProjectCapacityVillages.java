package com.backend.model.ForestClearanceFormB;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.backend.audit.Auditable;
import com.backend.model.WildLifeClearance.WildLifeClearance;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "fc_form_b_irrigation_project_capacity_villages", schema = "master")
public class FcFormBIrrigationProjectCapacityVillages extends Auditable<Integer>{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer Id;
	
	@Column(nullable = true)
	private Integer state;
	
	@Column(nullable = true)
	private Integer district;
	
	@Column(nullable = true)
	private Integer village;
	
	@Column(nullable = true)
	private Double area_benefited;
	
	private Boolean is_active;

	private Boolean is_deleted;

	public FcFormBIrrigationProjectCapacityVillages() {
		this.is_active=true;
		this.is_deleted=false;
	}
	
}
