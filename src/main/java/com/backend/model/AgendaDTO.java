package com.backend.model;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
public class AgendaDTO {

	@Column(nullable = true)
	private String title_of_meeting;

	@Column(nullable = true)
	private String meeting_mode;

	@Column(nullable = true)
	private String meeting_venue;
/*
	@OneToMany(targetEntity = DateTimeofMeeting.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "agenda_form_id", referencedColumnName = "id")
	private List<DateTimeofMeeting> dateTime = new ArrayList<>();
	*/
	@Column(nullable = true)
	private Date agenda_creation_date;

	@Column(nullable = true)
	private Date meeting_start_date;

	@Column(nullable = true)
	private Date meeting_end_date;

	@Column(nullable = true)
	private Integer agenda_history_id;

	@Column(nullable = true)
	private String state;

	@Column(nullable = true)
	private String agenda_status;

	@Column(nullable = true)
	private String abbr;

	@Column(nullable = true)
	private String agenda_id;

	@Column(nullable = true)
	private String remarks;

	@Column(nullable = true)
	private Integer sector;
	
	@Column(nullable = true)
	private Integer workgroup_id;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "additional_document", nullable = true)
	private DocumentDetails additional_document;
}
