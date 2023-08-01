package com.backend.model.ForestClearanceFormB;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.backend.audit.Auditable;
import com.backend.model.DocumentDetails;
import com.backend.model.FcFormBPartB.FcFormBPartBEnumerationDetails;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;

@Data
@Entity
@Table(name = "fc_form_b_proposed_land", schema = "master")
@JsonIgnoreProperties({ "created_by", "created_on", "updated_by", "updated_on" })
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class FcFormBProposedLand extends Auditable<Integer> {

	public FcFormBProposedLand(Integer id) {
		this.id = id;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@OneToOne
	@JoinColumn(name = "fc_form_b_id", nullable = true)
	@JsonIgnore
	private FcFormBProjectDetails fcFormBProjectDetails;
	
	@Column(nullable = false)
	private Double total_proposed_diversion_area;
	
	@Column(nullable = true)
	private Double total_non_forestland_area_required;

//	@Column(nullable = false)
//	private Integer total_proposed_diversion_period;

//	@Column(nullable = true)
//	private String legal_status_of_forest_land;
	
	@Column(nullable = true,length=500)
	private String legal_status_of_forest_land_other;
	
	@Column(nullable = false)
	private double total_forest_land;

	@Column(nullable = false)
	private double total_non_forest_land;
	
	@Column(nullable = true)
	private Double total_area_of_kmls;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "geo_referenced_map", nullable = true)
	private DocumentDetails geo_referenced_map;

	@OneToMany(targetEntity = FcFormBLegalStatus.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "fcFormBProposedLand_id", referencedColumnName = "id")
	private Set<FcFormBLegalStatus> formBLegalStatus = new HashSet<>();

	@Column(nullable = true, length = 1000)
	private String remarks;
	
	private Boolean is_active;

	private Boolean is_deleted;

	public FcFormBProposedLand() {
		this.is_active=true;
		this.is_deleted=false;
	}

	public FcFormBProposedLand(Integer id, Double total_proposed_diversion_area,
			Double total_non_forestland_area_required, Integer total_proposed_diversion_period,
//			String legal_status_of_forest_land, 
			String legal_status_of_forest_land_other, 
			double total_forest_land,
			double total_non_forest_land, Double total_area_of_kmls) {
		this.id = id;
		this.total_proposed_diversion_area = total_proposed_diversion_area;
		this.total_non_forestland_area_required = total_non_forestland_area_required;
//		this.total_proposed_diversion_period = total_proposed_diversion_period;
//		this.legal_status_of_forest_land = legal_status_of_forest_land;
		this.legal_status_of_forest_land_other = legal_status_of_forest_land_other;
		this.total_forest_land = total_forest_land;
		this.total_non_forest_land = total_non_forest_land;
		this.total_area_of_kmls = total_area_of_kmls;
	}

	public FcFormBProposedLand(Integer id, Double total_proposed_diversion_area,
			Double total_non_forestland_area_required, String legal_status_of_forest_land_other,
			double total_forest_land, double total_non_forest_land, Double total_area_of_kmls, Set<FcFormBLegalStatus> formBLegalStatus) {
		this.id = id;
		this.total_proposed_diversion_area = total_proposed_diversion_area;
		this.total_non_forestland_area_required = total_non_forestland_area_required;
		this.legal_status_of_forest_land_other = legal_status_of_forest_land_other;
		this.total_forest_land = total_forest_land;
		this.total_non_forest_land = total_non_forest_land;
		this.total_area_of_kmls = total_area_of_kmls;
		this.formBLegalStatus = formBLegalStatus;
	}


}
