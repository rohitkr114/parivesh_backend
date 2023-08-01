package com.backend.model.EcForm12;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ec_form_12_ec_status", schema = "master")
public class EcForm12EcStatus {
	
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
