package com.backend.model.ForestClearanceFormB;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.backend.audit.Auditable;
import com.backend.model.WildLifeClearance.WildLifeClearance;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "fc_form_b_submerged_area", schema = "master")
public class FcFormBSubmergedArea extends Auditable<Integer>{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer Id;

	@Column(nullable = true)
	private String area_submerged;

	@Column(nullable = true)
	private Double area;

	private Boolean is_active;

	private Boolean is_deleted;

	public FcFormBSubmergedArea() {
		this.is_active=true;
		this.is_deleted=false;
	}
}
