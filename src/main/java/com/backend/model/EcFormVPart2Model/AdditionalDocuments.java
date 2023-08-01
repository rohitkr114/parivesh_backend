package com.backend.model.EcFormVPart2Model;

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
import com.backend.model.FcFormEPartB.ForestClearanceEBasicDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "additional_documents", schema = "master")
public class AdditionalDocuments extends Auditable<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "ec_form_v_part_2_id", nullable = true)
//	@JsonIgnore
//	private EcFormVPart2 ecFormVPart2;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "documents", nullable = true)
	private DocumentDetails document_name;

	@Column(name = "ref_id", nullable = true, length = 100)
	private String ref_id;

	@Column(name = "clearance_id", nullable = true, length = 100)
	private String clearance_id;
	
	@Column(name = "is_active")
	private Boolean is_active =true;

	@Column(name = "is_deleted")
	private Boolean is_deleted =false;

//	public AdditionalDocuments(Boolean is_active, Boolean is_deleted) {
//		this.is_active = true;
//		this.is_deleted = false;
//	}

}
