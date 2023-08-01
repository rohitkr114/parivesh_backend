package com.backend.model.WildLifeClearance;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Where;

import com.backend.audit.Auditable;
import com.backend.model.Clearence;
import com.backend.model.CommonFormDetail;
import com.backend.model.DocumentDetails;
import com.backend.model.ForestClearanceAirportProposal;
import com.backend.model.ForestClearanceCroppingPattern;
import com.backend.model.ForestClearanceIrrigationProjectCapacityVillages;
import com.backend.model.ForestClearanceMiningProposals;
import com.backend.model.ForestClearanceProposedDiversions;
import com.backend.model.ForestClearanceRiverValleyProject;
import com.backend.model.ForestClearanceSubmergedArea;
import com.backend.model.PriorApprovals;
import com.backend.model.ProponentApplications;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Data;

//@Data
@Entity
@Table(name = "wl_clearance", schema = "master")
public class WildLifeClearance extends Auditable<Integer> implements Clearence {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	//added on 15-03-2023
	@Column(nullable = true, length=100)
	private String division_name;
	
	@Column(nullable = true, length = 100)
	private String unique_clearance_no;
	
	@Column(nullable = true, length = 100)
	private String green_clearance_no;

	@Column(nullable = true, length = 100)
	private String category_of_project;
	//added on 17-01-2023
	@Column(nullable = true, length = 100)
	private String type_of_category_project;
	//added on 17-01-2023
	@Column(nullable = true, length = 100)
	private String sub_category_of_project;

	@Column(nullable = true)
	private String project_category_id;
	
	@Column(nullable = true, length = 500)
	private String project_category_others;

	@Column(name = "state")
	private Integer state;

	@Column(nullable = true, length = 100)
	private String category_of_area;
		

	@Column(nullable = true)
	private boolean is_any_prior_approval;

	@Column(name = "proposal_no", unique = true, length = 50, nullable = false)
	private String proposal_no;

	@Transient
	@JsonProperty(access = Access.READ_ONLY)
	private ProponentApplications proponentApplications;

	@OneToMany(mappedBy = "wildLifeClearance", cascade = CascadeType.ALL)
	@Where(clause = "isDelete='false'")
	List<PriorApprovals> priorApprovals = new ArrayList<>();

	@OneToOne(mappedBy = "wildLifeClearance")
	WLProposedLand wlProposedLand;

	@OneToOne(mappedBy = "wildLifeClearance")
	WLOtherDetails wlOtherDetails;

	@OneToOne(mappedBy = "wildLifeClearance")
	WLEnclosures wlEnclosures;

	@OneToOne(mappedBy = "wildLifeClearance")
	WLUndertaking wlUndertaking;

	@OneToOne(targetEntity = CommonFormDetail.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "caf_id", nullable = true)
	@JsonIgnore
	private CommonFormDetail commonFormDetail;

	@OneToMany(mappedBy = "wlclearance", cascade = CascadeType.ALL)
	@Where(clause = "is_delete='false'")
	List<WLDivisionLandDetail> wldivisionLandDetails = new ArrayList<>();

	@OneToMany(mappedBy = "wlclearance", cascade = CascadeType.ALL)
	@Where(clause = "is_delete='false'")
	List<WLComponentWiseDetails> wlComponentWiseDetails = new ArrayList<>();

	@OneToMany(mappedBy = "wildLifeClearance", cascade = CascadeType.ALL)
	@Where(clause = "isDelete='false'")
	List<ForestClearanceProposedDiversions> forestClearanceProposedDiversions = new ArrayList<>();
	
	//added on 18042023
	@OneToMany(mappedBy = "wildLifeClearance", cascade = CascadeType.ALL)
	@Where(clause = "isDelete='false'")
	List<WildLifeClearanceProposedDiversions> wildLifeClearanceProposedDiversions = new ArrayList<>();
	//added on 18042023

	@OneToMany(mappedBy = "wildLifeClearance")
	List<ForestClearanceAirportProposal> wlcAirportProposals = new ArrayList<>();

	@OneToMany(mappedBy = "wildLifeClearance", cascade = CascadeType.ALL)
	@Where(clause = "is_deleted='false'")
	List<ForestClearanceCroppingPattern> wlcirrigationFcCroppingPattern = new ArrayList<>();

	@OneToMany(mappedBy = "wildLifeClearance", cascade = CascadeType.ALL)
	@Where(clause = "is_deleted='false'")
	List<ForestClearanceSubmergedArea> wlcSubmergedArea = new ArrayList<>();

