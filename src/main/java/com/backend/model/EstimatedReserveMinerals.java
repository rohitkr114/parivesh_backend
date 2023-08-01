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
@Table(name="estimated_reserve_minerals",schema = "master")
public class EstimatedReserveMinerals extends Auditable<Integer>{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(length=100,nullable = true)
	private String estimated_reserves_name;

	@Column(nullable = true)
	private Double estimated_reserves_fl;

	@Column(nullable = true)
	private Double estimated_reserves_nfl;

	@Column(length = 1000,nullable = true)
	private String estimated_reserves_remarks;
	
	@Column(nullable = true)
	private Boolean is_active;
	
	@Column(nullable = true)
	private Boolean is_delete;
	
	public EstimatedReserveMinerals() {
		this.is_active=true;
		this.is_delete=false;
	}
}
