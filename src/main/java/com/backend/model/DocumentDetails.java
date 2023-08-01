package com.backend.model;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.backend.audit.Auditable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@Entity
@Table(name = "document_details", schema = "master")
@JsonIgnoreProperties({ "created_by", "created_on", "updated_by", "updated_on" })
public class DocumentDetails extends Auditable<Integer> {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "ref_id", nullable = true, length = 100)
	private String ref_id;

	@Column(name = "type", length = 50, nullable = false)
	private String type;

	@Column(name = "proposal_no", length = 50, nullable = true)
	private String proposal_no;

	@Column(name = "document_mapping_id", nullable = false)
	private Integer document_mapping_id;

	@Column(name = "document_name", length = 255, nullable = false)
	private String document_name;

	@Column(name = "UUID", length = 100, nullable = false)
	private String UUID;

	@Column(name = "version", length = 15, nullable = false)
	private String version;

	@Column(nullable = false)
	private boolean is_active;

	@Column(nullable = false)
	private boolean is_deleted;

	public DocumentDetails() {
		this.is_active = true;
		this.is_deleted = false;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRef_id() {
		return ref_id;
	}

	public void setRef_id(String ref_id) {
		this.ref_id = ref_id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getDocument_mapping_id() {
		return document_mapping_id;
	}

	public void setDocument_mapping_id(Integer document_mapping_id) {
		this.document_mapping_id = document_mapping_id;
	}

	public String getDocument_name() {
		return document_name;
	}

	public void setDocument_name(String document_name) {
		this.document_name = document_name;
	}

	public String getUUID() {
		return UUID;
	}

	public void setUUID(String uUID) {
		UUID = uUID;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public boolean isIs_active() {
		return is_active;
	}

	public void setIs_active(boolean is_active) {
		this.is_active = is_active;
	}

	public boolean isIs_deleted() {
		return is_deleted;
	}

	public void setIs_deleted(boolean is_deleted) {
		this.is_deleted = is_deleted;
	}

	public String getProposal_no() {
		return proposal_no;
	}

	public void setProposal_no(String proposal_no) {
		this.proposal_no = proposal_no;
	}

}