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
@Table(name = "atomic_minerals", schema = "master")
public class AtomicMinerals extends Auditable<Integer> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name="capacity_of_mining",nullable=true)
	private Double capacity_of_mining;
	
	@Column(name="area_to_be_mined",nullable=true)
	private Double area_to_be_mined;
	
	@Column(name="type_of_mineral_to_extracted",nullable=true,length=50)
	private String type_of_mineral_to_extracted;
	
	@Column(name="end_use_of_mineral",nullable=true,length=100)
	private String end_use_of_mineral;
	
	@Column(name="is_active", nullable=true)
	private Boolean is_active;
	
	@Column(name="is_deleted", nullable=true)
	private Boolean is_deleted;
	
	public AtomicMinerals() {
		this.is_active=true;
		this.is_deleted=false;
	}
	
}
