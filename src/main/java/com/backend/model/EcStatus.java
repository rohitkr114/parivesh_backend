package com.backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.backend.audit.Auditable;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Data;

@Data
@Entity
@Table(name = "ec_implementation_status", schema = "master")
public class EcStatus extends Auditable<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name = "ec_granted", nullable = true)
	private String ec_granted;
	
	@Column(name = "cte_granted", nullable = true, length = 500)
	private String cte_granted;
	
	@Column(name = "cto_granted", nullable = true, length = 500)
	private String cto_granted;
	
	@Column(name = "unimplemented_details", nullable = true, length = 500)
	private String unimplemented_details;
	
	@Column(name = "proposal_no", nullable = true)
	@JsonProperty(access = Access.READ_ONLY)
	private String proposal_no;
	
	@Column(name = "remarks", nullable = true, length = 500)
	private String remarks;
	
	@Column(nullable = false)
	@JsonProperty(access = Access.READ_ONLY)
	private Boolean is_active;

	@Column(nullable = false)
	@JsonProperty(access = Access.READ_ONLY)
	private Boolean is_deleted;
	
	public EcStatus() {
		this.is_active = true;
		this.is_deleted = false;
	}
}
