package com.backend.model.EcForm7;

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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.backend.audit.Auditable;
import com.backend.model.DocumentDetails;
import com.backend.model.ForestClearanceFormB.FcFormBLegalStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "ec_form_7_attached_documents", schema = "master")
public class EcForm7AttachedDocuments extends Auditable<Integer>{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@OneToOne
	@JoinColumn(name = "ec_form_7_id", nullable = true)
	@JsonIgnore
	@EqualsAndHashCode.Exclude
	private EcForm7 ecForm7;

	@OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinColumn(name = "objection_from_transferor_copy_id", nullable = true)
	private DocumentDetails objection_from_transferor_copy;

	@OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinColumn(name = "undertaking_by_transferee_copy_id", nullable = true)
	private DocumentDetails undertaking_by_transferee_copy;

	@OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinColumn(name = "transfer_from_competent_copy_id", nullable = true)
	private DocumentDetails transfer_from_competent_copy;
	
	@OneToMany(targetEntity = EcForm7AdditionalInforDetails.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "ec_form_7_attached_documents_id", referencedColumnName = "id")
	@EqualsAndHashCode.Exclude
	private Set<EcForm7AdditionalInforDetails> ecForm7AdditionalInforDetails = new HashSet<>();
	
	@OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinColumn(name = "previous_compliance_report_id", nullable = true)
	private DocumentDetails previous_compliance_report;
	
	@Column(nullable = true)
	private Boolean is_delete;

	@Column(nullable = true)
	private Boolean is_active;
	
	public EcForm7AttachedDocuments() {
		this.is_active=true;
		this.is_delete=false;
	}

	public EcForm7AttachedDocuments(Integer id) {
		this.id = id;
	}
	
}
