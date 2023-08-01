package com.backend.model;

import java.util.Date;

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
@Table(name = "crz_site_visit_committee", schema = "master")
public class CrzSiteVisitCommittee extends Auditable<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;


	@Column(name = "name", length = 255, nullable = true)
	private String name;

	@Column(name = "committee_role", length = 255, nullable = true)
	private String committee_role;

	@Column(name = "site_details", length = 1000, nullable = true)
	private String site_details;

	private Date site_visit_to;

	private Date site_visit_from;

	private Boolean is_active;

	private Boolean is_delete;

	public CrzSiteVisitCommittee() {
		this.is_active = true;
		this.is_delete = false;
	}

}
