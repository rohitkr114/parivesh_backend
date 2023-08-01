//package com.backend.model;
//
//import java.util.Date;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.SequenceGenerator;
//import javax.persistence.Table;
//
//import org.hibernate.annotations.CreationTimestamp;
//import org.hibernate.annotations.Immutable;
//
//@Entity
//@Immutable
//@Table(name = "fc_proposal_division_vw", schema = "master" )
//public class FCProposalDivisionVW {
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "_seq")
//	@SequenceGenerator(name = "_seq", sequenceName = "entity_sequence", allocationSize = 1, initialValue = 1)
//	private Long entityId;
//	
//	@Column(nullable = false)
//	private Integer proposal_id;
//	
//	@Column(nullable = false, unique = true, length = 80)
//	private String proposal_no;
//	
//	@Column(nullable = false)
//	private Integer division_id;
//	
//	@CreationTimestamp
//	private Date created_on;
//	
//	@Column(nullable = true)
//	private Boolean isdelete;
//	
//	private Long project_category_id;
//	
//	private String project_category;
//	
//	private String form_type;
//
//	public Integer getProposal_id() {
//		return proposal_id;
//	}
//
//	public void setProposal_id(Integer proposal_id) {
//		this.proposal_id = proposal_id;
//	}
//
//	public String getProposal_no() {
//		return proposal_no;
//	}
//
//	public void setProposal_no(String proposal_no) {
//		this.proposal_no = proposal_no;
//	}
//
//	public Integer getDivision_id() {
//		return division_id;
//	}
//
//	public void setDivision_id(Integer division_id) {
//		this.division_id = division_id;
//	}
//
//	public Date getCreated_on() {
//		return created_on;
//	}
//
//	public void setCreated_on(Date created_on) {
//		this.created_on = created_on;
//	}
//
//	public Boolean getIsdelete() {
//		return isdelete;
//	}
//
//	public void setIsdelete(Boolean isdelete) {
//		this.isdelete = isdelete;
//	}
//
//	public Long getProject_category_id() {
//		return project_category_id;
//	}
//
//	public void setProject_category_id(Long project_category_id) {
//		this.project_category_id = project_category_id;
//	}
//
//	public String getProject_category() {
//		return project_category;
//	}
//
//	public void setProject_category(String project_category) {
//		this.project_category = project_category;
//	}
//
//	public String getForm_type() {
//		return form_type;
//	}
//
//	public void setForm_type(String form_type) {
//		this.form_type = form_type;
//	}
//	
//	
//}
