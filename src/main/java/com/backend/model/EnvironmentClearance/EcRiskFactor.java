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
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "ec_risk_factor", schema = "master")
public class EcRiskFactor extends Auditable<Integer> {

	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ec_partb_id", nullable = true)
	@JsonIgnore
	private EcPartB ecPartB;
	
	@Column(name = "hazardous_substances_explosion", nullable = true)
	private boolean hazardous_substances_explosion;
	
	@Column(name = "hazardous_substances_explosion_details", nullable = true, length = 1000)
	private String hazardous_substances_explosion_details;
	
	@Column(name = "any_other_cause", nullable = true)
	private boolean any_other_cause;
	
	@Column(name = "any_other_cause_details", nullable = true, length = 1000)
	private String any_other_cause_details;
	
	@Column(name = "project_affected_natural_disaster", nullable = true)
	private boolean project_affected_natural_disaster;
	
	@Column(name = "project_affected_natural_disaster_details", nullable = true, length = 1000)
	private String project_affected_natural_disaster_details;
	
	@Column(name = "occurences_change_disease", nullable = true)
	private boolean occurences_change_disease;
	
	@Column(name = "occurences_change_disease_details", nullable = true, length = 1000)
	private String occurences_change_disease_details;
	
	@Column(name = "project_adversely_wellbeing", nullable = true)
	private boolean project_adversely_wellbeing;
	
	@Column(name = "project_adversely_wellbeing_details", nullable = true, length = 1000)
	private String project_adversely_wellbeing_details;
	
	@Column(name = "vulnerable_group_people", nullable = true)
	private boolean vulnerable_group_people;
	
	@Column(name = "vulnerable_group_people_details", nullable = true, length = 1000)
	private String vulnerable_group_people_details;
	
	@Column(name = "risk_management_plan", nullable = true)
	private boolean risk_management_plan;
	
	@Column(name = "risk_management_plan_details", nullable = true, length = 1000)
	private String risk_management_plan_details;
	
	@Column(name = "impact_proposed_activity", nullable = true)
	private boolean impact_proposed_activity;
	
	@Column(name = "impact_existing_agriculture", nullable = true, length = 500)
	private String impact_existing_agriculture;
	
	@Column(name = "impact_existing_natural_resource", nullable = true, length = 500)
	private String impact_existing_natural_resource;
	
	@Column(name = "impact_existing_community_facility", nullable = true, length = 500)
	private String impact_existing_community_facility;
	
	@Column(name = "impact_existing_others", nullable = true, length = 500)
	private String impact_existing_others;
	
	@Column(name = "lead_development_facilities", nullable = true)
	private boolean lead_development_facilities;
	
	@Column(name = "lead_development_facilities_details", nullable = true, length = 500)
	private String lead_development_facilities_details;
	
	@Column(name = "lead_site_impact", nullable = true)
	private boolean lead_site_impact;
	
	@Column(name = "lead_site_impact_details", nullable = true, length = 500)
	private String lead_site_impact_details;
	
	@Column(name = "precedent_later_development", nullable = true)
	private boolean precedent_later_development;
	
	@Column(name = "precedent_later_development_details", nullable = true, length = 500)
	private String precedent_later_development_details;
	
	@Column(name = "have_cumulative_effect", nullable = true)
	private boolean have_cumulative_effect;
	
	@Column(name = "have_cumulative_effect_details", nullable = true, length = 500)
	private String have_cumulative_effect_details;
	
	@Column(name = "lead_growth_species", nullable = true)
	private boolean lead_growth_species;
	
	@Column(name = "lead_growth_species_details", nullable = true, length = 500)
	private String lead_growth_species_details;
	
	@Column(name = "any_threat_biodiversity", nullable = true)
	private boolean any_threat_biodiversity;
	
	@Column(name = "local_ecosystem_description", nullable = true, length = 500)
	private String local_ecosystem_description;
	
	@Column(name = "will_proposed_project", nullable = true)
	private boolean will_proposed_project;
	
	@Column(name = "will_proposed_project_details", nullable = true, length = 500)
	private String will_proposed_project_details;
	
	@Column(name = "any_impact_anthropological", nullable = true)
	private boolean any_impact_anthropological;
	
	@Column(name = "any_impact_anthropological_details", nullable = true, length = 500)
	private String any_impact_anthropological_details;
	
	@Column(name = "will_proposal_result", nullable = true)
	private boolean will_proposal_result;
	
	@Column(name = "will_proposal_result_details", nullable = true, length = 500)
	private String will_proposal_result_details;
	
	@Column(name = "will_proposal_cause", nullable = true)
	private boolean will_proposal_cause;
	
