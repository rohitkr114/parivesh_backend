package com.backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.backend.audit.Auditable;

import lombok.Data;

@Data
@Entity
@Table(name="village",schema="master")
public class Village extends Auditable<Integer>{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer s_no;
	
	@Column(name="state_code")
	private Integer state_code;
	
	@Column(name="state_name",length =100)
	private String state_name;
	
	@Column(name="district")
	private Integer district;
	
	@Column(name="district_name",length =100)
	private String district_name;
	
	@Column(name="sub_district_code")
	private Integer sub_district_code;
	
	@Column(name="sub_district_name",length =200)
	private String sub_district_name;
	
	@Column(name="village_code")
	private Integer village_code;
	
	@Column(name="villlage_version")
	private Integer village_version;
	
	@Column(name="village_name",length =200)
	private String village_name;
	
	@Column(name="village_name_in_local",length =200)
	private String village_name_in_local;
	
	@Column(name="village_status",length =100)
	private String village_status;
	
	@Column(name="census_2001_code")
	private Integer census_2001_code;
	
	@Column(name="census_2011_code")
	private Integer census_2011_code;
	
	@Column(nullable = true)
	private Boolean is_active;
	
	@Column(nullable = true)
	private Boolean is_deleted;
	
	public Village() {
		this.is_active=true;
		this.is_deleted=false;
	}

	public Integer getS_no() {
		return s_no;
	}

	public void setS_no(Integer s_no) {
		this.s_no = s_no;
	}

	public Integer getState_code() {
		return state_code;
	}

	public void setState_code(Integer state_code) {
		this.state_code = state_code;
	}

	public String getState_name() {
		return state_name;
	}

	public void setState_name(String state_name) {
		this.state_name = state_name;
	}

	public Integer getDistrict() {
		return district;
	}

	public void setDistrict(Integer district) {
		this.district = district;
	}

	public String getDistrict_name() {
		return district_name;
	}

	public void setDistrict_name(String district_name) {
		this.district_name = district_name;
	}

	public Integer getSub_district_code() {
		return sub_district_code;
	}

	public void setSub_district_code(Integer sub_district_code) {
		this.sub_district_code = sub_district_code;
	}

	public String getSub_district_name() {
		return sub_district_name;
	}

	public void setSub_district_name(String sub_district_name) {
		this.sub_district_name = sub_district_name;
	}

	public Integer getVillage_code() {
		return village_code;
	}

	public void setVillage_code(Integer village_code) {
		this.village_code = village_code;
	}

	public Integer getVillage_version() {
		return village_version;
	}

	public void setVillage_version(Integer village_version) {
		this.village_version = village_version;
	}

	public String getVillage_name() {
		return village_name;
	}

	public void setVillage_name(String village_name) {
		this.village_name = village_name;
	}

	public String getVillage_name_in_local() {
		return village_name_in_local;
	}

	public void setVillage_name_in_local(String village_name_in_local) {
		this.village_name_in_local = village_name_in_local;
	}

	public String getVillage_status() {
		return village_status;
	}

	public void setVillage_status(String village_status) {
		this.village_status = village_status;
	}

	public Integer getCensus_2001_code() {
		return census_2001_code;
	}

	public void setCensus_2001_code(Integer census_2001_code) {
		this.census_2001_code = census_2001_code;
	}

	public Integer getCensus_2011_code() {
		return census_2011_code;
	}

	public void setCensus_2011_code(Integer census_2011_code) {
		this.census_2011_code = census_2011_code;
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
	
	
}
