package com.backend.model.EcForm8TransferOfTOR;

import com.backend.audit.Auditable;
import com.backend.model.Clearence;
import com.backend.model.CommonFormDetail;
import com.backend.model.EnvironmentClearence;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.backend.model.ProponentApplications;
import com.backend.model.EcForm7.EcForm7ProjectActivityDetails;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import javax.persistence.*;

import org.hibernate.annotations.Where;

import lombok.NoArgsConstructor;

/**
 * Project Detail information which is sec 1, 2, 3
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "ec_form8_transfer_of_tor", schema = "master")
public class EcForm8TransferOfTOR extends Auditable<Integer> implements Clearence {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "single_window_number", nullable = true)
	private String project_sw_no;

	@Column(name = "project_name", nullable = true)
	private String project_name;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "caf_id", nullable = true)
	@JsonIgnore
	private CommonFormDetail commonFormDetail;

	@Column(name = "proposal_no", nullable = true)
	private String proposal_no;

	@OneToOne
	@JoinColumn(name = "ec_id", nullable = true)
	private EnvironmentClearence environmentClearence;

	@Column(name = "ec_id", nullable = true, updatable = false, insertable = false)
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Integer ec_id;

	/**
	 * ########### TRANSFEREE DETAILS ################
	 */

	@Column(name = "application_made_by")
	private String application_made_by;

	@Column(name = "transferer_name", nullable = true)
	private String transferer_name;

	@Column(name = "transferer_street", nullable = true)
	private String transferer_street;

	@Column(name = "transferer_city", nullable = true)
	private String transferer_city;

	@Column(name = "transferer_district", nullable = true)
	private Integer transferer_district;

	@Column(name = "transferer_state", nullable = true)
	private Integer transferer_state;

	@Column(name = "transferer_pin_code", nullable = true)
	private Integer transferer_pincode;

	@Column(name = "transferer_landmarks", nullable = true)
	private String transferer_landmark;

	@Column(name = "transferer_email_address", nullable = true)
	private String transferer_email;

	@Column(name = "transferer_landline_address", nullable = true)
	private String transferer_landline_no;

	@Column(name = "transferer_mobile_number", nullable = true)
	private String transferer_mobile_no;

	@Column(name = "transferer_legal_status_of_company", nullable = true)
	private String transferer_legal_status;

	/**
	 * ########### TRANSFEREE DETAILS ################
	 */

	@Column(name = "transferee_organisation_name", nullable = true)
	private String transferee_name;

	@Column(name = "transferee_street_number_and_name", nullable = true)
	private String transferee_street;

	@Column(name = "transferee_village", nullable = true)
	private String transferee_city;

	@Column(name = "transferee_district", nullable = true)
	private Integer transferee_district;

	@Column(name = "transferee_state", nullable = true)
	private Integer transferee_state;

	@Column(name = "transferee_pincode", nullable = true)
	private Integer transferee_pincode;

	@Column(name = "transferee_landmarks", nullable = true)
	private String transferee_landmark;

	@Column(name = "transferee_email_address", nullable = true)
	private String transferee_email;

	@Column(name = "transferee_landline_address", nullable = true)
	private String transferee_landline_no;

	@Column(name = "transferee_mobile_number", nullable = true)
	private String transferee_mobile_no;

	@Column(name = "transferee_legal_status_of_company", nullable = true)
	private String transferee_legal_status;

	/**
	 * ########### APPLICANT DETAILS ################
	 */

	@Column(name = "applicant_name", length = 300)
	private String applicant_name;

	@Column(name = "applicant_designation", length = 300)
	private String applicant_designation;

	@Column(name = "applicant_street", length = 300)
	private String applicant_street;

	@Column(name = "applicant_city", length = 100)
	private String applicant_city;

	@Column(name = "applicant_district", length = 30)
	private Integer applicant_district;

	@Column(name = "applicant_state", length = 30)
	private Integer applicant_state;

	@Column(name = "applicant_pincode", length = 30)
	private Integer applicant_pincode;

	@Column(name = "applicant_landmark", length = 300)
	private String applicant_landmark;

	@Column(name = "applicant_email")
	private String applicant_email;

	@Column(name = "applicant_landline_no", length = 300)
	private String applicant_landline_no;

	@Column(name = "applicant_mobile_no", length = 10)
	private String applicant_mobile_no;

	@Transient
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private ProponentApplications proponentApplications;

	@OneToOne(mappedBy = "ecForm8TransferOfTOR")
	private EcForm8Undertaking ecForm8Undertaking;

	@OneToOne(mappedBy = "ecForm8TransferOfTOR")
	private EcForm8LocationOfProject ecForm8LocationOfProject;

	@OneToOne(mappedBy = "ecForm8TransferOfTOR")
	private ECForm8TransferCOP ecForm8TransferCOP;

	@OneToOne(mappedBy = "ecForm8TransferOfTOR")
	private EcForm8DocumentAttached ecForm8DocumentAttached;

	@OneToOne(mappedBy = "ecForm8TransferOfTOR")
	private EcForm8AdditionalDocument additional_documents;

	@OneToOne(mappedBy = "ecForm8TransferOfTOR")
	private EcForm8DetailOfTOR ecForm8DetailOfTOR;

	@OneToMany(mappedBy = "ecForm8TransferOfTOR", cascade = CascadeType.ALL)
	@Where(clause = "isDelete = 'false'")
	List<EcForm8ProjectActivityDetails> ecForm8ProjectActivityDetails = new ArrayList<>();

	@Column(name = "is_proposed_required", nullable = true)
	private Boolean is_proposed_required;
	
	@Column(nullable = true)
	private String project_category;

	@Column(name = "major_activity_id", nullable = true)
	private Integer major_activity_id;

	@Column(name = "major_sub_activity_id", nullable = true)
	private Integer major_sub_activity_id;
	
	@Column(nullable = true)
	private Boolean is_multiple_items_involved;
	
	@Column(nullable = true)
	private String ec_proposal_no;

	public EcForm8TransferOfTOR(Integer id, String proposal_no) {
		this.id = id;
		this.proposal_no = proposal_no;
	}
}