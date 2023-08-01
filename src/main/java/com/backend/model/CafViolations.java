package com.backend.model;

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
import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Data
@Entity
@Table(name = "caf_violations", schema = "master")
public class CafViolations extends Auditable<Integer> {
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getViolation_act() {
		return violation_act;
	}

	public void setViolation_act(String violation_act) {
		this.violation_act = violation_act;
	}

	public String getViolation_year() {
		return violation_year;
	}

	public void setViolation_year(String violation_year) {
		this.violation_year = violation_year;
	}

	public String getDirection_issued_by() {
		return direction_issued_by;
	}

	public void setDirection_issued_by(String direction_issued_by) {
		this.direction_issued_by = direction_issued_by;
	}

	public String getDirection_detail() {
		return direction_detail;
	}

	public void setDirection_detail(String direction_detail) {
		this.direction_detail = direction_detail;
	}

	public String getViolation_summary() {
		return violation_summary;
	}

	public void setViolation_summary(String violation_summary) {
		this.violation_summary = violation_summary;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	public String getViolation_act_other() {
		return violation_act_other;
	}

	public void setViolation_act_other(String violation_act_other) {
		this.violation_act_other = violation_act_other;
	}

	private String violation_act;
	@Column(length = 500)
	private String type_of_violation;
	private String violation_year;
	private String direction_issued_by;

	@Column(nullable=true)
	private String violation_year_to;

	@Column(nullable=true)
	private Boolean is_any_direction;

	@Column(length = 500,nullable=true)
	private String violation_act_other;
	@Column(length = 500)
	private String direction_detail;
	@Column(length = 500,nullable=true)
	private String direction_detail_other;
	public String getDirection_detail_other() {
		return direction_detail_other;
	}

	public void setDirection_detail_other(String direction_detail_other) {
		this.direction_detail_other = direction_detail_other;
	}

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "violation_direction_copy_id", nullable = true)
	private DocumentDetails violation_direction_copy;
	
	@Column(length = 500)
	private String violation_summary;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "violation_report_id", nullable = true)
	private DocumentDetails violation_report;
	
	@Column(nullable = false)
	private boolean is_active;

	@Column(nullable = false)
	private boolean is_deleted;
	
	public CafViolations() {
		this.is_active = true;
		this.is_deleted = false;
	}

	public DocumentDetails getViolation_direction_copy() {
		return violation_direction_copy;
	}

	public void setViolation_direction_copy(DocumentDetails violation_direction_copy) {
		this.violation_direction_copy = violation_direction_copy;
	}

	public DocumentDetails getViolation_report() {
		return violation_report;
	}

	public void setViolation_report(DocumentDetails violation_report) {
		this.violation_report = violation_report;
	}

	public boolean isIs_active() {
		return is_active;
	}

	public void setIs_active(boolean is_active) {
		this.is_active = is_active;
	}

	public boolean isIs_deleted() {
		return is_deleted;
	}

	public void setIs_deleted(boolean is_deleted) {
		this.is_deleted = is_deleted;
	}

	public String getType_of_violation() {
		return type_of_violation;
	}

	public void setType_of_violation(String type_of_violation) {
		this.type_of_violation = type_of_violation;
	}
 
	
}