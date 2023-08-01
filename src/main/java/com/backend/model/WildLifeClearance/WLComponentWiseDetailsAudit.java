package com.backend.model.WildLifeClearance;

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
@Table(name = "wl_component_wise_details_audit", schema = "master")
public class WLComponentWiseDetailsAudit extends Auditable<Integer>{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer Id;

	@Column(length = 500,nullable = true)
	private String component;
	
	@Column(length = 500,nullable = true)
	private String linear_type;
	
	@Column(length = 500,nullable = true)
	private String name_protected_area;

	@Column(nullable = true)
	private Double forest_land_under_protected_area;
	
	@Column(nullable = true)
	private Double non_forest_land_under_protected_area;
	
	@Column(nullable = true)
	private Double total_land_under_protected_area;
	
	@Column(nullable = true)
	private Double forest_land_outside_protected_area;
	
	@Column(nullable = true)
	private Double non_forest_land_outside_protected_area;
	
	@Column(nullable = true)
	private Double total_land_outside_protected_area;
	
	@Column(nullable = true)
	private Boolean is_active;
	
	@Column(nullable = true)
	private Boolean is_delete;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "wl_clearance_id", nullable = true)
	@JsonIgnore
	private WildLifeClearanceAudit wildLifeClearanceaudit;
	
	//added on 26112022
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "wl_proposed_land_Id", nullable = true)
	@JsonIgnore
	private WLProposedLandAudit wlProposedLandAudit;

	//end 26112022

}
