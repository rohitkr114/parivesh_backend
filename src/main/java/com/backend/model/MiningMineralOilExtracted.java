package com.backend.model;

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
@Table(name = "mining_mineral_oil_extracted", schema = "master")
public class MiningMineralOilExtracted extends Auditable<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(nullable = true,length=100)
	private String mineral_oil_extracted;

	@Column(nullable = true,length=10)
	private String mineral_oil_classification;

	@Column(nullable = true)
	private Double mineral_oil_mtpa;

	@Column(nullable = true)
	private Double mineral_oil_mmscmd;
	
	@Column(nullable = true)
	private Boolean is_active;
	
	@Column(nullable = true)
	private Boolean is_deleted;
	
	public MiningMineralOilExtracted() {
		this.is_active=true;
		this.is_deleted=false;
	}
	
}
