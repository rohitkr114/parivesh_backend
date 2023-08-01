package com.backend.model;

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
import javax.persistence.Transient;

import com.backend.audit.Auditable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Data;

@Data
@Entity
@Table(name="ms_recommendation_eac_details",schema = "authority")
public class MsRecommendationEACDetails extends Auditable<Integer>{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "ms_recommendation_id", nullable = true)
	@JsonIgnore
	private MsRecommendation ms_recommendation;
	
	private Boolean is_auto_tor_applied;
	
	@Transient
	@JsonProperty(access = Access.READ_ONLY)
	private DepartmentApplication departmentApplication;
	
	@Column(length = 100)
	private String proposal_for;
	
	@Column(length = 500,nullable = true)
	private String eac_reason;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "eac_document", nullable = true)
	private DocumentDetails eac_document;
	
	@Column(name = "is_active")
	private Boolean is_active;
	
	@Column(name = "is_deleted")
	private Boolean is_deleted;
	
	public MsRecommendationEACDetails() {
		this.is_active = true;
		this.is_deleted = false;
	}
	
	public MsRecommendationEACDetails(Integer id) {
		this.id=id;
	}

}
