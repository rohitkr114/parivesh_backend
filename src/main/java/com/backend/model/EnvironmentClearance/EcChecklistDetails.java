package com.backend.model.EnvironmentClearance;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.backend.audit.Auditable;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "ec_checklist_dtls", schema = "master")
public class EcChecklistDetails extends Auditable<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer Id;

	@ManyToOne
	@JoinColumn(name = "ec_partB_id")
	@JsonIgnore
	private EcPartB ecPartB;

	@Column(name = "checklist_type", nullable = false, length = 100)
	private String checklist_type;

	@Column(name = "checklist_value", nullable = false, length = 50)
	private String checklist_value;

	@Column(name = "checklist_sub_type", nullable = false, length = 100)
	private String checklist_sub_type;

	@Column(name = "checklist_sub_type_value", nullable = false, length = 50)
	private String checklist_sub_type_value;

	@Column(name = "reason_remarks", nullable = false, length = 100)
	private String reason_remarks;
	
	@Column(name = "is_active")
	private boolean is_active;

	@Column(name = "is_deleted")
	private boolean is_deleted;

	public EcChecklistDetails() {
		this.is_active = true;
		this.is_deleted = false;
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public EcPartB getEcPartB() {
		return ecPartB;
	}

	public void setEcPartB(EcPartB ecPartB) {
		this.ecPartB = ecPartB;
	}

	public String getChecklist_type() {
		return checklist_type;
	}

	public void setChecklist_type(String checklist_type) {
		this.checklist_type = checklist_type;
	}

	public String getChecklist_value() {
		return checklist_value;
	}

	public void setChecklist_value(String checklist_value) {
		this.checklist_value = checklist_value;
	}

	public String getChecklist_sub_type() {
		return checklist_sub_type;
	}

	public void setChecklist_sub_type(String checklist_sub_type) {
		this.checklist_sub_type = checklist_sub_type;
	}

	public String getChecklist_sub_type_value() {
		return checklist_sub_type_value;
	}

	public void setChecklist_sub_type_value(String checklist_sub_type_value) {
		this.checklist_sub_type_value = checklist_sub_type_value;
	}

	public String getReason_remarks() {
		return reason_remarks;
	}

	public void setReason_remarks(String reason_remarks) {
		this.reason_remarks = reason_remarks;
	}

	public boolean isIs_deleted() {
		return is_deleted;
	}

	public void setIs_deleted(boolean is_deleted) {
		this.is_deleted = is_deleted;
	}

}
