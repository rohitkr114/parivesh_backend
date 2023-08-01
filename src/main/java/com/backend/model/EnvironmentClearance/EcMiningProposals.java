package com.backend.model.EnvironmentClearance;

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
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;


@Data
@Entity
@Table(name = "ec_mining_proposals", schema = "master")
public class EcMiningProposals extends Auditable<Integer> {

	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ec_partb_id", nullable = true)
	@JsonIgnore
	private EcPartB ecPartB;
	
	@Column(name = "date_of_issue", nullable = true)
	private Date date_of_issue;
	
	@Column(name = "date_of_validity", nullable = true)
	private Date date_of_validity;
	
	@Column(name = "lease_period", nullable = true)
	private String lease_period;
	
	@Column(name = "date_of_expiry", nullable = true)
	private Date date_of_expiry;
	
	@Column(name = "lease_area", nullable = true, length= 50)
	private String lease_area;
	
	@Column(name = "production_capacity", nullable = true, length= 50)
	private String production_capacity;
	
	@Column(name = "detail_of_lease", nullable = true, length= 300)
	private String detail_of_lease;
	
	@Column(name = "other_info", nullable = true, length= 500)
	private String other_info;
	
	@Column(name = "status_of_approval", nullable = true)
	private String status_of_approval;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "mining_mineral_mined_id", nullable = true)
	@Where(clause = "is_deleted='false'")
	private Set<MiningMineralsMined> miningMineralsMineds;
	
	@Column(name = "total_excavation_mtpa", nullable = true, length= 50)
	private Double total_excavation_mtpa;
	
	@Column(name = "total_excavation_annum", nullable = true, length= 50)
	private Double total_excavation_annum;
	
	@Column(name = "stripping_ratio", nullable = true)
	private String stripping_ratio;
	
	@Column(name = "excavation_other_info", nullable = true, length= 300)
	private String excavation_other_info;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "mining_mineral_reserves_id", nullable = true)
	@Where(clause = "is_deleted='false'")
	private Set<MiningMineralReserves> miningMineralReserves;
	
	@Column(name = "approved_life_of_mine", nullable = true)
	private String approved_life_of_mine;
	
	@Column(name = "estimate_life_of_mine", nullable = true)
	private String estimate_life_of_mine;
	
	@Column(name = "life_of_mine_other_info", nullable = true, length = 500)
	private String life_of_mine_other_info;
	
	@Column(name = "type_of_mining", nullable = true)
	private String type_of_mining;
	
	@Column(name = "method_of_mining", nullable = true)
	private String method_of_mining;
	
	@Column(name = "type_of_mining_other_info", nullable = true, length = 500)
	private String type_of_mining_other_info;
	
	@Column(name = "type_of_blasting", nullable = true, length = 100)
	private String type_of_blasting;
	
	@Column(name = "mitigation_blast_control", nullable = true, length = 200)
	private String mitigation_blast_control;
	
	@Column(name = "blasting_other_info", nullable = true, length = 500)
	private String blasting_other_info;
	
	@Column(name = "proposed_to_install_coal", nullable = true)
	private boolean proposed_to_install_coal;
	
	@Column(name = "coal_capacity", nullable = true)
	private Double coal_capacity;
	
	@Column(name = "coal_proposed_process", nullable = true)
	private String coal_proposed_process;	
	
	@Column(name = "coal_other_info", nullable = true, length = 300)
	private String coal_other_info;	
	
	@Column(name = "coal_beneficiation", nullable = true, length = 100)
	private String coal_beneficiation;	
	
	@Column(name = "proposed_to_install_crusher", nullable = true)
	private boolean proposed_to_install_crusher;
	
	@Column(name = "number_of_crusher", nullable = true, length = 50)
	private Integer number_of_crusher;
	
	@Column(name = "capacity_of_crusher", nullable = true, length = 50)
	private Double capacity_of_crusher;

	@Column(name = "capacity_of_crusher_unit", nullable = true)
	private String capacity_of_crusher_unit;
	
	@Column(name = "total_capacity_of_crusher", nullable = true, length = 50)
	private Double total_capacity_of_crusher;

	@Column(name = "total_capacity_of_crusher_unit", nullable = true)
	private String total_capacity_of_crusher_unit;
	
	@Column(name = "dumping_strategy", nullable = true, length = 5000)
	private String dumping_strategy;
	
	@Column(name = "total_topsoil_excavated", nullable = true, length = 50)
	private Double total_topsoil_excavated;
	
	@Column(name = "topsoil_utilization_strategy", nullable = true, length = 100)
	private String topsoil_utilization_strategy;	
	
	@Column(name = "topsoil_other_info", nullable = true, length = 300)
	private String topsoil_other_info;	
	
	@Column(name = "total_quarry_area", nullable = true, length = 50)
	private Double total_quarry_area;
	
	@Column(name = "final_void_area", nullable = true, length = 50)
	private Double final_void_area;
	
	@Column(name = "final_void_max_depth", nullable = true, length = 50)
	private Double final_void_max_depth;
	
	@Column(name = "quarry_other_info", nullable = true, length = 300)
	private String quarry_other_info;	
	
	@Column(name = "transportation_mode_up_pithead", nullable = true, length = 100)
	private String transportation_mode_up_pithead;	
	
	@Column(name = "transportation_mode_from_pithead", nullable = true, length = 100)
	private String transportation_mode_from_pithead;	
	
	@Column(name = "transportation_mode_from_loading", nullable = true, length = 100)
	private String transportation_mode_from_loading;	
	
	@Column(name = "transportation_mode_other_info", nullable = true, length = 300)
	private String transportation_mode_other_info;	
	
	@Column(name = "plantation_area", nullable = true, length = 50)
	private Double plantation_area;
	
	@Column(name = "water_body", nullable = true, length = 50)
	private Double water_body;
	
	@Column(name = "public_use", nullable = true, length = 50)
	private Double public_use;
	
	@Column(name = "other_use", nullable = true, length = 50)
	private Double other_use;
	
	@Column(name = "approved_dsr", nullable = true)
	private boolean approved_dsr;
	
	@Column(name = "instant_proposal", nullable = true)
	private boolean instant_proposal;
	
	@Column(name = "total_ml_area", nullable = true, length = 50)
	private Double total_ml_area;
	
	@Column(name = "no_of_mines_falling", nullable = true, length = 50)
	private Integer no_of_mines_falling;
	
	@Column(name = "category_of_cluster", nullable = true)
	private String category_of_cluster;	
	
	@Column(name = "replenishment_study_case", nullable = true)
	private String replenishment_study_case;	
	
	@Column(name = "dsr_other_info", nullable = true, length = 300)
	private String dsr_other_info;	
	
	@Column(name = "year_of_commencement", nullable = true)
	private String year_of_commencement;	
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "mining_production_details_id", nullable = true)
	@Where(clause = "is_deleted='false'")
	private Set<MiningProductionDetails> miningProductionDetails;
	
	@Column(name="external_dumping_area")
	private Double external_dumping_area;
	
	@Column(name="external_dumping_maximum_area")
	private Double external_dumping_maximum_area;
	
	@Column(name="external_dumping_remarks",nullable = true,length = 500)
	private String external_dumping_remarks;
	
	@Column(name="internal_dumping_area")
	private Double internal_dumping_area;
	
	@Column(name="internal_dumping_maximum_area")
	private Double internal_dumping_maximum_area;
	
	@Column(name="internal_dumping_remarks",nullable = true,length = 500)
	private String internal_dumping_remarks;
	
	@Column(name="toposoil_dumping_area")
	private Double toposoil_dumping_area;
	
	@Column(name="toposoil_dumping_maximum_area")
	private Double toposoil_dumping_maximum_area;
	
	@Column(name="toposoil_dumping_remarks",nullable = true,length = 500)
	private String toposoil_dumping_remarks;

	public String getCapacity_of_crusher_unit() {
		return capacity_of_crusher_unit;
	}

	public void setCapacity_of_crusher_unit(String capacity_of_crusher_unit) {
		this.capacity_of_crusher_unit = capacity_of_crusher_unit;
	}

	public String getTotal_capacity_of_crusher_unit() {
		return total_capacity_of_crusher_unit;
	}

	public void setTotal_capacity_of_crusher_unit(String total_capacity_of_crusher_unit) {
		this.total_capacity_of_crusher_unit = total_capacity_of_crusher_unit;
	}

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

	public Date getDate_of_issue() {
		return date_of_issue;
	}

	public void setDate_of_issue(Date date_of_issue) {
		this.date_of_issue = date_of_issue;
	}

	public Date getDate_of_validity() {
		return date_of_validity;
	}

	public void setDate_of_validity(Date date_of_validity) {
		this.date_of_validity = date_of_validity;
	}

	public String getLease_period() {
		return lease_period;
	}

	public void setLease_period(String lease_period) {
		this.lease_period = lease_period;
	}

	public Date getDate_of_expiry() {
		return date_of_expiry;
	}

	public void setDate_of_expiry(Date date_of_expiry) {
		this.date_of_expiry = date_of_expiry;
	}

	public String getDetail_of_lease() {
		return detail_of_lease;
	}

	public void setDetail_of_lease(String detail_of_lease) {
		this.detail_of_lease = detail_of_lease;
	}

	public String getOther_info() {
		return other_info;
	}

	public void setOther_info(String other_info) {
		this.other_info = other_info;
	}

	public String getStatus_of_approval() {
		return status_of_approval;
	}

	public void setStatus_of_approval(String status_of_approval) {
		this.status_of_approval = status_of_approval;
	}

	public Set<MiningMineralsMined> getMiningMineralsMineds() {
		return miningMineralsMineds;
	}

	public void setMiningMineralsMineds(Set<MiningMineralsMined> miningMineralsMineds) {
		this.miningMineralsMineds = miningMineralsMineds;
	}

	public Double getTotal_excavation_mtpa() {
		return total_excavation_mtpa;
	}

	public void setTotal_excavation_mtpa(Double total_excavation_mtpa) {
		this.total_excavation_mtpa = total_excavation_mtpa;
	}

	public Double getTotal_excavation_annum() {
		return total_excavation_annum;
	}

	public void setTotal_excavation_annum(Double total_excavation_annum) {
		this.total_excavation_annum = total_excavation_annum;
	}

	public String getStripping_ratio() {
		return stripping_ratio;
	}

	public void setStripping_ratio(String stripping_ratio) {
		this.stripping_ratio = stripping_ratio;
	}

	public String getExcavation_other_info() {
		return excavation_other_info;
	}

	public void setExcavation_other_info(String excavation_other_info) {
		this.excavation_other_info = excavation_other_info;
	}

	public Set<MiningMineralReserves> getMiningMineralReserves() {
		return miningMineralReserves;
	}

	public void setMiningMineralReserves(Set<MiningMineralReserves> miningMineralReserves) {
		this.miningMineralReserves = miningMineralReserves;
	}

	public String getApproved_life_of_mine() {
		return approved_life_of_mine;
	}

	public void setApproved_life_of_mine(String approved_life_of_mine) {
		this.approved_life_of_mine = approved_life_of_mine;
	}

	public String getEstimate_life_of_mine() {
		return estimate_life_of_mine;
	}

	public void setEstimate_life_of_mine(String estimate_life_of_mine) {
		this.estimate_life_of_mine = estimate_life_of_mine;
	}

	public String getLife_of_mine_other_info() {
		return life_of_mine_other_info;
	}

	public void setLife_of_mine_other_info(String life_of_mine_other_info) {
		this.life_of_mine_other_info = life_of_mine_other_info;
	}

	public String getType_of_mining() {
		return type_of_mining;
	}

	public void setType_of_mining(String type_of_mining) {
		this.type_of_mining = type_of_mining;
	}

	public String getMethod_of_mining() {
		return method_of_mining;
	}

	public void setMethod_of_mining(String method_of_mining) {
		this.method_of_mining = method_of_mining;
	}

	public String getType_of_mining_other_info() {
		return type_of_mining_other_info;
	}

	public void setType_of_mining_other_info(String type_of_mining_other_info) {
		this.type_of_mining_other_info = type_of_mining_other_info;
	}

	public String getType_of_blasting() {
		return type_of_blasting;
	}

	public void setType_of_blasting(String type_of_blasting) {
		this.type_of_blasting = type_of_blasting;
	}

	public String getMitigation_blast_control() {
		return mitigation_blast_control;
	}

	public void setMitigation_blast_control(String mitigation_blast_control) {
		this.mitigation_blast_control = mitigation_blast_control;
	}

	public String getBlasting_other_info() {
		return blasting_other_info;
	}

	public void setBlasting_other_info(String blasting_other_info) {
		this.blasting_other_info = blasting_other_info;
	}

	public boolean isProposed_to_install_coal() {
		return proposed_to_install_coal;
	}

	public void setProposed_to_install_coal(boolean proposed_to_install_coal) {
		this.proposed_to_install_coal = proposed_to_install_coal;
	}

	public Double getCoal_capacity() {
		return coal_capacity;
	}

	public void setCoal_capacity(Double coal_capacity) {
		this.coal_capacity = coal_capacity;
	}

	public String getCoal_proposed_process() {
		return coal_proposed_process;
	}

	public void setCoal_proposed_process(String coal_proposed_process) {
		this.coal_proposed_process = coal_proposed_process;
	}

	public String getCoal_other_info() {
		return coal_other_info;
	}

	public void setCoal_other_info(String coal_other_info) {
		this.coal_other_info = coal_other_info;
	}

	public String getCoal_beneficiation() {
		return coal_beneficiation;
	}

	public void setCoal_beneficiation(String coal_beneficiation) {
		this.coal_beneficiation = coal_beneficiation;
	}

	public boolean isProposed_to_install_crusher() {
		return proposed_to_install_crusher;
	}

	public void setProposed_to_install_crusher(boolean proposed_to_install_crusher) {
		this.proposed_to_install_crusher = proposed_to_install_crusher;
	}

	public Integer getNumber_of_crusher() {
		return number_of_crusher;
	}

	public void setNumber_of_crusher(Integer number_of_crusher) {
		this.number_of_crusher = number_of_crusher;
	}

	public Double getCapacity_of_crusher() {
		return capacity_of_crusher;
	}

	public void setCapacity_of_crusher(Double capacity_of_crusher) {
		this.capacity_of_crusher = capacity_of_crusher;
	}

	public Double getTotal_capacity_of_crusher() {
		return total_capacity_of_crusher;
	}

	public void setTotal_capacity_of_crusher(Double total_capacity_of_crusher) {
		this.total_capacity_of_crusher = total_capacity_of_crusher;
	}

	public String getDumping_strategy() {
		return dumping_strategy;
	}

	public void setDumping_strategy(String dumping_strategy) {
		this.dumping_strategy = dumping_strategy;
	}

	public Double getTotal_topsoil_excavated() {
		return total_topsoil_excavated;
	}

	public void setTotal_topsoil_excavated(Double total_topsoil_excavated) {
		this.total_topsoil_excavated = total_topsoil_excavated;
	}

	public String getTopsoil_utilization_strategy() {
		return topsoil_utilization_strategy;
	}

	public void setTopsoil_utilization_strategy(String topsoil_utilization_strategy) {
		this.topsoil_utilization_strategy = topsoil_utilization_strategy;
	}

	public String getTopsoil_other_info() {
		return topsoil_other_info;
	}

	public void setTopsoil_other_info(String topsoil_other_info) {
		this.topsoil_other_info = topsoil_other_info;
	}

	public Double getTotal_quarry_area() {
		return total_quarry_area;
	}

	public void setTotal_quarry_area(Double total_quarry_area) {
		this.total_quarry_area = total_quarry_area;
	}

	public Double getFinal_void_area() {
		return final_void_area;
	}

	public void setFinal_void_area(Double final_void_area) {
		this.final_void_area = final_void_area;
	}

	public Double getFinal_void_max_depth() {
		return final_void_max_depth;
	}

	public void setFinal_void_max_depth(Double final_void_max_depth) {
		this.final_void_max_depth = final_void_max_depth;
	}

	public String getQuarry_other_info() {
		return quarry_other_info;
	}

	public void setQuarry_other_info(String quarry_other_info) {
		this.quarry_other_info = quarry_other_info;
	}

	public String getTransportation_mode_up_pithead() {
		return transportation_mode_up_pithead;
	}

	public void setTransportation_mode_up_pithead(String transportation_mode_up_pithead) {
		this.transportation_mode_up_pithead = transportation_mode_up_pithead;
	}

	public String getTransportation_mode_from_pithead() {
		return transportation_mode_from_pithead;
	}

	public void setTransportation_mode_from_pithead(String transportation_mode_from_pithead) {
		this.transportation_mode_from_pithead = transportation_mode_from_pithead;
	}

	public String getTransportation_mode_from_loading() {
		return transportation_mode_from_loading;
	}

	public void setTransportation_mode_from_loading(String transportation_mode_from_loading) {
		this.transportation_mode_from_loading = transportation_mode_from_loading;
	}

	public String getTransportation_mode_other_info() {
		return transportation_mode_other_info;
	}

	public void setTransportation_mode_other_info(String transportation_mode_other_info) {
		this.transportation_mode_other_info = transportation_mode_other_info;
	}

	public Double getPlantation_area() {
		return plantation_area;
	}

	public void setPlantation_area(Double plantation_area) {
		this.plantation_area = plantation_area;
	}

	public Double getWater_body() {
		return water_body;
	}

	public void setWater_body(Double water_body) {
		this.water_body = water_body;
	}

	public Double getPublic_use() {
		return public_use;
	}

	public void setPublic_use(Double public_use) {
		this.public_use = public_use;
	}

	public Double getOther_use() {
		return other_use;
	}

	public void setOther_use(Double other_use) {
		this.other_use = other_use;
	}

	public boolean isApproved_dsr() {
		return approved_dsr;
	}

	public void setApproved_dsr(boolean approved_dsr) {
		this.approved_dsr = approved_dsr;
	}

	public boolean isInstant_proposal() {
		return instant_proposal;
	}

	public void setInstant_proposal(boolean instant_proposal) {
		this.instant_proposal = instant_proposal;
	}

	public Double getTotal_ml_area() {
		return total_ml_area;
	}

	public void setTotal_ml_area(Double total_ml_area) {
		this.total_ml_area = total_ml_area;
	}

	public Integer getNo_of_mines_falling() {
		return no_of_mines_falling;
	}

	public void setNo_of_mines_falling(Integer no_of_mines_falling) {
		this.no_of_mines_falling = no_of_mines_falling;
	}

	public String getCategory_of_cluster() {
		return category_of_cluster;
	}

	public void setCategory_of_cluster(String category_of_cluster) {
		this.category_of_cluster = category_of_cluster;
	}

	public String getReplenishment_study_case() {
		return replenishment_study_case;
	}

	public void setReplenishment_study_case(String replenishment_study_case) {
		this.replenishment_study_case = replenishment_study_case;
	}

	public String getDsr_other_info() {
		return dsr_other_info;
	}

	public void setDsr_other_info(String dsr_other_info) {
		this.dsr_other_info = dsr_other_info;
	}

	public String getYear_of_commencement() {
		return year_of_commencement;
	}

	public void setYear_of_commencement(String year_of_commencement) {
		this.year_of_commencement = year_of_commencement;
	}

	public Set<MiningProductionDetails> getMiningProductionDetails() {
		return miningProductionDetails;
	}

	public void setMiningProductionDetails(Set<MiningProductionDetails> miningProductionDetails) {
		this.miningProductionDetails = miningProductionDetails;
	}

	public Double getExternal_dumping_area() {
		return external_dumping_area;
	}

	public void setExternal_dumping_area(Double external_dumping_area) {
		this.external_dumping_area = external_dumping_area;
	}

	public Double getExternal_dumping_maximum_area() {
		return external_dumping_maximum_area;
	}

	public void setExternal_dumping_maximum_area(Double external_dumping_maximum_area) {
		this.external_dumping_maximum_area = external_dumping_maximum_area;
	}

	public String getExternal_dumping_remarks() {
		return external_dumping_remarks;
	}

	public void setExternal_dumping_remarks(String external_dumping_remarks) {
		this.external_dumping_remarks = external_dumping_remarks;
	}

	public Double getInternal_dumping_area() {
		return internal_dumping_area;
	}

	public void setInternal_dumping_area(Double internal_dumping_area) {
		this.internal_dumping_area = internal_dumping_area;
	}

	public Double getInternal_dumping_maximum_area() {
		return internal_dumping_maximum_area;
	}

	public void setInternal_dumping_maximum_area(Double internal_dumping_maximum_area) {
		this.internal_dumping_maximum_area = internal_dumping_maximum_area;
	}

	public String getInternal_dumping_remarks() {
		return internal_dumping_remarks;
	}

	public void setInternal_dumping_remarks(String internal_dumping_remarks) {
		this.internal_dumping_remarks = internal_dumping_remarks;
	}

	public Double getToposoil_dumping_area() {
		return toposoil_dumping_area;
	}

	public void setToposoil_dumping_area(Double toposoil_dumping_area) {
		this.toposoil_dumping_area = toposoil_dumping_area;
	}

	public Double getToposoil_dumping_maximum_area() {
		return toposoil_dumping_maximum_area;
	}

	public void setToposoil_dumping_maximum_area(Double toposoil_dumping_maximum_area) {
		this.toposoil_dumping_maximum_area = toposoil_dumping_maximum_area;
	}

	public String getToposoil_dumping_remarks() {
		return toposoil_dumping_remarks;
	}

	public void setToposoil_dumping_remarks(String toposoil_dumping_remarks) {
		this.toposoil_dumping_remarks = toposoil_dumping_remarks;
	}
	
	
	
}
