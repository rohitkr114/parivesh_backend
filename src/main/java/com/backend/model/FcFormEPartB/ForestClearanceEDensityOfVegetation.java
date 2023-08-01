package com.backend.model.FcFormEPartB;

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
@Table(name="fc_form_e_part_b_density_of_vegetation",schema = "master")
public class ForestClearanceEDensityOfVegetation extends Auditable<Integer>{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fc_e_part_b_id", nullable = true)
	@JsonIgnore
	private ForestClearanceEBasicDetails forestClearanceEBasicDetails;
	
	@Column(nullable = false)
	private Double vegetation_area;
	
	@Column(nullable = false)
	private Double vegetation_canopy_density;
	
	@Column(length = 100,nullable = false)
	private String vegetation_eco_class;

	private Boolean is_active;
	
	private Boolean is_deleted;
	
	public ForestClearanceEDensityOfVegetation() {
		this.is_active=true;
		this.is_deleted=false;
	}
}
