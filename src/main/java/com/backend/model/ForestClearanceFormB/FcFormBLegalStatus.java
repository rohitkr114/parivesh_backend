package com.backend.model.ForestClearanceFormB;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.backend.audit.Auditable;

import lombok.Data;

@Data
@Entity
@Table(name = "fc_form_b_legal_status", schema = "master")
public class FcFormBLegalStatus extends Auditable<Integer> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer id;
	
	@Column(nullable = true)
	private String legal_status_of_forest_land;
	
	@Column(nullable = true)
	private Double area;
	private Boolean is_active;

	private Boolean is_deleted;

	public FcFormBLegalStatus() {
		this.is_active=true;
		this.is_deleted=false;
	}

}
