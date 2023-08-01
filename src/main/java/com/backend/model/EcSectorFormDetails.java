package com.backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.backend.audit.Auditable;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;



@Entity
@Table(name="ec_sector_form_details", schema = "master")
public class EcSectorFormDetails extends Auditable<Integer>{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name="form")
	private String form;
	
	@ManyToOne
	@JoinColumn(name="sector_activity_id",nullable = true)
	@JsonIgnore
	private Activities sectorActivities;
	
	@Column(name="is_active",nullable = true)
	private Boolean is_active;
	
	@Column(name="is_deleted",nullable = true)
	private Boolean is_deleted;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getForm() {
		return form;
	}

	public void setForm(String form) {
		this.form = form;
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

	public Activities getSectorActivities() {
		return sectorActivities;
	}

	public void setSectorActivities(Activities sectorActivities) {
		this.sectorActivities = sectorActivities;
	}
	
	public EcSectorFormDetails() {
		is_active=true;
		is_deleted=false;
	}
	
}
