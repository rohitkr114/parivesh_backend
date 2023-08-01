package com.backend.model;

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
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "forest_clearance_component_detail", schema = "master")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class ForestClearanceComponentDetail extends Auditable<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;

	@Column(nullable = true, length = 500)
	String component;

	Double forest_area_total;

	Double non_forest_area_total;

	private boolean isDelete;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fc_Id", nullable = false)
//	@JsonBackReference(value = "fc_reference")
	@JsonIgnore
	private ForestClearance forestClearance;

	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "geo_referenced_map_id", nullable = true)
	private DocumentDetails geo_referenced_map;
	
	public DocumentDetails getGeo_referenced_map() {
		return geo_referenced_map;
	}

	public void setGeo_referenced_map(DocumentDetails geo_referenced_map) {
		geo_referenced_map.setId(null);
		this.geo_referenced_map = geo_referenced_map;
	}
	ForestClearanceComponentDetail() {
		this.isDelete = false;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getComponent() {
		return component;
	}

	public void setComponent(String component) {
		this.component = component;
	}

	public Double getForest_area_total() {
		return forest_area_total;
	}

	public void setForest_area_total(Double forest_area_total) {
		this.forest_area_total = forest_area_total;
	}

	public Double getNon_forest_area_total() {
		return non_forest_area_total;
	}

	public void setNon_forest_area_total(Double non_forest_area_total) {
		this.non_forest_area_total = non_forest_area_total;
	}

	public ForestClearance getForestClearance() {
		return forestClearance;
	}

	public void setForestClearance(ForestClearance forestClearance) {
		this.forestClearance = forestClearance;
	}

	public boolean isDelete() {
		return isDelete;
	}

	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}

}
