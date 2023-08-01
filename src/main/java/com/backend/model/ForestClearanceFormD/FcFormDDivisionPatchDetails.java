package com.backend.model.ForestClearanceFormD;

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
@Table(name="fc_form_d_division_patch_details",schema="master")
public class FcFormDDivisionPatchDetails extends Auditable<Integer>{

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private Integer Id;
	
	@Column(length = 30,nullable = true)
	private String patch_id;
	
	@Column(nullable = true)
	private Double area;
	
	@Column(nullable = true)
	private Double nfl_area;
	
	@Column(length = 1000,nullable = true)
	private String remarks;
	
	private Boolean is_active;
	
	private Boolean is_deleted;
	
	public FcFormDDivisionPatchDetails() {
		this.is_active=true;
		this.is_deleted=false;
	}
	
}
