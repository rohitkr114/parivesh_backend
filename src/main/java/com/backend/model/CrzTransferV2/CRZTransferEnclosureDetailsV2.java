package com.backend.model.CrzTransferV2;

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

import com.backend.audit.Auditable;
import com.backend.model.DocumentDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name="crz_transfer_enclosure_details_v2",schema="master")
public class CRZTransferEnclosureDetailsV2 extends Auditable<Integer> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "transferor_affidavit_copy_id", nullable = true)
	private DocumentDetails transferorAffidavitCopy;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "transferee_affidavit_copy_id", nullable = true)
	private DocumentDetails transfereeAffidavitCopy;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "transferee_reason_id", nullable = true)
	private DocumentDetails transferReason;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "crz_transfer_id", nullable = false)
	@JsonIgnore
	private CRZTransferProposalDetailsV2 crzTransferProposalDetails;
	
	@Column(name="is_active")
	private Boolean isActive=true;
	
	@Column(name="is_deleted")
	private Boolean isDeleted=false; 
}
