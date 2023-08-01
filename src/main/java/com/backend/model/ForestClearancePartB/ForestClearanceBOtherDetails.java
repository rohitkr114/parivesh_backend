package com.backend.model.ForestClearancePartB;

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
@Table(name="forest_clearance_part_b_other_details",schema = "master")
public class ForestClearanceBOtherDetails extends Auditable<Integer>{
	



	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fc_partb_id", nullable = true)
	@JsonIgnore
	private ForestClearanceBBasicDetails forestClearanceBBasicDetails;

//	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//	@JoinColumn(name = "fc_form_a_part_b_patch_id", nullable = true)
//	private ForestClearanceBPatches forestClearanceBPatches;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "site_inspection_report_id", nullable = true)
	private DocumentDetails site_inspection_report;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "recommendation_file_copy_id", nullable = true)
	private DocumentDetails recommendation_file_copy;
	
	@Column(nullable = false)
	private String recomm_total_forest_land;
	
	@Column(nullable = false)
	private String recomm_area;
	
	@Column(length = 100,nullable = false)
	private String recommendation_of_dfo;
	
//	@Column(length = 20,nullable = false)
//	private String recomm_period_to;
	
//	@Column(length = 20,nullable = false)
//	private String recomm_period_from;
	
	@Column(length = 1000,nullable = true)
	private String justification;

	private Boolean is_active;
	
	private Boolean is_deleted;
	
	
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}

//
//	public ForestClearanceBPatches getForestClearanceBPatches() {
//		return forestClearanceBPatches;
//	}
//
//
//	public void setForestClearanceBPatches(ForestClearanceBPatches forestClearanceBPatches) {
//		this.forestClearanceBPatches = forestClearanceBPatches;
//	}


	public DocumentDetails getSite_inspection_report() {
		return site_inspection_report;
	}


	public void setSite_inspection_report(DocumentDetails site_inspection_report) {
		this.site_inspection_report = site_inspection_report;
	}


	public DocumentDetails getRecommendation_file_copy() {
		return recommendation_file_copy;
	}


	public void setRecommendation_file_copy(DocumentDetails recommendation_file_copy) {
		this.recommendation_file_copy = recommendation_file_copy;
	}


	public String getRecomm_total_forest_land() {
		return recomm_total_forest_land;
	}


	public void setRecomm_total_forest_land(String recomm_total_forest_land) {
		this.recomm_total_forest_land = recomm_total_forest_land;
	}


	public String getRecomm_area() {
		return recomm_area;
	}


	public void setRecomm_area(String recomm_area) {
		this.recomm_area = recomm_area;
	}


	public String getRecommendation_of_dfo() {
		return recommendation_of_dfo;
	}


	public void setRecommendation_of_dfo(String recommendation_of_dfo) {
		this.recommendation_of_dfo = recommendation_of_dfo;
	}


//	public String getRecomm_period_to() {
//		return recomm_period_to;
//	}


//	public void setRecomm_period_to(String recomm_period_to) {
//		this.recomm_period_to = recomm_period_to;
//	}


	public String getJustification() {
		return justification;
	}


	public void setJustification(String justification) {
		this.justification = justification;
	}


	public Boolean getIs_active() {
		return is_active;
	}


	public void setIs_active(Boolean is_active) {
		this.is_active = is_active;
	}


	public Boolean getIs_deleted() {
		return is_deleted;
	}


	public void setIs_deleted(Boolean is_deleted) {
		this.is_deleted = is_deleted;
	}


	ForestClearanceBOtherDetails(){
		this.is_active=true;
		this.is_deleted=false;
	}
}
