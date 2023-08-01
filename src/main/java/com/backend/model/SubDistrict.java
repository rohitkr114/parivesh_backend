package com.backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="sub_district",schema="master")
public class SubDistrict {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer s_no;
	
	@Column(name="state_code")
	private Integer state_code;
	
	@Column(name="state_name",length =100)
	private String state_name;
	
	@Column(name="district_code")
	private Integer district_code;
	
	@Column(name="district_name",length =100)
	private String district_name;
	
	@Column(name="sub_district_code")
	private Integer sub_district_code;
	
	@Column(name="sub_district_version")
	private Integer sub_district_version;
	
	@Column(name="sub_district_name",length =200)
	private String sub_district_name;
	
	@Column(name="census_2001_code")
	private Integer census_2001_code;
	
	@Column(name="census_2011_code")
	private Integer census_2011_code;
	
	@Column(nullable = true)
	private Boolean is_active;
	
	@Column(nullable = true)
	private Boolean is_deleted;
	
}
