package com.backend.model.EKyc;

import java.io.Serializable;

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
import javax.persistence.Transient;

import com.backend.audit.Auditable;
import com.backend.model.DocumentDetails;
import com.backend.model.User;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Entity
@Data
@Table(name = "update_e_kyc", schema = "authentication")
public class UpdateEKyc extends Auditable<Integer> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name = "contact_person_name", nullable = true)
	private String contactPersonName;
	
	@Column(name = "old_contact_person_name", nullable = true)
	private String applicant_name;
	
	@Column(name = "designation", nullable = true)
	private String designation;
	
	@Column(name = "mobile_number", nullable = true)
	private String mobileNo;
	
	@Column(name = "email_id", nullable = true)
	private String email;
	
	@Column(name = "old_email_id", nullable = true)
	private String applicant_email;
	
	@Column(name = "landline_number", nullable = true)
	private String landlineNO;
	
	@Column(name = "status", nullable = true)
	private String status;
	
	@Column(name = "district")
	private String district;

	@Column(name = "city")
	private String city;

	@Column(name = "pincode")
	private String pincode;

	@Column(name = "address", length = 1000)
	private String address;
	
	@Column(name = "state_ut")
	private String state_ut;
	
	@Transient
	private String token;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "auth_letter_id")
	private DocumentDetails authLetterId;
	
	@Transient
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private User user_id;

}
