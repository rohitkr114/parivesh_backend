package com.backend.model.CrzTransfer;

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

import com.backend.audit.Auditable;
import com.backend.model.CommonFormDetail;
import com.backend.model.DocumentDetails;
import com.backend.model.ProponentApplications;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
@Table(name = "crz_transfer_form", schema = "master")
public class CrzTransferDetails extends Auditable<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(nullable = true)
	private String proposal_no;

	@Column(nullable = true)
	private String file_no;

	@Column(nullable = true)
	private Date grant_date;

	@Column(nullable = true)
	private Date validity_end_date;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "clearance_letter_id", nullable = true)
	private DocumentDetails clearance_letter;
	
	@Column(nullable = true)
	private String cin_number;
	
	@Column(nullable = true)
	private String org_name;

	@Column(nullable = true)
	private String address;

	@Column(nullable = true)
	private String legal_status;

	@Column(nullable = true)
	private String joint_venture_details;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "affidavit_by_transferor_id", nullable = true)
	private DocumentDetails affidavit_by_transferor;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "affidavit_by_transferee_id", nullable = true)
	private DocumentDetails affidavit_by_transferee;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "reason_of_transfer_id", nullable = true)
	private DocumentDetails reason_of_transfer;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "additional_documents_id", nullable = true)
	private DocumentDetails additional_documents;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "caf_id", nullable = false)
	@JsonIgnore
	private CommonFormDetail commonFormDetail;

	@Transient
	@JsonProperty(access = Access.READ_ONLY)
	private ProponentApplications proponentApplications;

	@OneToOne(mappedBy = "crzTransferDetails")
	private CrzTransferUndertaking crzTransferUndertaking;
	
	@Column(name = "is_active", nullable = true)
	private Boolean is_active;

	@Column(name = "is_delete", nullable = true)
	private Boolean is_delete;

	public CrzTransferDetails() {
		this.is_active = true;
		this.is_delete = false;
	}
}
