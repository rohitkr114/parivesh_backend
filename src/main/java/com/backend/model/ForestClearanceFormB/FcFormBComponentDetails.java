package com.backend.model.ForestClearanceFormB;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.backend.audit.Auditable;
import com.backend.model.DocumentDetails;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;

@Data
@Entity
@Table(name = "fc_form_b_component_details", schema = "master")
public class FcFormBComponentDetails extends Auditable<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@ManyToOne
	@JoinColumn(name = "fc_form_b_id", nullable = true)
	@JsonIgnore
	private FcFormBProjectDetails fcFormBProjectDetails;

	@Column(nullable = true, length = 500)
	private String component;
	
	@Column(nullable = true)
	private Double forest_area_total;

	@Column(nullable = true)
	private Double non_forest_area_total;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "geo_referenced_map", nullable = true)
	private DocumentDetails geo_referenced_map;
	
	private Boolean is_active;

	private Boolean is_deleted;
	
	public DocumentDetails getGeo_referenced_map() {
		return geo_referenced_map;
	}

	public void setGeo_referenced_map(DocumentDetails geo_referenced_map) {
		geo_referenced_map.setId(null);
		this.geo_referenced_map = geo_referenced_map;
	}
	public FcFormBComponentDetails() {
		this.is_active=true;
		this.is_deleted=false;
	}
	
}
