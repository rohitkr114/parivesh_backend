package com.backend.model.EcFormVModel;

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
import javax.persistence.Transient;

import com.backend.audit.Auditable;
import com.backend.model.Clearence;
import com.backend.model.CommonFormDetail;
import com.backend.model.DocumentDetails;
import com.backend.model.ProponentApplications;
import com.backend.model.EcPartC.EcUndertaking;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
@Table(name = "ec_form_v", schema = "master")
public class EcFormV extends Auditable<Integer> implements Clearence {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(nullable = true)
	private Integer ec_id;
	
	@Column(name = "nature_of_tor", nullable = true)
	private String nature_of_tor;

	@Column(name = "date_of_issue_tor", nullable = true)
	private Date date_of_issue_tor;

	@Column(name = "date_of_issue_additional_tor", nullable = true)
	private Date date_of_issue_additional_tor;

	@Column(name = "moef_file_no", nullable = true, length = 100)
	private String moef_file_no;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "tor_letter_id", nullable = true)
	private DocumentDetails tor_letter;

	@Column(name = "is_any_amendment_tor", nullable = true)
	private Boolean is_any_amendment_tor;

	@Column(name = "date_of_issue_amendment_tor", nullable = true)
	private Date date_of_issue_amendment_tor;

	@Column(name = "amendment_details", nullable = true, length = 500)
	private String amendment_details;

	@Column(name = "proposal_no", nullable = true)
	private String proposal_no;

	@Transient
	@JsonProperty(access = Access.READ_ONLY)
	private ProponentApplications proponentApplications;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "tor_letter_copy_id", nullable = true)
	private DocumentDetails tor_letter_copy;

	@Column(name = "public_hearing_number", nullable = true, length = 200)
	private String public_hearing_number;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "form_five_eia_emp_id", nullable = true)
	private DocumentDetails form_five_eia_emp;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "form_five_executive_summary_id", nullable = true)
	private DocumentDetails form_five_executive_summary;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "form_five_report_vernacular_id", nullable = true)
	private DocumentDetails form_five_report_vernacular;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "form_five_location_map_id", nullable = true)
	private DocumentDetails form_five_location_map;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "caf_id", nullable = false)
	@JsonIgnore
	private CommonFormDetail commonFormDetail;

	@OneToOne(mappedBy = "ecFormV")
	private EcFormVUndertaking ecFormVUndertaking;

	private Boolean is_active;

	private Boolean is_delete;

	public EcFormV() {
		this.is_active = true;
		this.is_delete = false;
	}

	public EcFormV(Integer id, String nature_of_tor, Date date_of_issue_tor, Date date_of_issue_additional_tor,
			String moef_file_no, Boolean is_any_amendment_tor, Date date_of_issue_amendment_tor,
			String amendment_details, String proposal_no, String public_hearing_number, Boolean is_active,
			Boolean is_delete) {
		this.id = id;
		this.nature_of_tor = nature_of_tor;
		this.date_of_issue_tor = date_of_issue_tor;
		this.date_of_issue_additional_tor = date_of_issue_additional_tor;
		this.moef_file_no = moef_file_no;
		this.is_any_amendment_tor = is_any_amendment_tor;
		this.date_of_issue_amendment_tor = date_of_issue_amendment_tor;
		this.amendment_details = amendment_details;
		this.proposal_no = proposal_no;
		this.public_hearing_number = public_hearing_number;
		this.is_active = is_active;
		this.is_delete = is_delete;
	}

	public EcFormV(Integer id) {
		this.id = id;
	}

}
