package com.backend.model.EnvironmentClearance;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.backend.audit.Auditable;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "ec_air_pollution_mitigation", schema = "master")
public class EcAirPollutionMitigation extends Auditable<Integer>{

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer Id;

	@ManyToOne
	@JoinColumn(name = "ec_partB_id")
	@JsonIgnore
	private EcPartB ecPartB;
	
	@Column(name = "pollution_source", nullable = true, length = 50)
	private String pollution_source;

	@Column(name = "pollution_source_other", nullable = true, length = 50)
	private String pollution_source_other;
	
	@Column(name = "pollutant", nullable = true, length = 50)
	private String pollutant;
	
	@Column(name = "mitigation_measures", nullable = true, length = 100)
	private String mitigation_measures;
	
	@Column(name="is_active")
	private boolean is_active;
	
	@Column(name="is_deleted")
	private boolean is_deleted; 
	
	public EcAirPollutionMitigation() {
		this.is_active = true;
		this.is_deleted = false;
	}
	
	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public EcPartB getEcPartB() {
		return ecPartB;
	}

	public void setEcPartB(EcPartB ecPartB) {
		this.ecPartB = ecPartB;
	}

	public String getPollution_source() {
		return pollution_source;
	}

	public void setPollution_source(String pollution_source) {
		this.pollution_source = pollution_source;
	}

	public String getPollutant() {
		return pollutant;
	}

	public void setPollutant(String pollutant) {
		this.pollutant = pollutant;
	}

	public String getMitigation_measures() {
		return mitigation_measures;
	}

	public void setMitigation_measures(String mitigation_measures) {
		this.mitigation_measures = mitigation_measures;
	}

	public boolean isIs_deleted() {
		return is_deleted;
	}

	public void setIs_deleted(boolean is_deleted) {
		this.is_deleted = is_deleted;
	}
	
	
	
	
}
