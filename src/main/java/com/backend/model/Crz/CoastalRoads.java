package com.backend.model.Crz;

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
@Table(name = "coastal_roads", schema = "master")
public class CoastalRoads extends Auditable<Integer> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name="area_of_land_reclamation", nullable=true)
	private Double area_of_land_reclamation;
	
	@Column(name="estimated_quantity_for_reclamation", nullable=true)
	private Double estimated_quantity_for_reclamation;
	
	@Column(name="carrying_capacity_of_traffic", nullable=true, length=1000)
	private String carrying_capacity_of_traffic;
	
	@Column(name="is_active", nullable=true)
	private Boolean is_active;
	
	@Column(name="is_deleted", nullable=true)
	private Boolean is_deleted;
	
	public CoastalRoads() {
		this.is_active=true;
		this.is_deleted=false;
	}
}
