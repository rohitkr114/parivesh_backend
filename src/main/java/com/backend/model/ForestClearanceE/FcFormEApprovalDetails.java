package com.backend.model.ForestClearanceE;

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
@Table(name="fc_form_e_approval_details",schema = "master")
public class FcFormEApprovalDetails extends Auditable<Integer>{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(nullable = true)
	private String area_approved_component;
	
	@Column(nullable = true)
	private Double area_approved_non_forest_land;
	
	@Column(nullable = true)
	private Double area_approved_forest_land;
	
	@Column(nullable = true)
	private String area_proposed_component;
	
	@Column(nullable = true)
	private Double area_proposed_non_forest_land;
	
	@Column(nullable = true)
	private Double area_proposed_forest_land;
	
	private Boolean is_active;
	
	private Boolean is_delete;
	
	public FcFormEApprovalDetails() {
		this.is_active=true;
		this.is_delete=false;
	}

	public FcFormEApprovalDetails(Integer id) {
		this.id = id;
	}
	
}
