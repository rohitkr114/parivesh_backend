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
@Table(name="fc_campa_payment_proposal_details",schema = "authority")
public class FcCampaPaymentProposalDetails extends Auditable<Integer>{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(nullable = true)
	private Double extended_fc_area;
	
	@Column(nullable = true,length = 100)
	private String proposal_for;
	
	@Column(nullable = true)
	private String additional_fc_area;
	
	@Column(nullable = true)
	private String stage_I_clearance_details;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fc_campa_payment_completion_id", nullable = true)
	@JsonIgnore
	private FcCampaPaymentCompletionDetails fcCampaPaymentCompletionDetails;
	
	private Boolean is_active;
	
	private Boolean is_deleted;
	
	public FcCampaPaymentProposalDetails() {
		this.is_active=true;
		this.is_deleted=false;
	}
	
}
