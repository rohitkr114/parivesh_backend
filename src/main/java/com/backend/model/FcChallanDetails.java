package com.backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.backend.audit.Auditable;

import lombok.Data;

@Entity
@Table(name = "fc_challan_details", schema = "master")
@Data
public class FcChallanDetails extends Auditable<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "application_id")
	private Integer applicationId;

	@Column(name = "client_code", length=50)
	private String clientCode;

	@Column(name = "state")
	private Integer state;

	@Column(name = "district")
	private Integer district;

	@Column(name = "client_location", length = 255)
	private String clientLocation;

	@Column(name = "remitter_name", length = 255)
	private String remitterName;

	@Column(name = "moef_file_no", length = 255)
	private String moefFileNo;

	@Column(name = "address", length = 255)
	private String address;

	@Column(name = "remitter_email", length = 255)
	private String remitterEmail;

	@Column(name = "remitter_mobile", length = 20)
	private String remitterMobile;

	@Column(name = "remitter_landline", length = 20)
	private String remitterLandLine;

	@Column(name = "amount")
	private Double amount;

	@Column(name = "beneficiary_branch", length = 255)
	private String beneficiaryBranch;

	@Column(name = "beneficiary_name", length = 100)
	private String beneficiaryName;

	@Column(name = "beneficiary_ifsc_code", length = 50)
	private String beneficiaryIfscCode;

	@Column(name = "beneficiary_account_number", length = 50)
	private String beneficiaryAccountNumber;

	@Column(name = "beneficiary_bank_name", length = 255)
	private String beneficiaryBankName;

	@Column(name = "beneficiary_bank_address", length = 255)
	private String beneficiaryBankAddress;

	@Column(name = "challan_no", length = 500)
	private String challanNo;

	@Column(nullable = false)
	private boolean is_active;

	@Column(nullable = false)
	private boolean is_deleted;

	FcChallanDetails() {
		this.is_active = true;
		this.is_deleted = false;
	}

} 
