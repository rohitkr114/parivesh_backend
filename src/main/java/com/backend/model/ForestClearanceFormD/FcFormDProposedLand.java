package com.backend.model.ForestClearanceFormD;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.backend.audit.Auditable;
import com.backend.model.DocumentDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "fc_form_d_proposed_land", schema = "master")
public class FcFormDProposedLand extends Auditable<Integer>{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(nullable = false)
	private Double total_proposed_diversion_area;	
	
	@Column(nullable = true)
	private Integer total_proposed_diversion_period;
	
	@Column(nullable = true)
	private Double total_forest_land;
	
	@Column(nullable = true)
	private Double total_non_forest_land;
	
	@Column(nullable = true)
	private Double total_area_of_kmls;
	
	@Column(nullable = true)
	private Double distance;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "geo_referenced_map_id", nullable = true)
	private DocumentDetails geo_referenced_map;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "letter_of_intent_id", nullable = true)
	private DocumentDetails letter_of_intent;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "copy_map_outer_boundary_id", nullable = true)
	private DocumentDetails copy_map_outer_boundary;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fc_form_d_id", nullable = false)
	@JsonIgnore
	private FcFormD fcFormD;
	
	private Boolean is_active;
	
	private Boolean is_deleted;

	public FcFormDProposedLand() {
		this.is_active=true;
		this.is_deleted=false;
	}
	
	public FcFormDProposedLand(Integer id, Double total_proposed_diversion_area,
			Integer total_proposed_diversion_period, Double total_forest_land,
			Double total_non_forest_land, Double total_area_of_kmls, Double distance) {
		super();
		this.id = id;
		this.total_proposed_diversion_area = total_proposed_diversion_area;
		this.total_proposed_diversion_period = total_proposed_diversion_period;
		this.total_forest_land = total_forest_land;
		this.total_non_forest_land = total_non_forest_land;
		this.total_area_of_kmls = total_area_of_kmls;
		this.distance = distance;
	}
	
	public FcFormDProposedLand(Integer id) {
		this.id=id;
	}

}
