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

import org.hibernate.annotations.Where;

import com.backend.audit.Auditable;
import com.backend.model.ForestClearancePartB.ForestClearanceBBasicDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Data;

@Data
@Entity
@Table(name = "clearance_action", schema = "master")
@Where(clause = "is_deleted='false'")
public class ClearanceAction extends Auditable<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "step", nullable = true)
	private String step;
	
	@Column(name = "action", nullable = true)
	private String action;
	
	@Column(name = "form", nullable = true)
	private String form;
	
	@Column(name = "proponent_status", nullable = true)
	private String proponent_status;
	
	@Column(name = "department_status", nullable = true)
	private String department_status;
	
	@Column(name = "flow_order", nullable = true)
	private Integer flow_order;
	
	@Column(name = "completion_days", nullable = true)
	private Integer completion_days;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "application_id", nullable = true)
	private Applications applications;
	
	@Column(nullable = true)
	private String headline;
	
	@Column(nullable = false)
	@JsonProperty(access = Access.READ_ONLY)
	private Boolean is_active;

	@Column(nullable = false)
	@JsonProperty(access = Access.READ_ONLY)
	private Boolean is_deleted;
	
	public ClearanceAction() {
		this.is_active = true;
		this.is_deleted = false;
		this.flow_order = 0;
		this.completion_days = 0;
	}
}
