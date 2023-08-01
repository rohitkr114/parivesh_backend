package com.backend.model.ForestClearanceFormCPartB;

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
@Table(name="fc_form_c_part_b_district_wise_kml_details",schema = "master")
public class FcFormCPartBDistrictWiseKmlDetails extends Auditable<Integer>{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private String range;
	
	private String forest_type;
	
	private String site_name;
	
	private Double forest_area_proposed;
	
	private String location_of_forest;
	
	private String shape_of_forest_area_proposed;
	
	@Column(nullable = true,columnDefinition = "boolean default true")
	private Boolean manual_entry;
	
	@Column(nullable = false)
	private Boolean is_active;

	@Column(nullable = false)
	private Boolean is_deleted;
	
	public FcFormCPartBDistrictWiseKmlDetails() {
		this.is_active=true;
		this.is_deleted=false;
	}

}
