package com.backend.model;

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

import lombok.Data;

@Data
@Entity
@Table(name = "caf_litigations", schema = "master")
public class CafLitigations extends Auditable<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(length = 500)
	private String order_details;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "litigation_order_copy_id", nullable = true)
	private DocumentDetails litigation_order_copy;

	@Column(length = 500)
	private String court_name;

	@Column(nullable=true,length=500)
	private String court_name_other;

	@Column(length = 100)
	private String court_bench_name;
	
	@Column(length = 300)
	private String case_category;
	@Column(length = 300)
	private String case_category_other;
	public String getCourt_name_other() {
		return court_name_other;
	}

	public void setCourt_name_other(String court_name_other) {
		this.court_name_other = court_name_other;
	}

	public String getCase_category_other() {
		return case_category_other;
	}

	public void setCase_category_other(String case_category_other) {
		this.case_category_other = case_category_other;
	}

	private String case_status;

	@Column(length = 500)
	private String court_direction_description;

	@Column(nullable = false)
	private boolean is_active;

	@Column(nullable = false)
	private boolean is_deleted;

	public CafLitigations() {
		this.is_active = true;
		this.is_deleted = false;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOrder_details() {
		return order_details;
	}

	public void setOrder_details(String order_details) {
		this.order_details = order_details;
	}

	public String getCourt_name() {
		return court_name;
	}

	public void setCourt_name(String court_name) {
		this.court_name = court_name;
	}

	public String getCourt_bench_name() {
		return court_bench_name;
	}

	public void setCourt_bench_name(String court_bench_name) {
		this.court_bench_name = court_bench_name;
	}

	public String getCase_category() {
		return case_category;
	}

	public void setCase_category(String case_category) {
		this.case_category = case_category;
	}

	public String getCase_status() {
		return case_status;
	}

	public void setCase_status(String case_status) {
		this.case_status = case_status;
	}

	public String getCourt_direction_description() {
		return court_direction_description;
	}

	public void setCourt_direction_description(String court_direction_description) {
		this.court_direction_description = court_direction_description;
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

	public DocumentDetails getLitigation_order_copy() {
		return litigation_order_copy;
	}

	public void setLitigation_order_copy(DocumentDetails litigation_order_copy) {
		this.litigation_order_copy = litigation_order_copy;
	}
	
	
	
	
	
}