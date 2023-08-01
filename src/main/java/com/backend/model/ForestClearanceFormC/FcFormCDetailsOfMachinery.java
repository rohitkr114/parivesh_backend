package com.backend.model.ForestClearanceFormC;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.backend.audit.Auditable;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name="fc_form_c_details_of_machinery")
public class FcFormCDetailsOfMachinery extends Auditable<Integer>{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(nullable=true) 
	private String name_equipment;
	
	@Column(nullable=true) 
	private String mode_of_traction;
	
	@Column(nullable=true) 
	private Double size;
	
	@Column(nullable=true) 
	private Double estimated_deployment;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fc_form_c_id", nullable = false)
	@JsonIgnore
	private FcFormC fcFormC;

	private Double maximum_noise;
	
	@Column(nullable = true)
	private Boolean is_active;
	
	@Column(nullable = true)
	private Boolean is_delete;
	
	public FcFormCDetailsOfMachinery() {
		this.is_active=true;
		this.is_delete=false;
	}
}
