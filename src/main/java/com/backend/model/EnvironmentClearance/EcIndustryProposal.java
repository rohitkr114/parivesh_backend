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
@Table(name = "ec_industry_proposal", schema = "master")
public class EcIndustryProposal extends Auditable<Integer> {

	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ec_partb_id", nullable = true)
	@JsonIgnore
	private EcPartB ecPartB;
	
	@Column(nullable = true, length = 300)
	private String plant;
	
	@Column(nullable = true, length = 200)
	private String existing_configuration;
	
	@Column(nullable = true, length = 200)
	private String proposed_configuration;
	
	@Column(nullable = true, length = 200)
	private String final_configuration;
	
	@Column(nullable = true, length = 500)
	private String remarks;
	
	@Column(nullable = true, length = 300)
	private String adoption_details;
	
	@Column(nullable = true)
	private String coke_quenching;
	
	@Column(nullable = true, length = 200)
	private String heat_recovery_blast_furnace;
	
	@Column(nullable = true, length = 200)
	private String heat_recovery_sinter;
	
	@Column(nullable = true, length = 100)
	private String slag_details;
	
	@Column(nullable = true, length = 300)
	private String metal_recovery_details;
	
	@Column(nullable = true, length = 300)
	private String slag_utilization;
	
	@Column(nullable = true, length = 200)
	private String jigging_detals;
	
	@Column(nullable = true, length = 200)
	private String acid_fume_control;
	
	@Column(nullable = true, length = 200)
	private String metal_detail_recovery_metallurgy;
	
	@Column(nullable = true, length = 200)
	private String waste_heat_system;
	
	@Column(nullable = true, length = 200)
	private String hazardous_waste_coproccesing;
	
	@Column(nullable = true, length = 200)
	private String fluoride_consumption;
	
	@Column(nullable = true, length = 200)
	private String spent_pot_utilization;
	
	@Column(nullable = true,length = 200)
	private String red_mud_handling;
	
	@Column(nullable = true,length = 200)
	private String red_mud_slag;
	
	@Column(nullable = true,length = 200)
	private String asbestos_fibre_monitoring;
	
	@Column(nullable = true,length = 200)
	private String occupational_health;
	
	@Column(nullable = true,length = 200)
	private String action_plan_handling;
	
	@Column(nullable = true,length = 200)
	private String elemental_chlorine_free;
	
	@Column(nullable = true,length = 200)
	private String odor_control_techniques;
	
	@Column(nullable = true,length = 200)
	private String hazardous_waste_mgmt;
	
	@Column(nullable = true,length = 200)
	private String water_consumption_reduction;
	
	@Column(nullable = true,length = 200)
	private String hazardous_waste_mgmt_paper_mft;
	
	@Column(nullable = true,length = 200)
	private String odor_control_measures;
	
	@Column(nullable = true,length = 200)
	private String odor_control_measures_chemical_itd;
	
	@Column(nullable = true,length = 200)
	private String cetp_membership;
	
	@Column(nullable = true)
	private String coke_quenching_other;
	
	@Column(nullable = true,length = 200)
	private String tsdf_details;
	
	@Column(nullable = true,length = 200)
	private String tsdf_membership;

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

	public String getPlant() {
		return plant;
	}

	public void setPlant(String plant) {
		this.plant = plant;
	}

	public String getExisting_configuration() {
		return existing_configuration;
	}

	public void setExisting_configuration(String existing_configuration) {
		this.existing_configuration = existing_configuration;
	}

	public String getProposed_configuration() {
		return proposed_configuration;
	}

	public void setProposed_configuration(String proposed_configuration) {
		this.proposed_configuration = proposed_configuration;
	}

	public String getFinal_configuration() {
		return final_configuration;
	}

