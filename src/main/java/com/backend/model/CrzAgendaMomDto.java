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
@Table(name = "crz_agenda_mom", schema = "master")
@NoArgsConstructor
@AllArgsConstructor
public class CrzAgendaMomDto implements Serializable {

	private static final long serialVersionUID = 123L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "mom_id")
	private Integer mom_id;
	
	@Column(name = "mom_name")
	private String mom_name;

	@Column(name = "agenda_id")
	private Integer agenda_id;
	
	@Column(name = "agenda_name")
	private String agenda_name;

	@Column(name = "opening_remark")
	private String opening_remark;

	@Column(name = "last_eac_meeting")
	private String last_eac_meeting;

	@Column(name = "mom_creation_date")
	private Date mom_creation_date;

	@Column(name = "meeting_title")
	private String meeting_title;

	@Column(name = "mode_of_meeting")
	private String mode_of_meeting;

	@Column(name = "meeting_date")
	private Date meeting_date;

	@Column(name = "status")
	private String status;

	@Column(name = "created_by")
	private Integer created_by;

	@Column(name = "created_on")
	private Date created_on;

	@Column(name = "updated_by")
	private Integer updated_by;

	@Column(name = "updated_on")
	private Date updated_on;

	@Column(name = "chairman_remarks")
	private String chairman_remarks;
	
	@Column(name = "pdffilepath")
	private String pdffilepath;
}
