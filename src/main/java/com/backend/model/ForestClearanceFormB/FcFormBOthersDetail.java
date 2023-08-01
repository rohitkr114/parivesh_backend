package com.backend.model.ForestClearanceFormB;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.backend.audit.Auditable;
import com.backend.model.DocumentDetails;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;

@Data
@Entity
@Table(name = "fc_form_b_others_detail", schema = "master")
@JsonIgnoreProperties({ "created_by", "created_on", "updated_by", "updated_on" })
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class FcFormBOthersDetail extends Auditable<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@OneToOne
	@JoinColumn(name = "fc_form_b_id", nullable = true)
	@JsonIgnore
	private FcFormBProjectDetails fcFormBProjectDetails;

	@Column(nullable = true)
	private Boolean is_cost_analyzed;

	@Column(nullable = true)
	private double total_loss;

	@Column(nullable = true)
	private Double total_benefit;

	@Column(nullable = true)
	private Double cost_benefit_ratio;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "cost_benefit_report", nullable = true)
	private DocumentDetails cost_benefit_report;

	@Column(nullable = true, length = 1000)
	private String cost_not_analyzed_reason;

	// EC
	@Column(nullable = true)
	private Boolean is_ec_required;

	@Column(nullable = true)
	private String ec_application_status;

	@Column(nullable = true)
	private String ec_proposal_no;

	@Column(nullable = true)
	private Date ec_issued_date;

	@Column(nullable = true)
	private String ec_moefcc_file_no;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "ec_letter", nullable = true)
	private DocumentDetails ec_letter;

	@Column(nullable = true)
	private Date ec_submission_date;

	@Column(nullable = true)
	private Double distance_from_nearest_eco_area;

	@Column(nullable = true)
	private Double distance_from_nearest_protected_area;

	@Column(nullable = true)
	private String ec_application_sub_status;

	@Column(nullable = true, length = 1000)
	private String ec_non_submission_reason;

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
	@JoinColumn(name = "pa_report", nullable = true)
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
	@JoinColumn(name = "esz_report", nullable = true)
	private DocumentDetails esz_report;

	@Column(nullable = true)
	private Date esz_application_date;

	@Column(nullable = true, length = 1000)
	private String esz_non_submission_reason;

	@Column(nullable = true, length = 1000)
	private String esz_non_nbwl_approval_reason;

	@Column(nullable = true)
	private Boolean is_within_scheduled_area;
	
	private Boolean is_active;

	private Boolean is_deleted;

	public FcFormBOthersDetail() {
		this.is_active=true;
		this.is_deleted=false;
	}

	public FcFormBOthersDetail(Integer id) {
		this.id = id;
	}
	
}
