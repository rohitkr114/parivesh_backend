package com.backend.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.backend.audit.Auditable;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@Entity
@Table(name = "district", schema = "master")
@JsonIgnoreProperties("hibernateLazyInitializer")
public class District extends Auditable<Integer> implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

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

	public Integer getState_code() {
		return state_code;
	}

	public void setState_code(Integer state_code) {
		this.state_code = state_code;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
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

	@Column(nullable = false)
	private Integer code;

	private Integer state_code;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "state_code", referencedColumnName = "code", insertable = false, updatable = false)
	private State state;

	@Column(nullable = false)
	private String name;

	@Column(nullable = true, length = 2)
	private String census_code_2001;

	@Column(nullable = true, length = 4)
	private String census_code_2011;

	@Column(nullable = false)
	private boolean is_active;

	@Column(nullable = false)
	private boolean is_deleted;
	
	public District(Integer id, Integer code, Integer state_code, String name, String census_code_2001,
			String census_code_2011, boolean is_active, boolean is_deleted) {
		this.id = id;
		this.code = code;
		this.state_code = state_code;
		this.name = name;
		this.census_code_2001 = census_code_2001;
		this.census_code_2011 = census_code_2011;
		this.is_active = is_active;
		this.is_deleted = is_deleted;
	}

}