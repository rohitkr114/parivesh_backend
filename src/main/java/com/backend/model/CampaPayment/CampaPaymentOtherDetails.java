package com.backend.model.CampaPayment;

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
@Table(name="campa_payment_other_details", schema="authority")
public class CampaPaymentOtherDetails extends Auditable<Integer> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(nullable = true)
	private Boolean is_proposal_applicable;
	
	@Column(name="compensatory_afforestation", nullable=true)
	private Double compensatory_afforestation;
	
	@Column(name="additional_compensatory_afforestation", nullable=true)
	private Double additional_compensatory_afforestation;
	
	@Column(name="penal_compensatory_afforestation", nullable=true)
	private Double penal_compensatory_afforestation;
	
	@Column(name="catchment_area", nullable=true)
	private Double catchment_area;
	
	@Column(name="afforestation_safety_zone", nullable=true)
	private Double afforestation_safety_zone;
	
	@Column(name="additional_charges", nullable=true)
	private Double additional_charges;
	
	@Column(name="net_present_value", nullable=true)
	private Double net_present_value;

	@Column(name="penal_net_present_value", nullable=true)
	private Double penal_net_present_value;
	@Column(name="wildlife_conservation", nullable=true)
	private Double wildlife_conservation;
	
	@Column(name="soil_conservation", nullable=true)
	private Double soil_conservation;
	
	@Column(name="total", nullable=true)
	private Double total;
	
	@Column(name="is_other_charges", nullable=true)
	private Boolean is_other_charges;
	
	@Column(name="remarks", nullable=true)
	private String remarks;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "campa_payment_id", nullable = true)
	@JsonIgnore
	private CampaPaymentDetails campaPaymentDetails;
		
	@Column(name="is_active")
	private Boolean is_active;
	
	@Column(name = "is_deleted")
	private Boolean is_deleted;
	
	public CampaPaymentOtherDetails() {
		this.is_active = true;
		this.is_deleted = false;
	}
}
