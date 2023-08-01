package com.backend.model.EnvironmentClearance;

import javax.persistence.Id;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Where;

import com.backend.audit.Auditable;
import com.backend.model.CommonFormDetail;
import com.backend.model.EnvironmentClearence;
import com.backend.model.ProponentApplications;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "ec_part_B", schema = "master")
public class EcPartB extends Auditable<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer Id;

	@Column(name = "need_of_project", nullable = true, length = 500)
	private String need_of_project;

	@Column(name = "demand_supply_gap", nullable = true, length = 300)
	private String demand_supply_gap;

	@Column(name = "social_infra_available", nullable = true, length = 500)
	private String social_infra_available;

	@Column(name = "social_infra_proposed", nullable = true, length = 500)
	private String social_infra_proposed;

	@Column(name = "connectivity_railway", nullable = true, length = 300)
	private String connectivity_railway;

	@Column(name = "connectivity_railway_distance", nullable = true, length = 50)
	private String connectivity_railway_distance;

	@Column(name = "connectivity_airport", nullable = true, length = 300)
	private String connectivity_airport;

	@Column(name = "connectivity_airport_distance", nullable = true, length = 50)
	private String connectivity_airport_distance;

	@Column(name = "nearest_town_city_details", nullable = true, length = 150)
	private String nearest_town_city_details;

	@Column(name = "nearest_town_city_details_distance", nullable = true, length = 100)
	private String nearest_town_city_details_distance;

	@Column(name = "soil_classification", nullable = true, length = 500)
	private String soil_classification;

	@Column(name = "distance_HFL_river", nullable = true)
	private Double distance_HFL_river;

	@Column(name = "project_social_benefit", nullable = true, length = 500)
	private String project_social_benefit;

	@Column(name = "project_financial_benefit", nullable = true, length = 500)
	private String project_financial_benefit;

	@Column(name = "date_of_start_construction", nullable = true)
	private Date date_of_start_construction;

	@Column(name = "date_of_completion_construction", nullable = true)
	private Date date_of_completion_construction;

	@Column(nullable = true)
	private Boolean is_amendment;
	
	@Column(nullable = true)
	private Integer parent_id;
	
	@Column(name = "is_active")
	private boolean is_active;

	@Column(name = "is_deleted")
	private boolean is_deleted;

	@OneToOne(targetEntity = EnvironmentClearence.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "ec_id", nullable = false)
	@JsonIgnore
	private EnvironmentClearence environmentClearence;

	@OneToOne(mappedBy = "ecPartB")
	private EcConstructionDetail ecConstructionDetail;

	@OneToOne(mappedBy = "ecPartB")
	private EcPhysicalChanges ecPhysicalChanges;
	
	@OneToOne(mappedBy = "ecPartB")
	private EcPollutionDetails ecPollutionDetails;

	@OneToOne(mappedBy = "ecPartB")
	private EcWasteProduction ecWasteProduction;

	@OneToOne(mappedBy = "ecPartB")
	private EcGreenBelt ecGreenBelt;

	@OneToOne(mappedBy = "ecPartB")
	private EcRiskFactor ecRiskFactor;

	@OneToOne(mappedBy = "ecPartB")
	private EcMiningProposals ecMiningProposals;

	@OneToOne(mappedBy = "ecPartB")
	private EcTownshipProposals ecTownshipProposals;

	@OneToOne(mappedBy = "ecPartB")
	private EcIndustryProposal ecIndustryProposal;

	@OneToOne(mappedBy = "ecPartB")
	private EcWaterDetails ecWaterDetails;

	@OneToOne(mappedBy = "ecPartB")
	private EcRiverValleyProject ecRiverValley;
	
	@OneToOne(mappedBy = "ecPartB")
	private EcTSDFProposals ecTSDFProposals;
	
	@OneToOne(mappedBy = "ecPartB")
	private EcCETPProposals ecCETPProposals;
	
	@OneToOne(mappedBy = "ecPartB")
	private EcCBWTFProposals ecCBWTFProposals;
	
	@OneToOne(mappedBy = "ecPartB")
	private EcCMSWMFProposals ecCMSWMFProposals;

	@Transient
	private ProponentApplications proponentApplications;

	@OneToMany(mappedBy = "ecPartB", cascade = CascadeType.ALL)
	@Where(clause = "is_deleted='false'")
	List<EcDemolitionTempConstruction> ecDemolitionTempConstruction = new ArrayList<>();

	@OneToMany(mappedBy = "ecPartB", cascade = CascadeType.ALL)
	@Where(clause = "is_deleted='false'")
	List<EcResource> ecResource = new ArrayList<>();

	@OneToMany(mappedBy = "ecPartB", cascade = CascadeType.ALL)
	@Where(clause = "is_deleted='false'")
	List<EcProposedProjectLandDetails> ecProposedProjectLandDetails = new ArrayList<>();

	@OneToMany(mappedBy = "ecPartB", cascade = CascadeType.ALL)
	@Where(clause = "is_deleted='false'")
	List<EcWasteDetails> ecWasteDetails = new ArrayList<>();

	@OneToMany(mappedBy = "ecPartB", cascade = CascadeType.ALL)
	@Where(clause = "is_deleted='false'")
	List<EcPollutantDetails> ecPollutantDetails = new ArrayList<>();

	@OneToMany(mappedBy = "ecPartB", cascade = CascadeType.ALL)
	@Where(clause = "is_deleted='false'")
	List<EcChecklistDetails> ecChecklistDetails = new ArrayList<>();

	@OneToMany(mappedBy = "ecPartB", cascade = CascadeType.ALL)
	@Where(clause = "is_deleted='false'")
	List<EcAirPollutionMitigation> ecAirPollutionMitigations = new ArrayList<>();

	@OneToMany(mappedBy = "ecPartB", cascade = CascadeType.ALL)
