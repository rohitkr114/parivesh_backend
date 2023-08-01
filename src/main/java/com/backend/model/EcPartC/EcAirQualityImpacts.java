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
@Table(name = "ec_air_quality_impacts", schema = "master")
public class EcAirQualityImpacts extends Auditable<Integer>{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "air_quality_impact_ids", nullable = true)
	@JsonIgnore
	private EcPartC ecPartC;
	
	@Column(name = "aqi_location_lat", nullable = true, length = 53)
	private String aqi_location_lat;

	@Column(name = "aqi_location_long", nullable = true, length = 53)
	private String aqi_location_long;

	@Column(name = "aqi_location_core", nullable = true, length = 255)
	private String aqi_location_core;

	@Column(name = "aqi_criterial_pollutant", nullable = true, length = 50)
	private String aqi_criterial_pollutant;

	@Column(name = "aqi_criterial_pollutant_other", nullable = true, length = 50)
	private String aqi_criterial_pollutant_other;
	
	@Column(name = "aqi_unit", nullable = true, length = 50)
	private String aqi_unit;
	
	@Column(name = "aqi_baseline_concentration", nullable = true)
	private String aqi_baseline_concentration;
	
	@Column(name = "aqi_predicted_incr_value", nullable = true)
	private Double aqi_predicted_incr_value;
	
	@Column(name = "aqi_total_glc", nullable = true)
	private Double aqi_total_glc;
	
	@Column(name = "aqi_prescribed_standard", nullable = true, length = 300)
	private String aqi_prescribed_standard;

	@Column(name = "is_active")
	private Boolean is_active;

	@Column(name = "is_deleted")
	private Boolean is_deleted;
	
	public EcAirQualityImpacts() {
		this.is_active = true;
		this.is_deleted = false;
	}

	public EcAirQualityImpacts(Integer id, String aqi_location_lat, String aqi_location_long, String aqi_location_core,
			String aqi_criterial_pollutant, String aqi_criterial_pollutant_other, String aqi_unit,
			String aqi_baseline_concentration, Double aqi_predicted_incr_value, Double aqi_total_glc,
			String aqi_prescribed_standard) {
		this.id = id;
		this.aqi_location_lat = aqi_location_lat;
		this.aqi_location_long = aqi_location_long;
		this.aqi_location_core = aqi_location_core;
		this.aqi_criterial_pollutant = aqi_criterial_pollutant;
		this.aqi_criterial_pollutant_other = aqi_criterial_pollutant_other;
		this.aqi_unit = aqi_unit;
		this.aqi_baseline_concentration = aqi_baseline_concentration;
		this.aqi_predicted_incr_value = aqi_predicted_incr_value;
		this.aqi_total_glc = aqi_total_glc;
		this.aqi_prescribed_standard = aqi_prescribed_standard;
	}
	
	
}
