package com.backend.model.CampaPayment;

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

import com.backend.audit.Auditable;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name="campa_payment_other_charges", schema="authority")
public class CampaPaymentOtherCharges extends Auditable<Integer> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name="other_charges", nullable=true)
	private Double other_charges;
	
	@Column(name="other_charges_name", nullable=true)
	private String other_charges_name;
	
	@ManyToOne
	@JoinColumn(name="campa_payment_id", nullable=false)
	@JsonIgnore
	private CampaPaymentDetails campaPaymentDetails;
	
	@Column(name="is_active")
	private Boolean is_active;
	
	@Column(name = "is_deleted")
	private Boolean is_deleted;
	
	public CampaPaymentOtherCharges() {
		this.is_active = true;
		this.is_deleted = false;
	}
}
