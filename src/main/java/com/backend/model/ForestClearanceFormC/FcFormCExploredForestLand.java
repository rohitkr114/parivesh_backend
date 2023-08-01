package com.backend.model.ForestClearanceFormC;

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
@Table(name="fc_form_c_explored_forest_land",schema = "master")
public class FcFormCExploredForestLand extends Auditable<Integer>{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fc_form_c_id", nullable = false)
	@JsonIgnore
	private FcFormC fcFormC;
	
	@Column(nullable = true) 
	private String legal_status_of_forest_land;
	
	@Column(nullable = true)
	private String legal_status_of_forest_land_other;
	
	@Column(nullable = true) 
	private Double legal_status_forest_land_area;

	private Boolean is_delete;
	
	private Boolean is_active;
	
	public FcFormCExploredForestLand() {
		this.is_active=true;
		this.is_delete=false;
	}
}
