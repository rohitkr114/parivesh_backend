package com.backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.backend.audit.Auditable;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;

@Data
@Entity
@Table(name = "forest_clearance_patch_kml_details", schema = "master")
public class ForestClearancePatchKmlDetails extends Auditable<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "topoSheet_no", nullable = true, length = 50)
	private String topoSheet_no;
	@Column(name = "state", nullable = true, length = 50)
	private String state;

	@Column(name = "district", nullable = true, length = 50)
	private String district;

	@Column(name = "division", nullable = true, length = 50)
	private String division;

	@Column(name = "village", nullable = true, length = 50)
	private String village;

	@Column(name = "plot_no", nullable = true, length = 20)
	private String plot_no;

	@Column(name = "sub_district", nullable = true, length = 50)
	private String sub_district;

	@Column(name = "range", nullable = true, length = 50)
	private String range;

	@Column(name = "present_owner", nullable = true, length = 50)
	private String present_owner;

	@Column(name = "area")
	private String area;

	@Column(nullable = true)
	private Integer division_code;

	@Column(nullable = true)
	private Integer state_code;

	@Column(nullable = true)
	private Integer village_code;

	@Column(nullable = true)
	private Integer sub_district_code;

	@Column(nullable = true)
	private Integer district_code;

	@Column(nullable = true)
	private Boolean manual_entry;

	@Column(name = "is_active")
	private boolean is_active;

	@Column(name = "is_deleted")
	private boolean is_deleted;

	public String getTopoSheet_no() {
		return topoSheet_no;
	}

	public void setTopoSheet_no(String topoSheet_no) {
		this.topoSheet_no = topoSheet_no;
	}

	public String getSub_district() {
		return sub_district;
	}

	public void setSub_district(String sub_district) {
		this.sub_district = sub_district;
	}

	ForestClearancePatchKmlDetails() {
		this.is_active = true;
		this.is_deleted = false;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public String getVillage() {
		return village;
	}

	public void setVillage(String village) {
		this.village = village;
	}

	public String getPlot_no() {
		return plot_no;
	}

	public void setPlot_no(String plot_no) {
		this.plot_no = plot_no;
	}

	public String getPresent_owner() {
		return present_owner;
	}

	public void setPresent_owner(String present_owner) {
		this.present_owner = present_owner;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public Integer getDivision_code() {
		return division_code;
	}

	public void setDivision_code(Integer division_code) {
		this.division_code = division_code;
	}

	public Integer getState_code() {
		return state_code;
	}

	public void setState_code(Integer state_code) {
		this.state_code = state_code;
	}

	public Integer getVillage_code() {
		return village_code;
	}

	public void setVillage_code(Integer village_code) {
		this.village_code = village_code;
	}

	public Integer getSub_district_code() {
		return sub_district_code;
	}

	public void setSub_district_code(Integer sub_district_code) {
		this.sub_district_code = sub_district_code;
	}

	public String getRange() {
		return range;
	}

	public void setRange(String range) {
		this.range = range;
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

	public Boolean isManual_entry() {
		return manual_entry;
	}

	public void setManual_entry(Boolean manual_entry) {
		if (manual_entry == true) {
			this.manual_entry = manual_entry;
		} else {
			this.manual_entry = false;
		}

	}

}
