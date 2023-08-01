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
@Table(name = "ec_ambient_air_quality", schema = "master")
public class EcAmbientAirQuality extends Auditable<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ambient_air_quality_ids", nullable = true)
	@JsonIgnore
	private EcPartC ecPartC;
	
	@Column(name = "aaq_location_lat", nullable = true, length = 53)
	private String aaq_location_lat;
	
	@Column(name = "aaq_location_long", nullable = true, length = 53)
	private String aaq_location_long;
	
	@Column(name = "aaq_location_core", nullable = true, length = 255)
	private String aaq_location_core;
	
	@Column(name = "aaq_criterial_pollutant", nullable = true, length = 50)
	private String aaq_criterial_pollutant;
	
	@Column(name = "aaq_criterial_pollutant_other", nullable = true, length = 50)
	private String aaq_criterial_pollutant_other;
	
	@Column(name = "aaq_unit", nullable = true, length = 50)
	private String aaq_unit;
	
	@Column(name = "aaq_unit_other", nullable = true, length = 50)
	private String aaq_unit_other;
	
	@Column(name = "aaq_max_value", nullable = true, length = 255)
	private String aaq_max_value;
	
	@Column(name = "aaq_min_value", nullable = true, length = 255)
	private String aaq_min_value;
	
	@Column(name = "aaq_percentile_value", nullable = true, length = 255)
	private String aaq_percentile_value;
	
	@Column(name = "aaq_percentile_value_to", nullable = true, length = 255)
	private String aaq_percentile_value_to;
	
	@Column(name = "aaq_prescribed_standard", nullable = true, length = 300)
	private String aaq_prescribed_standard;
	
	@Column(name = "is_active")
	private Boolean is_active;

	@Column(name = "is_deleted")
	private Boolean is_deleted;
	
	public EcAmbientAirQuality() {
		this.is_active = true;
		this.is_deleted = false;
	}

	public EcAmbientAirQuality(Integer id, String aaq_location_lat, String aaq_location_long, String aaq_location_core,
			String aaq_criterial_pollutant, String aaq_criterial_pollutant_other, String aaq_unit,
			String aaq_unit_other, String aaq_max_value, String aaq_min_value, String aaq_percentile_value,
			String aaq_percentile_value_to, String aaq_prescribed_standard) {
		this.id = id;
		this.aaq_location_lat = aaq_location_lat;
		this.aaq_location_long = aaq_location_long;
		this.aaq_location_core = aaq_location_core;
		this.aaq_criterial_pollutant = aaq_criterial_pollutant;
		this.aaq_criterial_pollutant_other = aaq_criterial_pollutant_other;
		this.aaq_unit = aaq_unit;
		this.aaq_unit_other = aaq_unit_other;
		this.aaq_max_value = aaq_max_value;
		this.aaq_min_value = aaq_min_value;
		this.aaq_percentile_value = aaq_percentile_value;
		this.aaq_percentile_value_to = aaq_percentile_value_to;
		this.aaq_prescribed_standard = aaq_prescribed_standard;
	}
	
	
}
