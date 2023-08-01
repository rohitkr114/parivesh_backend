package com.backend.model.ForestClearanceFormB;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import com.backend.audit.Auditable;
import com.backend.model.DocumentDetails;
import com.backend.model.EstimatedReserveMinerals;
import com.backend.model.EnvironmentClearance.MiningMineralReserves;
import com.backend.model.EnvironmentClearance.MiningMineralsMined;
import com.backend.model.EnvironmentClearance.MiningProductionDetails;
import com.backend.model.WildLifeClearance.WildLifeClearance;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "fc_form_b_mining_proposal", schema = "master")
public class FcFormBMiningProposals extends Auditable<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@OneToOne
	@JoinColumn(name = "fc_form_b_id", nullable = true)
	@JsonIgnore
	private FcFormBProjectDetails fcFormBProjectDetails;
//	
//	@OneToOne
//	@JoinColumn(name = "wlc_id", nullable = true)
//	@JsonIgnore
//	private WildLifeClearance wildLifeClearance;

	@Column(name = "date_of_issue", nullable = true)
	private Date date_of_issue;

	@Column(name = "date_of_validity", nullable = true)
	private String date_of_validity;

	@Column(name = "lease_period", nullable = true)
	private String lease_period;

	@Column(name = "lease_period_month", nullable = true)
	private String lease_period_month;
	@Column(name = "date_of_expiry", nullable = true)
	private Date date_of_expiry;

	@Column(name = "lease_area", nullable = true)
	private Double lease_area;

	@Column(name = "production_capacity", nullable = true, length = 50)
	private Double production_capacity;

	@Column(name = "detail_of_lease", nullable = true, length = 1000)
	private String detail_of_lease;

	@Column(name = "other_info", nullable = true, length = 1000)
	private String other_info;

	@Column(name = "status_of_approval", nullable = true)
	private String status_of_approval;

	@Column(name = "total_excavation_mtpa", nullable = true, length = 50)
	private Double total_excavation_mtpa;

	@Column(name = "total_excavation_annum", nullable = true, length = 50)
	private Double total_excavation_annum;

	@Column(name = "period_of_mining_lease", nullable = true)
	private Integer period_of_mining_lease;

	@Column(name = "stripping_ratio", nullable = true)
	private String stripping_ratio;

	@Column(name = "excavation_other_info", nullable = true, length = 1000)
	private String excavation_other_info;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "fc_form_b_mining_mineral_mined_id", nullable = true)
	@Where(clause = "is_deleted='false'")
	private Set<FcFormBMiningMineralsMined> fcFormBMiningMineralsMineds;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "fc_form_b_mining_mineral_reserves_id", nullable = true)
	@Where(clause = "is_deleted='false'")
	private Set<FcFormBMiningMineralReserves> fcFormBMiningMineralReserves;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "fc_form_b_mining_production_details_id", nullable = true)
	@Where(clause = "is_deleted='false'")
	private Set<FcFormBMiningProductionDetails> fcFormBMiningProductionDetails;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "fc_form_b_estimated_Reserve_Minerals_id", nullable = true)
	@Where(clause = "is_delete='false'")
	private Set<FcFormBEstimatedReserveMinerals> fcFormBEstimatedReserveMinerals;

	@Column(name = "approved_life_of_mine", nullable = true)
	private String approved_life_of_mine;

	@Column(name = "estimate_life_of_mine", nullable = true)
	private String estimate_life_of_mine;

	@Column(name = "life_of_mine_other_info", nullable = true, length = 1000)
	private String life_of_mine_other_info;

	@Column(name = "type_of_mining", nullable = true)
	private String type_of_mining;

	@Column(name = "method_of_mining", nullable = true)
	private String method_of_mining;

	@Column(name = "type_of_mining_other_info", nullable = true, length = 1000)
	private String type_of_mining_other_info;

	@Column(name = "type_of_blasting", nullable = true, length = 500)
	private String type_of_blasting;

	@Column(name = "mitigation_blast_control", nullable = true, length = 1000)
	private String mitigation_blast_control;

	@Column(name = "blasting_other_info", nullable = true, length = 1000)
	private String blasting_other_info;

	@Column(name = "proposed_to_install_coal", nullable = true)
	private boolean proposed_to_install_coal;

	@Column(name = "coal_capacity", nullable = true, length = 50)
	private Double coal_capacity;

	@Column(name = "coal_proposed_process", nullable = true)
	private String coal_proposed_process;

	@Column(name = "coal_other_info", nullable = true, length = 1000)
	private String coal_other_info;

	@Column(name = "coal_beneficiation", nullable = true, length = 200)
	private String coal_beneficiation;

	@Column(name = "proposed_to_install_crusher", nullable = true)
	private boolean proposed_to_install_crusher;

	@Column(name = "number_of_crusher", nullable = true, length = 50)
	private Integer number_of_crusher;

	@Column(name = "capacity_of_crusher", nullable = true)
	private Double capacity_of_crusher;

	@Column(name = "total_capacity_of_crusher", nullable = true)
	private Double total_capacity_of_crusher;

	@Column(name = "dumping_strategy", nullable = true, length = 5000)
	private String dumping_strategy;

	@Column(name = "total_topsoil_excavated", nullable = true)
	private Double total_topsoil_excavated;

	@Column(name = "topsoil_utilization_strategy", nullable = true, length = 1000)
	private String topsoil_utilization_strategy;

	@Column(name = "topsoil_other_info", nullable = true, length = 1000)
	private String topsoil_other_info;

	@Column(name = "total_quarry_area", nullable = true)
	private Double total_quarry_area;

	@Column(name = "final_void_area", nullable = true)
	private Double final_void_area;

	@Column(name = "final_void_max_depth", nullable = true)
	private Double final_void_max_depth;

	@Column(name = "quarry_other_info", nullable = true, length = 1000)
	private String quarry_other_info;

	@Column(name = "transportation_mode_up_pithead", nullable = true, length = 1000)
	private String transportation_mode_up_pithead;

	@Column(name = "transportation_mode_from_pithead", nullable = true, length = 1000)
	private String transportation_mode_from_pithead;

	@Column(name = "transportation_mode_from_loading", nullable = true, length = 1000)
	private String transportation_mode_from_loading;

	@Column(name = "transportation_mode_other_info", nullable = true, length = 1000)
	private String transportation_mode_other_info;

	@Column(name = "plantation_area", nullable = true)
	private Double plantation_area;

	@Column(name = "water_body", nullable = true)
	private Double water_body;

	@Column(name = "public_use", nullable = true)
	private Double public_use;

	@Column(name = "other_use", nullable = true)
	private Double other_use;

	@Column(name = "approved_dsr", nullable = true)
	private boolean approved_dsr;

	@Column(name = "instant_proposal", nullable = true)
	private boolean instant_proposal;

	@Column(name = "total_ml_area", nullable = true)
	private Double total_ml_area;

	@Column(name = "no_of_mines_falling", nullable = true)
	private Integer no_of_mines_falling;

	@Column(name = "category_of_cluster", nullable = true)
	private String category_of_cluster;

	@Column(name = "replenishment_study_case", nullable = true)
	private String replenishment_study_case;

	@Column(name = "dsr_other_info", nullable = true, length = 1000)
	private String dsr_other_info;

	@Column(name = "year_of_commencement", nullable = true)
	private String year_of_commencement;

	private String capacity_of_crusher_unit;

	private String total_capacity_of_crusher_unit;

	private Date from_mining_plan;

	private Date to_mining_plan;

	private Date from_mining_lease;

	private Date to_mining_lease;
	@Column(length = 100, nullable = true)
	private String approving_authority_name;

	@Column(nullable = true)
	private Boolean is_mineral_reserves_access;

	@Column(length = 100, nullable = true)
	private String authority_designation;

	@Column(nullable = true)
	private Date date_of_grant_pl;

	@Column(nullable = true)
	private Date from_extension_pl;

	@Column(nullable = true)
	private Date to_extension_pl;

	@Column(nullable = true)
	private Integer fl_no_bore_holes;

	@Column(nullable = true)
	private Double fl_diameter;

	@Column(nullable = true)
	private Integer nfl_no_bore_holes;

	@Column(nullable = true)
	private Double nfl_diameter;

	private Boolean is_active;

	private Boolean is_deleted;

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

	public FcFormBMiningProposals() {
		this.is_active = true;
		this.is_deleted = false;
	}

