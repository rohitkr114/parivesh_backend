package com.backend.model.FcFormAPartIIINodal;

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
import javax.persistence.Transient;

import org.hibernate.annotations.Where;

import com.backend.audit.Auditable;
import com.backend.model.DepartmentApplication;
import com.backend.model.DocumentDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Data;

@Data
@Entity
@Table(name = "fc_form_A_part_III_nodal_basic_details", schema = "authority")
public class FcFormAPartIIINodalBasicDetails extends Auditable<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(nullable = true)
	private Integer proposal_for;

	@Column(nullable = true)
	private Integer project_id;

	@Column(length = 100, nullable = true)
	private String sw_no;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "site_inspection_report_id")
	private DocumentDetails siteInspectionReport;

	@Column(name = "total_forest_area_applied")
	private Double totalForestAreaApplied;

	@Column(name = "deliberated_at_PSC")
	private boolean deliberatedAtPSC;

	@Column(name = "recommendation_status")
	private String recommendationStatus;

	@Column(name = "recommended_area")
	private Double recommendedArea;

	@Column(name = "forest_comes_under_LWEOFCLAC")
	private boolean forestComesUnderLWEOFCLAC;
	
	
	@Column(name = "justification",length=5000)
	private String justification;
	
	@Column(nullable = true)
	private Boolean is_right_way_applicable ;
	
	@Column(nullable = true)
	private String proposal_status;
	
	@Column(nullable = true)
	private String in_principal_approval_date;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "in_principal_letter_copy_id", nullable = true)
	private DocumentDetails in_principal_letter_copy;
	
	@Column(nullable = true)
	private String return_date;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "returning_letter_copy_id", nullable = true)
	private DocumentDetails returning_letter_copy;
	
	@Column(nullable = true)
	private String withdrawn_date;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "withdrawn_letter_copy_id", nullable = true)
	private DocumentDetails withdrawn_letter_copy;
	
	@Column(nullable = true)
	private String rejection_date;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "rejection_letter_copy_id", nullable = true)
	private DocumentDetails rejection_letter_copy;
	
	@Column(nullable = true)
	private String closed_date;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "closed_letter_copy_id", nullable = true)
	private DocumentDetails closed_letter_copy;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "upload_signed_clearance_letter_id", nullable=true)
	private DocumentDetails uploadSignedClearanceLetter;
	
	private Boolean is_active;

	private Boolean is_deleted;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fc_form_a_part_iii_nodal_ckecklist_details_id", nullable = false)
	@JsonIgnore
	private FcFormAPartIIINodalCheckListDetails fcFormAPartIIINodalCheckListDetails;

	public FcFormAPartIIINodalBasicDetails() {
		this.is_active = true;
		this.is_deleted = false;
	}

}
