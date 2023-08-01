package com.backend.model.EcPartC;

import java.util.Date;

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
@Table(name = "ec_baseline_collection", schema = "master")
public class EcBaseLineCollections extends Auditable<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "baseline_collection_ids", nullable = true)
	@JsonIgnore
	private EcPartC ecPartC;
	
	@Column(name = "season", nullable = true)
	private String season;
	
	@Column(name = "period_from", nullable = true)
	private Date period_from;
	
	@Column(name = "period_to", nullable = true)
	private Date period_to;
		
	@Column(name = "meteorology", nullable = true, length = 53)
	private String meteorology;
	
	@Column(name = "ambient_air", nullable = true, length = 53)
	private String ambient_air;
	
	@Column(name = "water_surface", nullable = true, length = 53)
	private String water_surface;
	
	@Column(name = "ground_water", nullable = true, length = 53)
	private String ground_water;
	
	@Column(name = "ground_water_level", nullable = true, length = 53)
	private String ground_water_level;
	
	@Column(name = "noise_level", nullable = true, length = 53)
	private String noise_level;
	
	@Column(name = "soil_level", nullable = true, length = 53)
	private String soil_quality;
	
	@Column(name = "is_active")
	private Boolean is_active;

	@Column(name = "is_deleted")
	private Boolean is_deleted;
	
	public EcBaseLineCollections() {
		this.is_active = true;
		this.is_deleted = false;
	}

	public EcBaseLineCollections(Integer id, String season, Date period_from, Date period_to, String meteorology,
			String ambient_air, String water_surface, String ground_water, String ground_water_level,
			String noise_level, String soil_quality) {
		this.id = id;
		this.season = season;
		this.period_from = period_from;
		this.period_to = period_to;
		this.meteorology = meteorology;
		this.ambient_air = ambient_air;
		this.water_surface = water_surface;
		this.ground_water = ground_water;
		this.ground_water_level = ground_water_level;
		this.noise_level = noise_level;
		this.soil_quality = soil_quality;
	}
	
	
}
