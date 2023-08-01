package com.backend.model.EcForm11;

import java.util.ArrayList;
import java.util.Date;
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

import com.backend.model.*;
import org.hibernate.annotations.Where;

import com.backend.audit.Auditable;
import com.backend.model.EcPartC.EcPartC;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Data;

@Data
@Entity
@Table(name="ec_form_11_project_details",schema="master")
public class EcForm11ProjectDetails extends Auditable<Integer> implements Clearence {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name="proposal_no",length=50)
	private String proposalNo;
	
	@Column(name="project_name",length=500)
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
	
	@Column(name = "ec_id", nullable = true, updatable = false, insertable = false)
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
	private Integer ecPartAId;
	
//	@OneToOne(fetch = FetchType.EAGER)
//	@JoinColumn(name = "ec_form6_activity_id", nullable = true)
//	private Activities activities;
	
	@OneToMany(mappedBy = "ecForm11ProjectDetails",cascade = CascadeType.ALL)
	@Where(clause = "is_deleted = false")
	private List<EcForm11ProjectActivityDetails> ecForm11ProjectActivityDetails= new ArrayList<EcForm11ProjectActivityDetails>();

	@OneToOne(mappedBy="ecForm11ProjectDetails", cascade = CascadeType.ALL)
	@Where(clause = "is_deleted = false")
	private EcForm11OtherDetails ecForm11OtherDetails;
	
	@OneToMany(mappedBy = "ecForm11ProjectDetails",cascade = CascadeType.ALL)
	@Where(clause = "is_deleted = false")
	private List<EcForm11SPCBDetails> ecForm11SPCBDetails= new ArrayList<EcForm11SPCBDetails>();
	
	@OneToOne(mappedBy="ecForm11ProjectDetails", cascade = CascadeType.ALL)
	@Where(clause = "is_deleted = false")
	private EcForm11Undertaking ecForm11Undertaking;
	
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
