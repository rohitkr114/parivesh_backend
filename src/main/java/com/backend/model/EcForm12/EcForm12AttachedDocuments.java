package com.backend.model.EcForm12;

import com.backend.audit.Auditable;
import com.backend.model.DocumentDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "ec_form_12_attached_documents", schema = "master")
public class EcForm12AttachedDocuments extends Auditable<Integer>{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@OneToOne
	@JoinColumn(name = "ec_form_12_id", nullable = true)
	@JsonIgnore
	@EqualsAndHashCode.Exclude
	private EcForm12 ecForm12;

	@OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinColumn(name = "objection_from_transferor_copy_id", nullable = true)
	private DocumentDetails objection_from_transferor_copy;

	@OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinColumn(name = "undertaking_by_transferee_copy_id", nullable = true)
	private DocumentDetails undertaking_by_transferee_copy;

	@OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinColumn(name = "transfer_from_competent_copy_id", nullable = true)
	private DocumentDetails transfer_from_competent_copy;
	
	@OneToMany(targetEntity = EcForm12AdditionalInforDetails.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "ec_form_12_attached_documents_id", referencedColumnName = "id")
	@EqualsAndHashCode.Exclude
	private Set<EcForm12AdditionalInforDetails> ecForm12AdditionalInforDetails = new HashSet<>();
	
	@OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinColumn(name = "previous_compliance_report_id", nullable = true)
	private DocumentDetails previous_compliance_report;
	
	@Column(nullable = true)
	private Boolean is_delete;

	@Column(nullable = true)
	private Boolean is_active;
	
	public EcForm12AttachedDocuments() {
		this.is_active=true;
		this.is_delete=false;
	}

	public EcForm12AttachedDocuments(Integer id) {
		this.id = id;
	}
	
}
