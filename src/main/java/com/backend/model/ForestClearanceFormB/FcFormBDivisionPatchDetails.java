package com.backend.model.ForestClearanceFormB;

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
@Table(name="fc_form_b_division_patch_details",schema="master")
public class FcFormBDivisionPatchDetails  extends Auditable<Integer>{
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private Integer Id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fc_form_b_Id", nullable = true)
	@JsonIgnore
	private FcFormBProjectDetails fcFormBProjectDetails; 
	
	@Column(length = 30)
	private String patch_id;
	
	private Double area;
	
	@Column(nullable = true)
	private Double nfl_area;
	
	@Column(length = 1000)
	private String remarks;
	
	private Boolean is_active;
	
	private boolean is_deleted;
	
	FcFormBDivisionPatchDetails(){
		this.is_active=true;
		this.is_deleted=false;
	}
}
