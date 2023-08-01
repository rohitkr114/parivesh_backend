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
@Table(name = "site_inspection_report", schema = "master")
public class SiteInspectionReport extends Auditable<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(nullable = true)
	private String status;

	@Column(nullable = true)
	private Integer proponent_application_id;

	@Column(nullable = true, length = 5000)
	private String remarks;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "document", nullable = true)
	private DocumentDetails document;

	private Boolean is_delete;

	private Boolean is_active;

	public SiteInspectionReport() {
		this.is_active = true;
		this.is_delete = false;
	}

}
