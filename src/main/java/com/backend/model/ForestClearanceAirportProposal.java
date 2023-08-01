package com.backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.backend.audit.Auditable;
import com.backend.model.EnvironmentClearance.EcPartB;
import com.backend.model.WildLifeClearance.WildLifeClearance;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "fc_airport_proposal", schema = "master")
public class ForestClearanceAirportProposal extends Auditable<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer Id;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fc_id", nullable = true)
	@JsonIgnore
	private ForestClearance forestClearance;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "wlc_id", nullable = true)
	@JsonIgnore
	private WildLifeClearance wildLifeClearance;

	@Column(length = 1000)
	private String project_components;

	@Column(length = 50)
	private String passenger_capacity;

	@Column(length = 1000)
	private String aircraft_compatibility;

	@Column
	private Double total_built_up_area;

	@Column(length = 1000)
	private String any_other_details;

	@Column(length = 1000)
	private String nearest_airport_details;

	@Column(length = 1000)
	private String near_by_habitation;

	@Column(length = 1000)
	private String connectivity_to_proposed_project;

	@Column(length = 1000)
	private String parking_provision;

	private boolean is_deleted;

	private boolean is_active;

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public ForestClearance getForestClearance() {
		return forestClearance;
	}

	public void setForestClearance(ForestClearance forestClearance) {
		this.forestClearance = forestClearance;
	}

	public String getProject_components() {
		return project_components;
	}

	public void setProject_components(String project_components) {
		this.project_components = project_components;
	}

	public String getPassenger_capacity() {
		return passenger_capacity;
	}

	public void setPassenger_capacity(String passenger_capacity) {
		this.passenger_capacity = passenger_capacity;
	}

	public String getAircraft_compatibility() {
		return aircraft_compatibility;
	}

	public void setAircraft_compatibility(String aircraft_compatibility) {
		this.aircraft_compatibility = aircraft_compatibility;
	}

	public Double getTotal_built_up_area() {
		return total_built_up_area;
	}

	public void setTotal_built_up_area(Double total_built_up_area) {
		this.total_built_up_area = total_built_up_area;
	}

	public String getAny_other_details() {
		return any_other_details;
	}

	public void setAny_other_details(String any_other_details) {
		this.any_other_details = any_other_details;
	}

	public String getNearest_airport_details() {
		return nearest_airport_details;
	}

	public void setNearest_airport_details(String nearest_airport_details) {
		this.nearest_airport_details = nearest_airport_details;
	}

	public String getNear_by_habitation() {
		return near_by_habitation;
	}

	public void setNear_by_habitation(String near_by_habitation) {
		this.near_by_habitation = near_by_habitation;
	}

	public String getConnectivity_to_proposed_project() {
		return connectivity_to_proposed_project;
	}

	public void setConnectivity_to_proposed_project(String connectivity_to_proposed_project) {
		this.connectivity_to_proposed_project = connectivity_to_proposed_project;
	}

	public String getParking_provision() {
		return parking_provision;
	}

	public void setParking_provision(String parking_provision) {
		this.parking_provision = parking_provision;
	}

	public boolean isIs_deleted() {
		return is_deleted;
	}

	public void setIs_deleted(boolean is_deleted) {
		this.is_deleted = is_deleted;
	}

	public boolean isIs_active() {
		return is_active;
	}

	public void setIs_active(boolean is_active) {
		this.is_active = is_active;
	}

	public WildLifeClearance getWildLifeClearance() {
		return wildLifeClearance;
	}

	public void setWildLifeClearance(WildLifeClearance wildLifeClearance) {
		this.wildLifeClearance = wildLifeClearance;
	}

}
