package com.backend.model.CampaPaymentCompletion;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.backend.audit.Auditable;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name="fc_campa_payment_bank_details",schema = "authority")
public class FcCampaPaymentBankDetails extends Auditable<Integer>{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(nullable = true)
	private String remittance_no;
	
	@Column(nullable = true)
	private String bank_name;
	
	@Column(nullable = true)
	private String draft_number;
	
	@Column(nullable = true)
	private String remittance_date;
	
	@Column(nullable = true)
	private String utr_no;
	
	@Column(nullable = true)
	private String dd_cheque_no;
	
	@Column(nullable = true)
	private String deposit_bank;
	
	@Column(nullable = true)
	private String remarks;
	
	private Boolean is_active;
	
	private Boolean is_deleted;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fc_campa_payment_completion_id", nullable = true)
	@JsonIgnore
	private FcCampaPaymentCompletionDetails fcCampaPaymentCompletionDetails;
	
	public FcCampaPaymentBankDetails() {
		this.is_active=true;
		this.is_deleted=false;
	}
	
}
