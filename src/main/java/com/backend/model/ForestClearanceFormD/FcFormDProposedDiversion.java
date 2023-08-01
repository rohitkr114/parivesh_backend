package com.backend.model.ForestClearanceFormD;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import com.backend.audit.Auditable;
import com.backend.model.DocumentDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name="fc_form_d_proposed_diversion",schema = "master")
public class FcFormDProposedDiversion extends Auditable<Integer>{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(nullable = true, length = 50)
	private String division;

	@Column(nullable = true, length = 50)
	private Integer total_patches;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "kml_id", nullable = true)
	private DocumentDetails kml;

	@Column(nullable = true, length = 1000)
	private String remarks;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "diversion_map_copy_id", nullable = true)
	private DocumentDetails diversion_map_copy;
	
	@Column(name="total_forest_land_for_division",nullable = true)
	private Double total_forest_land_for_division;
	
	@Column(name="total_non_forest_land_for_division",nullable = true)
	private Double total_non_forest_land_for_division;
	
	@Column(name="protected_area",nullable = true)
	private Double protected_area;
	
	@Column(name="core_zone_area",nullable = true)
	private Double core_zone_area;
	
	@Column(name="buffer_zone_area",nullable = true)
	private Double buffer_zone_area;
	
	@Column(nullable = true)
	private Integer division_id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fc_form_d_Id", nullable = true)
	@JsonIgnore
	private FcFormD fcFormD;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "fc_form_d_diversion_detail_id", nullable = true)
	private List<FcFormDProposedDiversionDetails> fcFormDProposedDiversionDetails=new ArrayList<>();

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "fc_form_d_division_patch_details_id", nullable = true)
	@Where(clause = "is_deleted='false'")
	private Set<FcFormDDivisionPatchDetails> fcFormDDivisionPatchDetails;
	
	private Boolean is_active;
	
	private Boolean is_deleted;
	
	public FcFormDProposedDiversion() {
		this.is_active=true;
		this.is_deleted=false;
	}
}
