package com.backend.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.backend.audit.Auditable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@Entity
@Table(name = "consultant_individual_qci_dump", schema = "master")
public class ConsultantIndividualQCIDump{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@JsonProperty("IndividualId")
	@Column(name = "IndividualId", nullable = true)
	private long IndividualId;

	@JsonProperty("NameOfConsultant")
	@Column(name = "NameOfConsultant", nullable = true)
	private String NameOfConsultant;

	@JsonProperty("RoleOfIndividual")
	@Column(name = "RoleOfIndividual", nullable = true)
	private String RoleOfIndividual;

	@JsonProperty("Engagement")
	@Column(name = "Engagement", nullable = true)
	private String Engagement;

	@JsonProperty("PanNo")
	@Column(name = "PanNo", nullable = true)
	private String PanNo;

	@JsonProperty("MobileNo")
	@Column(name = "MobileNo", nullable = true)
	private String MobileNo;

	@JsonProperty("EmailId")
	@Column(name = "EmailId", nullable = true)
	private String EmailId;

	@JsonProperty("Category")
	@Column(name = "Category", nullable = true)
	private String Category;

	@JsonProperty("SectorsOfAccreditation")
	@Column(name = "SectorsOfAccreditation", nullable = true)
	private String SectorsOfAccreditation;

	@JsonProperty("ValidityOfAccreditation")
	@Column(name = "ValidityOfAccreditation", nullable = true)
	private String ValidityOfAccreditation;

	@JsonProperty("Address")
	@Column(name = "Address", nullable = true)
	private String Address;

	@JsonProperty("ValididityFlag")
	@Column(name = "ValididityFlag", nullable = true)
	private String ValididityFlag;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "consultantOrganisation_id")
	@JsonIgnore
	private ConsultantOrganisationQCIDump consultantOrganisationQCIDump;
}
