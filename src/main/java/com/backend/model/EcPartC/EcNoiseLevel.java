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
@Table(name = "ec_noise_level", schema = "master")
public class EcNoiseLevel extends Auditable<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "noise_level_ids", nullable = true)
	@JsonIgnore
	private EcPartC ecPartC;

	@Column(name = "nl_location_lat", nullable = true, length = 53)
	private String nl_location_lat;

	@Column(name = "nl_location_long", nullable = true, length = 53)
	private String nl_location_long;

	@Column(name = "nl_location_core", nullable = true, length = 255)
	private String nl_location_core;

	@Column(name = "nl_category", nullable = true, length = 20)
	private String nl_category;
	
	@Column(name = "nl_observed_noise_day_time", nullable = true, length = 255)
	private String nl_observed_noise_day_time;
	
	@Column(name = "nl_observed_noise_day_time_to", nullable = true, length = 255)
	private String nl_observed_noise_day_time_to;
	
	@Column(name = "nl_observed_noise_night_time", nullable = true, length = 255)
	private String nl_observed_noise_night_time;
	
	@Column(name = "nl_observed_noise_night_time_to", nullable = true, length = 255)
	private String nl_observed_noise_night_time_to;
	
	@Column(name = "nl_prescribed_standard_day_time", nullable = true, length = 53)
	private String nl_prescribed_standard_day_time;
	
	@Column(name = "nl_prescribed_standard_night_time", nullable = true, length = 53)
	private String nl_prescribed_standard_night_time;
	
	@Column(name = "is_active")
	private Boolean is_active;

	@Column(name = "is_deleted")
	private Boolean is_deleted;
	
	public EcNoiseLevel() {
		this.is_active = true;
		this.is_deleted = false;
	}

	public EcNoiseLevel(Integer id, String nl_location_lat, String nl_location_long, String nl_location_core,
			String nl_category, String nl_observed_noise_day_time, String nl_observed_noise_night_time,
			String nl_observed_noise_day_time_to, String nl_observed_noise_night_time_to, String nl_prescribed_standard_day_time, String nl_prescribed_standard_night_time) {
		this.id = id;
		this.nl_location_lat = nl_location_lat;
		this.nl_location_long = nl_location_long;
		this.nl_location_core = nl_location_core;
		this.nl_category = nl_category;
		this.nl_observed_noise_day_time = nl_observed_noise_day_time;
		this.nl_observed_noise_night_time = nl_observed_noise_night_time;
		this.nl_observed_noise_day_time_to = nl_observed_noise_day_time_to;
		this.nl_observed_noise_night_time_to = nl_observed_noise_night_time_to;
		this.nl_prescribed_standard_day_time = nl_prescribed_standard_day_time;
		this.nl_prescribed_standard_night_time = nl_prescribed_standard_night_time;
	}
	
	
}
