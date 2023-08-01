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
@Table(name = "mining_mineral_oil_production_detail", schema = "master")
public class MiningMineralOilProductionDetail extends Auditable<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(nullable = true)
	private String production_detail_fin_year;

	@Column(nullable = true)
	private Double production_details_sactioned_ec;

	@Column(nullable = true)
	private Double production_details_sactioned_cto;

	@Column(nullable = true)
	private Double production_details_sactioned_licence;

	@Column(nullable = true)
	private Double actual_production;

	@Column(nullable = true)
	private Double excess_production;

	@Column(nullable = true)
	private Boolean is_active;

	@Column(nullable = true)
	private Boolean is_deleted;

	public MiningMineralOilProductionDetail() {
		this.is_active = true;
		this.is_deleted = false;
	}

}
