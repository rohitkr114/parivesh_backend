package com.backend.model.EnvironmentClearance;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.backend.audit.Auditable;

import lombok.Data;

@Data
@Entity
@Table(name = "mining_mineral_mined", schema = "master")
public class MiningMineralsMined extends Auditable<Integer> {

	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name = "mineral_name", nullable = true, length= 100) 
	private String mineral_name;
	
	@Column(name = "mineral_classification", nullable = true) 
	private String mineral_classification;
	
	@Column(name = "production_capacity", nullable = true, length= 50) 
	private Double production_capacity;
	
	@Column(name = "remarks", nullable = true, length= 1000) 
	private String remarks;
	
	@Column(name="is_active")
	private boolean is_active;
	
	@Column(name="is_deleted")
	private boolean is_deleted; 
	
	public MiningMineralsMined() {
		this.is_active = true;
		this.is_deleted = false;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMineral_name() {
		return mineral_name;
	}

	public void setMineral_name(String mineral_name) {
		this.mineral_name = mineral_name;
	}

	public String getMineral_classification() {
		return mineral_classification;
	}

	public void setMineral_classification(String mineral_classification) {
		this.mineral_classification = mineral_classification;
	}

	public Double getProduction_capacity() {
		return production_capacity;
	}

	public void setProduction_capacity(Double production_capacity) {
		this.production_capacity = production_capacity;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public boolean isIs_active() {
		return is_active;
	}

	public void setIs_active(boolean is_active) {
		this.is_active = is_active;
	}

	public boolean isIs_deleted() {
		return is_deleted;
	}

	public void setIs_deleted(boolean is_deleted) {
		this.is_deleted = is_deleted;
	}
	
	
	
	
	
	
}
