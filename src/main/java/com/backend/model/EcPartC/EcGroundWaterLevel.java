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
@Table(name = "ec_ground_water_level", schema = "master")
public class EcGroundWaterLevel extends Auditable<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ground_water_level_ids", nullable = true)
	@JsonIgnore
	private EcPartC ecPartC;

	@Column(name = "gwl_location_lat", nullable = true, length = 53)
	private String gwl_location_lat;

	@Column(name = "gwl_location_long", nullable = true, length = 53)
	private String gwl_location_long;

	@Column(name = "gwl_location_core", nullable = true, length = 255)
	private String gwl_location_core;

	@Column(name = "gwl_pre_monsoon_from", nullable = true, length = 255)
	private String gwl_pre_monsoon_from;
	
	@Column(name = "gwl_pre_monsoon_to", nullable = true, length = 255)
	private String gwl_pre_monsoon_to;
	
	@Column(name = "gwl_post_monsoon_from", nullable = true, length = 255)
	private String gwl_post_monsoon_from;
	
	@Column(name = "gwl_post_monsoon_to", nullable = true, length = 255)
	private String gwl_post_monsoon_to;
	
	@Column(name = "is_active")
	private Boolean is_active;

	@Column(name = "is_deleted")
	private Boolean is_deleted;
	
	public EcGroundWaterLevel() {
		this.is_active = true;
		this.is_deleted = false;
	}

	public EcGroundWaterLevel(Integer id, String gwl_location_lat, String gwl_location_long, String gwl_location_core,
			String gwl_pre_monsoon_from, String gwl_pre_monsoon_to, String gwl_post_monsoon_from,
			String gwl_post_monsoon_to) {
		this.id = id;
		this.gwl_location_lat = gwl_location_lat;
		this.gwl_location_long = gwl_location_long;
		this.gwl_location_core = gwl_location_core;
		this.gwl_pre_monsoon_from = gwl_pre_monsoon_from;
		this.gwl_pre_monsoon_to = gwl_pre_monsoon_to;
		this.gwl_post_monsoon_from = gwl_post_monsoon_from;
		this.gwl_post_monsoon_to = gwl_post_monsoon_to;
	}
	
	
}
