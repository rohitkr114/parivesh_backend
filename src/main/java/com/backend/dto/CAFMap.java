package com.backend.dto;

import java.util.Date;
import java.util.List;

import com.backend.model.ProponentApplications;

public class CAFMap {

	private Date updatedOn;
	private String cafNo;
	private Integer cafId;
	List<ProponentApplicant> proponentApplicants;

	

	public List<ProponentApplicant> getProponentApplicants() {
		return proponentApplicants;
	}

	public void setProponentApplicants(List<ProponentApplicant> proponentApplicants) {
		this.proponentApplicants = proponentApplicants;
	}

    

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	public String getCafNo() {
		return cafNo;
	}

	public void setCafNo(String cafNo) {
		this.cafNo = cafNo;
	}

	public Integer getCafId() {
		return cafId;
	}

	public void setCafId(Integer cafId) {
		this.cafId = cafId;
	}


	
	
}
