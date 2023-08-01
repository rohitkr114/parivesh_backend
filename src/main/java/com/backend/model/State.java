package com.backend.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.backend.audit.Auditable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;


@Data
@Entity
@Table(name="state",schema="master")
@JsonIgnoreProperties("hibernateLazyInitializer")
public class State extends Auditable<Integer> implements Serializable{


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(nullable = false,unique = true)
	private Integer code;
	
	/*
	 * @JsonManagedReference
	 * 
	 * @OneToMany(mappedBy="state", targetEntity = District.class,fetch =
	 * FetchType.LAZY) private List<District> districtlist=new ArrayList<>();
	 */
	
	@OneToMany(mappedBy = "state", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<District> districtlist=new ArrayList<>();
	
	@Column(nullable=true)
	private Integer version;
	
	@Column(nullable = true)
	private String name;
	
	@Column(nullable = true)
	private String state_abbr;
	
	@Column(nullable = true,length = 2)
	private String census_code_2001;
	
	@Column(nullable = true,length = 4)
	private String census_code_2011;
	
	@Column(nullable = true)
	private char state_ut_flag;

	@Column(nullable = true)
	private Boolean process_enable;
	
	@Column(nullable = true)
	private boolean is_active;
	
	@Column(nullable = true)
	private boolean is_deleted;

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public List<District> getDistrictlist() {
		return districtlist;
	}

	public void setDistrictlist(List<District> districtlist) {
		this.districtlist = districtlist;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCensus_code_2001() {
		return census_code_2001;
	}

	public void setCensus_code_2001(String census_code_2001) {
		this.census_code_2001 = census_code_2001;
	}

	public String getCensus_code_2011() {
		return census_code_2011;
	}

	public void setCensus_code_2011(String census_code_2011) {
		this.census_code_2011 = census_code_2011;
	}

	public char getState_ut_flag() {
		return state_ut_flag;
	}

	public void setState_ut_flag(char state_ut_flag) {
		this.state_ut_flag = state_ut_flag;
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
	
	State(){
		this.is_active=true;
		this.is_deleted=false;
	}
}