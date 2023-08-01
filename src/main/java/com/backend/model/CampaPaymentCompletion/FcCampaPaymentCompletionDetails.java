package com.backend.model.CampaPaymentCompletion;

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
import com.backend.model.CampaPayment.CampaPaymentOtherCharges;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Data;

@Data
@Entity
@Table(name="fc_campa_payment_completion_details",schema = "authority")
public class FcCampaPaymentCompletionDetails extends Auditable<Integer>{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name="fc_id",nullable = true)
	private Integer fc_id;
	
	@Column(name="payment_mode", nullable=true)
	private String payment_mode;
	
	@Column(name="agency_name", nullable=true)
	private String agency_name;
	
	@Column(name="district_code", nullable=true)
	private String district_code;
	
	@Column(name="district", nullable=true,length = 100)
	private String district;
	
	@Column(name="state_code", nullable=true)
	private String state_code;
	
	@Column(name="state", nullable=true)
	private String state;
	
	@Column(name="mobile", nullable=true,length = 10)
	private String mobile;
	
	@Column(name="telephone", nullable=true)
	private String telephone;
	
	@Column(name="email_id", nullable=true)
	private String email_id;
	
	@Column(name="address", nullable=true,length = 500)
	private String address;
	
	@Column(name="regional_office",nullable = true,length = 500)
	private String regional_office;
	
	private Boolean is_active;
	
	private Boolean is_deleted;
	
	@OneToOne(mappedBy="fcCampaPaymentCompletionDetails")
	private FcCampaPaymentBankDetails fcPaymentBankDetails;
	
	@OneToOne(mappedBy="fcCampaPaymentCompletionDetails")
	private FcCampaPaymentProposalDetails fcCampaPaymentProposalDetails;
	
	@OneToMany(mappedBy="fcCampaPaymentCompletionDetails", cascade=CascadeType.ALL)
	@Where(clause="is_deleted='false'")
	private List<FcCampaTransactionDetails> fcCampaTransactionDetails;
	
	@Transient
	@JsonProperty(access = Access.READ_ONLY)
	private DepartmentApplication departmentApplication;
	
	public FcCampaPaymentCompletionDetails() {
		this.is_active=true;
		this.is_deleted=false;
	}
}
