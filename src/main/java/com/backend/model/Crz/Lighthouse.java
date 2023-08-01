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
@Table(name = "lighthouse", schema = "master")
public class Lighthouse extends Auditable<Integer> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name="total_area_of_construction_lighthouse",nullable=true)
	private Double total_area_of_construction_lighthouse;
	
	@Column(name="height_of_structure_lighthouse",nullable=true)
	private Double height_of_structure_lighthouse;
	
	@Column(name="is_active", nullable=true)
	private Boolean is_active;
	
	@Column(name="is_deleted", nullable=true)
	private Boolean is_deleted;
	
	public Lighthouse() {
		this.is_active=true;
		this.is_deleted=false;
	}
	
	
}
