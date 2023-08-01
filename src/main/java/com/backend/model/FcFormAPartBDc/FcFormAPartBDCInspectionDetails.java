package com.backend.model.FcFormAPartBDc;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.backend.audit.Auditable;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "fc_form_a_part_b_dc_inspection_details", schema = "master")
public class FcFormAPartBDCInspectionDetails extends Auditable<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fc_formA_DC_id", nullable = true)
	@JsonIgnore
	private FcFormAPartBDC fcFormAPartBDC;

	@Column(name = "inspection_date")
	private Date inspection_date;

	@Column(name = "officer_name", length = 50, nullable = true)
	private String officer_name;

	@Column(name = "department_name", length = 50, nullable = true)
	private String department_name;

	private Boolean is_active;

	private Boolean is_delete;

	public FcFormAPartBDCInspectionDetails() {
		this.is_active = true;
		this.is_delete = false;
	}

}
