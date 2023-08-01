package com.backend.dto;

import java.util.List;

public class ProjectProponentApplicant {
    
	String project_name;
	
	String sw_no;
	
	List<Approvals> approvals;

	public String getProject_name() {
		return project_name;
	}

	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}

	public String getSw_no() {
		return sw_no;
	}

	public void setSw_no(String sw_no) {
		this.sw_no = sw_no;
	}

	public List<Approvals> getApprovals() {
		return approvals;
	}

	public void setApprovals(List<Approvals> approvals) {
		this.approvals = approvals;
	}
	
	
	
	
	
}
