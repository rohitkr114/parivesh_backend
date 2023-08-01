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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="ec_waste_production",schema="master")
public class EcWasteProduction {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name="is_solid_waste_present",nullable = true)
	private Boolean is_solid_waste_present;
	
	@Column(name="is_plastic_waste_present",nullable = true)
	private Boolean is_plastic_waste_present;
	
	@Column(name="is_e_waste_present",nullable = true)
	private Boolean is_e_waste_present;
	
	@Column(name="is_battery_present",nullable = true)
	private Boolean is_battery_present;
	
	@Column(name="is_biomedical_waste_present",nullable = true)
	private Boolean is_biomedical_waste_present;
	
	@Column(name="is_hazardous_present",nullable = true)
	private Boolean is_hazardous_present;
	
	@Column(name="is_construction_present",nullable = true)
	private Boolean is_construction_present;
	
	@Column(name="is_other_present",nullable = true)
	private Boolean is_other_present;
	
	@Column(name="is_surplus_product_present",nullable = true)
	private Boolean is_surplus_product_present;
	
	@Column(name="is_measures_waste_present",nullable = true)
	private Boolean is_measures_waste_present;
	
	@Column(name="measures_waste_details",nullable = true)
	private String measures_waste_details;

	private Boolean is_active;
	
	private Boolean is_deleted;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ec_partb_id", nullable = true)
	@JsonIgnore
	private EcPartB ecPartB;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getIs_solid_waste_present() {
		return is_solid_waste_present;
	}

	public void setIs_solid_waste_present(Boolean is_solid_waste_present) {
		this.is_solid_waste_present = is_solid_waste_present;
	}

	public Boolean getIs_plastic_waste_present() {
		return is_plastic_waste_present;
	}

	public void setIs_plastic_waste_present(Boolean is_plastic_waste_present) {
		this.is_plastic_waste_present = is_plastic_waste_present;
	}

	public Boolean getIs_e_waste_present() {
		return is_e_waste_present;
	}

	public void setIs_e_waste_present(Boolean is_e_waste_present) {
		this.is_e_waste_present = is_e_waste_present;
	}

	public Boolean getIs_battery_present() {
		return is_battery_present;
	}

	public void setIs_battery_present(Boolean is_battery_present) {
		this.is_battery_present = is_battery_present;
	}

	public Boolean getIs_biomedical_waste_present() {
		return is_biomedical_waste_present;
	}

	public void setIs_biomedical_waste_present(Boolean is_biomedical_waste_present) {
		this.is_biomedical_waste_present = is_biomedical_waste_present;
	}

	public Boolean getIs_hazardous_present() {
		return is_hazardous_present;
	}

	public void setIs_hazardous_present(Boolean is_hazardous_present) {
		this.is_hazardous_present = is_hazardous_present;
	}

	public Boolean getIs_construction_present() {
		return is_construction_present;
	}

	public void setIs_construction_present(Boolean is_construction_present) {
		this.is_construction_present = is_construction_present;
	}

	public Boolean getIs_other_present() {
		return is_other_present;
	}

	public void setIs_other_present(Boolean is_other_present) {
		this.is_other_present = is_other_present;
	}

	public Boolean getIs_surplus_product_present() {
		return is_surplus_product_present;
	}

	public void setIs_surplus_product_present(Boolean is_surplus_product_present) {
		this.is_surplus_product_present = is_surplus_product_present;
	}

	public Boolean getIs_measures_waste_present() {
		return is_measures_waste_present;
	}

	public void setIs_measures_waste_present(Boolean is_measures_waste_present) {
		this.is_measures_waste_present = is_measures_waste_present;
	}

	public String getMeasures_waste_details() {
		return measures_waste_details;
	}

	public void setMeasures_waste_details(String measures_waste_details) {
		this.measures_waste_details = measures_waste_details;
	}

	public Boolean getIs_active() {
		return is_active;
	}

	public void setIs_active(Boolean is_active) {
		this.is_active = is_active;
	}

	public Boolean getIs_deleted() {
		return is_deleted;
	}

	public void setIs_deleted(Boolean is_deleted) {
		this.is_deleted = is_deleted;
	}

	public EcPartB getEcPartB() {
		return ecPartB;
	}

	public void setEcPartB(EcPartB ecPartB) {
		this.ecPartB = ecPartB;
	}
	
	public EcWasteProduction() {
		this.is_active=true;
		this.is_deleted=false;
	}
	
	
	
	
}

