package com.backend.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.backend.audit.Auditable;
import com.backend.constant.AppConstant;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(exclude = "commonFormDetail")
@Table(name = "caf_location_kml", schema = "master")
public class CafLocationOfKml extends Auditable<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "caf_id", nullable = false)
//	@JsonBackReference(value = "caf_detail_reference")
	@JsonIgnore
	private CommonFormDetail commonFormDetail;

	@Transient
	@JsonProperty
	private List<CafKML> cafKML = new ArrayList<>();

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "falling_border_in_states", nullable = true)
	private AppConstant.bool falling_border_in_states;

	@Column(name = "international_border_distance", nullable = true)
	private double international_border_distance;

	@Column(name = "shape_of_project", nullable = true)
	private String shape_of_project;

	@Column(name = "existing_non_forest_land", length = 12, nullable = true)
	private double existing_non_forest_land;

	@Column(name = "existing_forest_land", length = 12, nullable = true)
	private double existing_forest_land;

	@Column(name = "existing_total_land", nullable = true)
	private double existing_total_land;

	@Column(name = "additional_non_forest_land", length = 12, nullable = true)
	private double additional_non_forest_land;

	@Column(name = "additional_forest_land", length = 12, nullable = true)
	private double additional_forest_land;

	@Column(name = "additional_total_land", nullable = true)
	private double additional_total_land;

	@Column(name = "total_non_forest_land", nullable = true)
	private double total_non_forest_land;

	@Column(name = "total_forest_land", nullable = true)
	private double total_forest_land;

	@Column(name = "total_land", nullable = true)
	private double total_land;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public CommonFormDetail getCommonFormDetail() {
		return commonFormDetail;
	}

	public void setCommonFormDetail(CommonFormDetail commonFormDetail) {
		this.commonFormDetail = commonFormDetail;
	}

	public List<CafKML> getCafKML() {
		return cafKML;
	}

	public void setCafKML(List<CafKML> cafKML) {
		this.cafKML = cafKML;
	}

	public AppConstant.bool getFalling_border_in_states() {
		return falling_border_in_states;
	}

	public void setFalling_border_in_states(AppConstant.bool falling_border_in_states) {
		this.falling_border_in_states = falling_border_in_states;
	}

	public double getInternational_border_distance() {
		return international_border_distance;
	}

	public void setInternational_border_distance(double international_border_distance) {
		this.international_border_distance = international_border_distance;
	}

	public String getShape_of_project() {
		return shape_of_project;
	}

	public void setShape_of_project(String shape_of_project) {
		this.shape_of_project = shape_of_project;
	}

	public double getExisting_non_forest_land() {
		return existing_non_forest_land;
	}

	public void setExisting_non_forest_land(double existing_non_forest_land) {
		this.existing_non_forest_land = existing_non_forest_land;
	}

	public double getExisting_forest_land() {
		return existing_forest_land;
	}

	public void setExisting_forest_land(double existing_forest_land) {
		this.existing_forest_land = existing_forest_land;
	}

	public double getExisting_total_land() {
		return existing_total_land;
	}

	public void setExisting_total_land(double existing_total_land) {
		this.existing_total_land = existing_total_land;
	}

	public double getAdditional_non_forest_land() {
		return additional_non_forest_land;
	}

	public void setAdditional_non_forest_land(double additional_non_forest_land) {
		this.additional_non_forest_land = additional_non_forest_land;
	}

	public double getAdditional_forest_land() {
		return additional_forest_land;
	}

	public void setAdditional_forest_land(double additional_forest_land) {
		this.additional_forest_land = additional_forest_land;
	}

	public double getAdditional_total_land() {
		return additional_total_land;
	}

	public void setAdditional_total_land(double additional_total_land) {
		this.additional_total_land = additional_total_land;
	}

	public double getTotal_non_forest_land() {
		return total_non_forest_land;
	}

	public void setTotal_non_forest_land(double total_non_forest_land) {
		this.total_non_forest_land = total_non_forest_land;
	}

	public double getTotal_forest_land() {
		return total_forest_land;
	}

	public void setTotal_forest_land(double total_forest_land) {
		this.total_forest_land = total_forest_land;
	}

	public double getTotal_land() {
		return total_land;
	}

	public void setTotal_land(double total_land) {
		this.total_land = total_land;
	}
}
