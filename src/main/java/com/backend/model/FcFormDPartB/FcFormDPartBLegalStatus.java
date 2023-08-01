package com.backend.model.FcFormDPartB;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.backend.audit.Auditable;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "fc_form_d_part_b_legal_status", schema = "master")
public class FcFormDPartBLegalStatus extends Auditable<Integer>{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(length = 100)
	private String forest_land_legal_status;

	private Double forest_land;

	private Boolean is_active;

	private Boolean is_deleted;

	public FcFormDPartBLegalStatus() {
		this.is_active = true;
		this.is_deleted = false;
	}

	@Column(length = 100, nullable = true)
	private String please_specify_legal;

	
}
