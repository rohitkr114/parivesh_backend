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
@Table(name = "marine_disposal", schema = "master")
public class MarineDisposalofTreatedEffluent extends Auditable<Integer> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name="location_of_intake",nullable=true,length=100)
	private String location_of_intake;
	
	@Column(name="location_of_outfall",nullable=true,length=100)
	private String location_of_outfall;
	
	@Column(name="depth_of_outfall_point_marine",nullable=true)
	private Double depth_of_outfall_point_marine;
	
	@Column(name="length_of_pipeline_marine",nullable=true)
	private Double length_of_pipeline_marine;
	
	@Column(name="length_traversing_crz_area_marine",nullable=true)
	private Double length_traversing_crz_area_marine;
	
	@Column(name="length_of_pipeline_from_seashore_to_deep_sea_marine",nullable=true)
	private Double length_of_pipeline_from_seashore_to_deep_sea_marine;
	
	@Column(name="depth_of_excavation_marine",nullable=true)
	private Double depth_of_excavation_marine;
	
	@Column(name="width_of_excavation_marine",nullable=true)
	private Double width_of_excavation_marine;
	
	@Column(name="depth_of_outfall_point_from_surface_of_sea_water_marine",nullable=true)
	private Double depth_of_outfall_point_from_surface_of_sea_water_marine;
	
	@Column(name="depth_of_water_at_disposal_point",nullable=true)
	private Double depth_of_water_at_disposal_point;
	
	@Column(name="other_effluent",nullable=true,length=50)
	private String other_effluent;
	
	@Column(name="effluent_BOD", nullable=true,length=100)
	private String effluent_BOD;
	
	@Column(name="effluent_COD", nullable=true,length=100)
	private String effluent_COD;
	
	@Column(name="effluent_TSS", nullable=true,length=100)
	private String effluent_TSS;
	
	@Column(name="oil_and_grease", nullable=true,length=100)
	private String oil_and_grease;
	
	@Column(name="heavy_metals", nullable=true,length=100)
	private String heavy_metals;
	
	@Column(name="is_active", nullable=true)
	private Boolean is_active;
	
	@Column(name="is_deleted", nullable=true)
	private Boolean is_deleted;
	
	public MarineDisposalofTreatedEffluent() {
		this.is_active=true;
		this.is_deleted=false;
	}
}
