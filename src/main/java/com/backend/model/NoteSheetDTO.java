package com.backend.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity
public class NoteSheetDTO {
	
	@Id
	private Integer id;
	
	@Column(length = 3000,nullable = true)
	private String note;
	
	private Integer app_history_id;
	
	private Integer application_id;

	private String username;
	
	private String role_name;
	
	private String designation;
	
	private Long role_id;
	
	private Date created_on;
	
	private Date updated_on;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "document", nullable=true)
	private DocumentDetails document;
	
	private String status;
}
