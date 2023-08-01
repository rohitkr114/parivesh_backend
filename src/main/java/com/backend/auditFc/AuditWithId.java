//package com.backend.auditFc;
//
//import java.util.Date;
//
//import javax.persistence.Column;
//import javax.persistence.EntityListeners;
//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.MappedSuperclass;
//import javax.persistence.SequenceGenerator;
//import javax.persistence.Temporal;
//import javax.persistence.TemporalType;
//
//import org.hibernate.annotations.CreationTimestamp;
//import org.hibernate.annotations.UpdateTimestamp;
//import org.springframework.data.annotation.CreatedBy;
//import org.springframework.data.annotation.LastModifiedBy;
//import org.springframework.data.jpa.domain.support.AuditingEntityListener;
//
//import com.backend.model.UserEntity;
//import com.fasterxml.jackson.annotation.JsonFormat;
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//
//
//@MappedSuperclass
//@EntityListeners(AuditingEntityListener.class)
//@JsonIgnoreProperties({"createdBy","createdDate","updatedBy","updatedDate"})
//public class AuditWithId {
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "_seq")
//	@SequenceGenerator(name = "_seq", sequenceName = "entity_sequence", allocationSize = 1, initialValue = 1)
//	private Long id;
//
//	@Column(updatable = false)
//	@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
//	@Temporal(TemporalType.TIMESTAMP)
//	@CreationTimestamp
//	private Date createdDate;
//
//	@ManyToOne(fetch = FetchType.LAZY)
//	@CreatedBy
//	@JsonIgnore
//	@JoinColumn(name = "created_by")
//	private UserEntity  createdBy;
//	
//	@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
//	@Temporal(TemporalType.TIMESTAMP)
//	@UpdateTimestamp
//	private Date updatedDate;
//	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@LastModifiedBy
//	@JsonIgnore
//	@JoinColumn(name = "updated_by")
//	private UserEntity updatedBy;
//
//	@Column(nullable = true)
//	private Boolean isActive= true;
//
//	public Date getUpdatedDate() {
//		return updatedDate;
//	}
//
//	public void setUpdatedDate(Date updatedDate) {
//		this.updatedDate = updatedDate;
//	}
//
//	
//
//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	public Boolean getIsActive() {
//		return isActive;
//	}
//
//	public void setIsActive(Boolean isActive) {
//		this.isActive = isActive;
//	}
//
//	public Date getCreatedDate() {
//		return createdDate;
//	}
//
//	public void setCreatedDate(Date createdDate) {
//		this.createdDate = createdDate;
//	}
//
//	public UserEntity getCreatedBy() {
//		return createdBy;
//	}
//
//	public void setCreatedBy(UserEntity createdBy) {
//		this.createdBy = createdBy;
//	}
//
//	public UserEntity getUpdatedBy() {
//		return updatedBy;
//	}
//
//	public void setUpdatedBy(UserEntity updatedBy) {
//		this.updatedBy = updatedBy;
//	}
//}