package com.backend.model.EcForm6Part1;

import java.io.Serializable;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.backend.audit.Auditable;
import com.backend.model.CommonFormDetail;
import com.backend.model.DocumentDetails;
import com.backend.model.EnvironmentClearence;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ec_form6_basic_detail", schema = "master")
public class EcForm6BasicDetails extends Auditable<Integer> implements Serializable{
//public class EcForm6BasicDetails extends Auditable<Integer>{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique=true)
	private Integer id;
	
	@Column(name = "proposal_no", nullable = true)
	private String proposal_no;

	/*
	@Column(name = "caf_id", nullable = false, unique=true)
	private String caf_id;
	*/
	
	//@Column(name = "caf_id", nullable = false, unique=true, length = 50) // As per SRS Length 50
	//private String caf_id;

	@Column(name = "eia_notification_item_no_capacity") //int 50 as per srs
	private Integer eia_notification_item_no_capacity;

	@Column(name = "eia_notification_item_no_major_activity")
	private Integer major_activity_id;
	
	@Column(name = "eia_notification_item_no_major_sub_activity")
	private Integer major_sub_activity_id;

	@Column(name = "eia_notification_item_no_major_activity_capacity")
	private String eia_notification_item_no_major_activity_capacity;

	/*
	@Column(name = "eia_notification_item_no_minor_activity")
	private Integer eia_notification_item_no_minor_activity;
	*/
	
	@Column(name = "eia_notification_item_no_minor_activity", length = 10) // As per SRS varchar(10)
	private String eia_notification_item_no_minor_activity;

	/*
	@Column(name = "eia_notification_item_no_minor_activity_capacity")
	private String eia_notification_item_no_minor_activity_capacity;
	*/
	
	@Column(name = "eia_notification_item_no_minor_activity_capacity") // As per SRS int (50)
	private Integer eia_notification_item_no_minor_activity_capacity;

	/*
	@Column(name = "ammendment_date")
	private Date ammendment_date;
	*/
	
	/*@Temporal(TemporalType.DATE)
	@Column(name = "ammendment_date") // As per SRS Date format DD/MM/YYYY
	private Date ec_obtained_date;*/

	/*
	@Column(name = "date_of_issue_prior_ec") 
	private Date date_of_issue_prior_ec;
   */
	
	@Temporal(TemporalType.DATE)
	@Column(name = "date_of_issue_prior_ec") // As per SRS date format DD/MM/YYYY 
	private Date prior_environmental_clearance_date;
	
	
	/*
	@Column(name = "eia_notification_item_no")
	private Integer eia_notification_item_no;
	*/
	
	@Column(name = "eia_notification_item_no", length = 10)
	private String eia_notification_item_no;
	
	@Column(name = "file_reference_from_KMN_earlier")
	private Integer file_reference_from_KMN_earlier;

	/*
	@Column(name = "moef_cc_seiaa_File_no")
	private String moef_cc_seiaa_File_no;
	*/
	
	//@Column(name = "moef_cc_seiaa_File_no", length = 100) // As per SRS nvarchar(100)
	//private String moef_cc_seiaa_File_no;
	@Column(name = "moef_cc_seiaa_File_no", length = 100) // As per SRS nvarchar(100)
	private String moef_file_no;
	
	/*
	@Column(name = "whether_amendment")
	private String whether_amendment;
	*/
	
	@Column(name = "whether_amendment") // As per SRS Boolean
	private Boolean is_earlier_ec_obtained;
	
	/*@Column(name = "whether_act_select") // As per SRS Boolean
	private String ec_obtained_select;*/

	/*
	@Column(name = "whether_multiple_items")
	private Integer whether_multiple_items;
	*/

	@Column(name = "whether_multiple_items") //Yes or No
	private Boolean is_multiple_item_envolved;
	
	/*
	@Column(name = "copy_ec_letter_uuid")
	private String copy_ec_letter_uuid;
	*/
	
	/*
	//2
	@Column(name = "copy_ec_letter_uuid", length = 100) // As per SRS length 100
	private String copy_ec_letter_uuid;
	
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumns({
		@JoinColumn (name="uuid", referencedColumnName = "copy_ec_letter_uuid", insertable = false, updatable = false)
	})
	List<DocumentDetails> copy_ec_letter_doc;
	*/
	
	//2

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "upload_copy_ec_letter", nullable = true)
	//@JsonIgnore
	private DocumentDetails ec_letter_copy;
	
	
	
	/*
	@Column(name = "chronology_brief_uuid")
	private String chronology_brief_uuid;
	*/
	
	/*
	 //1
	@Column(name = "chronology_brief_uuid", length = 100) // As per SRS length 100
	private String chronology_brief_uuid;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumns({
		
		@JoinColumn (name="uuid", referencedColumnName = "chronology_brief_uuid", insertable = false, updatable = false)})
	List<DocumentDetails> chronology_brief_doc;
	*/
	
	////1
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "upload_chronology_brief", nullable = true)
	//@JsonIgnore
	private DocumentDetails chronology_of_clearances;
	
	
	
	// Child Table Reference , form6_basic_detail_earlier_ec( caf_id: 1:M), 
	
//	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//	//@JoinColumns({@JoinColumn (name="caf_id", referencedColumnName = "caf_id", insertable = true, updatable = true)})
//	@JoinColumns({@JoinColumn (name="form6_basic_id", referencedColumnName = "id", insertable = true, updatable = true)})
//	//List<EcForm6BasicDetailsEarlierEc> ecForm6BasicDetailsEarlierec;
//	//Changed as per UI Payload 
//	@JsonIgnore
//	List<EcForm6BasicDetailsEarlierEc> ecFormSixEcObtained;	
//	//document_details (copy_ec_letter_uuid : uuid) 1:1
	
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//@JoinColumns({@JoinColumn (name="form6_basic_id", referencedColumnName = "id", insertable = false, updatable = false)})
	@JoinColumn(name="form6_basic_id", referencedColumnName = "id")
	//@JsonIgnore
	//List<EcForm6BasicDetailsEarlierEc> ecFormSixEcObtained;  //ecFormSixEcObtained
	List<EcForm6BasicDetailsEarlierEc> ecFormSixEcObtained; 
	
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "basic_detail_id", referencedColumnName = "id")	
	//@JsonIgnore
	private List<Form6BasicDetailsActivity> ecActivities;
	
	// This is for minor activity
	@OnDelete(action = OnDeleteAction.CASCADE)
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "basic_detail_id", referencedColumnName = "id")
	private List<Form6BasicDetailsMinorActivity> ecFormSixMinorActivity;
	
	
	//, insertable = false, updatable = false
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "caf_id")
	@JsonIgnore
	private CommonFormDetail commonFormDetail;

	// Environment Clearance : This is integrated on 12 Dec.
	/*
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ec_id", nullable = true)
	@JsonIgnore
	private EnvironmentClearence environmentClearence;
	*/
	
	
	@Transient
	private String lastStatus;
	
	@Transient
	private Integer project_id;
}