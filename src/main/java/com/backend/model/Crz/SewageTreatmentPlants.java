package com.backend.model.Crz;

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
@Table(name = "sewage_treatment_plants", schema = "master")
public class SewageTreatmentPlants extends Auditable<Integer> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name="capacity",nullable=true)
	private Double capacity;
	
	@Column(name="total_area_of_construction",nullable=true)
	private Double total_area_of_construction;
	
	@Column(name="compliance_of_effluent_parameters",nullable=true,length=50)
	private String compliance_of_effluent_parameters;
	
	@Column(name="whether_discharge_is_in_sea_creek",nullable=true)
	private Boolean whether_discharge_is_in_sea_creek;
	
	@Column(name="distance_of_marine_outfall_point",nullable=true)
	private Double distance_of_marine_outfall_point;
	
	@Column(name="depth_of_outfall_point_sea_water",nullable=true)
	private Double depth_of_outfall_point_sea_water;
	
	@Column(name="depth_of_seabed",nullable=true)
	private Double depth_of_seabed;
	
	@Column(name="is_active", nullable=true)
	private Boolean is_active;
	
	@Column(name="is_deleted", nullable=true)
	private Boolean is_deleted;
	
	public SewageTreatmentPlants() {
		this.is_active=true;
		this.is_deleted=false;
	}
}
