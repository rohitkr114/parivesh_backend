package com.backend.model.EcForm6V2;

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

import com.backend.audit.Auditable;
import com.backend.model.DocumentDetails;

import lombok.Data;

@Data
@Entity
@Table(name="ec_form6_obtained_status", schema="master")
public class EcForm6ObtainedStatusV2 extends Auditable<Integer>{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name="ec_obtained_select", nullable=true)
	private String ecObtainedSelect;
	
	@Column(name="ec_obtained_date", nullable=true)
	private Date ecObtainedDate;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "ec_obtained_document_id", nullable = true)
	private DocumentDetails ecObtainedDocument;
	
	@Column(name="is_active")
	private Boolean isActive=true;
	
	@Column(name="is_deleted")
	private Boolean isDeleted=false;

}
