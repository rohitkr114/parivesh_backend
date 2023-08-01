package com.backend.model.EcPartC;

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
@Table(name = "ec_chemical_properties", schema = "master")
public class EcChemicalProperties extends Auditable<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "chemical_property_ids", nullable = true)
	@JsonIgnore
	private EcPartC ecPartC;

	@Column(name = "cp_location_lat", nullable = true, length = 53)
	private String cp_location_lat;

	@Column(name = "cp_location_long", nullable = true, length = 53)
	private String cp_location_long;

	@Column(name = "cp_location_core", nullable = true, length = 255)
	private String cp_location_core;

	@Column(name = "cp_criteria_parameter", nullable = true)
	private String cp_criteria_parameter;
	
	@Column(name = "cp_observed_value", nullable = true, length = 255)
	private String cp_observed_value;
	
	@Column(name = "cp_observed_value_to", nullable = true, length = 255)
	private String cp_observed_value_to;
	
	@Column(name = "cp_unit", nullable = true, length = 50)
	private String cp_unit;
	
	@Column(name = "cp_unit_other", nullable = true, length = 50)
	private String cp_unit_other;
	
	@Column(name = "cp_permissible_standard", nullable = true, length = 300)
	private String cp_permissible_standard;
	
	@Column(name = "is_active")
	private Boolean is_active;

	@Column(name = "is_deleted")
	private Boolean is_deleted;
	
	public EcChemicalProperties() {
		this.is_active = true;
		this.is_deleted = false;
	}

	public EcChemicalProperties(Integer id, String cp_location_lat, String cp_location_long, String cp_location_core,
			String cp_criteria_parameter, String cp_observed_value,String cp_observed_value_to, String cp_unit, String cp_unit_other,
			String cp_permissible_standard) {
		this.id = id;
		this.cp_location_lat = cp_location_lat;
		this.cp_location_long = cp_location_long;
		this.cp_location_core = cp_location_core;
		this.cp_criteria_parameter = cp_criteria_parameter;
		this.cp_observed_value = cp_observed_value;
		this.cp_observed_value_to = cp_observed_value_to;
		this.cp_unit = cp_unit;
		this.cp_unit_other = cp_unit_other;
		this.cp_permissible_standard = cp_permissible_standard;
	}
	
	
}
