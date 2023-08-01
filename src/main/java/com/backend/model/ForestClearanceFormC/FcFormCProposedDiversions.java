package com.backend.model.ForestClearanceFormC;

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
import com.backend.model.ForestClearanceDivisionPatchDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name="fc_form_c_proposed_diversions",schema="master")
public class FcFormCProposedDiversions extends Auditable<Integer>{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fc_form_c_Id", nullable = true)
	@JsonIgnore
	private FcFormC fcFormC;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "fc_diversion_detail_id", nullable = true)
	@Where(clause = "is_deleted='false'")
	private List<FcFormCProposedDiversionDetails> fcFormCProposedDiversionDetails=new ArrayList<>();
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "fc_proposed_diversion_id", nullable = true)
	@Where(clause = "is_deleted='false'")
	private Set<FcFormCDivisionPatchDetails> fcFormCDivisionPatchDetails;
	
	@Column(nullable = true, length = 50)
	private String division;

	@Column(nullable = true, length = 50)
	private Integer total_patches;

	@Column(nullable = true)
	private Integer division_id;
	
	@Column(nullable = true)
	private Double total_forest_land_for_division;
	
	@Column(nullable = true)
	private Double total_non_forest_land_for_division;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "kml_id", nullable = true)
	private DocumentDetails kml;

	@Column(nullable = true, length = 1000)
	private String remarks;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "diversion_map_copy_id", nullable = true)
	private DocumentDetails diversion_map_copy;
	
	@Column(nullable = true)
	private boolean is_delete;
	
	@Column(nullable = true)
	private boolean is_active;
	
	public FcFormCProposedDiversions() {
		this.is_active=true;
		this.is_delete=false;
	}
	
	public FcFormCProposedDiversions(Integer id) {
		this.id=id;
	}

	public FcFormCProposedDiversions(Integer id, List<FcFormCProposedDiversionDetails> fcFormCProposedDiversionDetails,
			Set<FcFormCDivisionPatchDetails> fcFormCDivisionPatchDetails, String division, Integer total_patches,
			DocumentDetails kml, DocumentDetails diversion_map_copy,Integer division_id,Double total_forest_land_for_division,Double total_non_forest_land_for_division) {
		this.id = id;
		this.fcFormCProposedDiversionDetails = fcFormCProposedDiversionDetails;
		this.fcFormCDivisionPatchDetails = fcFormCDivisionPatchDetails;
		this.division = division;
		this.total_patches = total_patches;
		this.kml = kml;
		this.diversion_map_copy = diversion_map_copy;
		this.division_id=division_id;
		this.total_forest_land_for_division=total_forest_land_for_division;
		this.total_non_forest_land_for_division=total_non_forest_land_for_division;
	}
	
	
}
