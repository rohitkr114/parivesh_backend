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

//@Data
@Entity
@Table(name = "wl_component_wise_details", schema = "master")
public class WLComponentWiseDetails extends Auditable<Integer>{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer Id;

	@Column(length = 500,nullable = true)
	private String component;
	// Added on 01-03-2022 
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
	private WildLifeClearance wlclearance;
	
	//added on 26112022
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "wl_proposed_land_Id", nullable = true)
	@JsonIgnore
	private WLProposedLand wlProposedLand;

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getComponent() {
		return component;
	}

	public void setComponent(String component) {
		this.component = component;
	}

	public String getLinear_type() {
		return linear_type;
	}

	public void setLinear_type(String linear_type) {
		this.linear_type = linear_type;
	}

	public String getName_protected_area() {
		return name_protected_area;
	}

	public void setName_protected_area(String name_protected_area) {
		this.name_protected_area = name_protected_area;
	}

	public Double getForest_land_under_protected_area() {
		return forest_land_under_protected_area;
	}

	public void setForest_land_under_protected_area(Double forest_land_under_protected_area) {
		this.forest_land_under_protected_area = forest_land_under_protected_area;
	}

	public Double getNon_forest_land_under_protected_area() {
		return non_forest_land_under_protected_area;
	}

	public void setNon_forest_land_under_protected_area(Double non_forest_land_under_protected_area) {
		this.non_forest_land_under_protected_area = non_forest_land_under_protected_area;
	}

	public Double getTotal_land_under_protected_area() {
		return total_land_under_protected_area;
	}

	public void setTotal_land_under_protected_area(Double total_land_under_protected_area) {
		this.total_land_under_protected_area = total_land_under_protected_area;
	}

	public Double getForest_land_outside_protected_area() {
		return forest_land_outside_protected_area;
	}

	public void setForest_land_outside_protected_area(Double forest_land_outside_protected_area) {
		this.forest_land_outside_protected_area = forest_land_outside_protected_area;
	}

	public Double getNon_forest_land_outside_protected_area() {
		return non_forest_land_outside_protected_area;
	}

	public void setNon_forest_land_outside_protected_area(Double non_forest_land_outside_protected_area) {
		this.non_forest_land_outside_protected_area = non_forest_land_outside_protected_area;
	}

	public Double getTotal_land_outside_protected_area() {
		return total_land_outside_protected_area;
	}

	public void setTotal_land_outside_protected_area(Double total_land_outside_protected_area) {
		this.total_land_outside_protected_area = total_land_outside_protected_area;
	}

	public Boolean getIs_active() {
		return is_active;
	}

	public void setIs_active(Boolean is_active) {
		this.is_active = is_active;
	}

	public Boolean getIs_delete() {
		return is_delete;
	}

	public void setIs_delete(Boolean is_delete) {
		this.is_delete = is_delete;
	}

	public WildLifeClearance getWlclearance() {
		return wlclearance;
	}

	public void setWlclearance(WildLifeClearance wlclearance) {
		this.wlclearance = wlclearance;
	}

	public WLProposedLand getWlProposedLand() {
		return wlProposedLand;
	}

	public void setWlProposedLand(WLProposedLand wlProposedLand) {
		this.wlProposedLand = wlProposedLand;
	}

	public WLComponentWiseDetails() {
		this.is_active = true;
        this.is_delete = false;
	}

	//end 26112022
	
	
	
}
