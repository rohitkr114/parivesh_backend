package com.backend.model.CampaPayment;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Where;

import com.backend.audit.Auditable;
import com.backend.model.DepartmentApplication;
import com.backend.model.ProponentApplications;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Data;

@Data
@Entity
@Table(name="campa_payment_details", schema="authority")
public class CampaPaymentDetails extends Auditable<Integer>{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name="fc_id", nullable=true)
	private Integer fc_id;
	
	@Column(name="agency_name", nullable=true)
	private String agency_name;
	
	@Column(name="district_code", nullable=true)
	private String district_code;
	
	@Column(name="district", nullable=true)
	private String district;
	
	@Column(name="state_code", nullable=true)
	private String state_code;
	
	@Column(name="state", nullable=true)
	private String state;
	
	@Column(name="mobile", nullable=true)
	private String mobile;
	
	@Column(name="telephone", nullable=true)
	private String telephone;
	
	@Column(name="email_id", nullable=true)
	private String email_id;
	
	@Column(name="address", nullable=true)
	private String address;
	
	@OneToOne(mappedBy="campaPaymentDetails")
	private CampaPaymentOtherDetails campaPaymentOtherDetails;
	
	@OneToMany(mappedBy="campaPaymentDetails", cascade=CascadeType.ALL)
	@Where(clause="is_deleted='false'")
	private List<CampaPaymentOtherCharges> campaPaymentOtherCharges;
	
	@Transient
	@JsonProperty(access = Access.READ_ONLY)
	private ProponentApplications proponentApplications;
	
	@Transient
	@JsonProperty(access = Access.READ_ONLY)
	private DepartmentApplication departmentApplication;

	@Transient
//	@JsonProperty(access = Access.READ_ONLY)
	private Boolean isMultipleDemand;
	
	@Column(name="is_active")
	private Boolean is_active;
	
	@Column(name = "is_deleted")
	private Boolean is_deleted;
	
	public CampaPaymentDetails() {
		this.is_active = true;
		this.is_deleted = false;
	}

}
