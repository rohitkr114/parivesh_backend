package com.backend.model.ForestClearanceFormB;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.backend.audit.Auditable;

import lombok.Data;

@Data
@Entity
@Table(name = "fc_form_b_mining_mineral_mined", schema = "master")
public class FcFormBMiningMineralsMined extends Auditable<Integer>{
	
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
	
	public FcFormBMiningMineralsMined() {
		this.is_active = true;
		this.is_deleted = false;
	}
}
