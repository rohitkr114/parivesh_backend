package com.backend.model;

import java.util.Date;
import java.util.Set;

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

import org.hibernate.annotations.Where;

import com.backend.audit.Auditable;
import com.backend.model.EnvironmentClearance.EcMiningProposals;
import com.backend.model.EnvironmentClearance.EcPartB;
import com.backend.model.EnvironmentClearance.MiningMineralReserves;
import com.backend.model.EnvironmentClearance.MiningMineralsMined;
import com.backend.model.EnvironmentClearance.MiningProductionDetails;
import com.backend.model.WildLifeClearance.WildLifeClearance;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "mining_proposals", schema = "master")
public class MiningProposals extends Auditable<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "caf_id")
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

	@Column(name = "date_of_issue", nullable = true)
	private Date dateOfIssue;

	@Column(name = "date_of_validity", nullable = true)
	private String dateOfValidity;

	@Column(name = "lease_period", nullable = true)
	private String leasePeriod;

	@Column(name = "date_of_expiry", nullable = true)
	private Date dateOfExpiry;

	@Column(name = "lease_area", nullable = true, length = 50)
	private String leaseArea;

	@Column(name = "production_capacity", nullable = true, length = 50)
	private String productionCapacity;

	@Column(name = "detail_of_lease", nullable = true, length = 300)
	private String detailOfLease;

	@Column(name = "other_info", nullable = true, length = 500)
	private String otherInfo;

	@Column(name = "status_of_approval", nullable = true)
	private String statusOfApproval;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "mining_mineral_mined_id", nullable = true)
	@Where(clause = "is_deleted='false'")
	private Set<MiningMineralsMined> miningMineralsMineds;

	@Column(name = "total_excavation_mtpa", nullable = true, length = 50)
	private Double totalExcavationMtpa;

	@Column(name = "total_excavation_annum", nullable = true, length = 50)
	private Double totalExcavationAnnum;

	@Column(name = "stripping_ratio", nullable = true)
	private String strippingRatio;

	@Column(name = "excavation_other_info", nullable = true, length = 300)
	private String excavationOtherInfo;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "mining_mineral_reserves_id", nullable = true)
	@Where(clause = "is_deleted='false'")
	private Set<MiningMineralReserves> miningMineralReserves;

	@Column(name = "approved_life_of_mine", nullable = true)
	private String approvedLifeOfMine;

	@Column(name = "estimate_life_of_mine", nullable = true)
	private String estimateLifeOfMine;

	@Column(name = "life_of_mine_other_info", nullable = true, length = 500)
	private String lifeOfMineOtherInfo;

	@Column(name = "type_of_mining", nullable = true)
	private String typeOfMining;

	@Column(name = "method_of_mining", nullable = true)
	private String methodOfMining;

	@Column(name = "type_of_mining_other_info", nullable = true, length = 500)
	private String typeOfMiningOtherInfo;

	@Column(name = "type_of_blasting", nullable = true, length = 100)
	private String typeOfBlasting;

	@Column(name = "mitigation_blast_control", nullable = true, length = 200)
	private String mitigationBlastControl;

	@Column(name = "blasting_other_info", nullable = true, length = 500)
	private String blastingOtherInfo;

	@Column(name = "proposed_to_install_coal", nullable = true)
	private boolean proposedToInstallCoal;

	@Column(name = "coal_capacity", nullable = true)
	private Double coalCapacity;

	@Column(name = "coal_proposed_process", nullable = true)
	private String coalProposedProcess;

	@Column(name = "coal_other_info", nullable = true, length = 300)
	private String coalOtherInfo;

	@Column(name = "coal_beneficiation", nullable = true, length = 100)
	private String coalBeneficiation;

	@Column(name = "proposed_to_install_crusher", nullable = true)
	private boolean proposedToInstallCrusher;

	@Column(name = "number_of_crusher", nullable = true, length = 50)
	private Integer numberOfCrusher;

	@Column(name = "capacity_of_crusher", nullable = true, length = 50)
	private Double capacityOfCrusher;

	@Column(name = "capacity_of_crusher_unit", nullable = true)
	private String capacityOfCrusherUnit;

	@Column(name = "total_capacity_of_crusher", nullable = true, length = 50)
	private Double totalCapacityOfCrusher;

	@Column(name = "total_capacity_of_crusher_unit", nullable = true)
	private String totalCapacityOfCrusherUnit;

	@Column(name = "dumping_strategy", nullable = true, length = 5000)
	private String dumpingStrategy;

	@Column(name = "total_topsoil_excavated", nullable = true, length = 50)
	private Integer total_topsoil_excavated;

	@Column(name = "topsoil_utilization_strategy", nullable = true, length = 100)
	private String topsoilUtilizationStrategy;

	@Column(name = "topsoil_other_info", nullable = true, length = 300)
	private String topsoilOtherInfo;

	@Column(name = "total_quarry_area", nullable = true, length = 50)
	private Double totalQuarryArea;

	@Column(name = "final_void_area", nullable = true, length = 50)
	private Integer finalVoidArea;

	@Column(name = "final_void_max_depth", nullable = true, length = 50)
	private Integer finalVoidMaxDepth;

	@Column(name = "quarry_other_info", nullable = true, length = 300)
	private String quarryOtherInfo;

	@Column(name = "transportation_mode_up_pithead", nullable = true, length = 100)
	private String transportationModeUpPithead;

	@Column(name = "transportation_mode_from_pithead", nullable = true, length = 100)
	private String transportationModeFromPithead;

	@Column(name = "transportation_mode_from_loading", nullable = true, length = 100)
	private String transportation_mode_from_loading;

	@Column(name = "transportation_mode_other_info", nullable = true, length = 300)
	private String transportationModeOtherInfo;

	@Column(name = "plantation_area", nullable = true, length = 50)
	private Integer plantationArea;

	@Column(name = "water_body", nullable = true, length = 50)
	private Integer waterBody;

	@Column(name = "public_use", nullable = true, length = 50)
	private Integer publicUse;

	@Column(name = "other_use", nullable = true, length = 50)
	private Integer otherUse;

	@Column(name = "approved_dsr", nullable = true)
	private boolean approvedDsr;

	@Column(name = "instant_proposal", nullable = true)
	private boolean instantProposal;

	@Column(name = "total_ml_area", nullable = true, length = 50)
	private Integer totalMlArea;

	@Column(name = "no_of_mines_falling", nullable = true, length = 50)
	private Integer noOfMinesFalling;

	@Column(name = "category_of_cluster", nullable = true)
	private String categoryOfCluster;

	@Column(name = "replenishment_study_case", nullable = true)
	private String replenishmentStudyCase;

	@Column(name = "dsr_other_info", nullable = true, length = 300)
	private String dsrOtherInfo;

	@Column(name = "year_of_commencement", nullable = true)
	private String yearOfCommencement;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "mining_production_details_id", nullable = true)
	@Where(clause = "is_deleted='false'")
	private Set<MiningProductionDetails> miningProductionDetails;

	@Column(name = "external_dumping_area")
	private Double externalDumpingArea;

	@Column(name = "external_dumping_maximum_area")
	private Double externalDumpingMaximumArea;

	@Column(name = "external_dumping_remarks", nullable = true, length = 500)
	private String externalDumpingRemarks;

	@Column(name = "internal_dumping_area")
	private Double internalDumpingArea;

	@Column(name = "internal_dumping_maximum_area")
	private Double internalDumpingMaximumArea;

	@Column(name = "internal_dumping_remarks", nullable = true, length = 500)
	private String internalDumpingRemarks;

	@Column(name = "toposoil_dumping_area")
	private Double toposoilDumpingArea;

	@Column(name = "toposoil_dumping_maximum_area")
	private Double toposoilDumpingMaximumArea;

	@Column(name = "toposoil_dumping_remarks", nullable = true, length = 500)
	private String toposoilDumpingRemarks;

	@Column(name = "from_mining_plan", nullable = true)
	private Date fromMiningPlan;

	@Column(name = "to_mining_plan", nullable = true)
	private Date toMiningPlan;

	@Column(name = "from_mining_lease", nullable = true)
	private Date fromMiningLease;

	@Column(name = "to_mining_lease", nullable = true)
	private Date toMiningLease;

	@Column(name = "is_active")
	private boolean isActive;

	@Column(name = "is_deleted")
	private boolean isDeleted;

	public MiningProposals(EcMiningProposals ecMiningProposal, EcPartB ecPartB) {
		super();
		this.cafId = ecPartB.getEnvironmentClearence().getCommonFormDetail().getId();
		this.ecPartB = ecPartB;
		this.dateOfIssue = ecMiningProposal.getDate_of_issue();
		this.dateOfValidity = ecMiningProposal.getDate_of_validity().toString();
		this.leasePeriod = ecMiningProposal.getLease_period();
		this.dateOfExpiry = ecMiningProposal.getDate_of_expiry();
		this.leaseArea = ecMiningProposal.getLease_area();
		this.productionCapacity = ecMiningProposal.getProduction_capacity();
		this.detailOfLease = ecMiningProposal.getDetail_of_lease();
		this.otherInfo = ecMiningProposal.getOther_info();
		this.statusOfApproval = ecMiningProposal.getStatus_of_approval();
		this.miningMineralsMineds = ecMiningProposal.getMiningMineralsMineds();
		this.totalExcavationMtpa = ecMiningProposal.getTotal_excavation_mtpa();
		this.totalExcavationAnnum = ecMiningProposal.getTotal_excavation_annum();
		this.strippingRatio = ecMiningProposal.getStripping_ratio();
		this.excavationOtherInfo = ecMiningProposal.getExcavation_other_info();
		this.miningMineralReserves = ecMiningProposal.getMiningMineralReserves();
		this.approvedLifeOfMine = ecMiningProposal.getApproved_life_of_mine();
		this.estimateLifeOfMine = ecMiningProposal.getEstimate_life_of_mine();
		this.lifeOfMineOtherInfo = ecMiningProposal.getLife_of_mine_other_info();
		this.typeOfMining = ecMiningProposal.getType_of_mining();
		this.methodOfMining = ecMiningProposal.getMethod_of_mining();
		this.typeOfMiningOtherInfo = ecMiningProposal.getType_of_mining_other_info();
		this.typeOfBlasting = ecMiningProposal.getType_of_blasting();
		this.mitigationBlastControl = ecMiningProposal.getMitigation_blast_control();
		this.blastingOtherInfo = ecMiningProposal.getBlasting_other_info();
		this.coalCapacity = ecMiningProposal.getCoal_capacity();
		this.coalProposedProcess = ecMiningProposal.getCoal_proposed_process();
		this.coalOtherInfo = ecMiningProposal.getCoal_other_info();
		this.coalBeneficiation = ecMiningProposal.getCoal_beneficiation();
		this.numberOfCrusher = ecMiningProposal.getNumber_of_crusher();
		this.capacityOfCrusher = ecMiningProposal.getCapacity_of_crusher();
		this.capacityOfCrusherUnit = ecMiningProposal.getCapacity_of_crusher_unit();
		this.totalCapacityOfCrusher = ecMiningProposal.getTotal_capacity_of_crusher();
		this.totalCapacityOfCrusherUnit = ecMiningProposal.getTotal_capacity_of_crusher_unit();
		this.dumpingStrategy = ecMiningProposal.getDumping_strategy();
		this.total_topsoil_excavated = ecMiningProposal.getTotal_topsoil_excavated().intValue();
		this.topsoilUtilizationStrategy = ecMiningProposal.getTopsoil_utilization_strategy();
		this.topsoilOtherInfo = ecMiningProposal.getTopsoil_other_info();
		this.totalQuarryArea = ecMiningProposal.getTotal_quarry_area();
		this.finalVoidArea = ecMiningProposal.getFinal_void_area().intValue();
		this.finalVoidMaxDepth = ecMiningProposal.getFinal_void_max_depth().intValue();
		this.quarryOtherInfo = ecMiningProposal.getQuarry_other_info();
		this.transportationModeUpPithead = ecMiningProposal.getTransportation_mode_up_pithead();
		this.transportationModeFromPithead = ecMiningProposal.getTransportation_mode_from_pithead();
		this.transportation_mode_from_loading = ecMiningProposal.getTransportation_mode_from_loading();
		this.transportationModeOtherInfo = ecMiningProposal.getTransportation_mode_other_info();
		this.plantationArea = ecMiningProposal.getPlantation_area().intValue();
		this.waterBody = ecMiningProposal.getWater_body().intValue();
		this.publicUse = ecMiningProposal.getPublic_use().intValue();
		this.otherUse = ecMiningProposal.getOther_use().intValue();
		this.approvedDsr = ecMiningProposal.isApproved_dsr();
		this.instantProposal = ecMiningProposal.isInstant_proposal();
		this.totalMlArea = ecMiningProposal.getTotal_ml_area().intValue();
		this.noOfMinesFalling = ecMiningProposal.getNo_of_mines_falling();
		this.categoryOfCluster = ecMiningProposal.getCategory_of_cluster();
		this.replenishmentStudyCase = ecMiningProposal.getReplenishment_study_case();
		this.dsrOtherInfo = ecMiningProposal.getDsr_other_info();
		this.yearOfCommencement = ecMiningProposal.getYear_of_commencement();
		this.miningProductionDetails = ecMiningProposal.getMiningProductionDetails();
		this.externalDumpingArea = ecMiningProposal.getExternal_dumping_area();
		this.externalDumpingMaximumArea = ecMiningProposal.getExternal_dumping_maximum_area();
		this.externalDumpingRemarks = ecMiningProposal.getExternal_dumping_remarks();
		this.internalDumpingArea = ecMiningProposal.getInternal_dumping_area();
		this.internalDumpingMaximumArea = ecMiningProposal.getInternal_dumping_maximum_area();
		this.internalDumpingRemarks = ecMiningProposal.getInternal_dumping_remarks();
	}

	public MiningProposals(ForestClearanceMiningProposals fcMiningProposals) {

		super();
		this.cafId = fcMiningProposals.getForestClearance().getCommonFormDetail().getId();
		this.forestClearance = fcMiningProposals.getForestClearance();
		this.wildLifeClearance = fcMiningProposals.getWildLifeClearance();
		this.dateOfIssue = fcMiningProposals.getDate_of_issue();
		this.dateOfValidity = fcMiningProposals.getDate_of_validity();
		this.leasePeriod = fcMiningProposals.getLease_period();
		this.dateOfExpiry = fcMiningProposals.getDate_of_expiry();
		this.leaseArea = fcMiningProposals.getLease_area().toString();
		this.productionCapacity = fcMiningProposals.getProduction_capacity().toString();
		this.detailOfLease = fcMiningProposals.getDetail_of_lease();
		this.otherInfo = fcMiningProposals.getOther_info();
		this.statusOfApproval = fcMiningProposals.getStatus_of_approval();
		this.miningMineralsMineds = fcMiningProposals.getMiningMineralsMineds();
		this.totalExcavationMtpa = fcMiningProposals.getTotal_excavation_mtpa();
		this.totalExcavationAnnum = fcMiningProposals.getTotal_excavation_annum();
		this.strippingRatio = fcMiningProposals.getStripping_ratio();
		this.excavationOtherInfo = fcMiningProposals.getExcavation_other_info();
		this.miningMineralReserves = fcMiningProposals.getMiningMineralReserves();
		this.approvedLifeOfMine = fcMiningProposals.getApproved_life_of_mine();
		this.estimateLifeOfMine = fcMiningProposals.getEstimate_life_of_mine();
		this.lifeOfMineOtherInfo = fcMiningProposals.getLife_of_mine_other_info();
		this.typeOfMining = fcMiningProposals.getType_of_mining();
		this.methodOfMining = fcMiningProposals.getMethod_of_mining();
		this.typeOfMiningOtherInfo = fcMiningProposals.getType_of_mining_other_info();
		this.typeOfBlasting = fcMiningProposals.getType_of_blasting();
		this.mitigationBlastControl = fcMiningProposals.getMitigation_blast_control();
		this.blastingOtherInfo = fcMiningProposals.getBlasting_other_info();
		this.proposedToInstallCoal = fcMiningProposals.isProposed_to_install_coal();
		this.coalCapacity = fcMiningProposals.getCoal_capacity();
		this.coalProposedProcess = fcMiningProposals.getCoal_proposed_process();
		this.coalOtherInfo = fcMiningProposals.getCoal_other_info();
		this.coalBeneficiation = fcMiningProposals.getCoal_beneficiation();
		this.proposedToInstallCrusher = fcMiningProposals.isProposed_to_install_crusher();
		this.numberOfCrusher = fcMiningProposals.getNumber_of_crusher();
		this.capacityOfCrusher = fcMiningProposals.getCapacity_of_crusher();
		this.capacityOfCrusherUnit = fcMiningProposals.getCapacity_of_crusher_unit();
		this.totalCapacityOfCrusher = fcMiningProposals.getTotal_capacity_of_crusher();
		this.totalCapacityOfCrusherUnit = fcMiningProposals.getTotal_capacity_of_crusher_unit();
		this.dumpingStrategy = fcMiningProposals.getDumping_strategy();
		this.total_topsoil_excavated = fcMiningProposals.getTotal_topsoil_excavated().intValue();
		this.topsoilUtilizationStrategy = fcMiningProposals.getTopsoil_utilization_strategy();
		this.topsoilOtherInfo = fcMiningProposals.getTopsoil_other_info();
		this.totalQuarryArea = fcMiningProposals.getTotal_quarry_area();
		this.finalVoidArea = fcMiningProposals.getFinal_void_area().intValue();
		this.finalVoidMaxDepth = fcMiningProposals.getFinal_void_max_depth().intValue();
		this.quarryOtherInfo = fcMiningProposals.getQuarry_other_info();
		this.transportationModeUpPithead = fcMiningProposals.getTransportation_mode_up_pithead();
		this.transportationModeFromPithead = fcMiningProposals.getTransportation_mode_from_pithead();
		this.transportation_mode_from_loading = fcMiningProposals.getTransportation_mode_from_loading();
		this.transportationModeOtherInfo = fcMiningProposals.getTransportation_mode_other_info();
		this.waterBody = fcMiningProposals.getWater_body().intValue();
		this.publicUse = fcMiningProposals.getPublic_use().intValue();
		this.otherUse = fcMiningProposals.getOther_use().intValue();
		this.approvedDsr = fcMiningProposals.isApproved_dsr();
		this.instantProposal = fcMiningProposals.isInstant_proposal();
		this.totalMlArea = fcMiningProposals.getTotal_ml_area().intValue();
		this.noOfMinesFalling = fcMiningProposals.getNo_of_mines_falling();
		this.categoryOfCluster = fcMiningProposals.getCategory_of_cluster();
		this.replenishmentStudyCase = fcMiningProposals.getReplenishment_study_case();
		this.dsrOtherInfo = fcMiningProposals.getDsr_other_info();
		this.yearOfCommencement = fcMiningProposals.getYear_of_commencement();
		this.miningProductionDetails = fcMiningProposals.getMiningProductionDetails();
		this.fromMiningPlan = fcMiningProposals.getFrom_mining_plan();
		this.toMiningPlan = fcMiningProposals.getTo_mining_plan();
		this.fromMiningLease = fcMiningProposals.getFrom_mining_lease();
		this.toMiningLease = fcMiningProposals.getTo_mining_lease();
		this.isActive = fcMiningProposals.isIs_active();
		this.isDeleted = fcMiningProposals.isIs_deleted();
	}

}
