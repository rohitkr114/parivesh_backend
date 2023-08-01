package com.backend.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "designation_entity", schema = "ua")
public class DesignationEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "_seq")
	@SequenceGenerator(name = "_seq", sequenceName = "entity_sequence", allocationSize = 1, initialValue = 1)
	private Long entityId;
	
	@Column(name = "designation_name" ,length = 30)
	private String designationName;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "abbreviation",length = 30)
	private String abbreviation;
	
	@Column(name ="designation_order")
	private Long designationOrder;	
	
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
	@LastModifiedBy
	@JsonIgnore
	@JoinColumn(name = "updated_by")
	private User updatedBy;

	@Column(nullable = true)
	private Boolean isActive= true;

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
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

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public User getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(User updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getDesignationName() {
		return designationName;
	}

	public void setDesignationName(String designationName) {
		this.designationName = designationName;
	}

	public String getDescription() {
		return description;
	}

	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getDesignationOrder() {
		return designationOrder;
	}

	public void setDesignationOrder(Long designationOrder) {
		this.designationOrder = designationOrder;
	}
}
