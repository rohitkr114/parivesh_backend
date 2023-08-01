package com.backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.backend.audit.Auditable;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Data;

@Data
@Entity
@Table(name = "clearance_fact_sheet", schema = "master")
public class ClearanceFactSheet extends Auditable<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private Integer proposal_id;

	private String proposal_no;

	private String step;

	@Lob
	private String data;

	@Column(nullable = false)
	@JsonProperty(access = Access.READ_ONLY)
	private Boolean is_active;

	@Column(nullable = false)
	@JsonProperty(access = Access.READ_ONLY)
	private Boolean is_deleted;
}
