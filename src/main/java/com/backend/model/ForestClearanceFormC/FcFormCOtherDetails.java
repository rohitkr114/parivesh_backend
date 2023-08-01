package com.backend.model.ForestClearanceFormC;

import java.util.Date;

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
import com.backend.model.DocumentDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name="fc_form_c_other_details",schema = "master")
public class FcFormCOtherDetails extends Auditable<Integer>{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(nullable = true)
	private Boolean is_ec_required;
	
	@Column(nullable = true)
	private String ec_application_status;
	
	@Column(nullable = true,length = 1000)
	private String ec_proposal_no;
	
	@Column(nullable = true)
	private Date ec_issued_date;
	
	@Column(nullable = true)
	private String ec_moefcc_file_no;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "ec_letter_id", nullable = true)
	private DocumentDetails ec_letter;
	
	@Column(nullable = true)
	private Date ec_submission_date;
	
	@Column(nullable = true)
	private String ec_application_sub_status;

	@Column(nullable = true, length = 1000)
	private String ec_non_submission_reason;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fc_form_c_id", nullable = true)
	@JsonIgnore
	private FcFormC fcFormC;
	
	@Column(nullable = true)
	private Boolean is_active;
	
	@Column(nullable = true)
	private Boolean is_delete;
	
	public FcFormCOtherDetails() {
		this.is_active=true;
		this.is_delete=false;
	}
	
	public FcFormCOtherDetails(Integer id) {
		this.id=id;
	}

	public FcFormCOtherDetails(Integer id,Boolean is_ec_required,
			String ec_application_status, String ec_proposal_no, Date ec_issued_date, String ec_moefcc_file_no,
			Date ec_submission_date,String ec_application_sub_status, String ec_non_submission_reason) {
		super();
		this.id = id;
		this.is_ec_required = is_ec_required;
		this.ec_application_status = ec_application_status;
		this.ec_proposal_no = ec_proposal_no;
		this.ec_issued_date = ec_issued_date;
		this.ec_moefcc_file_no = ec_moefcc_file_no;
		this.ec_submission_date = ec_submission_date;
		this.ec_application_sub_status = ec_application_sub_status;
		this.ec_non_submission_reason = ec_non_submission_reason;
	}
	
	
}
