package com.backend.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;

//import java.util.Date;
//import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.checkerframework.common.aliasing.qual.Unique;

import com.backend.audit.Auditable;
import com.backend.model.EcForm6V2.EcForm6ProjectDetailsV2;
import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity(name = "activities")
//@Table(name = "activities", schema = "master",uniqueConstraints = {@UniqueConstraint(columnNames= {"name"})})
@Table(name = "activities", schema = "master")
public class Activities extends Auditable<Integer> {

	
	@Id
	@GeneratedValue(
		    strategy = GenerationType.SEQUENCE,
		    generator = "seq_act"
	)
	@SequenceGenerator(
		    name = "seq_act",
		    sequenceName = "activity_sequence",
		    allocationSize =1, 
		    initialValue = 1
	)
	private Integer id;

	
	@Column(name = "name", nullable = false,unique = true)
	private String name;

	@Column(nullable = false)
	private boolean is_active;

	@Column(nullable = false)
	private boolean is_deleted;

	@Column(nullable = true)
	private String description;
	
	@Column(nullable = true, length = 50)
	private String item_no;
	
	@OneToMany(mappedBy = "activities", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<SubActivities> subActivities=new ArrayList<>();

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "sector_id", referencedColumnName = "entityId",nullable = true)
	private SectorEntity sectorEntity;
	
//	@JsonIgnore
	@OneToMany(mappedBy = "sectorActivities")
	private List<EcSectorFormDetails> ecSectorFormDetails;

	@OneToOne(mappedBy = "activities")
	@JsonIgnore
	private EnvironmentClearence environmentClearence;
	
	@OneToOne(mappedBy = "activities")
	@JsonIgnore
	private EcForm6ProjectDetailsV2 projectDetails;
	
	public Activities() {
		this.is_active = true;
		this.is_deleted = false;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public SectorEntity getSectorEntity() {
		return sectorEntity;
	}

	public void setSectorEntity(SectorEntity sectorEntity) {
		this.sectorEntity = sectorEntity;
	}

	public List<EcSectorFormDetails> getEcSectorFormDetails() {
		return ecSectorFormDetails;
	}

	public void setEcSectorFormDetails(List<EcSectorFormDetails> ecSectorFormDetails) {
		this.ecSectorFormDetails = ecSectorFormDetails;
	}

	public EnvironmentClearence getEnvironmentClearence() {
		return environmentClearence;
	}

	public void setEnvironmentClearence(EnvironmentClearence environmentClearence) {
		this.environmentClearence = environmentClearence;
	}

	
	  public List<SubActivities> getSubActivities() { return subActivities; }
	  
	  public void setSubActivities(List<SubActivities> subActivities) {
	  this.subActivities = subActivities; }

	public String getItem_no() {
		return item_no;
	}

	public void setItem_no(String item_no) {
		this.item_no = item_no;
	}
	 
	
}
