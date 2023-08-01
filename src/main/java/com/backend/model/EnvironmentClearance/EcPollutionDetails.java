package com.backend.model.EnvironmentClearance;

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
import com.backend.model.EnvironmentClearence;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "ec_pollution_details", schema = "master")
public class EcPollutionDetails extends Auditable<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer id;

	@OneToOne(targetEntity = EcPartB.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "ec_part_b_id", nullable = false)
	@JsonIgnore
	private EcPartB ecPartB;

	private boolean probable_generation_of_noise;

	@Column(name = "source_of_noise", nullable = true, length = 200)
	private String source_of_noise;

	@Column(name = "source_of_vibration", nullable = true, length = 200)
	private String source_of_vibration;

	@Column(name = "blasting_details", nullable = true, length = 200)
	private String blasting_details;

	@Column(name = "other_noise_details", nullable = true, length = 300)
	private String other_noise_details;

	private boolean mitigation_measure_for_noise;

	@Column(name = "mitigation_measure_for_noise_control", nullable = true, length = 200)
	private String mitigation_measure_for_noise_control;

	@Column(name = "mitigation_measure_for_vibration_control", nullable = true, length = 200)
	private String mitigation_measure_for_vibration_control;

	@Column(name = "other_mitigation_measure_details", nullable = true, length = 300)
	private String other_mitigation_measure_details;

	private boolean probable_generation_of_heat;

	@Column(name = "source_of_light", nullable = true, length = 200)
	private String source_of_light;

	@Column(name = "source_of_heat", nullable = true, length = 200)
	private String source_of_heat;

	@Column(name = "other_light_heat_details", nullable = true, length = 300)
	private String other_light_heat_details;

	private boolean mitigation_measures_for_light_heat;

	@Column(name = "mitigation_measure_for_light_control", nullable = true, length = 200)
	private String mitigation_measure_for_light_control;

	@Column(name = "mitigation_measure_for_heat_control", nullable = true, length = 200)
	private String mitigation_measure_for_heat_control;

	@Column(name = "other_mitigation_measure_for_light_heat", nullable = true, length = 300)
	private String other_mitigation_measure_for_light_heat;

	private boolean probable_water_pollutants;

	@Column(name = "organic_pollutants", nullable = true, length = 200)
	private String organic_pollutants;

	@Column(name = "inorganic_pollutants", nullable = true, length = 200)
	private String inorganic_pollutants;

	@Column(name = "micro_organism", nullable = true, length = 200)
	private String micro_organism;

	@Column(name = "sediments", nullable = true, length = 200)
	private String sediments;

	@Column(name = "heavy_metals", nullable = true, length = 200)
	private String heavy_metals;

	@Column(name = "others", nullable = true, length = 300)
	private String others;

	private boolean probable_sources_of_water_pollutants;

	@Column(name = "sources_of_water_pollution", nullable = true, length = 300)
	private String sources_of_water_pollution;

	@Column(name = "other_information", nullable = true, length = 500)
	private String other_information;
	
	@Column(name = "recycle_of_waste_water", nullable = true, length = 25000)
	private String recycle_of_waste_water;  // json for new and expansion.
	
	
	@Column(name = "offsite_treatment_plant", nullable = true)
	private Boolean offsite_treatment_plant;

	@Column(name = "treatment_plant_particulars", nullable = true, length = 25000)
	private String offsite_treatment_plant_particulars; // json for CETP/CSTP

	@Column(name = "onsite_treatment_plant", nullable = true)
	private Boolean onsite_treatment_plant;

	@Column(name = "type_of_treatment_plant", nullable = true, length = 255)
	private String type_of_treatment_plant;

	@Column(name = "agency_name", nullable = true, length = 150)
	private String agency_name;

	@Column(name = "agency_name_both", nullable = true, length = 150)
	private String agency_name_both;

	@Column(name = "capacity", nullable = true, length = 50)
	private String capacity;

	@Column(name = "capacity_unit", nullable = true, length = 255)
	private String capacity_unit;

	@Column(name = "capacity_both", nullable = true, length = 50)
	private String capacity_both;

	@Column(name = "capacity_unit_both", nullable = true, length = 255)
	private String capacity_unit_both;

	@Column(name = "technology", nullable = true, length = 200)
	private String technology;

	@Column(name = "technology_both", nullable = true, length = 200)
	private String technology_both;

	@Column(name = "present_loan_on_facility", nullable = true, length = 200)
	private String present_loan_on_facility;

	@Column(name = "present_loan_on_facility_both", nullable = true, length = 200)
	private String present_loan_on_facility_both;

	@Column(name = "distance_from_site", nullable = true, length = 50)
	private String distance_from_site;

	@Column(name = "distance_from_site_both", nullable = true, length = 50)
	private String distance_from_site_both;

	@Column(name = "distance_from_site_unit", nullable = true, length = 255)
	private String distance_from_site_unit;

	@Column(name = "distance_from_site_unit_both", nullable = true, length = 255)
	private String distance_from_site_unit_both;

	@Column(name = "reuse_of_treated_water", nullable = true, length = 200)
	private String reuse_of_treated_water;

	@Column(name = "reuse_of_treated_water_both", nullable = true, length = 200)
	private String reuse_of_treated_water_both;

	@Column(name = "mou_with_agency", nullable = true, length = 200)
	private String mou_with_agency;

	@Column(name = "mou_with_agency_both", nullable = true, length = 200)
	private String mou_with_agency_both;

	@Column(name = "waste_water_generated", nullable = true)
	private Boolean waste_water_generated;

	@Column(name = "waste_water_generated_reason", nullable = true, length = 500)
	private String waste_water_generated_reason;

	@Column(name = "waste_water_generated_expansion", nullable = true)
	private Boolean waste_water_generated_expansion;

	@Column(name = "waste_water_generated_reason_expansion", nullable = true, length = 500)
	private String waste_water_generated_reason_expansion;

	@Column(name = "type_of_treatment_plant_on_site", nullable = true, length = 255)
	private String type_of_treatment_plant_on_site;

	@Column(name = "type_of_treatment_plant_on_site_expansion", nullable = true, length = 255)
	private String type_of_treatment_plant_on_site_expansion;

	@Column(name = "onsite_capacity", nullable = true, length = 50)
	private String onsite_capacity;

	@Column(name = "onsite_capacity_unit", nullable = true, length = 255)
	private String onsite_capacity_unit;

	@Column(name = "onsite_capacity_both", nullable = true, length = 50)
	private String onsite_capacity_both;

	@Column(name = "onsite_capacity_unit_both", nullable = true, length = 255)
	private String onsite_capacity_unit_both;

	@Column(name = "onsite_capacity_expansion", nullable = true, length = 50)
	private String onsite_capacity_expansion;

	@Column(name = "onsite_capacity_unit_expansion", nullable = true, length = 255)
	private String onsite_capacity_unit_expansion;

	@Column(name = "onsite_capacity_expansion_both", nullable = true, length = 50)
	private String onsite_capacity_expansion_both;

	@Column(name = "onsite_capacity_unit_expansion_both", nullable = true, length = 255)
	private String onsite_capacity_unit_expansion_both;

	@Column(name = "etp_stp_technology", nullable = true, length = 150)
	private String etp_stp_technology;

	@Column(name = "etp_stp_technology_both", nullable = true, length = 150)
	private String etp_stp_technology_both;

	@Column(name = "etp_stp_technology_expansion", nullable = true, length = 150)
	private String etp_stp_technology_expansion;

	@Column(name = "etp_stp_technology_expansion_both", nullable = true, length = 150)
	private String etp_stp_technology_expansion_both;

	@Column(name = "onsite_treatment_plant_particulars", nullable = true, length = 25000)
	private String onsite_treatment_plant_particulars; // json for ETP/STP, new/expansion.

	private boolean treatment_plant_adequacy;

	@Column(name = "adequacy_details", nullable = true, length = 500)
	private String adequacy_details;

	@Column(name = "adequacy_reasons", nullable = true, length = 500)
	private String adequacy_reasons;

	@Column(name = "other_mitigation_measures", nullable = true, length = 500)
	private String other_mitigation_measures;

	private boolean dual_plumbing_system;

	@Column(name = "dual_plumbing_system_details", nullable = true, length = 500)
	private String dual_plumbing_system_details;

	@Column(name = "dual_plumbing_system_reasons", nullable = true, length = 500)
	private String dual_plumbing_system_reasons;

	private boolean proposed_mode_of_discharge;

	@Column(name = "mode_of_discharge", nullable = true, length = 200)
	private String mode_of_discharge;

	private boolean proposed_place_of_discharge;

	@Column(name = "place_of_discharge", nullable = true, length = 200)
	private String place_of_discharge;

	private boolean is_deleted;

	@Column(name = "other_mitigation_adequacy_details", length = 500, nullable = true)
	private String other_mitigation_adequacy_details;

	@Column(name = "mode_of_discharge_treated_effluent_other_information", length = 300, nullable = true)
	private String mode_of_discharge_treated_effluent_other_information;

	@Column(name = "air_pollutants_and_Mitigation", nullable = true)
	private Boolean air_pollutants_and_Mitigation;

	@Column(name = "place_of_discharge_other_information", length = 300, nullable = true)
	private String place_of_discharge_other_information;

	public EcPollutionDetails() {

		this.is_deleted = false;
	}

	public EcPartB getEcPartB() {
		return ecPartB;
	}

	public void setEcPartB(EcPartB ecPartB) {
		this.ecPartB = ecPartB;
	}

	public boolean isProbable_generation_of_noise() {
		return probable_generation_of_noise;
	}

	public void setProbable_generation_of_noise(boolean probable_generation_of_noise) {
		this.probable_generation_of_noise = probable_generation_of_noise;
	}

	public String getSource_of_noise() {
		return source_of_noise;
	}

	public void setSource_of_noise(String source_of_noise) {
		this.source_of_noise = source_of_noise;
	}

	public String getSource_of_vibration() {
		return source_of_vibration;
	}

	public void setSource_of_vibration(String source_of_vibration) {
		this.source_of_vibration = source_of_vibration;
	}

	public String getBlasting_details() {
		return blasting_details;
	}

	public void setBlasting_details(String blasting_details) {
		this.blasting_details = blasting_details;
	}

	public String getOther_noise_details() {
		return other_noise_details;
	}

	public void setOther_noise_details(String other_noise_details) {
		this.other_noise_details = other_noise_details;
	}

	public boolean isMitigation_measure_for_noise() {
		return mitigation_measure_for_noise;
	}

	public void setMitigation_measure_for_noise(boolean mitigation_measure_for_noise) {
		this.mitigation_measure_for_noise = mitigation_measure_for_noise;
	}

	public String getMitigation_measure_for_noise_control() {
		return mitigation_measure_for_noise_control;
	}

	public void setMitigation_measure_for_noise_control(String mitigation_measure_for_noise_control) {
		this.mitigation_measure_for_noise_control = mitigation_measure_for_noise_control;
	}

	public String getMitigation_measure_for_vibration_control() {
		return mitigation_measure_for_vibration_control;
	}

	public void setMitigation_measure_for_vibration_control(String mitigation_measure_for_vibration_control) {
		this.mitigation_measure_for_vibration_control = mitigation_measure_for_vibration_control;
	}

	public String getOther_mitigation_measure_details() {
		return other_mitigation_measure_details;
	}

	public void setOther_mitigation_measure_details(String other_mitigation_measure_details) {
		this.other_mitigation_measure_details = other_mitigation_measure_details;
	}

	public boolean isProbable_generation_of_heat() {
		return probable_generation_of_heat;
	}

	public void setProbable_generation_of_heat(boolean probable_generation_of_heat) {
		this.probable_generation_of_heat = probable_generation_of_heat;
	}

	public String getSource_of_light() {
		return source_of_light;
	}

	public void setSource_of_light(String source_of_light) {
		this.source_of_light = source_of_light;
	}

	public String getSource_of_heat() {
		return source_of_heat;
	}

	public void setSource_of_heat(String source_of_heat) {
		this.source_of_heat = source_of_heat;
	}

	public String getOther_light_heat_details() {
		return other_light_heat_details;
	}

	public void setOther_light_heat_details(String other_light_heat_details) {
		this.other_light_heat_details = other_light_heat_details;
	}

	public boolean isMitigation_measures_for_light_heat() {
		return mitigation_measures_for_light_heat;
	}

	public void setMitigation_measures_for_light_heat(boolean mitigation_measures_for_light_heat) {
		this.mitigation_measures_for_light_heat = mitigation_measures_for_light_heat;
	}

	public String getMitigation_measure_for_light_control() {
		return mitigation_measure_for_light_control;
	}

	public void setMitigation_measure_for_light_control(String mitigation_measure_for_light_control) {
		this.mitigation_measure_for_light_control = mitigation_measure_for_light_control;
	}

	public String getMitigation_measure_for_heat_control() {
		return mitigation_measure_for_heat_control;
	}

	public void setMitigation_measure_for_heat_control(String mitigation_measure_for_heat_control) {
		this.mitigation_measure_for_heat_control = mitigation_measure_for_heat_control;
	}

	public String getOther_mitigation_measure_for_light_heat() {
		return other_mitigation_measure_for_light_heat;
	}

	public void setOther_mitigation_measure_for_light_heat(String other_mitigation_measure_for_light_heat) {
		this.other_mitigation_measure_for_light_heat = other_mitigation_measure_for_light_heat;
	}

	public boolean isProbable_water_pollutants() {
		return probable_water_pollutants;
	}

	public void setProbable_water_pollutants(boolean probable_water_pollutants) {
		this.probable_water_pollutants = probable_water_pollutants;
	}

	public String getOrganic_pollutants() {
		return organic_pollutants;
	}

	public void setOrganic_pollutants(String organic_pollutants) {
		this.organic_pollutants = organic_pollutants;
	}

	public String getInorganic_pollutants() {
		return inorganic_pollutants;
	}

	public void setInorganic_pollutants(String inorganic_pollutants) {
		this.inorganic_pollutants = inorganic_pollutants;
	}

	public String getMicro_organism() {
		return micro_organism;
	}

	public void setMicro_organism(String micro_organism) {
		this.micro_organism = micro_organism;
	}

	public String getSediments() {
		return sediments;
	}

	public void setSediments(String sediments) {
		this.sediments = sediments;
	}

	public String getHeavy_metals() {
		return heavy_metals;
	}

	public void setHeavy_metals(String heavy_metals) {
		this.heavy_metals = heavy_metals;
	}

	public String getOthers() {
		return others;
	}

	public void setOthers(String others) {
		this.others = others;
	}

	public boolean isProbable_sources_of_water_pollutants() {
		return probable_sources_of_water_pollutants;
	}

	public void setProbable_sources_of_water_pollutants(boolean probable_sources_of_water_pollutants) {
		this.probable_sources_of_water_pollutants = probable_sources_of_water_pollutants;
	}

	public String getSources_of_water_pollution() {
		return sources_of_water_pollution;
	}

	public void setSources_of_water_pollution(String sources_of_water_pollution) {
		this.sources_of_water_pollution = sources_of_water_pollution;
	}

	public String getOther_information() {
		return other_information;
	}

	public void setOther_information(String other_information) {
		this.other_information = other_information;
	}

	public String getRecycle_of_waste_water() {
		return recycle_of_waste_water;
	}

	public void setRecycle_of_waste_water(String recycle_of_waste_water) {
		this.recycle_of_waste_water = recycle_of_waste_water;
	}

	public boolean isOffsite_treatment_plant() {
		return offsite_treatment_plant;
	}

	public void setOffsite_treatment_plant(boolean offsite_treatment_plant) {
		this.offsite_treatment_plant = offsite_treatment_plant;
	}

	public String getOffsite_treatment_plant_particulars() {
		return offsite_treatment_plant_particulars;
	}

	public void setOffsite_treatment_plant_particulars(String offsite_treatment_plant_particulars) {
		this.offsite_treatment_plant_particulars = offsite_treatment_plant_particulars;
	}

	public boolean isOnsite_treatment_plant() {
		return onsite_treatment_plant;
	}

	public void setOnsite_treatment_plant(boolean onsite_treatment_plant) {
		this.onsite_treatment_plant = onsite_treatment_plant;
	}

	public String getOnsite_treatment_plant_particulars() {
		return onsite_treatment_plant_particulars;
	}

	public void setOnsite_treatment_plant_particulars(String onsite_treatment_plant_particulars) {
		this.onsite_treatment_plant_particulars = onsite_treatment_plant_particulars;
	}

	public boolean isTreatment_plant_adequacy() {
		return treatment_plant_adequacy;
	}

	public void setTreatment_plant_adequacy(boolean treatment_plant_adequacy) {
		this.treatment_plant_adequacy = treatment_plant_adequacy;
	}

	public String getAdequacy_details() {
		return adequacy_details;
	}

	public void setAdequacy_details(String adequacy_details) {
		this.adequacy_details = adequacy_details;
	}

	public String getAdequacy_reasons() {
		return adequacy_reasons;
	}

	public void setAdequacy_reasons(String adequacy_reasons) {
		this.adequacy_reasons = adequacy_reasons;
	}

	public String getOther_mitigation_measures() {
		return other_mitigation_measures;
	}

	public void setOther_mitigation_measures(String other_mitigation_measures) {
		this.other_mitigation_measures = other_mitigation_measures;
	}

	public boolean isDual_plumbing_system() {
		return dual_plumbing_system;
	}

	public void setDual_plumbing_system(boolean dual_plumbing_system) {
		this.dual_plumbing_system = dual_plumbing_system;
	}

	public String getDual_plumbing_system_details() {
		return dual_plumbing_system_details;
	}

	public void setDual_plumbing_system_details(String dual_plumbing_system_details) {
		this.dual_plumbing_system_details = dual_plumbing_system_details;
	}

	public String getDual_plumbing_system_reasons() {
		return dual_plumbing_system_reasons;
	}

	public void setDual_plumbing_system_reasons(String dual_plumbing_system_reasons) {
		this.dual_plumbing_system_reasons = dual_plumbing_system_reasons;
	}

	public boolean isProposed_mode_of_discharge() {
		return proposed_mode_of_discharge;
	}

	public void setProposed_mode_of_discharge(boolean proposed_mode_of_discharge) {
		this.proposed_mode_of_discharge = proposed_mode_of_discharge;
	}

	public String getMode_of_discharge() {
		return mode_of_discharge;
	}

	public void setMode_of_discharge(String mode_of_discharge) {
		this.mode_of_discharge = mode_of_discharge;
	}

	public boolean isProposed_place_of_discharge() {
		return proposed_place_of_discharge;
	}

	public void setProposed_place_of_discharge(boolean proposed_place_of_discharge) {
		this.proposed_place_of_discharge = proposed_place_of_discharge;
	}

	public String getPlace_of_discharge() {
		return place_of_discharge;
	}

	public void setPlace_of_discharge(String place_of_discharge) {
		this.place_of_discharge = place_of_discharge;
	}

	public boolean isIs_deleted() {
		return is_deleted;
	}

	public void setIs_deleted(boolean is_deleted) {
		this.is_deleted = is_deleted;
	}

	public String getOther_mitigation_adequacy_details() {
		return other_mitigation_adequacy_details;
	}

	public void setOther_mitigation_adequacy_details(String other_mitigation_adequacy_details) {
		this.other_mitigation_adequacy_details = other_mitigation_adequacy_details;
	}

	public String getMode_of_discharge_treated_effluent_other_information() {
		return mode_of_discharge_treated_effluent_other_information;
	}

	public void setMode_of_discharge_treated_effluent_other_information(
			String mode_of_discharge_treated_effluent_other_information) {
		this.mode_of_discharge_treated_effluent_other_information = mode_of_discharge_treated_effluent_other_information;
	}

	public Boolean getAir_pollutants_and_Mitigation() {
		return air_pollutants_and_Mitigation;
	}

	public void setAir_pollutants_and_Mitigation(Boolean air_pollutants_and_Mitigation) {
		this.air_pollutants_and_Mitigation = air_pollutants_and_Mitigation;
	}

	public String getPlace_of_discharge_other_information() {
		return place_of_discharge_other_information;
	}

	public void setPlace_of_discharge_other_information(String place_of_discharge_other_information) {
		this.place_of_discharge_other_information = place_of_discharge_other_information;
	}

	public Boolean getOffsite_treatment_plant() {
		return offsite_treatment_plant;
	}

	public void setOffsite_treatment_plant(Boolean offsite_treatment_plant) {
		this.offsite_treatment_plant = offsite_treatment_plant;
	}

	public Boolean getOnsite_treatment_plant() {
		return onsite_treatment_plant;
	}

	public void setOnsite_treatment_plant(Boolean onsite_treatment_plant) {
		this.onsite_treatment_plant = onsite_treatment_plant;
	}

	public String getType_of_treatment_plant() {
		return type_of_treatment_plant;
	}

	public void setType_of_treatment_plant(String type_of_treatment_plant) {
		this.type_of_treatment_plant = type_of_treatment_plant;
	}

	public String getAgency_name() {
		return agency_name;
	}

	public void setAgency_name(String agency_name) {
		this.agency_name = agency_name;
	}

	public String getAgency_name_both() {
		return agency_name_both;
	}

	public void setAgency_name_both(String agency_name_both) {
		this.agency_name_both = agency_name_both;
	}

	public String getCapacity() {
		return capacity;
	}

	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}

	public String getCapacity_unit() {
		return capacity_unit;
	}

	public void setCapacity_unit(String capacity_unit) {
		this.capacity_unit = capacity_unit;
	}

	public String getCapacity_both() {
		return capacity_both;
	}

	public void setCapacity_both(String capacity_both) {
		this.capacity_both = capacity_both;
	}

	public String getCapacity_unit_both() {
		return capacity_unit_both;
	}

	public void setCapacity_unit_both(String capacity_unit_both) {
		this.capacity_unit_both = capacity_unit_both;
	}

	public String getTechnology() {
		return technology;
	}

	public void setTechnology(String technology) {
		this.technology = technology;
	}

	public String getTechnology_both() {
		return technology_both;
	}

	public void setTechnology_both(String technology_both) {
		this.technology_both = technology_both;
	}

	public String getPresent_loan_on_facility() {
		return present_loan_on_facility;
	}

	public void setPresent_loan_on_facility(String present_loan_on_facility) {
		this.present_loan_on_facility = present_loan_on_facility;
	}

	public String getPresent_loan_on_facility_both() {
		return present_loan_on_facility_both;
	}

	public void setPresent_loan_on_facility_both(String present_loan_on_facility_both) {
		this.present_loan_on_facility_both = present_loan_on_facility_both;
	}

	public String getDistance_from_site() {
		return distance_from_site;
	}

	public void setDistance_from_site(String distance_from_site) {
		this.distance_from_site = distance_from_site;
	}

	public String getDistance_from_site_both() {
		return distance_from_site_both;
	}

	public void setDistance_from_site_both(String distance_from_site_both) {
		this.distance_from_site_both = distance_from_site_both;
	}

	public String getDistance_from_site_unit() {
		return distance_from_site_unit;
	}

	public void setDistance_from_site_unit(String distance_from_site_unit) {
		this.distance_from_site_unit = distance_from_site_unit;
	}

	public String getDistance_from_site_unit_both() {
		return distance_from_site_unit_both;
	}

	public void setDistance_from_site_unit_both(String distance_from_site_unit_both) {
		this.distance_from_site_unit_both = distance_from_site_unit_both;
	}

	public String getReuse_of_treated_water() {
		return reuse_of_treated_water;
	}

	public void setReuse_of_treated_water(String reuse_of_treated_water) {
		this.reuse_of_treated_water = reuse_of_treated_water;
	}

	public String getReuse_of_treated_water_both() {
		return reuse_of_treated_water_both;
	}

	public void setReuse_of_treated_water_both(String reuse_of_treated_water_both) {
		this.reuse_of_treated_water_both = reuse_of_treated_water_both;
	}

	public String getMou_with_agency() {
		return mou_with_agency;
	}

	public void setMou_with_agency(String mou_with_agency) {
		this.mou_with_agency = mou_with_agency;
	}

	public String getMou_with_agency_both() {
		return mou_with_agency_both;
	}

	public void setMou_with_agency_both(String mou_with_agency_both) {
		this.mou_with_agency_both = mou_with_agency_both;
	}

	public Boolean getWaste_water_generated() {
		return waste_water_generated;
	}

	public void setWaste_water_generated(Boolean waste_water_generated) {
		this.waste_water_generated = waste_water_generated;
	}

	public String getWaste_water_generated_reason() {
		return waste_water_generated_reason;
	}

	public void setWaste_water_generated_reason(String waste_water_generated_reason) {
		this.waste_water_generated_reason = waste_water_generated_reason;
	}

	public Boolean getWaste_water_generated_expansion() {
		return waste_water_generated_expansion;
	}

	public void setWaste_water_generated_expansion(Boolean waste_water_generated_expansion) {
		this.waste_water_generated_expansion = waste_water_generated_expansion;
	}

	public String getWaste_water_generated_reason_expansion() {
		return waste_water_generated_reason_expansion;
	}

	public void setWaste_water_generated_reason_expansion(String waste_water_generated_reason_expansion) {
		this.waste_water_generated_reason_expansion = waste_water_generated_reason_expansion;
	}

	public String getType_of_treatment_plant_on_site() {
		return type_of_treatment_plant_on_site;
	}

	public void setType_of_treatment_plant_on_site(String type_of_treatment_plant_on_site) {
		this.type_of_treatment_plant_on_site = type_of_treatment_plant_on_site;
	}

	public String getType_of_treatment_plant_on_site_expansion() {
		return type_of_treatment_plant_on_site_expansion;
	}

	public void setType_of_treatment_plant_on_site_expansion(String type_of_treatment_plant_on_site_expansion) {
		this.type_of_treatment_plant_on_site_expansion = type_of_treatment_plant_on_site_expansion;
	}

	public String getOnsite_capacity() {
		return onsite_capacity;
	}

	public void setOnsite_capacity(String onsite_capacity) {
		this.onsite_capacity = onsite_capacity;
	}

	public String getOnsite_capacity_unit() {
		return onsite_capacity_unit;
	}

	public void setOnsite_capacity_unit(String onsite_capacity_unit) {
		this.onsite_capacity_unit = onsite_capacity_unit;
	}

	public String getOnsite_capacity_both() {
		return onsite_capacity_both;
	}

	public void setOnsite_capacity_both(String onsite_capacity_both) {
		this.onsite_capacity_both = onsite_capacity_both;
	}

	public String getOnsite_capacity_unit_both() {
		return onsite_capacity_unit_both;
	}

	public void setOnsite_capacity_unit_both(String onsite_capacity_unit_both) {
		this.onsite_capacity_unit_both = onsite_capacity_unit_both;
	}

	public String getOnsite_capacity_expansion() {
		return onsite_capacity_expansion;
	}

	public void setOnsite_capacity_expansion(String onsite_capacity_expansion) {
		this.onsite_capacity_expansion = onsite_capacity_expansion;
	}

	public String getOnsite_capacity_unit_expansion() {
		return onsite_capacity_unit_expansion;
	}

	public void setOnsite_capacity_unit_expansion(String onsite_capacity_unit_expansion) {
		this.onsite_capacity_unit_expansion = onsite_capacity_unit_expansion;
	}

	public String getOnsite_capacity_expansion_both() {
		return onsite_capacity_expansion_both;
	}

	public void setOnsite_capacity_expansion_both(String onsite_capacity_expansion_both) {
		this.onsite_capacity_expansion_both = onsite_capacity_expansion_both;
	}

	public String getOnsite_capacity_unit_expansion_both() {
		return onsite_capacity_unit_expansion_both;
	}

	public void setOnsite_capacity_unit_expansion_both(String onsite_capacity_unit_expansion_both) {
		this.onsite_capacity_unit_expansion_both = onsite_capacity_unit_expansion_both;
	}

	public String getEtp_stp_technology() {
		return etp_stp_technology;
	}

	public void setEtp_stp_technology(String etp_stp_technology) {
		this.etp_stp_technology = etp_stp_technology;
	}

	public String getEtp_stp_technology_both() {
		return etp_stp_technology_both;
	}

	public void setEtp_stp_technology_both(String etp_stp_technology_both) {
		this.etp_stp_technology_both = etp_stp_technology_both;
	}

	public String getEtp_stp_technology_expansion() {
		return etp_stp_technology_expansion;
	}

	public void setEtp_stp_technology_expansion(String etp_stp_technology_expansion) {
		this.etp_stp_technology_expansion = etp_stp_technology_expansion;
	}

	public String getEtp_stp_technology_expansion_both() {
		return etp_stp_technology_expansion_both;
	}

	public void setEtp_stp_technology_expansion_both(String etp_stp_technology_expansion_both) {
		this.etp_stp_technology_expansion_both = etp_stp_technology_expansion_both;
	}

}
