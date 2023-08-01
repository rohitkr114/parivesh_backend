package com.backend.model.EnvironmentClearance;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.backend.audit.Auditable;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "mining_mineral_reserves", schema = "master")
public class MiningMineralReserves extends Auditable<Integer> {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name = "mineral_name", nullable = true, length= 100) 
	private String mineral_name;
	
	@Column(name = "proved_reserves", nullable = true, length= 50) 
	private Double proved_reserves;
	
	@Column(name = "indicated_reserves", nullable = true, length= 50) 
	private Double indicated_reserves;
	
	@Column(name = "inferred_reserves", nullable = true, length= 50) 
	private Double inferred_reserves;
	
	@Column(name = "mineable_reserves", nullable = true, length= 50) 
	private Double mineable_reserves;
	
	@Column(name = "remarks", nullable = true, length= 1000) 
	private String remarks;
	
	@Column(name="is_active")
	private boolean is_active;
	
	@Column(name="is_deleted")
	private boolean is_deleted; 
	
	public MiningMineralReserves() {
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

	public Double getProved_reserves() {
		return proved_reserves;
	}

	public void setProved_reserves(Double proved_reserves) {
		this.proved_reserves = proved_reserves;
	}

	public Double getIndicated_reserves() {
		return indicated_reserves;
	}

	public void setIndicated_reserves(Double indicated_reserves) {
		this.indicated_reserves = indicated_reserves;
	}

	public Double getInferred_reserves() {
		return inferred_reserves;
	}

	public void setInferred_reserves(Double inferred_reserves) {
		this.inferred_reserves = inferred_reserves;
	}

	public Double getMineable_reserves() {
		return mineable_reserves;
	}

	public void setMineable_reserves(Double mineable_reserves) {
		this.mineable_reserves = mineable_reserves;
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
