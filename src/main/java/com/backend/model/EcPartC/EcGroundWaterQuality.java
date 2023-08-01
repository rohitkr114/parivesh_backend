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
@Table(name = "ec_ground_water_quality", schema = "master")
public class EcGroundWaterQuality extends Auditable<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ground_water_quality_ids", nullable = true)
	@JsonIgnore
	private EcPartC ecPartC;

	@Column(name = "gwq_location_lat", nullable = true, length = 53)
	private String gwq_location_lat;

	@Column(name = "gwq_location_long", nullable = true, length = 53)
	private String gwq_location_long;

	@Column(name = "gwq_location_core", nullable = true, length = 255)
	private String gwq_location_core;

	@Column(name = "gwq_criterial_pollutant", nullable = true, length = 50)
	private String gwq_criterial_pollutant;

	@Column(name = "gwq_criterial_pollutant_other", nullable = true, length = 50)
	private String gwq_criterial_pollutant_other;

	@Column(name = "gwq_unit", nullable = true, length = 50)
	private String gwq_unit;

	@Column(name = "gwq_unit_other", nullable = true, length = 50)
	private String gwq_unit_other;

	@Column(name = "gwq_observed_value", nullable = true)
	private String gwq_observed_value;
	
	@Column(name = "gwq_observed_value_to", nullable = true)
	private String gwq_observed_value_to;

	@Column(name = "gwq_desired_limits", nullable = true)
	private Double gwq_desired_limits;

	@Column(name = "gwq_permissible_limits", nullable = true, length = 300)
	private String gwq_permissible_limits;
	
	@Column(name = "is_active")
	private Boolean is_active;

	@Column(name = "is_deleted")
	private Boolean is_deleted;
	
	public EcGroundWaterQuality() {
		this.is_active = true;
		this.is_deleted = false;
	}

	public EcGroundWaterQuality(Integer id, String gwq_location_lat, String gwq_location_long, String gwq_location_core,
			String gwq_criterial_pollutant, String gwq_criterial_pollutant_other, String gwq_unit,
			String gwq_unit_other, String gwq_observed_value,String gwq_observed_value_to, Double gwq_desired_limits,
			String gwq_permissible_limits) {
		this.id = id;
		this.gwq_location_lat = gwq_location_lat;
		this.gwq_location_long = gwq_location_long;
		this.gwq_location_core = gwq_location_core;
		this.gwq_criterial_pollutant = gwq_criterial_pollutant;
		this.gwq_criterial_pollutant_other = gwq_criterial_pollutant_other;
		this.gwq_unit = gwq_unit;
		this.gwq_unit_other = gwq_unit_other;
		this.gwq_observed_value = gwq_observed_value;
		this.gwq_observed_value_to = gwq_observed_value_to;
		this.gwq_desired_limits = gwq_desired_limits;
		this.gwq_permissible_limits = gwq_permissible_limits;
	}
	
	
}