	@Column(name = "will_proposal_cause_details", nullable = true, length = 500)
	private String will_proposal_cause_details;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public EcPartB getEcPartB() {
		return ecPartB;
	}

	public void setEcPartB(EcPartB ecPartB) {
		this.ecPartB = ecPartB;
	}

	public boolean isHazardous_substances_explosion() {
		return hazardous_substances_explosion;
	}

	public void setHazardous_substances_explosion(boolean hazardous_substances_explosion) {
		this.hazardous_substances_explosion = hazardous_substances_explosion;
	}

	public String getHazardous_substances_explosion_details() {
		return hazardous_substances_explosion_details;
	}

	public void setHazardous_substances_explosion_details(String hazardous_substances_explosion_details) {
		this.hazardous_substances_explosion_details = hazardous_substances_explosion_details;
	}

	public boolean isAny_other_cause() {
		return any_other_cause;
	}

	public void setAny_other_cause(boolean any_other_cause) {
		this.any_other_cause = any_other_cause;
	}

	public String getAny_other_cause_details() {
		return any_other_cause_details;
	}

	public void setAny_other_cause_details(String any_other_cause_details) {
		this.any_other_cause_details = any_other_cause_details;
	}

	public boolean isProject_affected_natural_disaster() {
		return project_affected_natural_disaster;
	}

	public void setProject_affected_natural_disaster(boolean project_affected_natural_disaster) {
		this.project_affected_natural_disaster = project_affected_natural_disaster;
	}

	public String getProject_affected_natural_disaster_details() {
		return project_affected_natural_disaster_details;
	}

	public void setProject_affected_natural_disaster_details(String project_affected_natural_disaster_details) {
		this.project_affected_natural_disaster_details = project_affected_natural_disaster_details;
	}

	public boolean isOccurences_change_disease() {
		return occurences_change_disease;
	}

	public void setOccurences_change_disease(boolean occurences_change_disease) {
		this.occurences_change_disease = occurences_change_disease;
	}

	public String getOccurences_change_disease_details() {
		return occurences_change_disease_details;
	}

	public void setOccurences_change_disease_details(String occurences_change_disease_details) {
		this.occurences_change_disease_details = occurences_change_disease_details;
	}

	public boolean isProject_adversely_wellbeing() {
		return project_adversely_wellbeing;
	}

	public void setProject_adversely_wellbeing(boolean project_adversely_wellbeing) {
		this.project_adversely_wellbeing = project_adversely_wellbeing;
	}

	public String getProject_adversely_wellbeing_details() {
		return project_adversely_wellbeing_details;
	}

	public void setProject_adversely_wellbeing_details(String project_adversely_wellbeing_details) {
		this.project_adversely_wellbeing_details = project_adversely_wellbeing_details;
	}

	public boolean isVulnerable_group_people() {
		return vulnerable_group_people;
	}

	public void setVulnerable_group_people(boolean vulnerable_group_people) {
		this.vulnerable_group_people = vulnerable_group_people;
	}

	public String getVulnerable_group_people_details() {
		return vulnerable_group_people_details;
	}

	public void setVulnerable_group_people_details(String vulnerable_group_people_details) {
		this.vulnerable_group_people_details = vulnerable_group_people_details;
	}

	public boolean isRisk_management_plan() {
		return risk_management_plan;
	}

	public void setRisk_management_plan(boolean risk_management_plan) {
		this.risk_management_plan = risk_management_plan;
	}

	public String getRisk_management_plan_details() {
		return risk_management_plan_details;
	}

	public void setRisk_management_plan_details(String risk_management_plan_details) {
		this.risk_management_plan_details = risk_management_plan_details;
	}

	public boolean isImpact_proposed_activity() {
		return impact_proposed_activity;
	}

	public void setImpact_proposed_activity(boolean impact_proposed_activity) {
		this.impact_proposed_activity = impact_proposed_activity;
	}

	public String getImpact_existing_agriculture() {
		return impact_existing_agriculture;
	}

	public void setImpact_existing_agriculture(String impact_existing_agriculture) {
		this.impact_existing_agriculture = impact_existing_agriculture;
	}

	public String getImpact_existing_natural_resource() {
		return impact_existing_natural_resource;
	}

	public void setImpact_existing_natural_resource(String impact_existing_natural_resource) {
		this.impact_existing_natural_resource = impact_existing_natural_resource;
	}

	public String getImpact_existing_community_facility() {
		return impact_existing_community_facility;
	}

	public void setImpact_existing_community_facility(String impact_existing_community_facility) {
		this.impact_existing_community_facility = impact_existing_community_facility;
	}

