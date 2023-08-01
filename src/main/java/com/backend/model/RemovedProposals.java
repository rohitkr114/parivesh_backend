package com.backend.model;

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

import lombok.Data;

@Data
@Entity
@Table(name="removed_proposals", schema="master")
public class RemovedProposals extends Auditable<Integer> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name="proponent_application_id",nullable=false)
	private Integer proponentApplicationId;
	
	@Column(name="proposal_no")
	private String proposalNo;
	
	private String reason;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "document_id", nullable = true)
	private DocumentDetails document;
	
	@Column(name="is_active", nullable=true)
	private Boolean isActive=true;

}
