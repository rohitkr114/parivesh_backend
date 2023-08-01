package com.backend.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="country_code",schema = "master")
public class CountryCode implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public long getCreated_by() {
		return created_by;
	}

	public void setCreated_by(long created_by) {
		this.created_by = created_by;
	}

	public Date getCreated_on() {
		return created_on;
	}

	public void setCreated_on(Date created_on) {
		this.created_on = created_on;
	}

	public long getUpdated_by() {
		return updated_by;
	}

	public void setUpdated_by(long updated_by) {
		this.updated_by = updated_by;
	}

	public Date getUpdated_on() {
		return updated_on;
	}

	public void setUpdated_on(Date updated_on) {
		this.updated_on = updated_on;
	}

	@Column(name="code",nullable = false,length = 10)
	private String code;
	
	@Column(name="name",nullable = false,length = 100)
	private String name;
	
	@Column(name="is_active",nullable = false)
	private boolean is_active;
	
	@Column(name="is_deleted",nullable = false)
	private boolean is_deleted;
	
	@Column(name="created_by",nullable = false)
	private long created_by;
	
	@Column(name="created_on",nullable = false)
	private Date created_on;
	
	@Column(name="updated_by",nullable = false)
	private long updated_by;
	
	@Column(name="updated_on",nullable = false)
	private Date updated_on;

	public CountryCode(String code, String name) {
		super();
		this.code = code;
		this.name = name;
	}
	
	
	
	
}
