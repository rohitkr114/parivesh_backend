package com.backend.model.WildLifeClearance;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.backend.audit.Auditable;
import lombok.Data;

//@Data
@Entity
@Table(name = "wl_proposed_diversions_details", schema = "master")
public class WlProposedDiversionsDetails extends Auditable<Integer> {

	public String getToposheet_no() {
		return toposheet_no;
	}

	public void setToposheet_no(String toposheet_no) {
		this.toposheet_no = toposheet_no;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(nullable = true, length = 50)
	private String toposheet_no;

	@Column(name = "state", nullable = true, length = 50)
	private String state;

	@Column(nullable = true)
	private Integer state_code;

	@Column(nullable = false, length = 50)
	private String district;

	@Column(nullable = true)
	private Integer district_code;

	@Column(nullable = false, length = 50)
	private String village;

	@Column(nullable = true)
	private Integer village_code;

	@Column(nullable = true)
	private String range;

	@Column(nullable = true)
	private String forest_area_length;

	@Column(nullable = true)
	private String forest_area_width;

	@Column(nullable = true)
	private String forest_area_total;

	@Column(nullable = true)
	private String non_forest_area_length;

	@Column(nullable = true)
	private String non_forest_area_width;

	@Column(nullable = true)
	private String non_forest_area_total;
	@Column(nullable = true)
	private String non_linear_forest_area;
	@Column(nullable = true)
	private String non_linear_non_forest_area;

	@Column(name = "sub_district", nullable = true, length = 50)
	private String sub_district;

	@Column(nullable = true)
	private Integer sub_district_code;

	@Column(name = "manual_entry", nullable = true)
	private Boolean manual_entry;

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

	@Column(nullable = false)
	private boolean isDelete;

	WlProposedDiversionsDetails() {
		this.isDelete = false;

	}

	public boolean isDelete() {
		return isDelete;
	}

	public String getForest_area_length() {
		return forest_area_length;
	}

	public void setForest_area_length(String forest_area_length) {
		this.forest_area_length = forest_area_length;
	}

	public String getForest_area_width() {
		return forest_area_width;
	}

	public void setForest_area_width(String forest_area_width) {
		this.forest_area_width = forest_area_width;
	}

	public String getForest_area_total() {
		return forest_area_total;
	}

	public void setForest_area_total(String forest_area_total) {
		this.forest_area_total = forest_area_total;
	}

	public String getNon_forest_area_length() {
		return non_forest_area_length;
	}

	public void setNon_forest_area_length(String non_forest_area_length) {
		this.non_forest_area_length = non_forest_area_length;
	}

	public String getNon_forest_area_width() {
		return non_forest_area_width;
	}

	public void setNon_forest_area_width(String non_forest_area_width) {
		this.non_forest_area_width = non_forest_area_width;
	}

	public String getNon_forest_area_total() {
		return non_forest_area_total;
	}

	public void setNon_forest_area_total(String non_forest_area_total) {
		this.non_forest_area_total = non_forest_area_total;
	}

	public String getNon_linear_forest_area() {
		return non_linear_forest_area;
	}

	public void setNon_linear_forest_area(String non_linear_forest_area) {
		this.non_linear_forest_area = non_linear_forest_area;
	}

	public String getNon_linear_non_forest_area() {
		return non_linear_non_forest_area;
	}

	public void setNon_linear_non_forest_area(String non_linear_non_forest_area) {
		this.non_linear_non_forest_area = non_linear_non_forest_area;
	}

	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getVillage() {
		return village;
	}

	public void setVillage(String village) {
		this.village = village;
	}

	public String getRange() {
		return range;
	}

	public void setRange(String range) {
		this.range = range;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Integer getState_code() {
		return state_code;
	}

	public void setState_code(Integer state_code) {
		this.state_code = state_code;
	}

	public Integer getDistrict_code() {
		return district_code;
	}

	public void setDistrict_code(Integer district_code) {
		this.district_code = district_code;
	}

	public Integer getVillage_code() {
		return village_code;
	}

	public void setVillage_code(Integer village_code) {
		this.village_code = village_code;
	}

	public String getSub_district() {
		return sub_district;
	}

	public void setSub_district(String sub_district) {
		this.sub_district = sub_district;
	}

	public Integer getSub_district_code() {
		return sub_district_code;
	}

	public void setSub_district_code(Integer sub_district_code) {
		this.sub_district_code = sub_district_code;
	}

	public Boolean getManual_entry() {
		return manual_entry;
	}
	
}
