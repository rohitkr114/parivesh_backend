package com.backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.backend.audit.Auditable;

@Entity
@Table(name = "officetype_entity", schema = "ua")
public class OfficeTypeEntity extends Auditable<Integer>{
	
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
	private Date updatedDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	@JoinColumn(name = "updated_by")
	private User updatedBy;
	
	@Column(nullable = true)
	private Boolean isActive;
	*/
	
	@Column(name = "officetypeName" ,length = 100)
	private String officeTypeName;
	@Column(name = "abbreviation" ,length = 30)
	private String abbreviation;
	@Column(name = "ParentId")
	private Long ParentId;
	@Column(name = "jurisdiction" ,length = 50)
	private String jurisdiction;
	@Column(name = "description")
	private String description;
	
	

	public String getOfficeTypeName() {
		return officeTypeName;
	}

	public void setOfficeTypeName(String officeTypeName) {
		this.officeTypeName = officeTypeName;
	}

	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		abbreviation = abbreviation;
	}

	public Long getParentId() {
		return ParentId;
	}

	public void setParentId(Long parentId) {
		ParentId = parentId;
	}

	public String getJurisdiction() {
		return jurisdiction;
	}

	public void setJurisdiction(String jurisdiction) {
		this.jurisdiction = jurisdiction;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getEntityId() {
		return entityId;
	}

	public void setEntityId(Long entityId) {
		this.entityId = entityId;
	}
}

