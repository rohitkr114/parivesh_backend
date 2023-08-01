package com.backend.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.backend.audit.Auditable;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name="sector_Entity", schema = "ua")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "entityId")
public class SectorEntity extends Auditable<Integer>{

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "_seq")
	@SequenceGenerator(name = "_seq", sequenceName = "entity_sequence", allocationSize = 1, initialValue = 1)
	private Long entityId;
	
	/*
	@Column(updatable = false)
	@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	
	@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedDate;

	@Column(nullable = true)
	private Boolean isActive;
	
	
	@OneToMany(mappedBy = "sectorEntity")
	private List<EcSectorFormDetails> ecSectorFormDetails;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "EC_Sector_Id", nullable = true)
//	@JsonBackReference(value = "EC_Sector_reference")
	@JsonIgnore
	private EnvironmentClearence environmentClearence;
	*/
	
	@Column(name = "sector_name")
	private String sectorName;
	
	@Column(name = "sector_code")
	private String sectorCode;

	@Column(name = "description")
	private String description;

	@Column(nullable = true)
	private Boolean sub_activity_condition_applicability;

	
	@ManyToOne
	@JoinColumn(name = "workgroup_id",nullable = true)
	WorkGroupEntity workgroup;

	public Long getEntityId() {
		return entityId;
	}

	public void setEntityId(Long entityId) {
		this.entityId = entityId;
	}

	public String getSectorName() {
		return sectorName;
	}

	public void setSectorName(String sectorName) {
		this.sectorName = sectorName;
	}

	public String getSectorCode() {
		return sectorCode;
	}

	public void setSectorCode(String sectorCode) {
		this.sectorCode = sectorCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	

	public WorkGroupEntity getWorkgroup() {
		return workgroup;
	}

	public void setWorkgroup(WorkGroupEntity workgroup) {
		this.workgroup = workgroup;
	}

	
	
}