	@OneToOne(mappedBy = "wildLifeClearance")
	private ForestClearanceRiverValleyProject wlcRiverValleyProject;

	@OneToMany(mappedBy = "wildLifeClearance", cascade = CascadeType.ALL)
	@Where(clause = "is_deleted='false'")
	List<ForestClearanceIrrigationProjectCapacityVillages> wlcIrrigationProjectCapacityVillage = new ArrayList<>();

	/*
	 * @OneToMany(mappedBy = "wildLifeClearance", cascade = CascadeType.ALL)
	 * 
	 * @Where(clause = "is_deleted='false'") List<AdditionalInformation>
	 * wildlifeAdditionalInformations= new ArrayList<>();
	 */

	@OneToOne(mappedBy = "wildLifeClearance")
	private ForestClearanceMiningProposals wlcMiningProposals;

//	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//	@JoinColumn(name = "locating_project_justification", nullable = true)
//	private DocumentDetails locating_project_justification;
//	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "locating_project_justification_id", nullable = true)
	private DocumentDetails locating_project_justification;
	

	// added on 25112022
	@Column(nullable = true)
	private Boolean for_investigation_survey_new;

	@Column(nullable = true, length = 100)
	private String investigation_purpose;

	
	@Column(name = "physical_disturbance_within_protected_area", columnDefinition="boolean default false", nullable = false)
	//@Column(nullable = true)
	private Boolean physical_disturbance_within_protected_area=false;

	@Column(nullable = true, length = 100)
	private String extent_of_physical_disturbance_new;
	
	//added on 17-01-2023 
	@Column(nullable = true, length = 100)
	private String physical_disturbance_others;

	@Column(nullable = true)
	private Double no_of_trees_proposed_to_be_cut_new;

	@Column(nullable = true)
	private Integer treecutting_timerequired_new;

	@Column
	private Double earthwork_new;

	@Column
	private Integer earthwork_timerequired_new;
            
	@Column(nullable = true, length=100)
	private String physical_disturbance_type;
	
	// Changes done by Divyanee for adding a new field on 18052023 [Start]
	@Column
	private Integer physical_disturbance_others_timerequired;
	// Changes done by Divyanee for adding a new field on 18052023 [End]
	
	@Column(nullable = true)
	private Boolean is_active;

	@Column(nullable = true)
	private Boolean is_delete;
	
