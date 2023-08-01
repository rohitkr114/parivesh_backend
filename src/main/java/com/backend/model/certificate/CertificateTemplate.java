package com.backend.model.certificate;

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
@Table(name = "certificate_template", schema = "master")
public class CertificateTemplate extends Auditable<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "application_id")
	private String application_id;

	@Column(name = "category", length = 100, nullable = true)
	private String category;

	@Column(name = "sub_category", length = 100, nullable = true)
	private String sub_category;

	@Column(name = "condition", length = 1000)
	private String condition;

	@Column(name = "template", length = 10000)
	private String template;

	@Column(name = "type", length = 100)
	private String type;

	@Column(name = "is_active", nullable = true)
	private Boolean is_active;

	@Column(name = "is_delete", nullable = true)
	private Boolean is_delete;

	private CertificateTemplate() {
		this.is_active = true;
		this.is_delete = false;
	}

}
