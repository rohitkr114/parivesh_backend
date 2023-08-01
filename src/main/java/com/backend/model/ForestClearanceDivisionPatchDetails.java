package com.backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Id;

import com.backend.audit.Auditable;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name="forest_clearance_division_patch_details",schema ="master")
public class ForestClearanceDivisionPatchDetails extends Auditable<Integer>{
	
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private Integer Id;
	
	@Column(length = 30)
	private String patch_id;
	
	private Double area;
	
	@Column(nullable = true)
	private Double nfl_area;
	
	@Column(length = 1000)
	private String remarks;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fc_proposed_diversion_id", nullable = true)
	@JsonIgnore
	private ForestClearanceProposedDiversions forestClearanceProposedDiversions;
	
	private Boolean is_active;
	
	private boolean is_deleted;

	ForestClearanceDivisionPatchDetails(){
		this.is_active=true;
		this.is_deleted=false;
	}
	
}
