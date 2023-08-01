package com.backend.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "crz_proposal_timeline", schema = "master")
@NoArgsConstructor
@AllArgsConstructor
public class CrzProposalTimelineDto implements Serializable {

	private static final long serialVersionUID = 123L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;

	@Column(name = "proposal_app_id")
	private Integer proposal_app_id;

	@Column(name = "agenda_name")
	private String agenda_name;

	@Column(name = "mom_name")
	private String mom_name;

	@Column(name = "status_id")
	private Integer status_id;

	@Column(name = "created_by")
	private Integer created_by;

	@Column(name = "created_on")
	private Date created_on;

	@Column(name = "updated_by")
	private Integer updated_by;

	@Column(name = "updated_on")
	private Date updated_on;

	@Column(name = "remarks")
	private String remarks;
}