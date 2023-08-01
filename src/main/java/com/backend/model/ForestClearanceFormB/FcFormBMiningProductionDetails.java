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
@Table(name = "fc_form_b_mining_production_details", schema = "master")
public class FcFormBMiningProductionDetails extends Auditable<Integer>{
	
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name = "financial_year", nullable = true)
	private String financial_year;
	
	@Column(name = "sanctioned_ec", nullable = true)
	private Double sanctioned_ec;
	
	@Column(name = "sanctioned_cto", nullable = true)
	private Double sanctioned_cto;
	
	@Column(name = "sanctioned_approved_plan", nullable = true)
	private Double sanctioned_approved_plan;
	
	@Column(name = "actual_production", nullable = true)
	private Double actual_production;
	
	@Column(name = "excess_production", nullable = true)
	private Double excess_production;
	
	@Column(name="is_active")
	private boolean is_active;
	
	@Column(name="is_deleted")
	private boolean is_deleted; 
	
	public FcFormBMiningProductionDetails() {
		this.is_active = true;
		this.is_deleted = false;
	}

}
