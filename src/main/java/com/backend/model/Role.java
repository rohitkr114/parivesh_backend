package com.backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.backend.audit.Auditable;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "role", schema = "authentication")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "entityId")
public class Role extends Auditable<Integer>{

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "_seq")
	@SequenceGenerator(name = "_seq", sequenceName = "entity_sequence", allocationSize = 1, initialValue = 1)
	private Long entityId;

	/*
	@Column(updatable = false)
	@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date createdDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@CreatedBy
	@JsonIgnore
	@JoinColumn(name = "created_by")
	private User  createdBy;
	
	@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	@UpdateTimestamp
	private Date updatedDate;*/
	
	@Column(name = "rolename", length = 200)
	private String rolename;// pm1
	
	@Column(name = "maximum_allocationper_office", length = 2)
	private Long maximumAllocationperOffice;
	
	@Column(name = "abbreviation", length = 20)
	private String abbreviation;

	private String description;
	
	@Column(nullable = true)
	private Boolean isActive;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public Long getMaximumAllocationperOffice() {
		return maximumAllocationperOffice;
	}

	public void setMaximumAllocationperOffice(Long maximumAllocationperOffice) {
		this.maximumAllocationperOffice = maximumAllocationperOffice;
	}

	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public Long getEntityId() {
		return entityId;
	}

	public void setEntityId(Long entityId) {
		this.entityId = entityId;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	
	
}
