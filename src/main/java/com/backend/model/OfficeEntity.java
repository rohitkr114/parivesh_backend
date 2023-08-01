package com.backend.model;

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
import javax.persistence.UniqueConstraint;

import com.backend.audit.Auditable;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;

@Data
@Entity
@Table(name = "office_entity", schema = "ua", uniqueConstraints = @UniqueConstraint(columnNames = { "officeName" }))
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "entityId")
public class OfficeEntity extends Auditable<Integer>{
	
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
	
	@Column(name = "officename",length = 200)
	private String officeName;
	
	@Column(name = "officeaddress_line1",length = 100)
	private String officeaddress_line1;
	
	@Column(name = "officeaddress_line2",length = 100)
	private String officeaddress_line2;
	
	@Column(name = "abbreviation",length = 30)
	private String abbreviation;
	
	@Column(name = "district")
	private Integer district;
	
	@Column(name = "state")
	private Integer state;
	
	@Column(name = "tahsil")
	private Integer tahsil;
	
	@Column(name = "emailid" ,length =100)
	private String emailid;	
	
	@Column(name = "contactno" ,length = 12)
	private String contactNo;
	
	@Column(name = "telphone" ,length = 20)
	private String telphone;
	
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "office_type")
	@JsonIgnore
	private OfficeTypeEntity officeType;

	@Column(name = "pin" ,length = 6)
	private String pin;

	@Column(name = "parent_entityId")
	private Long parent;

	public String getOfficeaddress_line1() {
		return officeaddress_line1;
	}

	public void setOfficeaddress_line1(String officeaddress_line1) {
		this.officeaddress_line1 = officeaddress_line1;
	}

	public String getOfficeaddress_line2() {
		return officeaddress_line2;
	}

	public void setOfficeaddress_line2(String officeaddress_line2) {
		this.officeaddress_line2 = officeaddress_line2;
	}

	public String getOfficeName() {
		return officeName;
	}

	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	
	public String getEmailid() {
		return emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

	public Long getParent() {
		return parent;
	}

	public void setParent(Long parent) {
		this.parent = parent;
	}

	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public Integer getDistrict() {
		return district;
	}

	public void setDistrict(Integer district) {
		this.district = district;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getTahsil() {
		return tahsil;
	}

	public void setTahsil(Integer tahsil) {
		this.tahsil = tahsil;
	}

	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	public OfficeTypeEntity getOfficeType() {
		return officeType;
	}

	public void setOfficeType(OfficeTypeEntity officeType) {
		this.officeType = officeType;
	}

	public Long getEntityId() {
		return entityId;
	}

	public void setEntityId(Long entityId) {
		this.entityId = entityId;
	}
	

}
