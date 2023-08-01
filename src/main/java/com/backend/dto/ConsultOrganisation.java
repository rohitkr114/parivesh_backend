package com.backend.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ConsultOrganisation {

	@JsonProperty("OrgId")
	private String OrgId;
	
	@JsonProperty("AccreditationNo")
	private String AccreditationNo;
	
	@JsonProperty("OrganizationName")
	private String OrganizationName;
	
	@JsonProperty("OrganizationHead")
	private String OrganizationHead;
	
	@JsonProperty("Designation")
	private String Designation;
	
	@JsonProperty("Address")
	private String Address;
	
	@JsonProperty("LandlineNo")
	private String LandlineNo;
	
	@JsonProperty("MobileNo")
	private String MobileNo;
	
	@JsonProperty("EmailId")
	private String EmailId;
	
	@JsonProperty("SectorsOfAccreditation")
	private String SectorsOfAccreditation;
	
	@JsonProperty("ValidityOfAccreditation")
	private String ValidityOfAccreditation;
	
	@JsonProperty("PanNo")
	private String PanNo;
	
	@JsonProperty("Category")
	private String Category;
	
	@JsonProperty("ValididityFlag")
	private String ValididityFlag;
	
	@JsonProperty("Individuals")
	List<ConsultIndividual> Individuals = new ArrayList<>();

	public String getOrgId() {
		return OrgId;
	}

	public void setOrgId(String orgId) {
		OrgId = orgId;
	}

	public String getAccreditationNo() {
		return AccreditationNo;
	}

	public void setAccreditationNo(String accreditationNo) {
		AccreditationNo = accreditationNo;
	}

	public String getOrganizationName() {
		return OrganizationName;
	}

	public void setOrganizationName(String organizationName) {
		OrganizationName = organizationName;
	}

	public String getOrganizationHead() {
		return OrganizationHead;
	}

	public void setOrganizationHead(String organizationHead) {
		OrganizationHead = organizationHead;
	}

	public String getDesignation() {
		return Designation;
	}

	public void setDesignation(String designation) {
		Designation = designation;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getLandlineNo() {
		return LandlineNo;
	}

	public void setLandlineNo(String landlineNo) {
		LandlineNo = landlineNo;
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

	public String getPanNo() {
		return PanNo;
	}

	public void setPanNo(String panNo) {
		PanNo = panNo;
	}

	public String getCategory() {
		return Category;
	}

	public void setCategory(String category) {
		Category = category;
	}

	public String getValididityFlag() {
		return ValididityFlag;
	}

	public void setValididityFlag(String valididityFlag) {
		ValididityFlag = valididityFlag;
	}

	public List<ConsultIndividual> getIndividuals() {
		return Individuals;
	}

	public void setIndividuals(List<ConsultIndividual> individuals) {
		Individuals = individuals;
	}
	
	
	
	
	
	
}