	public String getImpact_existing_others() {
		return impact_existing_others;
	}

	public void setImpact_existing_others(String impact_existing_others) {
		this.impact_existing_others = impact_existing_others;
	}

	public boolean isLead_development_facilities() {
		return lead_development_facilities;
	}

	public void setLead_development_facilities(boolean lead_development_facilities) {
		this.lead_development_facilities = lead_development_facilities;
	}

	public String getLead_development_facilities_details() {
		return lead_development_facilities_details;
	}

	public void setLead_development_facilities_details(String lead_development_facilities_details) {
		this.lead_development_facilities_details = lead_development_facilities_details;
	}

	public boolean isLead_site_impact() {
		return lead_site_impact;
	}

	public void setLead_site_impact(boolean lead_site_impact) {
		this.lead_site_impact = lead_site_impact;
	}

	public String getLead_site_impact_details() {
		return lead_site_impact_details;
	}

	public void setLead_site_impact_details(String lead_site_impact_details) {
		this.lead_site_impact_details = lead_site_impact_details;
	}

	public boolean isPrecedent_later_development() {
		return precedent_later_development;
	}

	public void setPrecedent_later_development(boolean precedent_later_development) {
		this.precedent_later_development = precedent_later_development;
	}

	public String getPrecedent_later_development_details() {
		return precedent_later_development_details;
	}

	public void setPrecedent_later_development_details(String precedent_later_development_details) {
		this.precedent_later_development_details = precedent_later_development_details;
	}

	public boolean isHave_cumulative_effect() {
		return have_cumulative_effect;
	}

	public void setHave_cumulative_effect(boolean have_cumulative_effect) {
		this.have_cumulative_effect = have_cumulative_effect;
	}

	public String getHave_cumulative_effect_details() {
		return have_cumulative_effect_details;
	}

	public void setHave_cumulative_effect_details(String have_cumulative_effect_details) {
		this.have_cumulative_effect_details = have_cumulative_effect_details;
	}

	public boolean isLead_growth_species() {
		return lead_growth_species;
	}

	public void setLead_growth_species(boolean lead_growth_species) {
		this.lead_growth_species = lead_growth_species;
	}

	public String getLead_growth_species_details() {
		return lead_growth_species_details;
	}

	public void setLead_growth_species_details(String lead_growth_species_details) {
		this.lead_growth_species_details = lead_growth_species_details;
	}

	public boolean isAny_threat_biodiversity() {
		return any_threat_biodiversity;
	}

	public void setAny_threat_biodiversity(boolean any_threat_biodiversity) {
		this.any_threat_biodiversity = any_threat_biodiversity;
	}

	public String getLocal_ecosystem_description() {
		return local_ecosystem_description;
	}

	public void setLocal_ecosystem_description(String local_ecosystem_description) {
		this.local_ecosystem_description = local_ecosystem_description;
	}

	public boolean isWill_proposed_project() {
		return will_proposed_project;
	}

	public void setWill_proposed_project(boolean will_proposed_project) {
		this.will_proposed_project = will_proposed_project;
	}

	public String getWill_proposed_project_details() {
		return will_proposed_project_details;
	}

	public void setWill_proposed_project_details(String will_proposed_project_details) {
		this.will_proposed_project_details = will_proposed_project_details;
	}

	public boolean isAny_impact_anthropological() {
		return any_impact_anthropological;
	}

	public void setAny_impact_anthropological(boolean any_impact_anthropological) {
		this.any_impact_anthropological = any_impact_anthropological;
	}

	public String getAny_impact_anthropological_details() {
		return any_impact_anthropological_details;
	}

	public void setAny_impact_anthropological_details(String any_impact_anthropological_details) {
		this.any_impact_anthropological_details = any_impact_anthropological_details;
	}

	public boolean isWill_proposal_result() {
		return will_proposal_result;
	}

	public void setWill_proposal_result(boolean will_proposal_result) {
		this.will_proposal_result = will_proposal_result;
	}

	public String getWill_proposal_result_details() {
		return will_proposal_result_details;
	}

	public void setWill_proposal_result_details(String will_proposal_result_details) {
		this.will_proposal_result_details = will_proposal_result_details;
	}

	public boolean isWill_proposal_cause() {
		return will_proposal_cause;
	}

	public void setWill_proposal_cause(boolean will_proposal_cause) {
		this.will_proposal_cause = will_proposal_cause;
	}

	public String getWill_proposal_cause_details() {
		return will_proposal_cause_details;
	}

	public void setWill_proposal_cause_details(String will_proposal_cause_details) {
		this.will_proposal_cause_details = will_proposal_cause_details;
	}
	
	
}
