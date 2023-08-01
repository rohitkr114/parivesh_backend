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
@Table(name = "pipelines", schema = "master")
public class Pipelines extends Auditable<Integer> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name="length_of_pipeline", nullable=true)
	private Double length_of_pipeline;
	
	@Column(name="length_traversing_crz_area", nullable=true)
	private Double length_traversing_crz_area;
	
	@Column(name="length_of_pipeline_from_seashore_to_deep_sea", nullable=true)
	private Double length_of_pipeline_from_seashore_to_deep_sea;
	
	@Column(name="depth_of_excavation", nullable=true)
	private Double depth_of_excavation;
	
	@Column(name="width_of_excavation", nullable=true)
	private Double width_of_excavation;
	
	@Column(name="depth_of_outfall_point", nullable=true)
	private Double depth_of_outfall_point;
	
	@Column(name="temperature_of_effluent", nullable=true)
	private Double temperature_of_effluent;
	
	@Column(name="is_active", nullable=true)
	private Boolean is_active;
	
	@Column(name="is_deleted", nullable=true)
	private Boolean is_deleted;
	
	public Pipelines() {
		this.is_active=true;
		this.is_deleted=false;
	}
}
