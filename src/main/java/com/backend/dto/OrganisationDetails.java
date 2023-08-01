package com.backend.dto;

import lombok.Data;

@Data
public class OrganisationDetails {

	public String getOrganization_name() {
		return organization_name;
	}

	public void setOrganization_name(String organization_name) {
		this.organization_name = organization_name;
	}

	public String getOrganization_street() {
		return organization_street;
	}

	public void setOrganization_street(String organization_street) {
		this.organization_street = organization_street;
	}

	public String getOrganization_city() {
		return organization_city;
	}

	public void setOrganization_city(String organization_city) {
		this.organization_city = organization_city;
	}

	public String getOrganization_district() {
		return organization_district;
	}

	public void setOrganization_district(String organization_district) {
		this.organization_district = organization_district;
	}

	public String getOrganization_state() {
		return organization_state;
	}

	public void setOrganization_state(String organization_state) {
		this.organization_state = organization_state;
	}

	public String getOrganization_pincode() {
		return organization_pincode;
	}

	public void setOrganization_pincode(String organization_pincode) {
		this.organization_pincode = organization_pincode;
	}

	public String getOrganization_landmark() {
		return organization_landmark;
	}

	public void setOrganization_landmark(String organization_landmark) {
		this.organization_landmark = organization_landmark;
	}

	public String getOrganization_email() {
		return organization_email;
	}

	public void setOrganization_email(String organization_email) {
		this.organization_email = organization_email;
	}

	public String getOrganization_landline_no() {
		return organization_landline_no;
	}

	public void setOrganization_landline_no(String organization_landline_no) {
		this.organization_landline_no = organization_landline_no;
	}

	public String getOrganization_mobile_no() {
		return organization_mobile_no;
	}

	public void setOrganization_mobile_no(String organization_mobile_no) {
		this.organization_mobile_no = organization_mobile_no;
	}

	public ApplicantDetails getApplicantDetails() {
		return applicantDetails;
	}

	public void setApplicantDetails(ApplicantDetails applicantDetails) {
		this.applicantDetails = applicantDetails;
	}

	private String organization_name;
	private String organization_street;
	private String organization_city;
	private String organization_district;
	private String organization_state;
	private String organization_pincode;
	private String organization_landmark;
	private String organization_email;
	private String organization_landline_no;
	private String organization_mobile_no;
	
	private ApplicantDetails applicantDetails;
}
