package com.backend.model.ClientCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "client_code", schema = "master")
public class ClientCode {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cli_seq")
	@SequenceGenerator(name = "cli_seq", sequenceName = "client_sequence", allocationSize = 1, initialValue = 1)
	@Column(name = "id", nullable = false, unique = true)
	private Integer id;

	@Column(name = "state_code", nullable = true)
	private Integer state_code;

	@Column(name = "cl_code", nullable = true,length=50)
	private String client_code;

	@Column(name = "cl_name", nullable = true)
	private String client_name;

	@Column(name = "virtual_account_no", nullable = true)
	private String virtual_account_no;

	@Column(name = "ifsc_code", nullable = true)
	private String ifsc_code;


}
