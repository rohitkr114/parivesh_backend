package com.backend.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.backend.audit.Auditable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
@Table(name = "draft_form_data", schema = "master")
public class DraftFormData extends Auditable<Integer> {

	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_draft_form_data")
	@SequenceGenerator(name = "seq_draft_form_data", sequenceName = "draft_form_data_sequence", allocationSize = 1, initialValue = 1)
	@Id
	private Integer id;

	@Column(name = "application_id", nullable = true)
	private Integer applicationdId;

	@Column(name = "app_history_id", nullable = true)
	private Integer appHistoryId;

	@Column(name = "clearance_id", nullable = true)
	private Integer clearanceId;

	@Column(name = "form_id", nullable = true)
	private Integer formId;

	@Column(name = "step_id", nullable = true)
	private Integer stepId;

	@Column(name = "caf_id", nullable = true)
	private Integer cafId;

	@Basic(fetch = FetchType.LAZY)
	@Lob
	private String data;

	@Column(name = "version", nullable = false)
	private Integer version;

	@Column(name = "is_active")
	private Boolean is_active;

	@Column(name = "is_deleted")
	private Boolean is_deleted;

	public DraftFormData() {
		this.is_active = true;
		this.is_deleted = false;
	}
}
