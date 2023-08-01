package com.backend.dto;

import java.util.Date;

public class ProponentApplicant {

	String clearanceName;
	String proposalNo;
	String state;
	String lastStatus;
	Date updatedOn;
	Integer updatedBy;
	public String getClearanceName() {
		return clearanceName;
	}
	public void setClearanceName(String clearanceName) {
		this.clearanceName = clearanceName;
	}
	public String getProposalNo() {
		return proposalNo;
	}
	public void setProposalNo(String proposalNo) {
		this.proposalNo = proposalNo;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getLastStatus() {
		return lastStatus;
	}
	public void setLastStatus(String lastStatus) {
		this.lastStatus = lastStatus;
	}
	public Date getUpdatedOn() {
		return updatedOn;
	}
	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}
	public Integer getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}
	
	
	
	
}
