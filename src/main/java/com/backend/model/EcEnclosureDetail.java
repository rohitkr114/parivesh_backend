package com.backend.model;

import javax.persistence.CascadeType;
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
@Table(name = "ec_enclosures", schema = "master")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@JsonIgnoreProperties({ "created_by", "created_on", "updated_by", "updated_on" })
public class EcEnclosureDetail extends Auditable<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "component_layout_plan", nullable = true)
	private DocumentDetails componentLayoutPlan;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "letter_of_intent_mining", nullable = true)
	private DocumentDetails letterOfIntentMining;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "building_conceptual_plan", nullable = true)
	private DocumentDetails buildingConceptualPlan;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "feasibility_drawing_plan", nullable = true)
	private DocumentDetails feasibilityDrawingPlan;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "district_survey_report", nullable = true)
	private DocumentDetails districtSurveyReport;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "replenishment_study_report", nullable = true)
	private DocumentDetails replenishmentStudyReport;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "emp_report", nullable = true)
	private DocumentDetails empReport;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ec_id", nullable = false)
	@JsonIgnore
	private EnvironmentClearence enviromentClearence;

	public EcEnclosureDetail() {

	}

	public DocumentDetails getDistrictSurveyReport() {
		return districtSurveyReport;
	}

	public void setDistrictSurveyReport(DocumentDetails districtSurveyReport) {
		this.districtSurveyReport = districtSurveyReport;
	}

	public DocumentDetails getReplenishmentStudyReport() {
		return replenishmentStudyReport;
	}

	public void setReplenishmentStudyReport(DocumentDetails replenishmentStudyReport) {
		this.replenishmentStudyReport = replenishmentStudyReport;
	}

	public DocumentDetails getEmpReport() {
		return empReport;
	}

	public void setEmpReport(DocumentDetails empReport) {
		this.empReport = empReport;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public DocumentDetails getComponentLayoutPlan() {
		return componentLayoutPlan;
	}

	public void setComponentLayoutPlan(DocumentDetails componentLayoutPlan) {
		this.componentLayoutPlan = componentLayoutPlan;
	}

	public DocumentDetails getLetterOfIntentMining() {
		return letterOfIntentMining;
	}

	public void setLetterOfIntentMining(DocumentDetails letterOfIntentMining) {
		this.letterOfIntentMining = letterOfIntentMining;
	}

	public DocumentDetails getBuildingConceptualPlan() {
		return buildingConceptualPlan;
	}

	public void setBuildingConceptualPlan(DocumentDetails buildingConceptualPlan) {
		this.buildingConceptualPlan = buildingConceptualPlan;
	}

	public DocumentDetails getFeasibilityDrawingPlan() {
		return feasibilityDrawingPlan;
	}

	public void setFeasibilityDrawingPlan(DocumentDetails feasibilityDrawingPlan) {
		this.feasibilityDrawingPlan = feasibilityDrawingPlan;
	}

	public EnvironmentClearence getEnviromentClearence() {
		return enviromentClearence;
	}

	public void setEnviromentClearence(EnvironmentClearence enviromentClearence) {
		this.enviromentClearence = enviromentClearence;
	}
   
}
