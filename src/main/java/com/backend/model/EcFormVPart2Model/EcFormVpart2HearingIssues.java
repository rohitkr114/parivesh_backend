package com.backend.model.EcFormVPart2Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.backend.audit.Auditable;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "public_hearing_issues", schema = "master")
public class EcFormVpart2HearingIssues extends Auditable<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "major_issues_raised", nullable = true, length = 500)
	private String major_issues_raised;

	@Column(name = "project_proponent_response", nullable = true, length = 500)
	private String project_proponent_response;

//	@ManyToOne
//	@JoinColumn(name = "ec_form_v_part2_id", nullable = true)
//	@JsonIgnore
//	private EcFormVPart2 ecFormVPart2;

	@Column(name = "is_active")
	private Boolean is_active;

	@Column(name = "is_deleted")
	private Boolean is_deleted;

	public EcFormVpart2HearingIssues() {
		this.is_active = true;
		this.is_deleted = false;
	}

}
