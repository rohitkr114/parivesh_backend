package com.backend.model;

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
import com.backend.model.EnvironmentClearance.EcPartB;
import com.backend.model.EnvironmentClearance.EcRiverValleyProject;
import com.backend.model.WildLifeClearance.WildLifeClearance;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "river_valley_project", schema = "master")
public class RiverValleyProject extends Auditable<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;

	@Column(length = 50)
	private Integer cafId;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ec_partb_id", nullable = true)
	@JsonIgnore
	private EcPartB ecPartB;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fc_id", nullable = true)
	@JsonIgnore
	private ForestClearance forestClearance;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "wlc_id", nullable = true)
	@JsonIgnore
	private WildLifeClearance wildLifeClearance;

	private String riverValleyProject;

	private String involvesConstructionOf;

	@Column(name = "river_name", nullable = false, length = 300)
	private String riverName;

	@Column(name = "irrigation_project_configuration_json", nullable = true, length = 3000)
	private String irrigationProjectConfigurationJson;

	@Column(name = "irrigation_project_capacity_cultural_command", nullable = true, length = 50)
	private String irrigationProjectCapacityCulturalCommand;

	@Column(name = "irrigation_project_capacity_irrigable_command", nullable = true, length = 50)
	private String irrigationProjectCapacityIrrigableCommand;

	@Column(name = "irrigation_project_capacity_type_of_irrigation", nullable = true, length = 100)
	private String irrigationProjectCapacityTypeOfIrrigation;

	@Column(name = "irrigation_project_capacity_type_of_irrigation_value", nullable = true, length = 100)
	private String irrigationProjectCapacityTypeOfIrrigationValue;

	@Column(name = "hep_project_configuration_json", nullable = true, length = 3000)
	private String hepProjectConfigurationJson;

	@Column(name = "hep_project_capacity_json", nullable = true, length = 3000)
	private String hepProjectCapacityJson;

	private Double catchementArea;

	private Double averageRainfall;

	private Double waterAvailability;

	@Column(name = "muck_restoration_plan", nullable = true, length = 500)
	private String muckRestorationPlan;

	@Column(name = "schedule_1_spieces", nullable = true, length = 500)
	private String schedule1Spieces;

	@Column(name = "brief_on_e_flow", nullable = true, length = 500)
	private String briefOnEFlow;

	private String fishPassEnvisagedProvision;

	@Column(name = "reasons", nullable = true, length = 500)
	private String reasons;

	private Boolean isDeleted;

	private Boolean isActive;

	public RiverValleyProject(EcRiverValleyProject ecRiverValleyProject) {
		super();
		this.cafId = ecRiverValleyProject.getEcPartB().getEnvironmentClearence().getCommonFormDetail().getId();
		this.ecPartB = ecRiverValleyProject.getEcPartB();
		this.riverValleyProject = ecRiverValleyProject.getRiver_valley_project();
		this.involvesConstructionOf = ecRiverValleyProject.getInvolves_construction_of();
		this.riverName = ecRiverValleyProject.getRiver_name();
		this.irrigationProjectConfigurationJson = ecRiverValleyProject.getIrrigation_project_configuration_json();
		this.irrigationProjectCapacityCulturalCommand = ecRiverValleyProject
				.getIrrigation_project_capacity_cultural_command();
		this.irrigationProjectCapacityIrrigableCommand = ecRiverValleyProject
				.getIrrigation_project_capacity_irrigable_command();
		this.irrigationProjectCapacityTypeOfIrrigation = ecRiverValleyProject
				.getIrrigation_project_capacity_type_of_irrigation();
		this.irrigationProjectCapacityTypeOfIrrigationValue = ecRiverValleyProject
				.getIrrigation_project_capacity_type_of_irrigation();
		this.hepProjectConfigurationJson = ecRiverValleyProject.getHep_project_configuration_json();
		this.hepProjectCapacityJson = ecRiverValleyProject.getHep_project_capacity_json();
		this.catchementArea = ecRiverValleyProject.getCatchement_area();
		this.averageRainfall = ecRiverValleyProject.getAverage_rainfall();
		this.waterAvailability = ecRiverValleyProject.getWater_availability();
		this.muckRestorationPlan = ecRiverValleyProject.getMuck_restoration_plan();
		this.schedule1Spieces = ecRiverValleyProject.getSchedule_1_spieces();
		this.briefOnEFlow = ecRiverValleyProject.getBrief_on_e_flow();
		this.fishPassEnvisagedProvision = ecRiverValleyProject.getFish_pass_envisaged_provision();
		this.reasons = ecRiverValleyProject.getReasons();
		this.isDeleted = ecRiverValleyProject.isIs_deleted();
	}

	public RiverValleyProject(ForestClearanceRiverValleyProject fcRiverValleyProject) {
		super();
		this.cafId = fcRiverValleyProject.getForestClearance().getCommonFormDetail().getId();
		this.forestClearance = fcRiverValleyProject.getForestClearance();
		this.wildLifeClearance = fcRiverValleyProject.getWildLifeClearance();
		this.riverValleyProject = fcRiverValleyProject.getRiver_valley_project();
		this.involvesConstructionOf = fcRiverValleyProject.getInvolves_construction_of();
		this.riverName = fcRiverValleyProject.getRiver_name();
		this.irrigationProjectConfigurationJson = fcRiverValleyProject
				.getIrrigation_project_capacity_type_of_irrigation();
		this.irrigationProjectCapacityCulturalCommand = fcRiverValleyProject
				.getIrrigation_project_capacity_type_of_irrigation();
		this.irrigationProjectCapacityIrrigableCommand = fcRiverValleyProject
				.getIrrigation_project_capacity_type_of_irrigation();
		this.irrigationProjectCapacityTypeOfIrrigation = fcRiverValleyProject
				.getIrrigation_project_capacity_type_of_irrigation();
		this.irrigationProjectCapacityTypeOfIrrigationValue = fcRiverValleyProject
				.getIrrigation_project_capacity_type_of_irrigation();
		this.hepProjectConfigurationJson = fcRiverValleyProject.getHep_project_configuration_json();
		this.hepProjectCapacityJson = fcRiverValleyProject.getHep_project_capacity_json();
		this.catchementArea = fcRiverValleyProject.getCatchement_area();
		this.averageRainfall = fcRiverValleyProject.getAverage_rainfall();
		this.waterAvailability = fcRiverValleyProject.getWater_availability();
		this.muckRestorationPlan = fcRiverValleyProject.getMuck_restoration_plan();
		this.schedule1Spieces = fcRiverValleyProject.getSchedule_1_spieces();
		this.briefOnEFlow = fcRiverValleyProject.getBrief_on_e_flow();
		this.fishPassEnvisagedProvision = fcRiverValleyProject.getFish_pass_envisaged_provision();
		this.reasons = fcRiverValleyProject.getReasons();
		this.isDeleted = fcRiverValleyProject.isIs_deleted();
		this.isActive = fcRiverValleyProject.isIs_active();
	}

}
