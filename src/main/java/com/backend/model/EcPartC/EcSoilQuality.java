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
@Table(name = "ec_soil_quality", schema = "master")
public class EcSoilQuality extends Auditable<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "soil_quality_ids", nullable = true)
	@JsonIgnore
	private EcPartC ecPartC;

	@Column(name = "sq_location_lat", nullable = true, length = 53)
	private String sq_location_lat;

	@Column(name = "sq_location_long", nullable = true, length = 53)
	private String sq_location_long;

	@Column(name = "sq_location_core", nullable = true, length = 255)
	private String sq_location_core;

	@Column(name = "sq_soil_texture", nullable = true, length = 20)
	private String sq_soil_texture;

	@Column(name = "sq_particle_size_sand", nullable = true, length = 53)
	private String sq_particle_size_sand;

	@Column(name = "sq_particle_size_silt", nullable = true, length = 53)
	private String sq_particle_size_silt;

	@Column(name = "sq_particle_size_clay", nullable = true, length = 53)
	private String sq_particle_size_clay;

	@Column(name = "sq_water_holding_capacity", nullable = true, length = 53)
	private String sq_water_holding_capacity;

	@Column(name = "sq_porosity", nullable = true, length = 53)
	private String sq_porosity;

	@Column(name = "sq_particle_size_sand_to", nullable = true, length = 53)
	private String sq_particle_size_sand_to;

	@Column(name = "sq_particle_size_silt_to", nullable = true, length = 53)
	private String sq_particle_size_silt_to;

	@Column(name = "sq_particle_size_clay_to", nullable = true, length = 53)
	private String sq_particle_size_clay_to;

	@Column(name = "sq_water_holding_capacity_to", nullable = true, length = 53)
	private String sq_water_holding_capacity_to;

	@Column(name = "sq_porosity_to", nullable = true, length = 53)
	private String sq_porosity_to;

	@Column(name = "is_active")
	private Boolean is_active;

	@Column(name = "is_deleted")
	private Boolean is_deleted;

	public EcSoilQuality() {
		this.is_active = true;
		this.is_deleted = false;
	}

	public EcSoilQuality(Integer id, String sq_location_lat, String sq_location_long, String sq_location_core,
			String sq_soil_texture, String sq_particle_size_sand, String sq_particle_size_silt,
			String sq_particle_size_clay, String sq_water_holding_capacity, String sq_porosity,
			String sq_particle_size_sand_to, String sq_particle_size_silt_to, String sq_particle_size_clay_to,
			String sq_water_holding_capacity_to, String sq_porosity_to) {
		this.id = id;
		this.sq_location_lat = sq_location_lat;
		this.sq_location_long = sq_location_long;
		this.sq_location_core = sq_location_core;
		this.sq_soil_texture = sq_soil_texture;
		this.sq_particle_size_sand = sq_particle_size_sand;
		this.sq_particle_size_silt = sq_particle_size_silt;
		this.sq_particle_size_clay = sq_particle_size_clay;
		this.sq_water_holding_capacity = sq_water_holding_capacity;
		this.sq_porosity = sq_porosity;
		this.sq_particle_size_sand_to = sq_particle_size_sand_to;
		this.sq_particle_size_silt_to = sq_particle_size_silt_to;
		this.sq_particle_size_clay_to = sq_particle_size_clay_to;
		this.sq_water_holding_capacity_to = sq_water_holding_capacity_to;
		this.sq_porosity_to = sq_porosity_to;
	}

}
