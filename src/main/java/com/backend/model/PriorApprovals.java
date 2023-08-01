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
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import com.backend.audit.Auditable;
import com.backend.model.WildLifeClearance.WLOtherDetails;
import com.backend.model.WildLifeClearance.WildLifeClearance;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;

@Data
@Entity
@Table(name = "prior_approvals", schema = "master")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class PriorApprovals extends Auditable<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fc_id", nullable = true)
	@JsonIgnore
	private ForestClearance forestClearance;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "wl_id", nullable = true)
	@JsonIgnore
	private WildLifeClearance wildLifeClearance;
	
	/*@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "wl_other_id", nullable = true)
	@JsonIgnore
	private WLOtherDetails wlOtherDetails;*/
	
	@Column(nullable = true, length = 50)
	private String proposalNo;
	
	@Column(nullable = true, length = 50)
	private String prev_proposal_no;

	@Column(nullable = true, length = 350)
	private String proposalName;

	@Column(nullable = true)
	private String proposal_status;

	@Column(nullable = true, length = 60)
	private String moefccFileNo;

	@Column(nullable = true)
	private Double proposedDiversionArea;

	@Column(nullable = true)
	private Double divertedArea;

	@Column(nullable = true)
	private Date inPrincipleApprovalDate;

	@Column(nullable = true)
	private Date approvalDate;

	@Column(nullable = true)
	private Date applicationDate;

	@Column(nullable = true)
	private String entity_type;

	@Column(nullable = false)
	private boolean isDelete;

	PriorApprovals() {
		this.isDelete = false;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMoefccFileNo() {
		return moefccFileNo;
	}

	public void setMoefccFileNo(String moefccFileNo) {
		this.moefccFileNo = moefccFileNo;
	}

	public Double getProposedDiversionArea() {
		return proposedDiversionArea;
	}

	public void setProposedDiversionArea(Double proposedDiversionArea) {
		this.proposedDiversionArea = proposedDiversionArea;
	}

	public Double getDivertedArea() {
		return divertedArea;
	}

	public void setDivertedArea(Double divertedArea) {
		this.divertedArea = divertedArea;
	}

	public Date getInPrincipleApprovalDate() {
		return inPrincipleApprovalDate;
	}

	public void setInPrincipleApprovalDate(Date inPrincipleApprovalDate) {
		this.inPrincipleApprovalDate = inPrincipleApprovalDate;
	}

	public Date getApprovalDate() {
		return approvalDate;
	}

	public void setApprovalDate(Date approvalDate) {
		this.approvalDate = approvalDate;
	}

	public Date getApplicationDate() {
		return applicationDate;
	}

	public void setApplicationDate(Date applicationDate) {
		this.applicationDate = applicationDate;
	}

	public boolean isDelete() {
		return isDelete;
	}

	public void setDelete(boolean isDeleted) {
		this.isDelete = isDeleted;
	}

	public String getProposalNo() {
		return proposalNo;
	}

	public void setProposalNo(String proposalNo) {
		this.proposalNo = proposalNo;
	}

	public String getProposalName() {
		return proposalName;
	}

	public void setProposalName(String proposalName) {
		this.proposalName = proposalName;
	}

	public ForestClearance getForestClearance() {
		return forestClearance;
	}

	public void setForestClearance(ForestClearance forestClearance) {
		this.forestClearance = forestClearance;
	}

	public String getPrev_proposal_no() {
		return prev_proposal_no;
	}

	public void setPrev_proposal_no(String prev_proposal_no) {
		this.prev_proposal_no = prev_proposal_no;
	}

	public String getProposal_status() {
		return proposal_status;
	}

	public void setProposal_status(String proposal_status) {
		this.proposal_status = proposal_status;
	}

	public String getEntity_type() {
		return entity_type;
	}

	public void setEntity_type(String entity_type) {
		this.entity_type = entity_type;
	}

	public WildLifeClearance getWildLifeClearance() {
		return wildLifeClearance;
	}

	public void setWildLifeClearance(WildLifeClearance wildLifeClearance) {
		this.wildLifeClearance = wildLifeClearance;
	}

}
