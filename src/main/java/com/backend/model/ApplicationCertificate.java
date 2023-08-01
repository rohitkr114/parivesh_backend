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
@Table(name="application_certificate", schema="master")
public class ApplicationCertificate extends Auditable<Integer> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long Id;
	
	@Column(name="application_id", nullable=true)
	private Integer application_id;
	
	@Column(name="certificate_type", nullable=true, length=100)
	private String certificate_type;
	
	@Column(name="certificate_url", nullable=true, length=500)
	private String certificate_url;

	@Column(name="certificate_url2", nullable=true, length=500)
	private String certificate_url2;

	@Column(name="is_active")
	private Boolean is_active;
	
	@Column(name="is_deleted")
	private Boolean is_deleted;

}
