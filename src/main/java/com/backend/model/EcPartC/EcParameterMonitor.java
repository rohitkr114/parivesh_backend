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
@Table(name = "ec_parameter_monitor", schema = "master")
public class EcParameterMonitor extends Auditable<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parameter_monitor_ids", nullable = true)
	@JsonIgnore
	private EcPartC ecPartC;
	
	@Column(name = "attribute", nullable = true, length = 30)
	private String attribute;
	
	@Column(name = "attribute_other", nullable = true, length = 50)
	private String attribute_other;
	
	@Column(name = "proposed_parameter", nullable = true, length = 100)
	private String proposed_parameter;
	
	@Column(name = "location_lat", nullable = true, length = 53)
	private String location_lat;
	
	@Column(name = "location_long", nullable = true, length = 53)
	private String location_long;
	
	@Column(name = "monitoring_mode", nullable = true, length = 20)
	private String monitoring_mode;
	
	@Column(name = "monitoring_frequency", nullable = true, length = 50)
	private String monitoring_frequency;

	@Column(name = "monitoring_frequency_other", nullable = true, length = 50)
	private String monitoring_frequency_other;
	
	@Column(name = "project_phase", nullable = true, length = 20)
	private String project_phase;
	
	@Column(name = "monitoring_agency", nullable = true, length = 50)
	private String monitoring_agency;

	@Column(name = "monitoring_agency_other", nullable = true, length = 50)
	private String monitoring_agency_other;
	
	@Column(name = "is_active")
	private Boolean is_active;

	@Column(name = "is_deleted")
	private Boolean is_deleted;
	
	public EcParameterMonitor() {
		this.is_active = true;
		this.is_deleted = false;
	}

	public EcParameterMonitor(Integer id, String attribute, String attribute_other, String proposed_parameter,
			String location_lat, String location_long, String monitoring_mode, String monitoring_frequency,
			String monitoring_frequency_other, String project_phase, String monitoring_agency,
			String monitoring_agency_other) {
		this.id = id;
		this.attribute = attribute;
		this.attribute_other = attribute_other;
		this.proposed_parameter = proposed_parameter;
		this.location_lat = location_lat;
		this.location_long = location_long;
		this.monitoring_mode = monitoring_mode;
		this.monitoring_frequency = monitoring_frequency;
		this.monitoring_frequency_other = monitoring_frequency_other;
		this.project_phase = project_phase;
		this.monitoring_agency = monitoring_agency;
		this.monitoring_agency_other = monitoring_agency_other;
	}
	
	
}
