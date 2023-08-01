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
@Table(name="fc_form_c_proposed_diversion_Details",schema="master")
public class FcFormCProposedDiversionDetails extends Auditable<Integer>{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(nullable=true)
	private String unit;
	
	@Column(nullable = true, length = 50)
	private String toposheet_no;
	
	@Column(nullable = false, length = 50)
	private String district;

	@Column(nullable = false, length = 50) 
	private String village;

	@Column(nullable = true)
	private String range;

	@Column(nullable = true)
	private String forest_area_length;

	@Column(nullable = true)
	private String forest_area_width;

	@Column(nullable = true)
	private String forest_area_total;

	@Column(nullable = true)
	private String non_forest_area_length;

	@Column(nullable = true)
	private String non_forest_area_width;

	@Column(nullable = true)
	private String non_forest_area_total;
	
	@Column(nullable = true)
	private String non_linear_forest_area;
	
	@Column(nullable = true)
	private String non_linear_non_forest_area;
	
	/*
	@Column(nullable = true)
	private Double total_forest_land_for_division;
	
	@Column(nullable = true)
	private Double total_non_forest_land_for_division;*/
	
	@Column(name = "manual_entry", nullable = true)
	private Boolean manual_entry;
	
	private Boolean is_active;
	
	@Column(nullable = false)
	private boolean is_deleted;
	
	public FcFormCProposedDiversionDetails() {
		this.is_active=true;
		this.is_deleted=false;
	}
	
	public FcFormCProposedDiversionDetails(Integer id) {
		this.id=id;
	}

}
