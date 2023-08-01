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
@Table(name = "ec_summary_allocations", schema = "master")
public class EcSummaryAllocations extends Auditable<Integer> {


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "summary_allocation_ids", nullable = true)
	@JsonIgnore
	private EcPartC ecPartC;
	
	@Column(name = "emp", nullable = true, length = 100)
	private String emp;
	
	@Column(name = "capital_cost", nullable = true)
	private Double capital_cost;
	
	@Column(name = "recurring_cost", nullable = true)
	private Double recurring_cost;

	@Column(name = "is_active")
	private Boolean is_active;

	@Column(name = "is_deleted")
	private Boolean is_deleted;
	
	public EcSummaryAllocations() {
		this.is_active = true;
		this.is_deleted = false;
	}

	public EcSummaryAllocations(Integer id, String emp, Double capital_cost, Double recurring_cost) {
		this.id = id;
		this.emp = emp;
		this.capital_cost = capital_cost;
		this.recurring_cost = recurring_cost;
	}
	
	
}
