package com.backend.model.certificate;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.backend.audit.Auditable;
import com.backend.model.DocumentDetails;

import lombok.Data;

@Data
@Entity
@Table(name = "certificates", schema = "master")
public class Certificates extends Auditable<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "proponent_application_id")
	private Integer proponent_application_id;

	@Column(name = "template", length = 10000)
	private String template;

	@Column(name = "issue_date")
	private Date issue_date;

	@Column(name = "issued_by")
	private String issued_by;

	@Column(name = "is_active")
	private Boolean is_active;

	@Column(name = "is_delete")
	private Boolean is_delete;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "certificate_id", nullable = true)
	private DocumentDetails certificate;

}
