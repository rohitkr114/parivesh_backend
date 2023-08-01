package com.backend.model.ForestClearanceFormB;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.backend.audit.Auditable;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "fc_form_b_proposed_diversions_details", schema = "master")
public class FcFormBProposedDiversionsDetails extends Auditable<Integer>{

	public String getToposheet_no() {
		return toposheet_no;
	}

	public void setToposheet_no(String toposheet_no) {
		this.toposheet_no = toposheet_no;
	}
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "fc_form_b_Id", nullable = true)
//	@JsonIgnore
//	private FcFormBProjectDetails fcFormBProjectDetails; 
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;


	@Column(nullable = true, length = 50)
	private String toposheet_no;
	@Column(nullable = false, length = 50)
	private String district;

	@Column(nullable = false, length = 50) 
	private String village;
	
	@Column(nullable=true)
	private String unit;

	@Column(nullable = true)
	private String range;

	@Column(nullable = true)
	private String forest_area_length;

	@Column(nullable = true)
	private String forest_area_width;

	@Column(nullable = true)
	private String forest_area_total;

	@Column(nullable = true)
	private String non_forest_area_length;

	@Column(nullable = true)
	private String non_forest_area_width;

	@Column(nullable = true)
	private String non_forest_area_total;
	@Column(nullable = true)
	private String non_linear_forest_area;
	@Column(nullable = true)
	private String non_linear_non_forest_area;

	@Column(name = "manual_entry", nullable = true)
	private Boolean manual_entry;

	public Boolean isManual_entry() {
		return manual_entry;
	}

	public void setManual_entry(Boolean manual_entry) {
		if(manual_entry==true) {
			this.manual_entry = manual_entry;
		}else {
			this.manual_entry=false;
		}

	}
	@Column(nullable = false)
	private Boolean is_deleted;

	FcFormBProposedDiversionsDetails() {
		this.is_deleted = false;

	}

	public Boolean is_deleted() {
		return is_deleted;
	}

	public String getForest_area_length() {
		return forest_area_length;
	}

	public void setForest_area_length(String forest_area_length) {
		this.forest_area_length = forest_area_length;
	}

	public String getForest_area_width() {
		return forest_area_width;
	}

	public void setForest_area_width(String forest_area_width) {
		this.forest_area_width = forest_area_width;
	}

	public String getForest_area_total() {
		return forest_area_total;
	}

	public void setForest_area_total(String forest_area_total) {
		this.forest_area_total = forest_area_total;
	}

	public String getNon_forest_area_length() {
		return non_forest_area_length;
	}

	public void setNon_forest_area_length(String non_forest_area_length) {
		this.non_forest_area_length = non_forest_area_length;
	}

	public String getNon_forest_area_width() {
		return non_forest_area_width;
	}

	public void setNon_forest_area_width(String non_forest_area_width) {
		this.non_forest_area_width = non_forest_area_width;
	}

	public String getNon_forest_area_total() {
		return non_forest_area_total;
	}

	public void setNon_forest_area_total(String non_forest_area_total) {
		this.non_forest_area_total = non_forest_area_total;
	}

	public String getNon_linear_forest_area() {
		return non_linear_forest_area;
	}

	public void setNon_linear_forest_area(String non_linear_forest_area) {
		this.non_linear_forest_area = non_linear_forest_area;
	}

	public String getNon_linear_non_forest_area() {
		return non_linear_non_forest_area;
	}

	public void setNon_linear_non_forest_area(String non_linear_non_forest_area) {
		this.non_linear_non_forest_area = non_linear_non_forest_area;
	}

	public void setIs_deleted(Boolean is_deleted) {
		this.is_deleted = is_deleted;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getVillage() {
		return village;
	}

	public void setVillage(String village) {
		this.village = village;
	}

	public String getRange() {
		return range;
	}

	public void setRange(String range) {
		this.range = range;
	}






}
