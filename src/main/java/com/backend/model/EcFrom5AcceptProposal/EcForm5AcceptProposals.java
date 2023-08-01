package com.backend.model.EcFrom5AcceptProposal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.backend.audit.Auditable;
import com.backend.model.DepartmentApplication;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Data;

@Data
@Entity
@Table(name = "ec_form_5_accept_proposals", schema = "master")
public class EcForm5AcceptProposals extends Auditable<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "ec_id", nullable = true)
	private Integer ec_id;

	@Column(name = "recommendation", length = 2000, nullable = true)
	private String remarks;

	@Column(name = "ph_date", length = 255, nullable = true)
	private String ph_date;
	
	@Transient
	@JsonProperty(access = Access.READ_ONLY)
	private DepartmentApplication departmentApplication;

	@Column(name = "publicHearingDetails", length = 2000, nullable = true)
	private String publicHearingDetails;

	@Column(name="is_notified",nullable = true)
	private Boolean isNotified=false;

	@Column(name="is_active")
	private Boolean isActive=true;

}
