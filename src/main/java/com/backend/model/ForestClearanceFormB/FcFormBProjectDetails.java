package com.backend.model.ForestClearanceFormB;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
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
import javax.persistence.Transient;

import org.hibernate.annotations.Where;

import com.backend.audit.Auditable;
import com.backend.model.Clearence;
import com.backend.model.CommonFormDetail;
import com.backend.model.ProponentApplications;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Data;

@Data
@Entity
@Table(name = "fc_form_b", schema = "master")
public class FcFormBProjectDetails extends Auditable<Integer> implements Clearence {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(nullable = true)
	private Integer fc_id;

	@Column(nullable = true)
	private Integer state_code;

	@Column(nullable = true, length = 500)
	private String project_activity_id_other;

	@Column(nullable = true)
	private String project_category_code;

	@Column(nullable = true)
	private String project_category_id;
	
	@Column(length = 50, nullable = true)
	private String proposal_no;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "caf_id", nullable = true)
	private CommonFormDetail commonFormDetail; 
	
	@OneToMany(mappedBy = "fcFormBProjectDetails", cascade = CascadeType.ALL)
	@Where(clause = "is_deleted ='false'")
	private List<FcFormBPriorProposals> fcFormBPriorProposals = new ArrayList<>();

	@OneToMany(mappedBy = "fcFormBProjectDetails", cascade = CascadeType.ALL)
	@Where(clause = "is_deleted ='false'")
	private List<FcFormBComplianceDetails> fcFormBComplianceDetails = new ArrayList<>();
	
	@OneToMany(mappedBy = "fcFormBProjectDetails", cascade = CascadeType.ALL)
	@Where(clause = "is_deleted ='false'")
	private List<FcFormBPaymentDetails> fcFormBPaymentDetails = new ArrayList<>();
	
	@OneToOne(mappedBy = "fcFormBProjectDetails", cascade = CascadeType.ALL)
	@Where(clause = "is_deleted ='false'")
	private FcFormBApprovalDetails fcFormBApprovalDetails;
	
	@OneToMany(mappedBy = "fcFormBProjectDetails", cascade = CascadeType.ALL)
	@Where(clause = "is_deleted ='false'")
	private List<FcFormBPatchAreaDetails> fcFormBPatchAreaDetails = new ArrayList<>();
	
	@OneToMany(mappedBy = "fcFormBProjectDetails", cascade = CascadeType.ALL)
	@Where(clause = "is_deleted ='false'")
	private List<FcFormBLeaseDetails> fcFormBLeaseDetails = new ArrayList<>();
	
	@OneToOne(mappedBy = "fcFormBProjectDetails", cascade = CascadeType.ALL)
	@Where(clause = "is_deleted ='false'")
	private FcFormBProposedLand formBProposedLands;
	
	@OneToOne(mappedBy = "fcFormBProjectDetails", cascade = CascadeType.ALL)
	@Where(clause = "is_deleted ='false'")
	private FcFormBAfforestationDetails fcFormBAfforestationDetails;
	
//	@OneToMany(mappedBy = "fcFormBProjectDetails", cascade = CascadeType.ALL)
//	@Where(clause = "is_deleted ='false'")
//	private List<FcFormBProposedDiversionsDetails> fcFormBProposedDiversionsDetails = new ArrayList<>();
	
	@OneToMany(mappedBy = "fcFormBProjectDetails", cascade = CascadeType.ALL)
	@Where(clause = "is_delete ='false'")
	private List<FcFormBProposedDiversions> fcFormBProposedDiversions = new ArrayList<>();
	
//	@OneToMany(mappedBy = "fcFormBProjectDetails", cascade = CascadeType.ALL)
//	@Where(clause = "is_deleted ='false'")
//	private List<FcFormBDivisionPatchDetails> fcFormBDivisionPatchDetails = new ArrayList<>();
	
//	@OneToMany(targetEntity = FcFormBAirportProposal.class, cascade = CascadeType.ALL)
//	@JoinColumn(name = "fc_form_b_id", referencedColumnName = "id")
//	@Where(clause = "is_deleted ='false'")
//	private Set<FcFormBAirportProposal> fcFormBAirportProposals = new HashSet<>();
	
	@OneToMany(mappedBy = "fcFormBProjectDetails", cascade = CascadeType.ALL)
	@Where(clause = "is_deleted ='false'")
	private List<FcFormBComponentDetails> fcFormBComponentDetails = new ArrayList<>();
//	
//	@OneToMany(targetEntity = FcFormBCroppingPattern.class, cascade = CascadeType.ALL)
//	@JoinColumn(name = "fc_form_b_id", referencedColumnName = "id")
//	@Where(clause = "is_deleted ='false'")
//	private Set<FcFormBCroppingPattern> fcFormBCroppingPatterns = new HashSet<>();
//	
//	@OneToMany(targetEntity = FcFormBIrrigationProjectCapacityVillages.class, cascade = CascadeType.ALL)
//	@JoinColumn(name = "fc_form_b_id", referencedColumnName = "id")
//	@Where(clause = "is_deleted ='false'")
//	private Set<FcFormBIrrigationProjectCapacityVillages> fcFormBIrrigationProjectCapacityVillages = new HashSet<>();
	
	@OneToOne(mappedBy = "fcFormBProjectDetails", cascade = CascadeType.ALL)
	@Where(clause = "is_deleted ='false'")
	private FcFormBMiningProposals fcFormBMiningProposals;

	@OneToOne(mappedBy = "fcFormBProjectDetails", cascade = CascadeType.ALL)
	@Where(clause = "is_deleted ='false'")
	private FcFormBOthersDetail fcFormBOthersDetails;
	
	@OneToOne(mappedBy = "fcFormBProjectDetails", cascade = CascadeType.ALL)
	@Where(clause = "is_deleted ='false'")
	private FcFormBUndertaking fcFormBUndertaking;
//	
//	@OneToMany(targetEntity = FcFormBRiverValleyProject.class, cascade = CascadeType.ALL)
//	@JoinColumn(name = "fc_form_b_id", referencedColumnName = "id")
//	@Where(clause = "is_deleted ='false'")
//	private Set<FcFormBRiverValleyProject> fcFormBRiverValleyProjects = new HashSet<>();
//	
//	@OneToMany(targetEntity = FcFormBSubmergedArea.class, cascade = CascadeType.ALL)
//	@JoinColumn(name = "fc_form_b_id", referencedColumnName = "id")
//	@Where(clause = "is_deleted ='false'")
//	private Set<FcFormBSubmergedArea> fcFormBSubmergedAreas = new HashSet<>();
	
	@Transient
	@JsonProperty(access = Access.READ_ONLY)
	private ProponentApplications proponentApplications;

	@Column(nullable = true)
	private Integer parent_id;
	
	private Boolean is_active;

	private Boolean is_deleted;

	public FcFormBProjectDetails() {
		this.is_active=true;
		this.is_deleted=false;
	}

	public FcFormBProjectDetails(Integer id, Integer fc_id, Integer state_code, String project_activity_id_other, String project_category_code,
			String project_category_id, String proposal_no) {
		this.id = id;
		this.fc_id = fc_id;
		this.state_code = state_code;
		this.project_activity_id_other = project_activity_id_other;
		this.project_category_code = project_category_code;
		this.project_category_id = project_category_id;
		this.proposal_no = proposal_no;
	}
	public FcFormBProjectDetails(Integer id){
		this.id = id;
	}

	public FcFormBProjectDetails(Integer id, String proposal_no) {
		this.id = id;
		this.proposal_no = proposal_no;
	}
	
}
