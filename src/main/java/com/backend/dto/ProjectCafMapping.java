package com.backend.dto;

import java.util.List;

public class ProjectCafMapping {
	
	String swNo;
	String mainState;
	String projectName;
	Integer projectId;
	
	
	List<CAFMap> cafs;

	public List<CAFMap> getCafs() {
		return cafs;
	}

	public void setCafs(List<CAFMap> cafs) {
		this.cafs = cafs;
	}

	public String getSwNo() {
		return swNo;
	}

	public void setSwNo(String swNo) {
		this.swNo = swNo;
	}

	public String getMainState() {
		return mainState;
	}

	public void setMainState(String mainState) {
		this.mainState = mainState;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}
	
	
	

}
