package com.backend.model.FormDPartIV;

import com.backend.audit.Auditable;
import com.backend.model.DepartmentApplication;
import com.backend.model.DocumentDetails;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "fc_form_D_part_IV_basic_details", schema = "authority")
public class FcFormDPartIVBasicDetails extends Auditable<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(nullable = false)
	private Integer fc_id;

	private String proposal_number;

	@Column(nullable = true)
	private Integer proposal_for;

	@Column(nullable = true)
	private Integer project_id;

	@Column(nullable = true)
	private String status;

	@Column(length = 100, nullable = true)
	private String sw_no;

	@Column(name = "recommendation_status")
	private String recommendationStatus;

	@Column(name = "recommended_area")
	private Double recommendedArea;

	@Column(name = "state_file_no")
	private String stateFileNo;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "letter_of_recommendation")
	private DocumentDetails letterOfRecommendations;

	@Column(name = "signing_authority")
	private String signingAuthority;

	@Column(name = "proposal_status")
	private String proposalStatus;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_of_in_principle_approval")
	private Date dateOfInPrincipleApproval;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "upload_notification_document")
	private DocumentDetails uploadNotificationDocument;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_of_rejection")
	private Date dateOfRejection;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "letter_of_rejection")
	private DocumentDetails letterOfRejection;

	@Column(name = "justification",length=5000)
	private String justification;

	// part 3
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "upload_signed_clearance_letter")
	private DocumentDetails uploadSignedClearanceLetter;

	private Boolean is_active;

	private Boolean is_deleted;

	@Transient
	@JsonProperty(access = Access.READ_ONLY)
	private DepartmentApplication departmentApplication;

	public FcFormDPartIVBasicDetails() {
		this.is_active = true;
		this.is_deleted = false;
	}
}