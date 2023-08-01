package com.backend.model.EcPartC;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.backend.audit.Auditable;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Data;

@Data
@Entity
@Table(name = "ec_major_issues", schema = "master")
public class EcMajorIssuesRaised extends Auditable<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name = "major_issues_raised", nullable = true, length = 300)
	private String major_issues_raised;
	
	@Column(name = "pp_response", nullable = true, length = 300)
	private String pp_response;
	
	@Column(name = "is_final_eia_addressed", nullable = true)
	private Boolean is_final_eia_addressed;
	
	@Column(name = "final_eia_reference", nullable = true, length = 500)
	private String final_eia_reference;
	
	@Column(name = "is_active")
	private Boolean is_active;

	@Column(name = "is_deleted")
	private Boolean is_deleted;
	
	public EcMajorIssuesRaised() {
		this.is_active = true;
		this.is_deleted = false;
	}
	
}
