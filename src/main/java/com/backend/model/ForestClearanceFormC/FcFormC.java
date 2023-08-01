package com.backend.model.ForestClearanceFormC;

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

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Where;

import com.backend.audit.Auditable;
import com.backend.model.Clearence;
import com.backend.model.CommonFormDetail;
import com.backend.model.DocumentDetails;
import com.backend.model.ProponentApplications;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Data;

@Data
@Entity
@Table(name = "fc_form_c", schema = "master")
public class FcFormC extends Auditable<Integer> implements Clearence {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(nullable = true)
	private Integer state;

	@Column(nullable = true)
	private Boolean is_project_falls_within_protected_area;

	@Column(nullable = true)
	private Double total_prospecting_lease_area;

	@Column(nullable = true)
	private Date grant_prospecting_lease_date;

	@Column(nullable = true)
	private Double non_forest_proposed_exploration_area;

	@Column(nullable = true)
	private Double forest_proposed_exploration_area;

	@Transient
	@JsonProperty(access = Access.READ_ONLY)
	private ProponentApplications proponentApplications;

	@Column(nullable = true)
	private Boolean is_proposal_seeking_prior_approval;

	@Column(nullable = true)
	private String status_of_proposal;

	@Column(nullable = true)
	private Integer total_proposed_diversion_period;

	@Column(nullable = true, length = 500)
	private String legal_status_of_forest_land_other;

	@Column(nullable = true)
	private Double total_non_forest_land;

	@Column(nullable = true)
	private Double total_area_of_kmls;

	@Column(nullable = true)
	private Double legal_status_forest_land_area;

	@Column(name = "proposal_no", nullable = true,length=350)
	private String proposal_no;

	@OneToMany(mappedBy = "fcFormC", cascade = CascadeType.ALL)
	@Where(clause = "is_delete='false'")
	List<FcFormCPriorApproval> fcFormCPriorApprovals = new ArrayList<>();

	@OneToOne(targetEntity = CommonFormDetail.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "caf_id", nullable = true)
	private CommonFormDetail commonFormDetail;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "letter_of_intent_id", nullable = true)
	private DocumentDetails letter_of_intent_copy;

	@OneToOne(mappedBy = "fcFormC")
	private FcFormCProposedLand fcFormCProposedLand;

	@OneToOne(mappedBy = "fcFormC")
	private FcFormCOtherDetails fcFormCOtherDetails;

	@OneToOne(mappedBy = "fcFormC")
	private FcFormCAfforestationDetails fcFormCAfforestationDetails;

	@OneToOne(mappedBy = "fcFormC")
	private FcFormCActivitiesDetails fcFormCActivitiesDetails;

	@OneToOne(mappedBy = "fcFormC")
	private FcFormCLandDetails fcFormCLandDetails;

	@OneToOne(mappedBy = "fcFormC")
	private FcFormCUndertaking fcFormCUndertaking;

	@OneToMany(mappedBy = "fcFormC", cascade = CascadeType.ALL)
	@Fetch(FetchMode.SELECT)
	@Where(clause = "is_delete='false'")
	List<FcFormCProposedDiversions> fcFormCProposedDiversions = new ArrayList<>();

	@OneToMany(mappedBy = "fcFormC", cascade = CascadeType.ALL)
	@Fetch(FetchMode.SELECT)
	@Where(clause = "is_deleted='false'")
	List<FcFormCPatchKmls> fcFormCPatchKmls = new ArrayList<>();

	// Changes as per new Models

	@OneToOne(mappedBy = "fcFormC")
	private FcFormCProposedLandN fcFormCProposedLandN;

	@OneToOne(mappedBy = "fcFormC")
	private FcFormCActivitiesDetailsN fcFormCActivitiesDetailsN;

	@OneToOne(mappedBy = "fcFormC")
	private FcFormCLandDetailsN fcFormCLandDetailsN;

	@OneToMany(mappedBy = "fcFormC", cascade = CascadeType.ALL)
	@Where(clause = "is_delete='false'")
	List<FcFormCSurfaceSampling> fcFormCSamplings = new ArrayList<>();

	@OneToMany(mappedBy = "fcFormC", cascade = CascadeType.ALL)
	@Where(clause = "is_delete='false'")
	List<FcFormCDetailsOfMachinery> fcFormCDetailsOfMachineries = new ArrayList<>();
	
	@OneToMany(mappedBy = "fcFormC", cascade = CascadeType.ALL)
	@Where(clause = "is_delete='false'")
	List<FcFormCComplianceReport> fcFormCComplianceReports = new ArrayList<>();

	@OneToMany(mappedBy = "fcFormC", cascade = CascadeType.ALL)
	@Where(clause = "is_delete='false'")
	List<FcFormCExploredForestLand> fcFormCExploredForestLands = new ArrayList<>();

	@Column(nullable = true)
	private Boolean is_active;

	@Column(nullable = true)
	private Boolean is_deleted;

	@Column(nullable = true)
	private Integer parent_id;

	public FcFormC() {
		this.is_active = true;
		this.is_deleted = false;
	}

	public FcFormC(Integer FcId) {
		this.id = FcId;
	}

	/*
	 * public FcFormC(Integer id, Integer state, Boolean
	 * is_project_falls_within_protected_area, Double total_prospecting_lease_area,
	 * Date grant_prospecting_lease_date, Integer
	 * total_proposed_diversion_period,String legal_status_of_forest_land_other,
	 * Double total_non_forest_land, Double total_area_of_kmls,Double
	 * legal_status_forest_land_area, Double non_forest_proposed_exploration_area,
	 * Double forest_proposed_exploration_area,String proposal_no) { super();
	 * this.id = id; this.state = state; this.is_project_falls_within_protected_area
	 * = is_project_falls_within_protected_area; this.total_prospecting_lease_area =
	 * total_prospecting_lease_area; this.grant_prospecting_lease_date =
	 * grant_prospecting_lease_date; this.total_proposed_diversion_period =
	 * total_proposed_diversion_period; this.legal_status_of_forest_land_other =
	 * legal_status_of_forest_land_other; this.total_non_forest_land =
	 * total_non_forest_land; this.total_area_of_kmls = total_area_of_kmls;
	 * this.legal_status_forest_land_area=legal_status_forest_land_area;
	 * this.non_forest_proposed_exploration_area =
	 * non_forest_proposed_exploration_area; this.forest_proposed_exploration_area =
	 * forest_proposed_exploration_area; this.proposal_no=proposal_no; }
	 */

	public FcFormC(Integer id, Integer state, Boolean is_project_falls_within_protected_area,
			Double total_prospecting_lease_area, Date grant_prospecting_lease_date,
			Boolean is_proposal_seeking_prior_approval, String status_of_proposal, String proposal_no,
			Double non_forest_proposed_exploration_area, Double forest_proposed_exploration_area) {
		super();
		this.id = id;
		this.state = state;
		this.is_project_falls_within_protected_area = is_project_falls_within_protected_area;
		this.total_prospecting_lease_area = total_prospecting_lease_area;
		this.grant_prospecting_lease_date = grant_prospecting_lease_date;
		this.is_proposal_seeking_prior_approval = is_proposal_seeking_prior_approval;
		this.status_of_proposal = status_of_proposal;
		this.proposal_no = proposal_no;
		this.non_forest_proposed_exploration_area = non_forest_proposed_exploration_area;
		this.forest_proposed_exploration_area = forest_proposed_exploration_area;
	}

}