	public void setFinal_configuration(String final_configuration) {
		this.final_configuration = final_configuration;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getAdoption_details() {
		return adoption_details;
	}

	public void setAdoption_details(String adoption_details) {
		this.adoption_details = adoption_details;
	}

	public String getCoke_quenching() {
		return coke_quenching;
	}

	public void setCoke_quenching(String coke_quenching) {
		this.coke_quenching = coke_quenching;
	}

	public String getHeat_recovery_blast_furnace() {
		return heat_recovery_blast_furnace;
	}

	public void setHeat_recovery_blast_furnace(String heat_recovery_blast_furnace) {
		this.heat_recovery_blast_furnace = heat_recovery_blast_furnace;
	}

	public String getHeat_recovery_sinter() {
		return heat_recovery_sinter;
	}

	public void setHeat_recovery_sinter(String heat_recovery_sinter) {
		this.heat_recovery_sinter = heat_recovery_sinter;
	}

	public String getSlag_details() {
		return slag_details;
	}

	public void setSlag_details(String slag_details) {
		this.slag_details = slag_details;
	}

	public String getMetal_recovery_details() {
		return metal_recovery_details;
	}

	public void setMetal_recovery_details(String metal_recovery_details) {
		this.metal_recovery_details = metal_recovery_details;
	}

	public String getSlag_utilization() {
		return slag_utilization;
	}

	public void setSlag_utilization(String slag_utilization) {
		this.slag_utilization = slag_utilization;
	}

	public String getJigging_detals() {
		return jigging_detals;
	}

	public void setJigging_detals(String jigging_detals) {
		this.jigging_detals = jigging_detals;
	}

	public String getAcid_fume_control() {
		return acid_fume_control;
	}

	public void setAcid_fume_control(String acid_fume_control) {
		this.acid_fume_control = acid_fume_control;
	}

	public String getMetal_detail_recovery_metallurgy() {
		return metal_detail_recovery_metallurgy;
	}

	public void setMetal_detail_recovery_metallurgy(String metal_detail_recovery_metallurgy) {
		this.metal_detail_recovery_metallurgy = metal_detail_recovery_metallurgy;
	}

	public String getWaste_heat_system() {
		return waste_heat_system;
	}

	public void setWaste_heat_system(String waste_heat_system) {
		this.waste_heat_system = waste_heat_system;
	}

	public String getHazardous_waste_coproccesing() {
		return hazardous_waste_coproccesing;
	}

	public void setHazardous_waste_coproccesing(String hazardous_waste_coproccesing) {
		this.hazardous_waste_coproccesing = hazardous_waste_coproccesing;
	}

	public String getFluoride_consumption() {
		return fluoride_consumption;
	}

	public void setFluoride_consumption(String fluoride_consumption) {
		this.fluoride_consumption = fluoride_consumption;
	}

	public String getSpent_pot_utilization() {
		return spent_pot_utilization;
	}

	public void setSpent_pot_utilization(String spent_pot_utilization) {
		this.spent_pot_utilization = spent_pot_utilization;
	}

	public String getRed_mud_handling() {
		return red_mud_handling;
	}

	public void setRed_mud_handling(String red_mud_handling) {
		this.red_mud_handling = red_mud_handling;
	}

	public String getRed_mud_slag() {
		return red_mud_slag;
	}

	public void setRed_mud_slag(String red_mud_slag) {
		this.red_mud_slag = red_mud_slag;
	}

	public String getAsbestos_fibre_monitoring() {
		return asbestos_fibre_monitoring;
	}

	public void setAsbestos_fibre_monitoring(String asbestos_fibre_monitoring) {
		this.asbestos_fibre_monitoring = asbestos_fibre_monitoring;
	}

	public String getOccupational_health() {
		return occupational_health;
	}

	public void setOccupational_health(String occupational_health) {
		this.occupational_health = occupational_health;
	}

	public String getAction_plan_handling() {
		return action_plan_handling;
	}

	public void setAction_plan_handling(String action_plan_handling) {
		this.action_plan_handling = action_plan_handling;
	}

	public String getElemental_chlorine_free() {
		return elemental_chlorine_free;
	}

	public void setElemental_chlorine_free(String elemental_chlorine_free) {
		this.elemental_chlorine_free = elemental_chlorine_free;
	}

	public String getOdor_control_techniques() {
		return odor_control_techniques;
	}

	public void setOdor_control_techniques(String odor_control_techniques) {
		this.odor_control_techniques = odor_control_techniques;
	}

	public String getHazardous_waste_mgmt() {
		return hazardous_waste_mgmt;
	}

	public void setHazardous_waste_mgmt(String hazardous_waste_mgmt) {
		this.hazardous_waste_mgmt = hazardous_waste_mgmt;
	}

	public String getWater_consumption_reduction() {
		return water_consumption_reduction;
	}

	public void setWater_consumption_reduction(String water_consumption_reduction) {
		this.water_consumption_reduction = water_consumption_reduction;
	}

	public String getHazardous_waste_mgmt_paper_mft() {
		return hazardous_waste_mgmt_paper_mft;
	}

	public void setHazardous_waste_mgmt_paper_mft(String hazardous_waste_mgmt_paper_mft) {
		this.hazardous_waste_mgmt_paper_mft = hazardous_waste_mgmt_paper_mft;
	}

	public String getOdor_control_measures() {
		return odor_control_measures;
	}

	public void setOdor_control_measures(String odor_control_measures) {
		this.odor_control_measures = odor_control_measures;
	}

	public String getOdor_control_measures_chemical_itd() {
		return odor_control_measures_chemical_itd;
	}

	public void setOdor_control_measures_chemical_itd(String odor_control_measures_chemical_itd) {
		this.odor_control_measures_chemical_itd = odor_control_measures_chemical_itd;
	}

	public String getCetp_membership() {
		return cetp_membership;
	}

	public void setCetp_membership(String cetp_membership) {
		this.cetp_membership = cetp_membership;
	}

	public String getTsdf_details() {
		return tsdf_details;
	}

	public void setTsdf_details(String tsdf_details) {
		this.tsdf_details = tsdf_details;
	}

	public String getTsdf_membership() {
		return tsdf_membership;
	}

	public void setTsdf_membership(String tsdf_membership) {
		this.tsdf_membership = tsdf_membership;
	}
	
}