//	public FcFormBMiningProposals(Integer id, Date date_of_issue, String date_of_validity, String lease_period,
//			String lease_period_month, Date date_of_expiry, Double lease_area, Double production_capacity,
//			String detail_of_lease, String other_info, String status_of_approval, Double total_excavation_mtpa,
//			Double total_excavation_annum, String stripping_ratio, String excavation_other_info,
//			List<FcFormBMiningMineralsMined> fcFormBMiningMineralsMineds,
//			List<FcFormBMiningMineralReserves> fcFormBMiningMineralReserves,
//			List<FcFormBMiningProductionDetails> fcFormBMiningProductionDetails,
//			List<FcFormBEstimatedReserveMinerals> fcFormBEstimatedReserveMinerals, String approved_life_of_mine,
//			String estimate_life_of_mine, String life_of_mine_other_info, String type_of_mining,
//			String method_of_mining, String type_of_mining_other_info, String type_of_blasting,
//			String mitigation_blast_control, String blasting_other_info, boolean proposed_to_install_coal,
//			Double coal_capacity, String coal_proposed_process, String coal_other_info, String coal_beneficiation,
//			boolean proposed_to_install_crusher, Integer number_of_crusher, Double capacity_of_crusher,
//			Double total_capacity_of_crusher, String dumping_strategy, Double total_topsoil_excavated,
//			String topsoil_utilization_strategy, String topsoil_other_info, Double total_quarry_area,
//			Double final_void_area, Double final_void_max_depth, String quarry_other_info,
//			String transportation_mode_up_pithead, String transportation_mode_from_pithead,
//			String transportation_mode_from_loading, String transportation_mode_other_info, Double plantation_area,
//			Double water_body, Double public_use, Double other_use, boolean approved_dsr, boolean instant_proposal,
//			Double total_ml_area, Integer no_of_mines_falling, String category_of_cluster,
//			String replenishment_study_case, String dsr_other_info, String year_of_commencement,
//			String capacity_of_crusher_unit, String total_capacity_of_crusher_unit, Date from_mining_plan,
//			Date to_mining_plan, Date from_mining_lease, Date to_mining_lease, String approving_authority_name,
//			Boolean is_mineral_reserves_access, String authority_designation, Date date_of_grant_pl,
//			Date from_extension_pl, Date to_extension_pl, Integer fl_no_bore_holes, Double fl_diameter,
//			Integer nfl_no_bore_holes, Double nfl_diameter) {
//		super();
//		this.id = id;
//		this.date_of_issue = date_of_issue;
//		this.date_of_validity = date_of_validity;
//		this.lease_period = lease_period;
//		this.lease_period_month = lease_period_month;
//		this.date_of_expiry = date_of_expiry;
//		this.lease_area = lease_area;
//		this.production_capacity = production_capacity;
//		this.detail_of_lease = detail_of_lease;
//		this.other_info = other_info;
//		this.status_of_approval = status_of_approval;
//		this.total_excavation_mtpa = total_excavation_mtpa;
//		this.total_excavation_annum = total_excavation_annum;
//		this.stripping_ratio = stripping_ratio;
//		this.excavation_other_info = excavation_other_info;
//		this.fcFormBMiningMineralsMineds = fcFormBMiningMineralsMineds;
//		this.fcFormBMiningMineralReserves = fcFormBMiningMineralReserves;
//		this.fcFormBMiningProductionDetails = fcFormBMiningProductionDetails;
//		this.fcFormBEstimatedReserveMinerals = fcFormBEstimatedReserveMinerals;
//		this.approved_life_of_mine = approved_life_of_mine;
//		this.estimate_life_of_mine = estimate_life_of_mine;
//		this.life_of_mine_other_info = life_of_mine_other_info;
//		this.type_of_mining = type_of_mining;
//		this.method_of_mining = method_of_mining;
//		this.type_of_mining_other_info = type_of_mining_other_info;
//		this.type_of_blasting = type_of_blasting;
//		this.mitigation_blast_control = mitigation_blast_control;
//		this.blasting_other_info = blasting_other_info;
//		this.proposed_to_install_coal = proposed_to_install_coal;
//		this.coal_capacity = coal_capacity;
//		this.coal_proposed_process = coal_proposed_process;
//		this.coal_other_info = coal_other_info;
//		this.coal_beneficiation = coal_beneficiation;
//		this.proposed_to_install_crusher = proposed_to_install_crusher;
//		this.number_of_crusher = number_of_crusher;
//		this.capacity_of_crusher = capacity_of_crusher;
//		this.total_capacity_of_crusher = total_capacity_of_crusher;
//		this.dumping_strategy = dumping_strategy;
//		this.total_topsoil_excavated = total_topsoil_excavated;
//		this.topsoil_utilization_strategy = topsoil_utilization_strategy;
//		this.topsoil_other_info = topsoil_other_info;
//		this.total_quarry_area = total_quarry_area;
//		this.final_void_area = final_void_area;
//		this.final_void_max_depth = final_void_max_depth;
//		this.quarry_other_info = quarry_other_info;
//		this.transportation_mode_up_pithead = transportation_mode_up_pithead;
//		this.transportation_mode_from_pithead = transportation_mode_from_pithead;
//		this.transportation_mode_from_loading = transportation_mode_from_loading;
//		this.transportation_mode_other_info = transportation_mode_other_info;
//		this.plantation_area = plantation_area;
//		this.water_body = water_body;
//		this.public_use = public_use;
//		this.other_use = other_use;
//		this.approved_dsr = approved_dsr;
//		this.instant_proposal = instant_proposal;
//		this.total_ml_area = total_ml_area;
//		this.no_of_mines_falling = no_of_mines_falling;
//		this.category_of_cluster = category_of_cluster;
//		this.replenishment_study_case = replenishment_study_case;
//		this.dsr_other_info = dsr_other_info;
//		this.year_of_commencement = year_of_commencement;
//		this.capacity_of_crusher_unit = capacity_of_crusher_unit;
//		this.total_capacity_of_crusher_unit = total_capacity_of_crusher_unit;
//		this.from_mining_plan = from_mining_plan;
//		this.to_mining_plan = to_mining_plan;
//		this.from_mining_lease = from_mining_lease;
//		this.to_mining_lease = to_mining_lease;
//		this.approving_authority_name = approving_authority_name;
//		this.is_mineral_reserves_access = is_mineral_reserves_access;
//		this.authority_designation = authority_designation;
//		this.date_of_grant_pl = date_of_grant_pl;
//		this.from_extension_pl = from_extension_pl;
//		this.to_extension_pl = to_extension_pl;
//		this.fl_no_bore_holes = fl_no_bore_holes;
//		this.fl_diameter = fl_diameter;
//		this.nfl_no_bore_holes = nfl_no_bore_holes;
//		this.nfl_diameter = nfl_diameter;
//	}

	public FcFormBMiningProposals(Integer id) {
		this.id = id;
	}

}