//	@Where(clause = "is_deleted='false'")
	List<EcAirportProposal> ecAirportProposals = new ArrayList<>();

	@OneToMany(mappedBy = "ecPartB", cascade = CascadeType.ALL)
	@Where(clause = "is_deleted='false'")
	List<EcCroppingPattern> irrigationEcCroppingPattern = new ArrayList<>();

	@OneToMany(mappedBy = "ecPartB", cascade = CascadeType.ALL)
	@Where(clause = "is_deleted='false'")
	List<EcIrrigationProjectCapacityVillage> ecIrrigationProjectCapacityVillage = new ArrayList<>();

	@OneToMany(mappedBy = "ecPartB", cascade = CascadeType.ALL)
	@Where(clause = "is_deleted='false'")
	List<EcSubmergedArea> ecSubmergedArea = new ArrayList<>();

	@OneToMany(mappedBy = "ecPartB", cascade = CascadeType.ALL)	
	@Where(clause = "is_deleted='false'")
	private List<ProposedLandUse> proposedLandUse = new ArrayList<>();

	@OneToMany(mappedBy = "ecPartB", cascade = CascadeType.ALL)
	@Where(clause = "is_deleted='false'")
	private List<CurrentLandUse> currentLandUse = new ArrayList<>();

	@OneToMany(mappedBy = "ecPartB", cascade = CascadeType.ALL)
	@Where(clause = "is_deleted='false'")
	private List<ProposedLandUseExpansion> proposedLandUseExpansion = new ArrayList<>();

	@OneToMany(mappedBy = "ecPartB", cascade = CascadeType.ALL)
	@Where(clause = "is_deleted='false'")
	private List<DemolitionDetails> DemolitionDetails = new ArrayList<>();

	@OneToMany(mappedBy = "ecPartB", cascade = CascadeType.ALL)
	@Where(clause = "is_deleted='false'")
	private List<ConstructionDetails> constructionDetails = new ArrayList<>();

	@OneToMany(mappedBy = "ecPartB", cascade = CascadeType.ALL)
	@Where(clause = "is_deleted='false'")
	private List<EcStreamCrossing> ecStreamCrossing = new ArrayList<>();

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getNeed_of_project() {
		return need_of_project;
	}

	public void setNeed_of_project(String need_of_project) {
		this.need_of_project = need_of_project;
	}

	public boolean isIs_active() {
		return is_active;
	}

	public void setIs_active(boolean is_active) {
		this.is_active = is_active;
	}

	public boolean isIs_deleted() {
		return is_deleted;
	}

	public void setIs_deleted(boolean is_deleted) {
		this.is_deleted = is_deleted;
	}

	public EcPartB() {
		this.is_active = true;
		this.is_deleted = false;
	}

	public String getDemand_supply_gap() {
		return demand_supply_gap;
	}

	public void setDemand_supply_gap(String demand_supply_gap) {
		this.demand_supply_gap = demand_supply_gap;
	}

	public String getSocial_infra_available() {
		return social_infra_available;
	}

	public void setSocial_infra_available(String social_infra_available) {
		this.social_infra_available = social_infra_available;
	}

	public String getSocial_infra_proposed() {
		return social_infra_proposed;
	}

	public void setSocial_infra_proposed(String social_infra_proposed) {
		this.social_infra_proposed = social_infra_proposed;
	}

	public String getConnectivity_railway() {
		return connectivity_railway;
	}

	public String getConnectivity_railway_distance() {
		return connectivity_railway_distance;
	}

	public void setConnectivity_railway_distance(String connectivity_railway_distance) {
		this.connectivity_railway_distance = connectivity_railway_distance;
	}

	public String getConnectivity_airport_distance() {
		return connectivity_airport_distance;
	}

	public void setConnectivity_airport_distance(String connectivity_airport_distance) {
		this.connectivity_airport_distance = connectivity_airport_distance;
	}

	public String getNearest_town_city_details_distance() {
		return nearest_town_city_details_distance;
	}

	public void setNearest_town_city_details_distance(String nearest_town_city_details) {
		this.nearest_town_city_details_distance = nearest_town_city_details;
	}

	public void setConnectivity_railway(String connectivity_railway) {
		this.connectivity_railway = connectivity_railway;
	}

	public String getConnectivity_airport() {
		return connectivity_airport;
	}

	public void setConnectivity_airport(String connectivity_airport) {
		this.connectivity_airport = connectivity_airport;
	}

	public String getNearest_town_city_details() {
		return nearest_town_city_details;
	}

	public void setNearest_town_city_details(String nearest_town_city_details) {
		this.nearest_town_city_details = nearest_town_city_details;
	}

	public String getSoil_classification() {
		return soil_classification;
	}

	public void setSoil_classification(String soil_classification) {
		this.soil_classification = soil_classification;
	}

	public Double getDistance_HFL_river() {
		return distance_HFL_river;
	}

	public void setDistance_HFL_river(Double distance_HFL_river) {
		this.distance_HFL_river = distance_HFL_river;
	}

	public String getProject_social_benefit() {
		return project_social_benefit;
	}

	public void setProject_social_benefit(String project_social_benefit) {
		this.project_social_benefit = project_social_benefit;
	}

	public String getProject_financial_benefit() {
		return project_financial_benefit;
	}

	public void setProject_financial_benefit(String project_financial_benefit) {
		this.project_financial_benefit = project_financial_benefit;
	}

	public Date getDate_of_start_construction() {
		return date_of_start_construction;
	}

	public void setDate_of_start_construction(Date date_of_start_construction) {
		this.date_of_start_construction = date_of_start_construction;
	}

	public Date getDate_of_completion_construction() {
		return date_of_completion_construction;
	}

	public void setDate_of_completion_construction(Date date_of_completion_construction) {
		this.date_of_completion_construction = date_of_completion_construction;
	}

	public EnvironmentClearence getEnvironmentClearence() {
		return environmentClearence;
	}

	public void setEnvironmentClearence(EnvironmentClearence environmentClearence) {
		this.environmentClearence = environmentClearence;
	}

	public List<EcDemolitionTempConstruction> getEcDemolitionTempConstruction() {
		return ecDemolitionTempConstruction;
	}

	public void setEcDemolitionTempConstruction(List<EcDemolitionTempConstruction> ecDemolitionTempConstruction) {
		this.ecDemolitionTempConstruction = ecDemolitionTempConstruction;
	}

	public EcConstructionDetail getEcConstructionDetail() {
		return ecConstructionDetail;
	}

	public void setEcConstructionDetail(EcConstructionDetail ecConstructionDetail) {
		this.ecConstructionDetail = ecConstructionDetail;
	}

	public EcPhysicalChanges getEcPhysicalChanges() {
		return ecPhysicalChanges;
	}

	public void setEcPhysicalChanges(EcPhysicalChanges ecPhysicalChanges) {
		this.ecPhysicalChanges = ecPhysicalChanges;
	}

	public EcWasteProduction getEcWasteProduction() {
		return ecWasteProduction;
	}

	public void setEcWasteProduction(EcWasteProduction ecWasteProduction) {
		this.ecWasteProduction = ecWasteProduction;
	}

	public EcRiskFactor getEcRiskFactor() {
		return ecRiskFactor;
	}

	public void setEcRiskFactor(EcRiskFactor ecRiskFactor) {
		this.ecRiskFactor = ecRiskFactor;
	}

	public EcMiningProposals getEcMiningProposals() {
		return ecMiningProposals;
	}

	public void setEcMiningProposals(EcMiningProposals ecMiningProposals) {
		this.ecMiningProposals = ecMiningProposals;
	}

	public EcTownshipProposals getEcTownshipProposals() {
		return ecTownshipProposals;
	}

	public void setEcTownshipProposals(EcTownshipProposals ecTownshipProposals) {
		this.ecTownshipProposals = ecTownshipProposals;
	}

	public EcIndustryProposal getEcIndustryProposal() {
		return ecIndustryProposal;
	}

	public void setEcIndustryProposal(EcIndustryProposal ecIndustryProposal) {
		this.ecIndustryProposal = ecIndustryProposal;
	}

	public ProponentApplications getProponentApplications() {
		return proponentApplications;
	}

	public void setProponentApplications(ProponentApplications proponentApplications) {
		this.proponentApplications = proponentApplications;
	}

	public List<EcResource> getEcResource() {
		return ecResource;
	}

	public void setEcResource(List<EcResource> ecResource) {
		this.ecResource = ecResource;
	}

	public List<EcProposedProjectLandDetails> getEcProposedProjectLandDetails() {
		return ecProposedProjectLandDetails;
	}

	public void setEcProposedProjectLandDetails(List<EcProposedProjectLandDetails> ecProposedProjectLandDetails) {
		this.ecProposedProjectLandDetails = ecProposedProjectLandDetails;
	}

	public List<EcWasteDetails> getEcWasteDetails() {
		return ecWasteDetails;
	}

	public void setEcWasteDetails(List<EcWasteDetails> ecWasteDetails) {
		this.ecWasteDetails = ecWasteDetails;
	}

	public List<EcPollutantDetails> getEcPollutantDetails() {
		return ecPollutantDetails;
	}

	public void setEcPollutantDetails(List<EcPollutantDetails> ecPollutantDetails) {
		this.ecPollutantDetails = ecPollutantDetails;
	}

	public List<EcChecklistDetails> getEcChecklistDetails() {
		return ecChecklistDetails;
	}

	public void setEcChecklistDetails(List<EcChecklistDetails> ecChecklistDetails) {
		this.ecChecklistDetails = ecChecklistDetails;
	}

	public List<EcAirPollutionMitigation> getEcAirPollutionMitigations() {
		return ecAirPollutionMitigations;
	}

	public void setEcAirPollutionMitigations(List<EcAirPollutionMitigation> ecAirPollutionMitigations) {
		this.ecAirPollutionMitigations = ecAirPollutionMitigations;
	}

	public List<EcAirportProposal> getEcAirportProposals() {
		return ecAirportProposals;
	}

	public void setEcAirportProposals(List<EcAirportProposal> ecAirportProposals) {
		this.ecAirportProposals = ecAirportProposals;
	}

}
