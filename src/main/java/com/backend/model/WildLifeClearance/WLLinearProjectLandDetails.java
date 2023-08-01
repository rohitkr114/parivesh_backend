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
@Table(name = "wl_linear_project_land_details", schema = "master")
public class WLLinearProjectLandDetails extends Auditable<Integer> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer Id;

	@Column(nullable = true)
	private String component_linear;
	
	@Column(nullable = true)
	private String name_protected_area_linear;
	
	@Column(nullable = true)
	private Double length_under_protected_area;
	
	@Column(nullable = true)
	private Double width_under_protected_area;
	
	@Column(nullable = true)
	private Double total_area_under_protected_area;
	
	@Column(nullable = true)
	private Double length_outside_protected_area;
	
	@Column(nullable = true)
	private Double width_outside_protected_area;
	
	@Column(nullable = true)
	private Double total_area_outside_protected_area;
	
	@Column(nullable = true)
	private Double total_length;
	
	@Column(nullable = true)
	private Double total_width;
	
	@Column(nullable = true)
	private Double total_area;
	
	@Column(nullable = true)
	private Boolean is_active;
	
	@Column(nullable = true)
	private Boolean is_delete;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "wl_clearance_id", nullable = true)
	@JsonIgnore
	private WildLifeClearance wlclearance;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "wl_proposed_land_Id", nullable = true)
	@JsonIgnore
	private WLProposedLand wlProposedLand;

}