	//added on 30112022
	@Column(nullable = true, length=100)
	private String division;
	
	
	//added on 13032023
	@Column(nullable = false,columnDefinition = "boolean default false")
	private Boolean sub_category_right_way=false;
	//end added on 13032023
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "location_and_map_new_id", nullable = true)
	private DocumentDetails location_and_map_new;
	

	public boolean isIs_any_prior_approval() {
		return is_any_prior_approval;
	}

	public void setIs_any_prior_approval(boolean is_any_prior_approval) {
		this.is_any_prior_approval = is_any_prior_approval;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUnique_clearance_no() {
		return unique_clearance_no;
	}

	public void setUnique_clearance_no(String unique_clearance_no) {
		this.unique_clearance_no = unique_clearance_no;
	}

	public String getGreen_clearance_no() {
		return green_clearance_no;
	}

	public void setGreen_clearance_no(String green_clearance_no) {
		this.green_clearance_no = green_clearance_no;
	}

	public String getCategory_of_project() {
		return category_of_project;
	}

	public void setCategory_of_project(String category_of_project) {
		this.category_of_project = category_of_project;
	}

	public String getType_of_category_project() {
		return type_of_category_project;
	}

	public void setType_of_category_project(String type_of_category_project) {
		this.type_of_category_project = type_of_category_project;
	}

	public String getSub_category_of_project() {
		return sub_category_of_project;
	}

	public void setSub_category_of_project(String sub_category_of_project) {
		this.sub_category_of_project = sub_category_of_project;
	}

	public String getProject_category_id() {
		return project_category_id;
	}

	public void setProject_category_id(String project_category_id) {
		this.project_category_id = project_category_id;
	}

	public String getProject_category_others() {
		return project_category_others;
	}

	public void setProject_category_others(String project_category_others) {
		this.project_category_others = project_category_others;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getCategory_of_area() {
		return category_of_area;
	}

	public void setCategory_of_area(String category_of_area) {
		this.category_of_area = category_of_area;
	}

	public String getProposal_no() {
		return proposal_no;
	}

	public void setProposal_no(String proposal_no) {
		this.proposal_no = proposal_no;
	}

	public ProponentApplications getProponentApplications() {
		return proponentApplications;
	}

	public void setProponentApplications(ProponentApplications proponentApplications) {
		this.proponentApplications = proponentApplications;
	}

	public List<PriorApprovals> getPriorApprovals() {
		return priorApprovals;
	}

	public void setPriorApprovals(List<PriorApprovals> priorApprovals) {
		this.priorApprovals = priorApprovals;
	}

	public WLProposedLand getWlProposedLand() {
		return wlProposedLand;
	}

	public void setWlProposedLand(WLProposedLand wlProposedLand) {
		this.wlProposedLand = wlProposedLand;
	}

	public WLOtherDetails getWlOtherDetails() {
		return wlOtherDetails;
	}

	public void setWlOtherDetails(WLOtherDetails wlOtherDetails) {
		this.wlOtherDetails = wlOtherDetails;
	}

	public WLEnclosures getWlEnclosures() {
		return wlEnclosures;
	}

	public void setWlEnclosures(WLEnclosures wlEnclosures) {
		this.wlEnclosures = wlEnclosures;
	}

	public WLUndertaking getWlUndertaking() {
		return wlUndertaking;
	}

	public void setWlUndertaking(WLUndertaking wlUndertaking) {
		this.wlUndertaking = wlUndertaking;
	}

	public CommonFormDetail getCommonFormDetail() {
		return commonFormDetail;
	}

	public void setCommonFormDetail(CommonFormDetail commonFormDetail) {
		this.commonFormDetail = commonFormDetail;
	}

	public List<WLDivisionLandDetail> getWldivisionLandDetails() {
		return wldivisionLandDetails;
	}

	public void setWldivisionLandDetails(List<WLDivisionLandDetail> wldivisionLandDetails) {
		this.wldivisionLandDetails = wldivisionLandDetails;
	}

	public List<WLComponentWiseDetails> getWlComponentWiseDetails() {
		return wlComponentWiseDetails;
	}

	public void setWlComponentWiseDetails(List<WLComponentWiseDetails> wlComponentWiseDetails) {
		this.wlComponentWiseDetails = wlComponentWiseDetails;
	}

	public List<ForestClearanceProposedDiversions> getForestClearanceProposedDiversions() {
		return forestClearanceProposedDiversions;
	}

	public void setForestClearanceProposedDiversions(
			List<ForestClearanceProposedDiversions> forestClearanceProposedDiversions) {
		this.forestClearanceProposedDiversions = forestClearanceProposedDiversions;
	}

	public List<ForestClearanceAirportProposal> getWlcAirportProposals() {
		return wlcAirportProposals;
	}

	public void setWlcAirportProposals(List<ForestClearanceAirportProposal> wlcAirportProposals) {
		this.wlcAirportProposals = wlcAirportProposals;
	}

	public List<ForestClearanceCroppingPattern> getWlcirrigationFcCroppingPattern() {
		return wlcirrigationFcCroppingPattern;
	}

	public void setWlcirrigationFcCroppingPattern(List<ForestClearanceCroppingPattern> wlcirrigationFcCroppingPattern) {
		this.wlcirrigationFcCroppingPattern = wlcirrigationFcCroppingPattern;
	}

	public List<ForestClearanceSubmergedArea> getWlcSubmergedArea() {
		return wlcSubmergedArea;
	}

	public void setWlcSubmergedArea(List<ForestClearanceSubmergedArea> wlcSubmergedArea) {
		this.wlcSubmergedArea = wlcSubmergedArea;
	}

	public ForestClearanceRiverValleyProject getWlcRiverValleyProject() {
		return wlcRiverValleyProject;
	}

	public void setWlcRiverValleyProject(ForestClearanceRiverValleyProject wlcRiverValleyProject) {
		this.wlcRiverValleyProject = wlcRiverValleyProject;
	}

	public List<ForestClearanceIrrigationProjectCapacityVillages> getWlcIrrigationProjectCapacityVillage() {
		return wlcIrrigationProjectCapacityVillage;
	}

	public void setWlcIrrigationProjectCapacityVillage(
			List<ForestClearanceIrrigationProjectCapacityVillages> wlcIrrigationProjectCapacityVillage) {
		this.wlcIrrigationProjectCapacityVillage = wlcIrrigationProjectCapacityVillage;
	}

	public ForestClearanceMiningProposals getWlcMiningProposals() {
		return wlcMiningProposals;
	}

	public void setWlcMiningProposals(ForestClearanceMiningProposals wlcMiningProposals) {
		this.wlcMiningProposals = wlcMiningProposals;
	}

	public DocumentDetails getLocating_project_justification() {
		return locating_project_justification;
	}

	public void setLocating_project_justification(DocumentDetails locating_project_justification) {
		this.locating_project_justification = locating_project_justification;
	}

	public Boolean getFor_investigation_survey_new() {
		return for_investigation_survey_new;
	}

	public void setFor_investigation_survey_new(Boolean for_investigation_survey_new) {
		this.for_investigation_survey_new = for_investigation_survey_new;
	}

	public String getInvestigation_purpose() {
		return investigation_purpose;
	}

	public void setInvestigation_purpose(String investigation_purpose) {
		this.investigation_purpose = investigation_purpose;
	}

	public Boolean getPhysical_disturbance_within_protected_area() {
		return physical_disturbance_within_protected_area;
	}

	public void setPhysical_disturbance_within_protected_area(Boolean physical_disturbance_within_protected_area) {
		this.physical_disturbance_within_protected_area = physical_disturbance_within_protected_area;
	}

	public String getExtent_of_physical_disturbance_new() {
		return extent_of_physical_disturbance_new;
	}

	public void setExtent_of_physical_disturbance_new(String extent_of_physical_disturbance_new) {
		this.extent_of_physical_disturbance_new = extent_of_physical_disturbance_new;
	}

	public String getPhysical_disturbance_others() {
		return physical_disturbance_others;
	}

	public void setPhysical_disturbance_others(String physical_disturbance_others) {
		this.physical_disturbance_others = physical_disturbance_others;
	}

	public Double getNo_of_trees_proposed_to_be_cut_new() {
		return no_of_trees_proposed_to_be_cut_new;
	}

	public void setNo_of_trees_proposed_to_be_cut_new(Double no_of_trees_proposed_to_be_cut_new) {
		this.no_of_trees_proposed_to_be_cut_new = no_of_trees_proposed_to_be_cut_new;
	}

	public Integer getTreecutting_timerequired_new() {
		return treecutting_timerequired_new;
	}

	public void setTreecutting_timerequired_new(Integer treecutting_timerequired_new) {
		this.treecutting_timerequired_new = treecutting_timerequired_new;
	}

	public Double getEarthwork_new() {
		return earthwork_new;
	}

	public void setEarthwork_new(Double earthwork_new) {
		this.earthwork_new = earthwork_new;
	}

	public Integer getEarthwork_timerequired_new() {
		return earthwork_timerequired_new;
	}

	public void setEarthwork_timerequired_new(Integer earthwork_timerequired_new) {
		this.earthwork_timerequired_new = earthwork_timerequired_new;
	}

	public String getPhysical_disturbance_type() {
		return physical_disturbance_type;
	}

	public void setPhysical_disturbance_type(String physical_disturbance_type) {
		this.physical_disturbance_type = physical_disturbance_type;
	}

	public Boolean getIs_active() {
		return is_active;
	}

	public void setIs_active(Boolean is_active) {
		this.is_active = is_active;
	}

	public Boolean getIs_delete() {
		return is_delete;
	}

	public void setIs_delete(Boolean is_delete) {
		this.is_delete = is_delete;
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public DocumentDetails getLocation_and_map_new() {
		return location_and_map_new;
	}

	public void setLocation_and_map_new(DocumentDetails location_and_map_new) {
		this.location_and_map_new = location_and_map_new;
	}

	public String getDivision_name() {
		return division_name;
	}

	public void setDivision_name(String division_name) {
		this.division_name = division_name;
	}

	public Boolean getSub_category_right_way() {
		return sub_category_right_way;
	}

	public void setSub_category_right_way(Boolean sub_category_right_way) {
		this.sub_category_right_way = sub_category_right_way;
	}

	public List<WildLifeClearanceProposedDiversions> getWildLifeClearanceProposedDiversions() {
		return wildLifeClearanceProposedDiversions;
	}

	public void setWildLifeClearanceProposedDiversions(
			List<WildLifeClearanceProposedDiversions> wildLifeClearanceProposedDiversions) {
		this.wildLifeClearanceProposedDiversions = wildLifeClearanceProposedDiversions;
	}

	public Integer getPhysical_disturbance_others_timerequired() {
		return physical_disturbance_others_timerequired;
	}

	public void setPhysical_disturbance_others_timerequired(Integer physical_disturbance_others_timerequired) {
		this.physical_disturbance_others_timerequired = physical_disturbance_others_timerequired;
	}
	
	

	
}
