package com.backend.model;

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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.backend.audit.Auditable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data 
@Entity
@Table(name = "caf_kml_plots", schema = "master")
@JsonIgnoreProperties({ "created_by", "created_on", "updated_by", "updated_on" })
public class CAFKMLPlots extends Auditable<Integer> {



	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name = "manual_entry", nullable = true)
	private Boolean manual_entry;

	public Boolean isManual_entry() {
		return manual_entry;
	}

	public void setManual_entry(Boolean manual_entry) {
		if(manual_entry==true) {
			this.manual_entry = manual_entry;
		}else {
			this.manual_entry=false;
		}
	
	}

	@Column(name = "toposheet_no", length = 50, nullable = false)
	private String toposheet_no;

	@Column(name = "state", length = 50, nullable = false)
	private String State;

	@Column(length = 50,nullable = true)
	private String state_code;
	
	@Column(length = 50,nullable = true)
	private String district_code;
	
	@Column(length = 50,nullable = true)
	private String village_code;
	
	@Column(name = "district", length = 50, nullable = false)
	private String District;

	@Column(name = "sub_district", length = 50, nullable = false)
	private String Sub_District;
	@Column(name = "sub_district_code", length = 50, nullable = true)
	private String sub_district_code;
	@Column(name = "village", length = 50, nullable = false)
	private String Village;

	@Column(name = "plot_no", length = 1000)
	private String plot_no;
	
	/*@ManyToOne(fetch = FetchType.EAGER ,cascade = CascadeType.ALL)
	@JsonIgnore
	private CafKML cafKML;*/

	@Column(nullable = false)
	private boolean is_active;

	@Column(nullable = false)
	private boolean is_deleted;

	public CAFKMLPlots() {
		this.is_active = true;
		this.is_deleted = false;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getToposheet_no() {
		return toposheet_no;
	}

	public void setToposheet_no(String toposheet_no) {
		this.toposheet_no = toposheet_no;
	}

	public String getState() {
		return State;
	}

	public void setState(String state) {
		State = state;
	}
	public String getSub_district_code() {
		return sub_district_code;
	}

	public void setSub_district_code(String sub_district_code) {
		this.sub_district_code = sub_district_code;
	}

	public String getDistrict() {
		return District;
	}

	public void setDistrict(String district) {
		District = district;
	}

	public String getSub_District() {
		return Sub_District;
	}

	public void setSub_District(String sub_District) {
		Sub_District = sub_District;
	}

	public String getVillage() {
		return Village;
	}

	public void setVillage(String village) {
		Village = village;
	}

	public String getPlot_no() {
		return plot_no;
	}

	public void setPlot_no(String plot_no) {
		this.plot_no = plot_no;
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

	public String getState_code() {
		return state_code;
	}

	public void setState_code(String state_code) {
		this.state_code = state_code;
	}

	public String getDistrict_code() {
		return district_code;
	}

	public void setDistrict_code(String district_code) {
		this.district_code = district_code;
	}

	public String getVillage_code() {
		return village_code;
	}

	public void setVillage_code(String village_code) {
		this.village_code = village_code;
	}

	public Boolean getManual_entry() {
		return manual_entry;
	}
	
	
}