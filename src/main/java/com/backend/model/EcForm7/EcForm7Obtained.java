package com.backend.model.EcForm7;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.backend.audit.Auditable;
import com.backend.model.DocumentDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "ec_form_7_obtained", schema = "master")
public class EcForm7Obtained extends Auditable<Integer> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "ec_form_7_project_activity_id", nullable = true)
	@JsonIgnore
	private EcForm7ProjectActivity ecForm7ProjectActivity;
	
	@Column(nullable = true)
	private String ec_obtained_select;

	@Column(nullable = true)
	private Date ec_obtained_date;

	@OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinColumn(name = "ec_obtained_document_id", nullable = true)
	private DocumentDetails ec_obtained_document;
	
	@Column(nullable = true)
	private Boolean is_delete;

	@Column(nullable = true)
	private Boolean is_active;
	
	private EcForm7Obtained() {
		this.is_active=true;
		this.is_delete=false;
	}

	public EcForm7Obtained(Integer id, String ec_obtained_select, Date ec_obtained_date) {
		this.id = id;
		this.ec_obtained_select = ec_obtained_select;
		this.ec_obtained_date = ec_obtained_date;
	}
	
}
