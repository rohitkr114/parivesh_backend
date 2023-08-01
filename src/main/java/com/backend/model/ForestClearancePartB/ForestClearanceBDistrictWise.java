package com.backend.model.ForestClearancePartB;

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
@Table(name= "forest_clearance_part_b_district_wise", schema="master")
public class ForestClearanceBDistrictWise extends Auditable<Integer>{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(length = 100)
	private String district_name;

	@Column(length = 100, nullable = true)
	private String district_code;
	
	private Double area;

	private Boolean is_active;
	
	private Boolean is_deleted;
	
	public ForestClearanceBDistrictWise() {
		this.is_active=true;
		this.is_deleted=false;
	}
}
