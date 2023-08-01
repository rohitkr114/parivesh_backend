package com.backend.model.ForestClearanceE;

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

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Where;

import com.backend.audit.Auditable;
import com.backend.model.DocumentDetails;
import com.backend.model.ForestClearance;
import com.backend.model.ForestClearanceFormC.FcFormC;
import com.backend.model.ForestClearanceFormC.FcFormCProposedLand;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name="fc_form_e_other_details",schema = "master")
public class FcFormEOtherDetails extends Auditable<Integer>{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(nullable = true)
	private Boolean is_part_of_proposed_land_diverted;
	
	@Column(nullable = true)
	private Integer no_of_patches;
	
	@Column(nullable = true)
	private Double area_of_forest_land;
	
	@Column(nullable = true)
	private Boolean is_pa_esz_involved;

	@Column(nullable = true, length = 1000)
	private String is_pa_esz_involved_reason;

	// if Yes PA
	@Column(nullable = true)
	private String protected_area_type;

	@Column(nullable = true)
	private String pa_approval_status;

	@Column(nullable = true)
	private String pa_proposal_no;

	@Column(nullable = true)
	private Date pa_approval_date;

	@Column(nullable = true)
	private String pa_reference_no;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "pa_report_id", nullable = true)
	private DocumentDetails pa_report;

	@Column(nullable = true)
	private Date pa_application_date;

	@Column(nullable = true)
	private String pa_non_submission_reason;

	// if No
	@Column(nullable = true)
	private Boolean esz_nbwl_approval_required;

	// ESZ
	@Column(nullable = true)
	private String esz_approval_status;

	@Column(nullable = true)
	private String esz_proposal_no;

	@Column(nullable = true)
	private Date esz_approval_date;

	@Column(nullable = true)
	private String esz_reference_no;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "esz_report_id", nullable = true)
	private DocumentDetails esz_report;

	@Column(nullable = true)
	private Date esz_application_date;

	@Column(nullable = true, length = 1000)
	private String esz_non_submission_reason;

	@Column(nullable = true, length = 1000)
	private String esz_non_nbwl_approval_reason;

	@Column(nullable = true)
	private Boolean is_within_scheduled_area;
//
//	@OneToMany(mappedBy = "fcFormEOtherDetails", cascade = CascadeType.ALL)
//	@Fetch(FetchMode.SELECT)
//	@Where(clause = "is_delete='false'")
//	List<FcFormEPatchDetails> fcFormEPatchDetails= new ArrayList<>();
	
	@Column(nullable = true)
	private Double distance_from_nearest_eco_area;
	
	@Column(nullable = true)
	private Double distance_from_nearest_protected_area;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fc_form_e_id", nullable = true)
	@JsonIgnore
	private FcFormE fcFormE;

	private Boolean is_delete;
	
	private Boolean is_active;
	
	public FcFormEOtherDetails() {
		this.is_active=true;
		this.is_delete=false;
	}

	public FcFormEOtherDetails(Integer id) {
		this.id = id;
	}

	public FcFormEOtherDetails(Integer id,Double distance_from_nearest_eco_area,Double distance_from_nearest_protected_area,Boolean is_part_of_proposed_land_diverted, Integer no_of_patches,
			Double area_of_forest_land, Boolean is_pa_esz_involved, String is_pa_esz_involved_reason,
			String protected_area_type, String pa_approval_status, String pa_proposal_no, Date pa_approval_date,
			String pa_reference_no, DocumentDetails pa_report, Date pa_application_date,
			String pa_non_submission_reason, Boolean esz_nbwl_approval_required, String esz_approval_status,
			String esz_proposal_no, Date esz_approval_date, String esz_reference_no, DocumentDetails esz_report,
			Date esz_application_date, String esz_non_submission_reason, String esz_non_nbwl_approval_reason,
			Boolean is_within_scheduled_area) {
		this.id = id;
		this.distance_from_nearest_eco_area=distance_from_nearest_eco_area;
		this.distance_from_nearest_protected_area=distance_from_nearest_protected_area;
		this.is_part_of_proposed_land_diverted = is_part_of_proposed_land_diverted;
		this.no_of_patches = no_of_patches;
		this.area_of_forest_land = area_of_forest_land;
		this.is_pa_esz_involved = is_pa_esz_involved;
		this.is_pa_esz_involved_reason = is_pa_esz_involved_reason;
		this.protected_area_type = protected_area_type;
		this.pa_approval_status = pa_approval_status;
		this.pa_proposal_no = pa_proposal_no;
		this.pa_approval_date = pa_approval_date;
		this.pa_reference_no = pa_reference_no;
		this.pa_report = pa_report;
		this.pa_application_date = pa_application_date;
		this.pa_non_submission_reason = pa_non_submission_reason;
		this.esz_nbwl_approval_required = esz_nbwl_approval_required;
		this.esz_approval_status = esz_approval_status;
		this.esz_proposal_no = esz_proposal_no;
		this.esz_approval_date = esz_approval_date;
		this.esz_reference_no = esz_reference_no;
		this.esz_report = esz_report;
		this.esz_application_date = esz_application_date;
		this.esz_non_submission_reason = esz_non_submission_reason;
		this.esz_non_nbwl_approval_reason = esz_non_nbwl_approval_reason;
		this.is_within_scheduled_area = is_within_scheduled_area;
	}
	
}
