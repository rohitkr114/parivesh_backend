package com.backend.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.backend.audit.Auditable;

import lombok.Data;

@Data
@Entity
@Table(name = "eds_details", schema = "master")
public class EDS_Details extends Auditable<Integer>{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(nullable = true)
	private String status;
	
	@Column(nullable = true)
	private Integer proponent_application_id;

	@Column(nullable = true)
	private String remarks;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "document", nullable = true)
	private DocumentDetails document;
	
	@Column(nullable = true)
	private Date date;
	
	private Boolean is_delete;
	
	private Boolean is_active;
	
	public EDS_Details() {
		this.is_active=true;
		this.is_delete=false;
	}
}
