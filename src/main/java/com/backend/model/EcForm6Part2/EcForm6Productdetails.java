package com.backend.model.EcForm6Part2;

import java.io.Serializable;
import java.sql.Date;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.backend.audit.Auditable;
import com.backend.model.DocumentDetails;
import com.backend.model.EcForm6Part1.EcForm6BasicDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@Table(name = "ec_form6_impl_product_detail", schema = "master")
public class EcForm6Productdetails extends Auditable<Integer> implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "project_status_activity", nullable = true)
	private String status_of_implementation;

	@Column(name = "project_status_latest_consent_ref_spcb_utpcc", length = 100, nullable = true)
	private String reference_number_consent_obtained;
	
	@ManyToOne
	@JoinColumn(name = "ec_form6_basic_detail_id", nullable = true)
	@JsonIgnore
	private EcForm6BasicDetails ecForm6BasicDetails;

	@Column(name = "project_status_date_latest_consent_issued")
	private Date latest_consent_issued_date;

	@Column(name = "project_status_validitydate_of_latest_consent")
	private Date latest_consent_validity;
	
	@Column(name = "project_status_reasons_of_thereof", length = 100, nullable = true)
	private String reason;

	@Column(name = "amendment_whether_sought")
	private Boolean amendment_environmental_clearance;

	@Column(name = "amendment_sought_for", nullable = true)
	private String amendment_sought_for;

	@Column(name = "amendment_conf_wether")// this is extra as per ui
	private Boolean  whether_any_other_amendment;

	@Column(name = "amendment_required_whether")
	private Boolean whether_any_amendment_exist;
	
	

	@Column(name = "reason_for_seeking")// added after ui side
	private String reason_for_seeking;
	
	@Column(nullable = true)
	private Boolean is_delete;

	@Column(nullable = true)
	private Boolean is_active;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "upload_latest_consent_order_copy", nullable = true)
	private DocumentDetails latest_consent_order_copy;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "upload_latest_consent_order_copy_including_renewal", nullable = true)
	//private DocumentDetails latest_consent_order_copy_including_renewal;
	private DocumentDetails consent_order_including_renewal;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "upload_brief_note_status_of_implementation", nullable = true)
	private DocumentDetails brief_note_status_of_implementation_copy;
	//private DocumentDetails brief_note_status_of_implementation;
	
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "upload_substantiating_reasons_of_delay", nullable = true)
	private DocumentDetails substantiating_reasons_of_delay ;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "upload_bar_chart_of_implementation", nullable = true)
	private DocumentDetails bar_chart_of_implementation;
	
	
	
	// child relationshipl ec_form6_impl_product_status_detail
	// cascade=org.hibernate.annotations.CascadeType
	@OnDelete(action = OnDeleteAction.CASCADE)
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "ec_Form6_Productdetails_id", referencedColumnName = "id")
	private List<EcForm6ProductStatusdetails> ecForm6EcStatus;

	@OnDelete(action = OnDeleteAction.CASCADE)
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "ec_Form6_Productdetails_id", referencedColumnName = "id")
	private List<EcForm6ProductProByprodetails> product;
	//private List<EcForm6ProductProByprodetails> ecForm6ProductProByprodetails;

	@OnDelete(action = OnDeleteAction.CASCADE)
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "ec_Form6_Productdetails_id", referencedColumnName = "id")
	private List<EcForm6ProductAmendmentRequiredApproved> product_otheramendment;

	@OnDelete(action = OnDeleteAction.CASCADE)
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "ec_Form6_Productdetails_id", referencedColumnName = "id")
	private List<EcForm6ProductAmendmentConfDetails> product_exist;
		   	
	// Added new
	@Column(name = "amendement_sought_other")
	private String amendement_sought_other;
	
	@Column(name = "reference_number_consent_establishment")
	private String reference_number_consent_establishment;
	
   	@Column(name = "consent_issue_date")
	private Date consent_issue_date;
	
   	@Column(name = "consent_validity")
	private Date consent_validity;
   	   	
	//New Property Endd
	
	@Transient
	private String lastStatus;
	
	public EcForm6Productdetails() {
		this.is_active = true;
		this.is_delete = false;
	}
	
}
