package com.backend.model.EcForm9;

import java.sql.Date;

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
import com.backend.model.CommonFormDetail;
import com.backend.model.DocumentDetails;
import com.backend.model.EnvironmentClearence;
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
@Table(name = "ec_form_9", schema = "master")
public class EcForm9Basicdetails extends Auditable<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ec_id", nullable = true)
	@JsonIgnore
	private EnvironmentClearence environmentClearence;

	@Column(name = "proposal_for", length = 100, nullable = true)
	private String proposal_for;

	@Column(name = "proposal_catg", length = 20, nullable = true)
	private String proposal_catg;

	@Column(name = "moefcc_file_no", length = 100, nullable = true)
	private String moefcc_file_no;

	@Column(name = "date_of_ec_issue", nullable = true)
	private Date date_of_ec_issue;

	@Column(name = "is_ec_issue_under_eia", nullable = true)
	private Boolean is_ec_issue_under_eia;

	@Column(name = "mine_lease_area", nullable = true)
	private Double mine_lease_area;

	@Column(name = "mine_production_capacity", nullable = true)
	private Double mine_production_capacity;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "kml", nullable = true)
	private DocumentDetails kml;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "caf_id", nullable = false)
	@JsonIgnore
	private CommonFormDetail commonFormDetail;

	@Column(name = "vesting_ref_no", length = 100, nullable = true)
	private String vesting_ref_no;

	@Column(name = "lease_allocation_date", nullable = true)
	private Date lease_allocation_date;

	@Column(name = "lease_area", nullable = true)
	private Double lease_area;

	@Column(name = "lease_validity_end_date", nullable = true)
	private Date lease_validity_end_date;

	@Column(name = "vesting_production_capacity", nullable = true)
	private Double vesting_production_capacity;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "vesting_copy", nullable = true)
	private DocumentDetails vesting_copy;

	@OneToOne(mappedBy = "ecForm9")
	private EcForm9Undertaking ecForm9Undertaking;

	@Column(name = "proposal_no", nullable = true)
	private String proposal_no;

	@Transient
	@JsonProperty(access = Access.READ_ONLY)
	private ProponentApplications proponentApplications;

	@Column(name = "is_active", nullable = true)
	private Boolean is_active;

	@Column(name = "is_delete", nullable = true)
	private Boolean is_delete;

	public EcForm9Basicdetails(Boolean is_active, Boolean is_delete) {
		this.is_active = true;
		this.is_delete = false;
	}

	public EcForm9Basicdetails(Integer id, String proposal_for, String proposal_catg, String moefcc_file_no,
			Date date_of_ec_issue, Boolean is_ec_issue_under_eia, Double mine_lease_area,
			Double mine_production_capacity, DocumentDetails kml, String vesting_ref_no, Date lease_allocation_date,
			Double lease_area, Date lease_validity_end_date, Double vesting_production_capacity,
			DocumentDetails vesting_copy, String proposal_no, Boolean is_active, Boolean is_delete) {
		super();
		this.id = id;
		this.proposal_for = proposal_for;
		this.proposal_catg = proposal_catg;
		this.moefcc_file_no = moefcc_file_no;
		this.date_of_ec_issue = date_of_ec_issue;
		this.is_ec_issue_under_eia = is_ec_issue_under_eia;
		this.mine_lease_area = mine_lease_area;
		this.mine_production_capacity = mine_production_capacity;
		this.kml = kml;
		this.vesting_ref_no = vesting_ref_no;
		this.lease_allocation_date = lease_allocation_date;
		this.lease_area = lease_area;
		this.lease_validity_end_date = lease_validity_end_date;
		this.vesting_production_capacity = vesting_production_capacity;
		this.vesting_copy = vesting_copy;
		this.proposal_no = proposal_no;
		this.is_active = is_active;
		this.is_delete = is_delete;
	}

}
