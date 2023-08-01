package com.backend.model;

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
import com.backend.model.WildLifeClearance.WildLifeClearance;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "fc_river_valley_project", schema = "master")
public class ForestClearanceRiverValleyProject extends Auditable<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer id;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fc_id", nullable = true)
	@JsonIgnore
	private ForestClearance forestClearance;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "wlc_id", nullable = true)
	@JsonIgnore
	private WildLifeClearance wildLifeClearance;

	private String river_valley_project;

	private String involves_construction_of;
	@Column(name = "river_name", nullable = false, length = 300)
	private String river_name;

	@Column(name = "irrigation_project_configuration_json", nullable = true, length = 3000)
	private String irrigation_project_configuration_json;

	@Column(name = "irrigation_project_capacity_cultural_command", nullable = true)
	private Double irrigation_project_capacity_cultural_command;

	@Column(name = "irrigation_project_capacity_irrigable_command", nullable = true)
	private Double irrigation_project_capacity_irrigable_command;

	@Column(name = "irrigation_project_capacity_type_of_irrigation", nullable = true, length = 100)
	private String irrigation_project_capacity_type_of_irrigation;

	@Column(name = "irrigation_project_capacity_type_of_irrigation_value", nullable = true, length = 100)
	private String irrigation_project_capacity_type_of_irrigation_value;

	@Column(name = "hep_project_configuration_json", nullable = true, length = 3000)
	private String hep_project_configuration_json;

	@Column(name = "hep_project_capacity_json", nullable = true, length = 3000)
	private String hep_project_capacity_json;

	private Double catchement_area;

	private Double average_rainfall;

	private Double water_availability;

	@Column(name = "muck_restoration_plan", nullable = true, length = 500)
	private String muck_restoration_plan;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "upload_muck_restoration_plan_id", nullable = true)
	private DocumentDetails upload_muck_restoration_plan;

	@Column(name = "schedule_1_spieces", nullable = true, length = 500)
	private String schedule_1_spieces;

	@Column(name = "brief_on_e_flow", nullable = true, length = 1000)
	private String brief_on_e_flow;

	private String fish_pass_envisaged_provision;
	@Column(name = "reasons", nullable = true, length = 500)
	private String reasons;

	private boolean is_deleted;

	private boolean is_active;

	public ForestClearance getForestClearance() {
		return forestClearance;
	}

	public void setForestClearance(ForestClearance forestClearance) {
		this.forestClearance = forestClearance;
	}

	public String getRiver_valley_project() {
		return river_valley_project;
	}

	public void setRiver_valley_project(String river_valley_project) {
		this.river_valley_project = river_valley_project;
	}

	public String getInvolves_construction_of() {
		return involves_construction_of;
	}

	public void setInvolves_construction_of(String involves_construction_of) {
		this.involves_construction_of = involves_construction_of;
	}

	public String getRiver_name() {
		return river_name;
	}

	public void setRiver_name(String river_name) {
		this.river_name = river_name;
	}

	public String getIrrigation_project_configuration_json() {
		return irrigation_project_configuration_json;
	}

	public void setIrrigation_project_configuration_json(String irrigation_project_configuration_json) {
		this.irrigation_project_configuration_json = irrigation_project_configuration_json;
	}

	public Double getIrrigation_project_capacity_cultural_command() {
		return irrigation_project_capacity_cultural_command;
	}

	public void setIrrigation_project_capacity_cultural_command(Double irrigation_project_capacity_cultural_command) {
		this.irrigation_project_capacity_cultural_command = irrigation_project_capacity_cultural_command;
	}

	public Double getIrrigation_project_capacity_irrigable_command() {
		return irrigation_project_capacity_irrigable_command;
	}

	public void setIrrigation_project_capacity_irrigable_command(Double irrigation_project_capacity_irrigable_command) {
		this.irrigation_project_capacity_irrigable_command = irrigation_project_capacity_irrigable_command;
	}

	public String getIrrigation_project_capacity_type_of_irrigation() {
		return irrigation_project_capacity_type_of_irrigation;
	}

	public void setIrrigation_project_capacity_type_of_irrigation(
			String irrigation_project_capacity_type_of_irrigation) {
		this.irrigation_project_capacity_type_of_irrigation = irrigation_project_capacity_type_of_irrigation;
	}

	public String getIrrigation_project_capacity_type_of_irrigation_value() {
		return irrigation_project_capacity_type_of_irrigation_value;
	}

	public void setIrrigation_project_capacity_type_of_irrigation_value(
			String irrigation_project_capacity_type_of_irrigation_value) {
		this.irrigation_project_capacity_type_of_irrigation_value = irrigation_project_capacity_type_of_irrigation_value;
	}

	public String getHep_project_configuration_json() {
		return hep_project_configuration_json;
	}

	public void setHep_project_configuration_json(String hep_project_configuration_json) {
		this.hep_project_configuration_json = hep_project_configuration_json;
	}

	public String getHep_project_capacity_json() {
		return hep_project_capacity_json;
	}

	public void setHep_project_capacity_json(String hep_project_capacity_json) {
		this.hep_project_capacity_json = hep_project_capacity_json;
	}

	public Double getCatchement_area() {
		return catchement_area;
	}

	public void setCatchement_area(Double catchement_area) {
		this.catchement_area = catchement_area;
	}

	public Double getAverage_rainfall() {
		return average_rainfall;
	}

	public void setAverage_rainfall(Double average_rainfall) {
		this.average_rainfall = average_rainfall;
	}

	public Double getWater_availability() {
		return water_availability;
	}

	public void setWater_availability(Double water_availability) {
		this.water_availability = water_availability;
	}

	public String getMuck_restoration_plan() {
		return muck_restoration_plan;
	}

	public void setMuck_restoration_plan(String muck_restoration_plan) {
		this.muck_restoration_plan = muck_restoration_plan;
	}

	public String getSchedule_1_spieces() {
		return schedule_1_spieces;
	}

	public void setSchedule_1_spieces(String schedule_1_spieces) {
		this.schedule_1_spieces = schedule_1_spieces;
	}

	public String getBrief_on_e_flow() {
		return brief_on_e_flow;
	}

	public void setBrief_on_e_flow(String brief_on_e_flow) {
		this.brief_on_e_flow = brief_on_e_flow;
	}

	public String getFish_pass_envisaged_provision() {
		return fish_pass_envisaged_provision;
	}

	public void setFish_pass_envisaged_provision(String fish_pass_envisaged_provision) {
		this.fish_pass_envisaged_provision = fish_pass_envisaged_provision;
	}

	public String getReasons() {
		return reasons;
	}

	public void setReasons(String reasons) {
		this.reasons = reasons;
	}

	public boolean isIs_deleted() {
		return is_deleted;
	}

	public void setIs_deleted(boolean is_deleted) {
		this.is_deleted = is_deleted;
	}

	public boolean isIs_active() {
		return is_active;
	}

	public void setIs_active(boolean is_active) {
		this.is_active = is_active;
	}

	public WildLifeClearance getWildLifeClearance() {
		return wildLifeClearance;
	}

	public void setWildLifeClearance(WildLifeClearance wildLifeClearance) {
		this.wildLifeClearance = wildLifeClearance;
	}

}
