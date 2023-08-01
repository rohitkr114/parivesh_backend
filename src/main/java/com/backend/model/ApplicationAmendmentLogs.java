package com.backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.backend.audit.Auditable;

import lombok.Data;

@Data
@Entity
@Table(name="application_amendment_logs", schema="master")
public class ApplicationAmendmentLogs extends Auditable<Integer> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name="ref_id",nullable=true)
	private Integer ref_id;
	
	@Column(name="ref_type",nullable=true,length=100)
	private String ref_type;
	
	@Column(name="description",nullable=true,length=255)
	private String description;
	
	@Column(name="field_reference",nullable=true,length=100)
	private String field_reference;
	
	@Column(name="prev_value",nullable=true,length=500)
	private String prev_value;
	
	@Column(name="amend_value",nullable=true,length=500)
	private String amend_value;
	
	@Column(name="remarks",nullable=true)
	private String remarks;
	
	@Column(name="is_active", nullable=true)
	private Boolean is_active;
	
	@Column(name="is_deleted", nullable=true)
	private Boolean is_deleted;
	
	public ApplicationAmendmentLogs() {
		this.is_active=true;
		this.is_deleted=false;
	}
}
