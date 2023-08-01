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
@Table(name = "crz_proposal_process_file_history", schema = "master")
@NoArgsConstructor
@AllArgsConstructor
public class CrzProposalProcessFileHistoryDto implements Serializable {

	private static final long serialVersionUID = 123L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;

	@Column(name = "proposal_id")
	private Integer proposal_id;

	@Column(name = "forward_to_user_id")
	private Integer forward_to_user_id;
	
	@Column(name = "forward_to_name")
	private String forward_to_name;

	@Column(name = "status")
	private String status;

	@Column(name = "role_id")
	private Integer role_id;

	@Column(name = "proposal_process_file_document_id")
	private String proposal_process_file_document_id;

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
	
	@Column(name = "remarks_by")
	private String remarks_by;
	
	@Column(name = "action")
	private Integer action;

	/* we are using same table of crz_agenda_attachments to save the attachments file details of Process File */
	private transient CrzAttachmentsDetail crzProcessFileAttachment;
	
	
}
