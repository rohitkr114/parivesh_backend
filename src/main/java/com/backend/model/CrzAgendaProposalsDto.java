package com.backend.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.beust.jcommander.internal.Nullable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "crz_agenda_proposals", schema = "master")
@NoArgsConstructor
@AllArgsConstructor
public class CrzAgendaProposalsDto implements Serializable {

	private static final long serialVersionUID = 123L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;

	@Column(name = "agenda_id")
	private Integer agenda_id;

	@Column(name = "application_id")
	private Integer application_id;
	
	@Column(name = "proposal_app_id")
	private Integer proposal_app_id;

	@Column(name = "proposal_no")
	private String proposal_no;

	@Column(name = "status")
	private String status;

	@Column(name = "project_name")
	private String project_name;

	@Column(name = "workgroup_name")
	private String workgroup_name;

	@Column(name = "officename")
	private String office_name;

	@Column(name = "rolename")
	private String role_name;

	@Column(name = "current_step_id")
	private Integer current_step_id;

	@Column(name = "process_step_mapping_id")
	private Integer process_step_mapping_id;

	@Column(name = "role_id")
	private Integer role_id;

	@Column(name = "office_id")
	private Integer office_id;

	@Column(name = "last_submission_date")
	private Date last_submission_date;

	@Column(name = "start_date")
	private Date start_date;

	@Column(name = "discussion_date")
	private Date discussion_date;

	@Column(name = "proposal_json")
	private String proposal_json;

	@Column(name = "process_id")
	private Integer process_id;

	@Column(name = "other_property")
	private String other_property;

	@Column(name = "workgroup_id")
	private Integer workgroup_id;

	@Column(name = "main_state")
	private Integer main_state;

	@Column(name = "main_district")
	private Integer main_district;

	@Column(name = "state_name")
	private String state_name;

	@Column(name = "district_name")
	private String district_name;

	@Column(name = "name_of_proponent")
	private String proponent_name;

	@Column(name = "mom_previous_delibration")
	private String mom_previous_delibration;

	@Column(name = "mom_current_delibration")
	private String mom_current_delibration;

	@Column(name = "mom_recommendation",nullable = false ,columnDefinition = "varchar(255) default 'NA'")
	private String mom_recommendation;

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

	@Column(name = "chairman_remarks")
	private String chairman_remarks;
	
	@Column(name = "salient_feature")
	private String salient_feature;
	
	private transient CrzProposalConditionMappingEntity crzProposalConditionMappings;
	
	@Column(name = "app_history_id")
	private Integer app_history_id;
}