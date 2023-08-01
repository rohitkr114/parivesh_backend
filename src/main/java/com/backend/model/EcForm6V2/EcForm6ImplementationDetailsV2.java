package com.backend.model.EcForm6V2;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.backend.audit.Auditable;
import com.backend.model.DocumentDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name="ec_form_6_implementation_details_v2",schema="master")
public class EcForm6ImplementationDetailsV2 extends Auditable<Integer> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private String moefccFileNo;
	
	private Date ecIssueDate;
	
	@Column(nullable=true, length=5000)
	private String reason;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "ec_letter_copy_id", nullable = true)
	private DocumentDetails ecLetterCopy;
	
	private Boolean isAmendmentRequired;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "chronology_clearance_document_id", nullable = true)
	private DocumentDetails chronologyClearanceDocument;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "consent_order_including_renewal_document_id", nullable = true)
	private DocumentDetails consentOrderIncludingRenewal;
	
	private String implementationStatus;
	
	@OneToMany(targetEntity=EcForm6ObtainedStatusV2.class,  cascade = CascadeType.ALL)
	@JoinColumn(name="ec_form6_obtained_status_id", referencedColumnName="id")
	private Set<EcForm6ObtainedStatusV2> ecForm6Obtainedstatus= new HashSet<EcForm6ObtainedStatusV2>();
	
	private String refNo;
	
	private Date consentDate;
	
	private Date validityOfConsent;
	
	private Date latestConsentDate;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "consent_copy_id", nullable = true)
	private DocumentDetails consentCopy;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "brief_note_id", nullable = true)
	private DocumentDetails briefNote;
	
	@OneToOne
	@JoinColumn(name="ec_form6_id",nullable=true)
	@JsonIgnore
	private EcForm6ProjectDetailsV2 ecForm6;
	
	@Column(name="is_active")
	private Boolean isActive=true;
	
	@Column(name="is_deleted")
	private Boolean isDeleted=false;
	
	

}
