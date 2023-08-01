package com.backend.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Where;

import com.backend.audit.Auditable;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@Entity
@Table(name="consultant_organisation_qci_dump",schema = "master")
public class ConsultantOrganisationQCIDump{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@JsonProperty("OrgId")
	@Column(name="OrgId",nullable = true)
	private String OrgId;
	
	@JsonProperty("AccreditationNo")
	@Column(name="AccreditationNo",nullable = true)
	private String AccreditationNo;
	
	@JsonProperty("OrganizationName")
	@Column(name="OrganizationName",nullable = true)
	private String OrganizationName;
	
	@JsonProperty("OrganizationHead")
	@Column(name="OrganizationHead",nullable = true)
	private String OrganizationHead;
	
	@JsonProperty("Designation")
	@Column(name="Designation",nullable = true)
	private String Designation;
	
	@JsonProperty("Address")
	@Column(name="Address",nullable = true)
	private String Address;
	
	@JsonProperty("LandlineNo")
	@Column(name="LandlineNo",nullable = true)
	private String LandlineNo;
	
	@JsonProperty("MobileNo")
	@Column(name="MobileNo",nullable = true)
	private String MobileNo;
	
	@JsonProperty("EmailId")
	@Column(name="EmailId",nullable = true)
	private String EmailId;
	
	@JsonProperty("SectorsOfAccreditation")
	@Column(name="SectorsOfAccreditation",nullable = true)
	private String SectorsOfAccreditation;
	
	@JsonProperty("ValidityOfAccreditation")
	@Column(name="ValidityOfAccreditation",nullable = true)
	private String ValidityOfAccreditation;
	
	@JsonProperty("PanNo")
	@Column(name="PanNo",nullable = true)
	private String PanNo;
	
	@JsonProperty("Category")
	@Column(name="Category",nullable = true)
	private String Category;
	
	@JsonProperty("ValididityFlag")
	@Column(name="ValididityFlag",nullable = true)
	private String ValididityFlag;
	

	@OneToMany(mappedBy = "consultantOrganisationQCIDump", cascade = CascadeType.ALL)
	@Fetch(FetchMode.SELECT)
	List<ConsultantIndividualQCIDump> Individuals = new ArrayList<>();
	
}
