package com.backend.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.backend.audit.Auditable;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "ec_proj_implementation_details", schema = "master")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "reference_no")
public class EcProjectImplementationDetails extends Auditable<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int reference_no;

	@Column(name = "date_of_consent", nullable = true)
	private Date date_of_consent;

	@Column(name = "validity_of_consent", nullable = true)
	private Date validity_of_consent;

	@Column(name = "copy_of_document", nullable = true)
	private Integer copy_of_document;

	@Column(name = "construction_year", nullable = true, length = 20)
	private String construction_year;

	@Column(name = "reason", nullable = true, length = 20)
	private String reason;

	@Column(nullable = false)
	private boolean isDelete;
	
	String proposalNo;

	@ManyToOne
	@JoinColumn(name = "ec_id", nullable = true)
	@JsonIgnore
	private EnvironmentClearence environmentClearence;

	public EcProjectImplementationDetails() {
		this.isDelete = false;
	}

	public int getReference_no() {
		return reference_no;
	}

	public void setReference_no(int reference_no) {
		this.reference_no = reference_no;
	}

	public Date getDate_of_consent() {
		return date_of_consent;
	}

	public void setDate_of_consent(Date date_of_consent) {
		this.date_of_consent = date_of_consent;
	}

	public Date getValidity_of_consent() {
		return validity_of_consent;
	}

	public void setValidity_of_consent(Date validity_of_consent) {
		this.validity_of_consent = validity_of_consent;
	}

	public Integer getCopy_of_document() {
		return copy_of_document;
	}

	public void setCopy_of_document(Integer copy_of_document) {
		this.copy_of_document = copy_of_document;
	}

	public String getConstruction_year() {
		return construction_year;
	}

	public void setConstruction_year(String construction_year) {
		this.construction_year = construction_year;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public boolean isDelete() {
		return isDelete;
	}

	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}

	public EnvironmentClearence getEnvironmentClearence() {
		return environmentClearence;
	}

	public void setEnvironmentClearence(EnvironmentClearence environmentClearence) {
		this.environmentClearence = environmentClearence;
	}

	public String getProposalNo() {
		return proposalNo;
	}

	public void setProposalNo(String proposalNo) {
		this.proposalNo = proposalNo;
	}
  
	
	
}
