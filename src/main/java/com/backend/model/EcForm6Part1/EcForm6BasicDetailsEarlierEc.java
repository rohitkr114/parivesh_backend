package com.backend.model.EcForm6Part1;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.backend.audit.Auditable;
import com.backend.model.DocumentDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ec_form6_basic_detail_earlier_ec", schema = "master")
public class EcForm6BasicDetailsEarlierEc extends Auditable<Integer> implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/*
	@Column(name = "caf_id", nullable = false, unique = false)
	private String caf_id;
	*/
	
	//@Column(name = "caf_id", nullable = false, unique = false, length = 50) // As per SRS Length 50
	//private String caf_id;

	/*@JsonIgnore
	@Column(name = "form6_basic_id")
	private Integer form6_basic_id;*/

	/*
	@Column(name = "earlier_ec_upload_uuid")
	private String earlier_ec_upload_uuid;
	*/
	
	/*
	@Column(name = "earlier_ec_upload_uuid", length = 100) // As per SRS Length 100
	private String earlier_ec_upload_uuid;

	@OneToMany
	@JoinColumns({
			@JoinColumn(name = "uuid", referencedColumnName = "earlier_ec_upload_uuid", insertable = true, updatable = true) })
	List<DocumentDetails> earlier_ec_upload_doc;
	*/

	//@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "ec_obtained_document_id", referencedColumnName = "id")
	//@JsonIgnore
	private DocumentDetails ec_obtained_document;
	
	@Column(name = "whether_act_select") // As per SRS Boolean
	private String ec_obtained_select;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "ammendment_date") // As per SRS Date format DD/MM/YYYY
	private Date ec_obtained_date;
	
}