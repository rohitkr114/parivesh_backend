package com.backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.backend.audit.Auditable;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Data;

@Data
@Entity
@Table(name="ms_recommendation", schema="authority")
public class MsRecommendation extends Auditable<Integer>{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(name="file_no", nullable=true, length=50)
	private String file_no;
	
	@Column(name="state_file_no", nullable=true, length=50 )
	private String state_file_no;
	
	@Column(name="dd_remarks", nullable=true)
	private String dd_remarks;
	
	@Column(name="ms_remarks", nullable=false)
	private String ms_remarks;
	
	@Column(name="so_remarks", nullable=true)
	private String so_remarks;
	
	@OneToOne(mappedBy = "ms_recommendation")
	private MsRecommendationEACDetails msEACDetails;
	
	@Transient
	@JsonProperty(access = Access.READ_ONLY)
	private DepartmentApplication departmentApplication;
	
	@Column(name = "is_active")
	private Boolean is_active;
	
	@Column(name = "is_deleted")
	private Boolean is_deleted;
	
	public MsRecommendation() {
		this.is_active = true;
		this.is_deleted = false;
	}
	
}
