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
@Table(name = "offshore_structures", schema = "master")
public class OffshoreStructures extends Auditable<Integer> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name="area_of_structure",nullable=true)
	private Double area_of_structure;
	
	@Column(name="height_of_structure_offshore",nullable=true)
	private Double height_of_structure_offshore;
	
	@Column(name="depth_of_sea_bed",nullable=true)
	private Double depth_of_sea_bed;
	
	@Column(name="any_other_information",nullable=true,length=1000)
	private String any_other_information;
	
	@Column(name="is_active", nullable=true)
	private Boolean is_active;
	
	@Column(name="is_deleted", nullable=true)
	private Boolean is_deleted;
	
	public OffshoreStructures() {
		this.is_active=true;
		this.is_deleted=false;
	}
}
