package com.backend.model.FcComplianceCondition;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.backend.audit.Auditable;

import lombok.Data;

@Data
@Entity
@Table(name = "fc_compliance_condition", schema = "master")
public class FcComplianceCondition extends Auditable<Integer>{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(nullable = true)
	private Integer application_id;
	
	@Column(nullable = true)
	private String general_conditions;
	
	@Column(nullable = true)
	private String Specific_conditions;
	
	@Column(nullable = true)
	private String standard_conditions;
	
	@Column(nullable = true)
	private Integer clearance_id;
	
	@Column(nullable = true, length = 1000)
	private String remarks;
	
	@Column(nullable = true)
	private String role_name;
	
	@Column(nullable = true)
	private Integer role_id;
}
