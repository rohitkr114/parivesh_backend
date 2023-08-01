package com.backend.dto;


import com.backend.model.Clearence;
import com.backend.model.ProponentApplications;

public class ClearenceCafData {

	ProponentApplications proponentApplications;
	
	Clearence clearence;
	
	

	
	public Clearence getClearence() {
		return clearence;
	}

	public void setClearence(Clearence clearence) {
		this.clearence = clearence;
	}

	public ProponentApplications getProponentApplications() {
		return proponentApplications;
	}

	public void setProponentApplications(ProponentApplications proponentApplications) {
		this.proponentApplications = proponentApplications;
	}
	
	
	
	
}
