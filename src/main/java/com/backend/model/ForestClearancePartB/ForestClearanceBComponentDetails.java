package com.backend.model.ForestClearancePartB;

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
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "forest_clearance_part_b_component_details", schema = "master")
public class ForestClearanceBComponentDetails extends Auditable<Integer>{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fc_form_a_part_b_id", nullable = false)
	@JsonIgnore
	private ForestClearanceBBasicDetails forestClearanceBBasicDetails;
	
	@Column(length=25)
	private String component;
	
	private Double forest_area_proposed;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "revised_map_id", nullable = true)
	private DocumentDetails documentDetails_revised_map;
	
	@Column(nullable = false)
	private boolean is_active;

	@Column(nullable = false)
	private boolean is_deleted;
	
	ForestClearanceBComponentDetails(){
		this.is_active = true;
		this.is_deleted = false;
	}
}
