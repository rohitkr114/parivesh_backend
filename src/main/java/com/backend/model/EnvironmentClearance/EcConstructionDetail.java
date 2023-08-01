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
@Table(name = "ec_construction_detail", schema = "master")
public class EcConstructionDetail extends Auditable<Integer> {

	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ec_partb_id", nullable = true)
	@JsonIgnore
	private EcPartB ecPartB;
	
	@Column(name = "water_requirement", nullable = true)
	private boolean water_requirement;
	
	@Column(name = "mineral_fuel", nullable = true)
	private boolean mineral_fuel;
	
	@Column(name = "construction_material", nullable = true)
	private boolean construction_material;
	
	@Column(name = "timber", nullable = true)
	private boolean timber;
	
	@Column(name = "electric_power", nullable = true)
	private boolean electric_power;
	
	@Column(name = "natural_resources_raw_materials", nullable = true)
	private boolean natural_resources_raw_materials;
	
	@Column(name = "hazardous_material", nullable = true)
	private boolean hazardous_material;
	
	@Column(name = "water_requirement_other_information", nullable = true)
	private String water_requirement_other_information;
	
	@Column(name = "minerals_other_information", nullable = true)
	private String minerals_other_information;
	
	@Column(name = "quantity_timber", nullable = true)
	private String quantity_timber;
	
	@Column(name = "source_timber", nullable = true)
	private String source_timber;
	
	@Column(name = "other_info_timber", nullable = true)
	private String other_info_timber;
	
	@Column(name = "total_electricity_requirment", nullable = true)
	private String total_electricity_requirment;
	
	@Column(name = "electric_main_source", nullable = true)
	private String electric_main_source;
	
	@Column(name = "electric_renewable_energy", nullable = true,length = 50)
	private String electric_renewable_energy;
	
	@Column(name = "electric_percentage_contribution", nullable = true)
	private Double electric_percentage_contribution;
	
	@Column(name = "electric_standby_arrangement", nullable = true, length = 300)
	private String electric_standby_arrangement;
	
	@Column(name = "electric_stack_height", nullable = true,length = 50)
	private String electric_stack_height;
	
	@Column(name = "electric_energy_conservation", nullable = true)
	private String electric_energy_conservation;
	
	@Column(name = "is_resource_efficiency", nullable = true)
	private boolean is_resource_efficiency;
	
	@Column(name = "resource_efficiency_optimization", nullable = true)
	private String resource_efficiency_optimization;

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

	public boolean isWater_requirement() {
		return water_requirement;
	}

	public void setWater_requirement(boolean water_requirement) {
		this.water_requirement = water_requirement;
	}

	public boolean isMineral_fuel() {
		return mineral_fuel;
	}

	public void setMineral_fuel(boolean mineral_fuel) {
		this.mineral_fuel = mineral_fuel;
	}

	public boolean isConstruction_material() {
		return construction_material;
	}

	public void setConstruction_material(boolean construction_material) {
		this.construction_material = construction_material;
	}

	public boolean isTimber() {
		return timber;
	}

	public void setTimber(boolean timber) {
		this.timber = timber;
	}

	public boolean isElectric_power() {
		return electric_power;
	}

	public void setElectric_power(boolean electric_power) {
		this.electric_power = electric_power;
	}

	public boolean isNatural_resources_raw_materials() {
		return natural_resources_raw_materials;
	}

	public void setNatural_resources_raw_materials(boolean natural_resources_raw_materials) {
		this.natural_resources_raw_materials = natural_resources_raw_materials;
	}

	public boolean isHazardous_material() {
		return hazardous_material;
	}

	public void setHazardous_material(boolean hazardous_material) {
		this.hazardous_material = hazardous_material;
	}

	public String getWater_requirement_other_information() {
		return water_requirement_other_information;
	}

	public void setWater_requirement_other_information(String water_requirement_other_information) {
		this.water_requirement_other_information = water_requirement_other_information;
	}

	public String getMinerals_other_information() {
		return minerals_other_information;
	}

	public void setMinerals_other_information(String minerals_other_information) {
		this.minerals_other_information = minerals_other_information;
	}

	public String getQuantity_timber() {
		return quantity_timber;
	}

	public void setQuantity_timber(String quantity_timber) {
		this.quantity_timber = quantity_timber;
	}

	public String getSource_timber() {
		return source_timber;
	}

	public void setSource_timber(String source_timber) {
		this.source_timber = source_timber;
	}

	public String getOther_info_timber() {
		return other_info_timber;
	}

	public void setOther_info_timber(String other_info_timber) {
		this.other_info_timber = other_info_timber;
	}

	public String getElectric_main_source() {
		return electric_main_source;
	}

	public void setElectric_main_source(String electric_main_source) {
		this.electric_main_source = electric_main_source;
	}

	public Double getElectric_percentage_contribution() {
		return electric_percentage_contribution;
	}

	public void setElectric_percentage_contribution(Double electric_percentage_contribution) {
		this.electric_percentage_contribution = electric_percentage_contribution;
	}

	public String getElectric_standby_arrangement() {
		return electric_standby_arrangement;
	}

	public void setElectric_standby_arrangement(String electric_standby_arrangement) {
		this.electric_standby_arrangement = electric_standby_arrangement;
	}

	public String getElectric_energy_conservation() {
		return electric_energy_conservation;
	}

	public void setElectric_energy_conservation(String electric_energy_conservation) {
		this.electric_energy_conservation = electric_energy_conservation;
	}

	public boolean isIs_resource_efficiency() {
		return is_resource_efficiency;
	}

	public void setIs_resource_efficiency(boolean is_resource_efficiency) {
		this.is_resource_efficiency = is_resource_efficiency;
	}

	public String getResource_efficiency_optimization() {
		return resource_efficiency_optimization;
	}

	public void setResource_efficiency_optimization(String resource_efficiency_optimization) {
		this.resource_efficiency_optimization = resource_efficiency_optimization;
	}
	
	
	
}
