package com.backend.model.EcSVReport;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
import com.backend.model.DepartmentApplication;
import com.backend.model.EcForm7.EcForm7EcStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Data;

@Data
@Entity
@Table(name = "ec_site_visit_report", schema = "master")
public class EcSiteVisitReport extends Auditable<Integer> {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "ec_id", nullable = true)
	private Integer ec_id;
	
	@Column(name = "crz_id", nullable = true)
	private Integer crz_id;

	@Column(name = "caf_id", nullable = true)
	private Integer caf_id;

	@Column(name = "recommendation", length = 1000, nullable = true)
	private String recommendation;

	@Column(name = "committee_observation", length = 1000, nullable = true)
	private String committee_observation;

	@Column(name = "esign", length = 255, nullable = true)
	private String esign;

	@Column(name = "additional_details", length = 500, nullable = true)
	private String additional_details;

	@Column(name = "proposal_no", nullable = true)
	private String proposal_no;

	@Column(name = "is_any_violation")
	private Boolean is_any_violation;

	@Column(name = "details_of_violation", length = 500)
	private String details_of_violation;

	@Column(name = "details_of_action", length = 500)
	private String details_of_action;

	@Column(name = "description_or_nature", length = 500)
	private String description_or_nature;

	@Column(name = "salient_feature", length = 500)
	private String salient_feature;

	@Column(name = "state", length = 255)
	private String state;

	@Column(name = "district", length = 255)
	private String district;

	@Column(name = "sub_district", length = 255)
	private String sub_district;

	@Column(name = "village", length = 255)
	private String village;

	@Column(name = "latitude", length = 255)
	private String latitude;

	@Column(name = "longitude", length = 255)
	private String longitude;

	@OneToMany(targetEntity = EcSiteVisitCommittee.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "ecSVRId", referencedColumnName = "id")
	private Set<EcSiteVisitCommittee> ecSiteVisitCommittee = new HashSet<>();

	@Transient
	@JsonProperty(access = Access.READ_ONLY)
	private DepartmentApplication departmentApplication;

	private Boolean is_active;

	private Boolean is_delete;

	public EcSiteVisitReport() {
		this.is_active = true;
		this.is_delete = false;
	}

}
