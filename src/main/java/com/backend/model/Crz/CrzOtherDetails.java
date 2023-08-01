package com.backend.model.Crz;

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
import com.backend.model.CommonFormDetail;
import com.backend.model.DocumentDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "crz_other_details", schema = "master")
public class CrzOtherDetails extends Auditable<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "crz_id", nullable = false)
	@JsonIgnore
	private CrzBasicDetails crzBasicDetails;

	@Column(name = "eia_studies_carried_out", nullable = true)
	private String eia_studies_carried_out;

	@Column(name = "eia_marine_studies_carried_out", nullable = true)
	private String eia_marine_studies_carried_out;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "upload_eias_marine", nullable = true)
	private DocumentDetails upload_eias_marine;
	
	@Column(name = "period_of_study_from_marine_eia", nullable = true)
	private String period_of_study_from_marine_eia;
	
	@Column(name = "period_of_study_to_marine_eia", nullable = true)
	private String period_of_study_to_marine_eia;
	
	@Column(name = "give_reason_marine_not_carried_out", nullable = true, length = 100)
	private String give_reason_marine_not_carried_out;
	
	@Column(name = "give_reason_not_applicable", nullable = true, length = 100)
	private String give_reason_not_applicable;
	
	@Column(name = "summary_details_of_eia_marine", nullable = true, length = 100)
	private String summary_details_of_eia_marine;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "upload_eias", nullable = true)
	private DocumentDetails upload_eias;

	@Column(name = "period_of_study_from_eia", nullable = true)
	private String period_of_study_from_eia;

	@Column(name = "period_of_study_to_eia", nullable = true)
	private String period_of_study_to_eia;

	@Column(name = "give_reason_not_carried_out", nullable = true, length = 100)
	private String give_reason_not_carried_out;
	
	@Column(name = "give_reason_marine_not_applicable", nullable = true, length = 100)
	private String give_reason_marine_not_applicable;

	@Column(name = "summary_details_of_eia", nullable = true, length = 100)
	private String summary_details_of_eia;

	@Column(name = "disaster_management_plan", nullable = true)
	private boolean disaster_management_plan;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "upload_disaster_management_plan_id", nullable = true)
	private DocumentDetails upload_disaster_management_plan;
	
	@Column(name = "upload_reason_thereof", nullable = true, length = 100)
	private String upload_reason_thereof;
	
	@Column(name = "discharge_liquid_effluents", nullable = true)
	private boolean discharge_liquid_effluents;
	
	@Column(name = "capacity_of_stp", nullable = true)
	private Double capacity_of_stp;

	@Column(name = "quantity_of_effluent_generated", nullable = true)
	private Double quantity_of_effluent_generated;

	@Column(name = "quantity_of_effluent_treated", nullable = true)
	private Double quantity_of_effluent_treated;

	@Column(name = "method_of_treatment_and_disposal", nullable = true, length = 100)
	private String method_of_treatment_and_disposal;
	
	@Column(name = "discharge_solid_waste", nullable = true)
	private boolean discharge_solid_waste;

	@Column(nullable = true)
	private String type_of_waste;

	@Column(name = "quantity_of_solid_waste_generated", nullable = true)
	private Double quantity_of_solid_waste_generated;

	@Column(nullable = true)
	private String mode_of_disposal;

	@Column(name = "source", nullable = true)
	private String source;

	@Column(name = "quantity", nullable = true)
	private Double quantity;

	@Column(name = "project_water_treatment", nullable = true)
	private boolean project_water_treatment;
	
	@Column(name = "quantity_of_waste_water", nullable = true)
	private Double quantity_of_waste_water;

	@Column(name = "treatment_capacity", nullable = true)
	private Double treatment_capacity;

	@Column(name = "quantity_of_treated_water", nullable = true)
	private Double quantity_of_treated_water;
	
	@Column(name = "mode_of_disposal_of_water", nullable = true)
	private String mode_of_disposal_of_water;

	@Column(name = "no_of_storage", nullable = true)
	private Integer no_of_storage;
	
	@Column(name = "capacity_storage", nullable = true)
	private Double capacity_storage;

	@Column(name = "number_of_recharge_pits", nullable = true)
	private Integer number_of_recharge_pits;
	
	@Column(name = "capacity_recharge", nullable = true)
	private Double capacity_recharge;
	
	@Column(name = "ground_water_withdrawal", nullable = true)
	private String ground_water_withdrawal;
	
	@Column(name = "ground_water_intersection", nullable = true)
	private String ground_water_intersection;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "copy_of_central_ground_water", nullable = true)
	private DocumentDetails copy_of_central_ground_water;

	@Column(name = "measures_proposed", nullable = true, length = 50)
	private String measures_proposed;

	@Column(name = "energy_saving_percentage", nullable = true)
	private Double energy_saving_percentage;

	@Column(name = "energy_saving_quantity", nullable = true)
	private Double energy_saving_quantity;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "copy_of_sczma", nullable = true)
	private DocumentDetails copy_of_sczma;

	@Column(name = "state_the_conditions_imposed", nullable = true, length = 100)
	private String state_the_conditions_imposed;

	@Column(name = "proposal_attracts_eia_notification", nullable = true)
	private boolean proposal_attracts_eia_notification;

	@Column(name = "status_of_proposal_for_ec", nullable = true)
	private String status_of_proposal_for_ec;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "social_and_environmental_issues_id", nullable = true)
	private DocumentDetails social_and_environmental_issues;

	@Column(name = "benefits_of_the_project", nullable = true, length = 100)
	private String benefits_of_the_project;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "additional_information_if_any_id", nullable = true)
	private DocumentDetails additional_information_if_any;

	@Column(name = "additional_information_of_document", nullable = true, length = 100)
	private String additional_information_of_document;
	
	@Column(name = "please_specify_water", nullable = true, length = 500)
	private String please_specify_water;
	
	@Column(name = "please_specify_waste", nullable = true, length = 500)
	private String please_specify_waste;
	
	@Column(name = "please_specify_source", nullable = true, length = 500)
	private String please_specify_source;
	
	@Column(name = "please_specify_disposal", nullable = true, length = 500)
	private String please_specify_disposal;
	
	@Column(name = "project_rainwater_harvest", nullable = true)
	private Boolean project_rainwater_harvest;
	
	@Column(nullable = true)
	private Double quantity_of_effluent_disposed;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "action_plan_consitions_SCZMA_id", nullable = true)
	private DocumentDetails action_plan_consitions_SCZMA;
	
	@Column(nullable = true,length=500)
	private String social_environmental_issues;
	
	@Column(name = "is_sczma_recommendation_available", nullable = true)
	private Boolean is_sczma_recommendation_available;
}
