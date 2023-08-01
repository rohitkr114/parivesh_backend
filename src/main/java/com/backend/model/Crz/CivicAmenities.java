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
@Table(name = "civic_amenities", schema = "master")
public class CivicAmenities extends Auditable<Integer> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name="total_built_up_area", nullable=true)
	private Double total_built_up_area;
	
	@Column(name="built_up_area", nullable=true)
	private Double built_up_area;
	
	@Column(name="height_of_structure", nullable=true)
	private Double height_of_structure;
	
	@Column(name="fsi_ratio", nullable=true)
	private Double fsi_ratio;
	
	@Column(name="governing_town_planning", nullable=true,length=1000)
	private String governing_town_planning;
	
	@Column(name="details_of_provision_of_car", nullable=true,length=1000)
	private String details_of_provision_of_car;
	
	@Column(name="is_active", nullable=true)
	private Boolean is_active;
	
	@Column(name="is_deleted", nullable=true)
	private Boolean is_deleted;
	
	public CivicAmenities() {
		this.is_active=true;
		this.is_deleted=false;
	}
}
