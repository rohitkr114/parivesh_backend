package com.backend.model.EcFormVPart2Model;

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

import org.hibernate.annotations.Where;

import com.backend.audit.Auditable;
import com.backend.model.CommonFormDetail;
import com.backend.model.DepartmentApplication;
import com.backend.model.DocumentDetails;
import com.backend.model.ProponentApplications;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ec_form_v_part2", schema = "master")
public class EcFormVPart2 extends Auditable<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "formV_id", nullable = false)
	private Integer formV_id;

	@Column(name = "written_comment_number", nullable = true, length = 50)
	private Integer written_comment_number;

	@Column(name = "remarks", nullable = true, length = 500)
	private String remarks;

	@Column(name = "proposal_no", nullable = true)
	private String proposal_no;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "document_id", nullable = true)
	private DocumentDetails documents;

//	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@OneToMany(targetEntity=AdditionalDocuments.class,  cascade = CascadeType.ALL)
	@JoinColumn(name="ec_form_v_part2_id", referencedColumnName="id")
	@Where(clause = "is_deleted='false'")
	private List<AdditionalDocuments> additionalDocuments = new ArrayList<>();

	@OneToMany(targetEntity = EcFormVPart2PublicHearingdetails.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "ec_form_v_part2_id", referencedColumnName = "id")
	@Where(clause = "is_deleted='false'")
	private List<EcFormVPart2PublicHearingdetails> hearingDetails = new ArrayList<EcFormVPart2PublicHearingdetails>();

	@OneToMany(targetEntity = EcFormVpart2HearingIssues.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "ec_form_v_part2_id", referencedColumnName = "id")
	@Where(clause = "is_deleted='false'")
	private List<EcFormVpart2HearingIssues> hearingIssues = new ArrayList<EcFormVpart2HearingIssues>();

	@OneToOne(mappedBy = "ecFormVPart2")
	private EcFormVPart2Undertaking ecFormVPart2Undertaking;

	@Transient
	@JsonProperty(access = Access.READ_ONLY)
	private ProponentApplications proponentApplications;

	@Transient
	@JsonProperty(access = Access.READ_ONLY)
	private DepartmentApplication departmentApplication;

	@Column(name = "is_active")
	private Boolean is_active;

	@Column(name = "is_deleted")
	private Boolean is_deleted;

	public EcFormVPart2(Boolean is_active, Boolean is_deleted) {
		this.is_active = true;
		this.is_deleted = false;
	}

}
