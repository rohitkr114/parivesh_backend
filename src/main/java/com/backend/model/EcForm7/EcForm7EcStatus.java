package com.backend.model.EcForm7;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "ec_form_7_ec_status", schema = "master")
public class EcForm7EcStatus {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(nullable = true)
	private String ec_granted;

	@Column(nullable = true, length = 500)
	private String cte_granted;

	@Column(nullable = true, length = 500)
	private String cto_granted;

	@Column(nullable = true, length = 500)
	private String unimplemented_details;

	@Column(nullable = true, length = 500)
	private String remarks;
}
