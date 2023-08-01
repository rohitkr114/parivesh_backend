package com.backend.model.CrzTransferV2;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Where;

import com.backend.audit.Auditable;
import com.backend.model.CommonFormDetail;
import com.backend.model.DocumentDetails;
import com.backend.model.ProponentApplications;
import com.backend.model.Crz.CrzBasicDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Data;

@Data
@Entity
@Table(name="crz_transfer_proposal_details_v2",schema="master")
public class CRZTransferProposalDetailsV2 extends Auditable<Integer> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name="proposal_no")
	private String proposalNo;
	
	@Column(name="file_no")
	private String fileNo;
	
	@Column(name="grant_date")
	private Date grantDate;
	
	@Column(name="validity_end_date")
	private Date validityEndDate;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "grant_letter_id", nullable = true)
	private DocumentDetails grantLetter;
	
	@Column(name="cin_no")
	private String cinNo;
	
	@Column(name="organization_name")
	private String organizationName;
	
	@Column(length=500)
	private String address;
	
	@Column(name="legal_status")
	private String legalStatus;
	
	@Column(name="joint_venture_details")
	private String jointVentureDetails;
	
	@OneToOne(mappedBy = "crzTransferProposalDetails", cascade = CascadeType.ALL)
	@Where(clause = "is_deleted = false")
	private CRZTransferUndertakingV2 crzTransferUndertaking;
	
	@OneToOne(mappedBy="crzTransferProposalDetails", cascade= CascadeType.ALL)
	@Where(clause="is_deleted=false")
	private CRZTransferEnclosureDetailsV2 crzTransferEnclosureDetailsV2;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "caf_id", nullable = false)
	@JsonIgnore
	private CommonFormDetail commonFormDetail;
	
	@Transient
	@JsonProperty(access = Access.READ_ONLY)
	private ProponentApplications proponentApplications;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "crz_id", nullable = true)
	@JsonIgnore
	private CrzBasicDetails crzBasicDetails;
	
	@Column(name = "crz_id", nullable = true, updatable = false, insertable = false)
	@JsonProperty(access = Access.READ_ONLY)
	private Integer crz_id;
	
	@Column(name="is_active")
	private Boolean isActive=true;
	
	@Column(name="is_deleted")
	private Boolean isDeleted=false; 

}
