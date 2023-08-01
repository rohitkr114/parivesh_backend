package com.backend.model.EcForm6V2;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.backend.audit.Auditable;
import com.backend.model.DocumentDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name="ec_form_6_amendment_details_v2",schema="master")
public class EcForm6AmendmentDetailsV2 extends Auditable<Integer> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private String type;
	
	private Date dateOfAmendment;
	
	@Column(name="is_amendment_required",nullable=true)
	private Boolean isAmendmentRequired;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "amendment_letter_id", nullable = true)
	private DocumentDetails amendmentLetter;
	
	@Column(length=2000)
	private String validityExtensionReason;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "delay_reason_id", nullable = true)
	private DocumentDetails reasonForDelay;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "bar_chart_schedule_id", nullable = true)
	private DocumentDetails barChartOfSchedule;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ec_form6_id", nullable = false)
	@JsonIgnore
	private EcForm6ProjectDetailsV2 ecForm6;
	
	@OneToMany(targetEntity=EcForm6AmendmentStatusV2.class,  cascade = CascadeType.ALL)
	@JoinColumn(name="ec_form6_amendment_details_id", referencedColumnName="id")
	private Set<EcForm6AmendmentStatusV2> ecForm6AmendmentStatusV2 = new HashSet<EcForm6AmendmentStatusV2>();
	
	@Column(name="is_active")
	private Boolean isActive=true;
	
	@Column(name="is_deleted")
	private Boolean isDeleted=false;
	
	
}
