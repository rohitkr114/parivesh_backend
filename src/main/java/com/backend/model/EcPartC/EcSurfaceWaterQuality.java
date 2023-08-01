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
@Table(name = "ec_surface_water_quality", schema = "master")
public class EcSurfaceWaterQuality extends Auditable<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "surface_water_quality_ids", nullable = true)
	@JsonIgnore
	private EcPartC ecPartC;

	@Column(name = "swq_location_lat", nullable = true, length = 53)
	private String swq_location_lat;

	@Column(name = "swq_location_long", nullable = true, length = 53)
	private String swq_location_long;

	@Column(name = "swq_location_core", nullable = true, length = 255)
	private String swq_location_core;

	@Column(name = "swq_criterial_pollutant", nullable = true, length = 50)
	private String swq_criterial_pollutant;

	@Column(name = "swq_criterial_pollutant_other", nullable = true, length = 50)
	private String swq_criterial_pollutant_other;

	@Column(name = "swq_unit", nullable = true, length = 50)
	private String swq_unit;

	@Column(name = "swq_unit_other", nullable = true, length = 50)
	private String swq_unit_other;

	@Column(name = "swq_observed_value", nullable = true, length = 255)
	private String swq_observed_value;
	
	@Column(name = "swq_observed_value_to", nullable = true, length = 255)
	private String swq_observed_value_to;

	@Column(name = "swq_is_2296", nullable = true, length = 300)
	private String swq_is_2296;

	@Column(name = "swq_cpcb_criteria_class", nullable = true,length = 300)
	private String swq_cpcb_criteria_class;

	@Column(name = "swq_cpcb_criteria_standard", nullable = true)
	private String swq_cpcb_criteria_standard;
	
	@Column(name = "is_active")
	private Boolean is_active;

	@Column(name = "is_deleted")
	private Boolean is_deleted;
	
	public EcSurfaceWaterQuality() {
		this.is_active = true;
		this.is_deleted = false;
	}

	public EcSurfaceWaterQuality(Integer id, String swq_location_lat, String swq_location_long,
			String swq_location_core, String swq_criterial_pollutant, String swq_criterial_pollutant_other,
			String swq_unit, String swq_unit_other, String swq_observed_value,String swq_observed_value_to, String swq_is_2296,
			String swq_cpcb_criteria_class, String swq_cpcb_criteria_standard) {
		this.id = id;
		this.swq_location_lat = swq_location_lat;
		this.swq_location_long = swq_location_long;
		this.swq_location_core = swq_location_core;
		this.swq_criterial_pollutant = swq_criterial_pollutant;
		this.swq_criterial_pollutant_other = swq_criterial_pollutant_other;
		this.swq_unit = swq_unit;
		this.swq_unit_other = swq_unit_other;
		this.swq_observed_value = swq_observed_value;
		this.swq_observed_value_to = swq_observed_value_to;
		this.swq_is_2296 = swq_is_2296;
		this.swq_cpcb_criteria_class = swq_cpcb_criteria_class;
		this.swq_cpcb_criteria_standard = swq_cpcb_criteria_standard;
	}
	
	
}
