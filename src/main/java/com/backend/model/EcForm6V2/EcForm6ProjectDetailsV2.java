package com.backend.model.EcForm6V2;

import java.util.ArrayList;
import java.util.List;

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
import javax.persistence.Transient;

import org.hibernate.annotations.Where;

import com.backend.audit.Auditable;
import com.backend.model.Activities;
import com.backend.model.Clearence;
import com.backend.model.CommonFormDetail;
import com.backend.model.EnvironmentClearence;
import com.backend.model.ProponentApplications;
import com.backend.model.EcPartC.EcPartC;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Data;

@Data
@Entity
@Table(name="ec_form_6_v2",schema="master")
public class EcForm6ProjectDetailsV2 extends Auditable<Integer> implements Clearence {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name="proposal_no", length=255)
	private String proposalNo;
	
	@Column(name="is_for_old_proposal",nullable=true)
	private Boolean isForOldProposal;
	
	@Column(name="proposal_old_for",nullable=true)
	private String proposalOldFor;
	
	@Column(name="project_name", length=1000)
	private String projectName;
	
	@Column(name="project_category")
	private String projectCategory;
	
	@Column(name="is_proposed_required")
	private Boolean isProposedRequired;
	
	@Column(name="reason_for_central_appraisal")
	private String reasonForCentralAppraisal;
	
	@Column(name="reason_for_central_appraisal_other")
	private String reasonForCentralAppraisalOther;
	
	@Column(name="is_multiple_item_involved",nullable=true)
	private Boolean isMultipleItemInvolved;
	
	@Column(name="major_activity_id")
	private Integer majorActivityId;
	
	@Column(name = "major_sub_activity_id", nullable = true)
	private Integer majorSubActivityId;
	
	@Column(name = "ec_id", nullable = false, updatable = false, insertable = false)
	@JsonProperty(access = Access.READ_ONLY)
	private Integer ec_id;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ec_id", nullable = true)
	@JsonIgnore
	private EcPartC ecPartc;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ec_part_a_id", nullable = true)
	@JsonIgnore
	private EnvironmentClearence environmentClearence;
	
	@Column(name = "ec_part_a_id", nullable = true, updatable = false, insertable = false)
	@JsonProperty(access = Access.READ_ONLY)
	private Integer ec_part_a_id;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ec_form6_activity_id", nullable = true)
	private Activities activities;
	
	@OneToMany(mappedBy="ecForm6",  cascade = CascadeType.ALL)
	@Where(clause="is_deleted=false")
	private List<EcForm6ProjectActivityDetailsV2> ecForm6ProjectActivityDetailsV2 = new ArrayList<EcForm6ProjectActivityDetailsV2>();
	
	@OneToOne(mappedBy = "ecForm6", cascade = CascadeType.ALL)
	@Where(clause = "is_deleted = false")
	private EcForm6AmendmentDetailsV2 amendmentDetails;
	
	@OneToMany(mappedBy = "ecForm6", cascade = CascadeType.ALL)
	@Where(clause = "is_deleted = false")
	private List<EcForm6UnitDetailsV2> unitDetails= new ArrayList<EcForm6UnitDetailsV2>();
	
	@OneToOne(mappedBy = "ecForm6", cascade = CascadeType.ALL)
	@Where(clause = "is_deleted = false")
	private EcForm6ConsultantV2 ecForm6Consultant;
	
	@OneToOne(mappedBy = "ecForm6", cascade = CascadeType.ALL)
	@Where(clause = "is_deleted = false")
	private EcForm6UndertakingV2 ecForm6Undertaking;
	
	@OneToOne(mappedBy="ecForm6", cascade = CascadeType.ALL)
	@Where(clause="is_deleted=false")
	private EcForm6ImplementationDetailsV2 ecForm6ImplementationDetailsV2;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "caf_id", nullable = false)
	@JsonIgnore
	private CommonFormDetail commonFormDetail;
	
	@Transient
	@JsonProperty(access = Access.READ_ONLY)
	private ProponentApplications proponentApplications;
	
	@Column(name="is_active")
	private Boolean isActive=true;
	
	@Column(name="is_deleted")
	private Boolean isDeleted=false;

}
