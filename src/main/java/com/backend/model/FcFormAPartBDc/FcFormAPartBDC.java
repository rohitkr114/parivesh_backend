package com.backend.model.FcFormAPartBDc;

import java.util.ArrayList;
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
import com.backend.model.DepartmentApplication;
import com.backend.model.DocumentDetails;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Data;

@Data
@Entity
@Table(name = "fc_form_a_part_b_dc", schema = "master")
public class FcFormAPartBDC extends Auditable<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "fc_id", nullable = true)
	private Integer fc_id;

	@Column(name = "is_land_verified", nullable = true)
	private Boolean is_land_verified;

	@Column(name = "verification_remarks", length = 1000, nullable = true)
	private String verification_remarks;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "verification_report_id", nullable = true)
	private DocumentDetails verification_report;

	@OneToMany(mappedBy = "fcFormAPartBDC", cascade = CascadeType.ALL)
	@Where(clause = "is_delete='false'")
	List<FcFormAPartBDCInspectionDetails> inspectionDetails = new ArrayList<>();

	@OneToOne(mappedBy = "fcFormAPartBDC", cascade = CascadeType.ALL)
	private FcFormAPartBDCUndertaking fcFormAPartBDCUndertaking;

	@Transient
	@JsonProperty(access = Access.READ_ONLY)
	private DepartmentApplication departmentApplication;

	private Boolean is_active;

	private Boolean is_delete;

	public FcFormAPartBDC() {
		this.is_active = true;
		this.is_delete = false;
	}

}
