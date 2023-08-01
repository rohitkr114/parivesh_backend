package com.backend.model.ForestClearanceFormB;

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
import com.backend.model.ForestClearanceFormC.FcFormCDivisionPatchDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "fc_form_b_proposed_diversions", schema = "master")
public class FcFormBProposedDiversions extends Auditable<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fc_form_b_Id", nullable = true)
	@JsonIgnore
	private FcFormBProjectDetails fcFormBProjectDetails;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "fc_diversion_detail_id", nullable = true)
	private List<FcFormBProposedDiversionsDetails> fcFormBProposedDiversionDetails = new ArrayList<>();

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "fc_proposed_diversion_id", nullable = true)
	@Where(clause = "is_deleted='false'")
	private Set<FcFormBDivisionPatchDetails> fcFormBDivisionPatchDetails;

	@Column(nullable = true, length = 50)
	private String division;

	@Column(nullable = true)
	private Integer division_id;

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

	@Column(name = "total_forest_land_for_division", nullable = true)
	private Double total_forest_land_for_division;

	@Column(name = "total_non_forest_land_for_division", nullable = true)
	private Double total_non_forest_land_for_division;

	@Column(nullable = true)
	private Boolean is_delete;

	@Column(nullable = true)
	private Boolean is_active;

	public FcFormBProposedDiversions() {
		this.is_active = true;
		this.is_delete = false;
	}
}
