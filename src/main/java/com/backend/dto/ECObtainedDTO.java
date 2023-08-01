package com.backend.dto;

public class ECObtainedDTO {
	
	private String MoefccFileNo;
	private String DateOfIssueEC;
	private String DateOfApplication;
	private String Message;
	
	
	public String getMoefccFileNo() {
		return MoefccFileNo;
	}
	public void setMoefccFileNo(String moefccFileNo) {
		MoefccFileNo = moefccFileNo;
	}
	public String getDateOfIssueEC() {
		return DateOfIssueEC;
	}
	public void setDateOfIssueEC(String dateOfIssueEC) {
		DateOfIssueEC = dateOfIssueEC;
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
