package com.backend.model.EcForm11;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
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
import com.backend.model.DocumentDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name="ec_form_11_spcb_details",schema="master")
public class EcForm11SPCBDetails extends Auditable<Integer>{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name="reference_no", length=1000)
	private String referenceNo;
	
	@Column(name="date_of_consent")
	private Date dateOfConsent;
	
	@Column(name="consent_validity_date")
	private Date consentValidityDate;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "consent_order_id", nullable = true)
	private DocumentDetails consentOrder;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="ec_form_11_id", nullable=false)
	@JsonIgnore
	private EcForm11ProjectDetails ecForm11ProjectDetails;
	
	@Column(name="is_active")
	private Boolean isActive=true;
	
	@Column(name="is_deleted")
	private Boolean isDeleted=false;
}
