package com.backend.model.Crz;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.backend.audit.Auditable;

import lombok.Data;

@Data
@Entity
@Table(name="crz_type_of_project",schema = "master")
public class CrzTypeOfProject extends Auditable<Integer>{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(nullable = true)
	private String type_of_project;
	
	@Column(nullable = true)
	private Double total_built_up_area;

	@Column(nullable = true)
	private Double built_up_area;

	@Column(nullable = true)
	private Double height_of_structure;

	@Column(nullable = true)
	private Double fsi_ratio;

	@Column(nullable = true)
	private String governing_town_planning;

	@Column(nullable = true)
	private String details_of_provision_of_car;

	@Column(nullable = true)
	private Double area_of_land_reclamation;

	@Column(nullable = true)
	private Double estimated_quantity_for_reclamation;

	@Column(nullable = true)
	private Double carrying_capacity_of_traffic;

	@Column(nullable = true)
	private Double length_of_pipeline;

	@Column(nullable = true)
	private Double length_traversing_crz_area;

	@Column(nullable = true)
	private Double length_of_pipeline_from_seashore_to_deep_sea;

	@Column(nullable = true)
	private Double depth_of_excavation;

	@Column(nullable = true)
	private Double width_of_excavation;

	@Column(nullable = true)
	private Double depth_of_outfall_point;

	@Column(nullable = true)
	private Double temperature_of_effluent;

	@Column(nullable = true)
	private String location_of_intake;

	@Column(nullable = true)
	private String location_of_outfall;

	@Column(nullable = true)
	private Double depth_of_outfall_point_marine;

	@Column(nullable = true)
	private Double length_of_pipeline_marine;

	@Column(nullable = true)
	private Double length_traversing_crz_area_marine;

	@Column(nullable = true)
	private Double length_of_pipeline_from_seashore_to_deep_sea_marine;

	@Column(nullable = true)
	private Double depth_of_excavation_marine;

	@Column(nullable = true)
	private Double width_of_excavation_marine;

	@Column(nullable = true)
	private Double depth_of_outfall_point_from_surface_of_sea_water_marine;

	@Column(nullable = true)
	private Double depth_of_water_at_disposal_point;

	@Column(nullable = true)
	private Double effluent_BOD;

	@Column(nullable = true)
	private Double effluent_COD;

	@Column(nullable = true)
	private Double effluent_TSS;

	@Column(nullable = true)
	private Double oil_and_grease;

	@Column(nullable = true)
	private Double heavy_metals;
	
	private Boolean is_active;
	
	private Boolean is_deleted;
	
	public CrzTypeOfProject() {
		this.is_active=true;
		this.is_deleted=false;
	}
	
}
