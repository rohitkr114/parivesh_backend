package com.backend.model;

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
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;

@Data
@Entity
@Table(name = "fc_enclosures", schema = "master")
@JsonIgnoreProperties({ "created_by", "created_on", "updated_by", "updated_on" })
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class FcEnclosures extends Auditable<Integer> {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(nullable = true,length= 1000)
	private String additional_information;



	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "copy_approved_mining_plan_id", nullable = true)
	private DocumentDetails copy_approved_mining_plan;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "copy_map_outer_boundary_id", nullable = true)
	private DocumentDetails copy_map_outer_boundary;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "copy_detailed_land_use_plan_id", nullable = true)
	private DocumentDetails copy_detailed_land_use_plan;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "copy_perspecting_licence_id", nullable = true)
	private DocumentDetails copy_perspecting_licence;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "copy_approval_accorded_moefcc_id", nullable = true)
	private DocumentDetails copy_approval_accorded_moefcc;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "copy_transportation_minerals_proposed_id", nullable = true)
	private DocumentDetails copy_transportation_minerals_proposed;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "copy_additional_information_id", nullable = true)
	private DocumentDetails copy_additional_information;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fc_id", nullable = false)
	@JsonIgnore
//	@JsonBackReference(value = "fc_reference")
	private ForestClearance forestClearance;

	public FcEnclosures() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAdditional_information() {
		return additional_information;
	}

	public void setAdditional_information(String additional_information) {
		this.additional_information = additional_information;
	}

	public ForestClearance getForestClearance() {
		return forestClearance;
	}

	public void setForestClearance(ForestClearance forestClearance) {
		this.forestClearance = forestClearance;
	}



	public DocumentDetails getCopy_approved_mining_plan() {
		return copy_approved_mining_plan;
	}

	public void setCopy_approved_mining_plan(DocumentDetails copy_approved_mining_plan) {
		this.copy_approved_mining_plan = copy_approved_mining_plan;
	}

	public DocumentDetails getCopy_map_outer_boundary() {
		return copy_map_outer_boundary;
	}

	public void setCopy_map_outer_boundary(DocumentDetails copy_map_outer_boundary) {
		this.copy_map_outer_boundary = copy_map_outer_boundary;
	}

	public DocumentDetails getCopy_detailed_land_use_plan() {
		return copy_detailed_land_use_plan;
	}

	public void setCopy_detailed_land_use_plan(DocumentDetails copy_detailed_land_use_plan) {
		this.copy_detailed_land_use_plan = copy_detailed_land_use_plan;
	}

	public DocumentDetails getCopy_perspecting_licence() {
		return copy_perspecting_licence;
	}

	public void setCopy_perspecting_licence(DocumentDetails copy_perspecting_licence) {
		this.copy_perspecting_licence = copy_perspecting_licence;
	}

	public DocumentDetails getCopy_approval_accorded_moefcc() {
		return copy_approval_accorded_moefcc;
	}

	public void setCopy_approval_accorded_moefcc(DocumentDetails copy_approval_accorded_moefcc) {
		this.copy_approval_accorded_moefcc = copy_approval_accorded_moefcc;
	}

	public DocumentDetails getCopy_transportation_minerals_proposed() {
		return copy_transportation_minerals_proposed;
	}

	public void setCopy_transportation_minerals_proposed(DocumentDetails copy_transportation_minerals_proposed) {
		this.copy_transportation_minerals_proposed = copy_transportation_minerals_proposed;
	}

	public DocumentDetails getCopy_additional_information() {
		return copy_additional_information;
	}

	public void setCopy_additional_information(DocumentDetails copy_additional_information) {
		this.copy_additional_information = copy_additional_information;
	}
   
}
