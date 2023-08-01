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
@Table(name="fc_form_c_surface_sampling",schema = "master")
public class FcFormCSurfaceSampling extends Auditable<Integer>{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fc_form_c_id", nullable = false)
	@JsonIgnore
	private FcFormC fcFormC;
	
	@Column(nullable = true)
	private String surface_sampling;
	
	@Column(nullable = true)
	private String surface_sampling_others;
	
	@Column(nullable = true)
	private Double area;
	
	@Column(nullable = true)
	private Boolean is_active;
	
	@Column(nullable = true)
	private Boolean is_delete;
	
	public FcFormCSurfaceSampling() {
		this.is_active=true;
		this.is_delete=false;
	}
}
