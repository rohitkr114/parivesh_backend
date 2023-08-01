package com.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ConsultIndividual {

	 @JsonProperty("IndividualId")
	 private long IndividualId;
	 
	 @JsonProperty("NameOfConsultant")
	 private String NameOfConsultant;
	 
	 @JsonProperty("RoleOfIndividual")
	 private String RoleOfIndividual;
	 
	 @JsonProperty("Engagement")
	 private String Engagement;
	 
	 @JsonProperty("PanNo")
	 private String PanNo;
	 
	 @JsonProperty("MobileNo")
	 private String MobileNo;
	 
	 @JsonProperty("EmailId")
	 private String EmailId;
	 
	 @JsonProperty("Category")
	 private String Category;
	 
	 @JsonProperty("SectorsOfAccreditation")
	 private String SectorsOfAccreditation;
	 
	 @JsonProperty("ValidityOfAccreditation")
	 private String ValidityOfAccreditation;
	 
	 @JsonProperty("Address")
	 private String Address;
	 
	 @JsonProperty("ValididityFlag")
	 private String ValididityFlag;

	public long getIndividualId() {
		return IndividualId;
	}

	public void setIndividualId(long individualId) {
		IndividualId = individualId;
	}

	public String getNameOfConsultant() {
		return NameOfConsultant;
	}

	public void setNameOfConsultant(String nameOfConsultant) {
		NameOfConsultant = nameOfConsultant;
	}

	public String getRoleOfIndividual() {
		return RoleOfIndividual;
	}

	public void setRoleOfIndividual(String roleOfIndividual) {
		RoleOfIndividual = roleOfIndividual;
	}

	public String getEngagement() {
		return Engagement;
	}

	public void setEngagement(String engagement) {
		Engagement = engagement;
	}

	public String getPanNo() {
		return PanNo;
	}

	public void setPanNo(String panNo) {
		PanNo = panNo;
	}

	public String getMobileNo() {
		return MobileNo;
	}

	public void setMobileNo(String mobileNo) {
		MobileNo = mobileNo;
	}

	public String getEmailId() {
		return EmailId;
	}

	public void setEmailId(String emailId) {
		EmailId = emailId;
	}

	public String getCategory() {
		return Category;
	}

	public void setCategory(String category) {
		Category = category;
	}

	public String getSectorsOfAccreditation() {
		return SectorsOfAccreditation;
	}

	public void setSectorsOfAccreditation(String sectorsOfAccreditation) {
		SectorsOfAccreditation = sectorsOfAccreditation;
	}

	public String getValidityOfAccreditation() {
		return ValidityOfAccreditation;
	}

	public void setValidityOfAccreditation(String validityOfAccreditation) {
		ValidityOfAccreditation = validityOfAccreditation;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getValididityFlag() {
		return ValididityFlag;
	}

	public void setValididityFlag(String valididityFlag) {
		ValididityFlag = valididityFlag;
	}
	 
    








}





