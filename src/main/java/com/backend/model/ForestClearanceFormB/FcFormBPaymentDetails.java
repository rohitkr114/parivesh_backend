package com.backend.model.ForestClearanceFormB;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.backend.audit.Auditable;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "fc_form_b_payment_details", schema ="master")
public class FcFormBPaymentDetails extends Auditable<Integer>{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "fc_form_b_id", nullable = true)
	@JsonIgnore
	private FcFormBProjectDetails fcFormBProjectDetails;
	
	@Column(length = 50, nullable = true)
	private String proposal_no;
	
	@Column(length = 100, nullable = true)
	private String moef_file_no;
	
	@Column(length = 100, nullable = true)
	private String item_nature;
	
	@Column(nullable = true)
	private Double amount_paid;
	
	@Column(nullable = true)
	private Date payment_date;

	private Boolean is_active;

	private Boolean is_deleted;

	public FcFormBPaymentDetails() {
		this.is_active=true;
		this.is_deleted=false;
	}

	public FcFormBPaymentDetails(Integer id, String proposal_no, String moef_file_no, String item_nature,
			Double amount_paid) {
		this.id = id;
		this.proposal_no = proposal_no;
		this.moef_file_no = moef_file_no;
		this.item_nature = item_nature;
		this.amount_paid = amount_paid;
	}
	public FcFormBPaymentDetails(Integer id, String proposal_no, String moef_file_no, String item_nature,
			Double amount_paid, Date payment_date) {
		this.id = id;
		this.proposal_no = proposal_no;
		this.moef_file_no = moef_file_no;
		this.item_nature = item_nature;
		this.amount_paid = amount_paid;
		this.payment_date = payment_date;
	}

	
}
