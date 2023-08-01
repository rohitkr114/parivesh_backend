package com.backend.dto;

public class FCGratedDTO {

	private String ProjectName;
	private String MoefccFileNo;
	private Double AreaProposedForDiversion;
	private Double AreaRecommendedForDiversion;
	private Double AreaDiverted;
	private String DateOfApproval;
	private String DateOfApplication;
	//private String ProposalStatus;
	private String Message;
	
	
	
	public String getProjectName() {
		return ProjectName;
	}
	public void setProjectName(String projectName) {
		ProjectName = projectName;
	}
	public String getMoefccFileNo() {
		return MoefccFileNo;
	}
	public void setMoefccFileNo(String moefccFileNo) {
		MoefccFileNo = moefccFileNo;
	}
	
	public Double getAreaProposedForDiversion() {
		return AreaProposedForDiversion;
	}
	public void setAreaProposedForDiversion(Double areaProposedForDiversion) {
		AreaProposedForDiversion = areaProposedForDiversion;
	}

	public Double getAreaRecommendedForDiversion() {
		return AreaRecommendedForDiversion;
	}
	public void setAreaRecommendedForDiversion(Double areaRecommendedForDiversion) {
		AreaRecommendedForDiversion = areaRecommendedForDiversion;
	}
	public Double getAreaDiverted() {
		return AreaDiverted;
	}
	public void setAreaDiverted(Double areaDiverted) {
		AreaDiverted = areaDiverted;
	}
	public String getDateOfApproval() {
		return DateOfApproval;
	}
	public void setDateOfApproval(String dateOfApproval) {
		DateOfApproval = dateOfApproval;
	}
	public String getDateOfApplication() {
		return DateOfApplication;
	}
	public void setDateOfApplication(String dateOfApplication) {
		DateOfApplication = dateOfApplication;
	}
	public String getMessage() {
		return Message;
	}
	public void setMessage(String message) {
		Message = message;
	}


	
}
