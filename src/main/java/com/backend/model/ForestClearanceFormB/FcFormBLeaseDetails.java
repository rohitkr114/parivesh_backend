package com.backend.model.ForestClearanceFormB;

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
@Table(name = "fc_form_b_lease_details", schema= "master")
public class FcFormBLeaseDetails extends Auditable<Integer> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "fc_form_b_id", nullable = true)
	@JsonIgnore
	private FcFormBProjectDetails fcFormBProjectDetails;
	
	@Column(length = 100, nullable = true)
	private String moef_file_no;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "approval_copy", nullable = true)
	private DocumentDetails approval_copy;
	
	@Column(nullable = true)
	private Date approval_date;
	
	private Boolean is_active;

	private Boolean is_deleted;

	public FcFormBLeaseDetails() {
		this.is_active=true;
		this.is_deleted=false;
	}

	public DocumentDetails approval_copy() {
		return approval_copy;
	}

	public void setApproval_copy(DocumentDetails approval_copy) {
		approval_copy.setId(null);
		this.approval_copy = approval_copy;
	}
	
//	public FcFormBLeaseDetails(Integer id, String moef_file_no, Date approval_date) {
//		this.id = id;
//		this.moef_file_no = moef_file_no;
//		this.approval_date = approval_date;
//	}
//
//	public FcFormBLeaseDetails(Integer id, String moef_file_no, DocumentDetails approval_copy, Date approval_date) {
//		this.id = id;
//		this.moef_file_no = moef_file_no;
//		this.approval_copy = approval_copy;
//		this.approval_date = approval_date;
//	}
//	
	
}
