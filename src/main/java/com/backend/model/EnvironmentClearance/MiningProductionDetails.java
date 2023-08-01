package com.backend.model.EnvironmentClearance;

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
@Table(name = "mining_production_details", schema = "master")
public class MiningProductionDetails extends Auditable<Integer> {

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
	
	public MiningProductionDetails() {
		this.is_active = true;
		this.is_deleted = false;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFinancial_year() {
		return financial_year;
	}

	public void setFinancial_year(String financial_year) {
		this.financial_year = financial_year;
	}

	public Double getSanctioned_ec() {
		return sanctioned_ec;
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

	public void setSanctioned_ec(Double sanctioned_ec) {
		this.sanctioned_ec = sanctioned_ec;
	}

	public Double getSanctioned_cto() {
		return sanctioned_cto;
	}

	public void setSanctioned_cto(Double sanctioned_cto) {
		this.sanctioned_cto = sanctioned_cto;
	}

	public Double getActual_production() {
		return actual_production;
	}

	public void setActual_production(Double actual_production) {
		this.actual_production = actual_production;
	}

	public Double getExcess_production() {
		return excess_production;
	}

	public void setExcess_production(Double excess_production) {
		this.excess_production = excess_production;
	}
}
