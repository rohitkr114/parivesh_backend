package com.backend.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "Employee", schema = "master")
@PrimaryKeyJoinColumn(referencedColumnName = "entityid")
@JsonIgnoreProperties({ "hibernateLazyInitializer" })
public class Employee extends User {

	@Column(name = "landline_No", nullable = true)
	private String landline_No;

	@Column(nullable = true, length = 10)
	private String std_code;

	@Column(name = "pan_no")
	private String pan_no;

	@Column(name = "map_multiple_projects")
	private Boolean Map_multiple_Projects;

	@Column(name = "website")
	private String website;

	@Column(name = "organisation_id", nullable = false, updatable = false, insertable = false)
	private Integer organisation_id;

	@Column(name = "designation", length = 50)
	private String Designation;

	@Column(name = "role")
	private String role;

	@Column(name = "is_aadhar_linked")
	private Boolean is_aadhar_linked;

	@Column(name = "verify_status", nullable = true)
	private String verify_status;

	@Column(name = "project_name")
	private String project_name;

	@ManyToMany(mappedBy = "employees")
//	@JsonBackReference(value = "project_employee_ref")
	private Set<ProjectDetails> projectDetails = new HashSet<>();

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "organisation_id", nullable = false)
//	@JsonBackReference(value = "organisation_reference")
	private Organisation organisation;

	public Employee() {
	}

	public Employee(ProjectProponentEntity entity, String type, HttpServletRequest request) {
		super(entity.getId(), entity.getName_of_Contact_Person(), entity.getAddress(), entity.getEmail(),
				entity.getMobile(), entity.getState_ut(), entity.getDistrict(), entity.getPincode(), type, request);
		this.landline_No = entity.getLandline_No();
		this.pan_no = entity.getPan_no();
		this.Map_multiple_Projects = true;
		this.website = entity.getWebsite();
		this.is_aadhar_linked = true;
		this.std_code = entity.getStd_code();
		this.Designation = entity.getDesignation();
		this.role = entity.getRole();
	}

	public Employee(ProjectProponentOther entity2, String type, HttpServletRequest request) {
		super(entity2.getId(), entity2.getName_of_Entity(), entity2.getAddress(), entity2.getEmail(),
				entity2.getMobile(), entity2.getState_ut(), entity2.getDistrict(), entity2.getPincode(), type, request);
		this.landline_No = entity2.getLandline_No();
		this.pan_no = entity2.getPan_no();
		this.Map_multiple_Projects = true;
		this.website = entity2.getWebsite();
		this.is_aadhar_linked = true;
		this.std_code = entity2.getStd_code();
	}

	public Employee(ProjectProponentGovernment entity2, String type, HttpServletRequest request) {
		super(entity2.getId(), entity2.getName_of_Contact_Person(), entity2.getAddress(), entity2.getEmail(),
				entity2.getMobile(), entity2.getState_ut(), entity2.getDistrict(), entity2.getPincode(), type, request);
		this.landline_No = entity2.getLandline_No();
		this.pan_no = entity2.getPan_no();
		this.Map_multiple_Projects = true;
		this.website = entity2.getWebsite();
		this.is_aadhar_linked = true;
		this.std_code = entity2.getStd_code();
	}

	public Employee(ProjectProponentIndividual individual2, String type, HttpServletRequest request) {
		super(individual2.getId(), individual2.getName_of_Entity(), individual2.getAddress(), individual2.getEmail(),
				individual2.getMobile(), individual2.getState_ut(), individual2.getDistrict(), individual2.getPincode(),
				type, request);
		this.landline_No = individual2.getLandline_No();
		this.pan_no = individual2.getPan_no();
		this.Map_multiple_Projects = true;
		this.website = individual2.getWebsite();
		this.is_aadhar_linked = true;
		this.std_code = individual2.getStd_code();
	}

	public String getLandline_No() {
		return landline_No;
	}

	public void setLandline_No(String landline_No) {
		this.landline_No = landline_No;
	}

	public String getPan_no() {
		return pan_no;
	}

	public void setPan_no(String pan_no) {
		this.pan_no = pan_no;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public Integer getOrganisation_id() {
		return organisation_id;
	}

	public void setOrganisation_id(Integer organisation_id) {
		this.organisation_id = organisation_id;
	}

	public String getDesignation() {
		return Designation;
	}

	public void setDesignation(String designation) {
		Designation = designation;
	}

	public Set<ProjectDetails> getProjectDetails() {
		return projectDetails;
	}

	public void setProjectDetails(Set<ProjectDetails> projectDetails) {
		this.projectDetails = projectDetails;
	}

	public Organisation getOrganisation() {
		return organisation;
	}

	public void setOrganisation(Organisation organisation) {
		this.organisation = organisation;
	}

}
