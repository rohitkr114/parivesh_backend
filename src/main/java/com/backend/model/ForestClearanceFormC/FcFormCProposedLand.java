package com.backend.model.ForestClearanceFormC;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.backend.audit.Auditable;
import com.backend.model.ForestClearanceE.FcFormE;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name="fc_form_c_proposed_land",schema ="master")
public class FcFormCProposedLand extends Auditable<Integer>{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(nullable = true)
	private Integer total_proposed_diversion_period;

	@Column(nullable = true)
	private String legal_status_of_forest_land;
	
	@Column(nullable = true,length=500)
	private String legal_status_of_forest_land_other;
	
	@Column(nullable = true)
	private Double total_forest_land;

	@Column(nullable = true)
	private Double total_non_forest_land;
	
	@Column(nullable = true)
	private Double total_area_of_kmls;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fc_form_c_id", nullable = true)
	@JsonIgnore
	private FcFormC fcFormC;

	@Column(nullable = true)
	private Double legal_status_forest_land_area;
	
	@Column(nullable = true)
	private Double canopy_density;
	
	@Column(nullable = true)
	private Boolean is_active;
	
	@Column(nullable = true)
	private Boolean is_deleted;
	
	public FcFormCProposedLand() {
		this.is_active=true;
		this.is_deleted=false;
	}

	public FcFormCProposedLand(Integer id) {
		this.id=id;
	}
	
	public FcFormCProposedLand(Integer id,Integer total_proposed_diversion_period,
			String legal_status_of_forest_land, String legal_status_of_forest_land_other, double total_forest_land,
			double total_non_forest_land, Double total_area_of_kmls,Double legal_status_forest_land_area,Double canopy_density) {
		super();
		this.id = id;
		this.total_proposed_diversion_period = total_proposed_diversion_period;
		this.legal_status_of_forest_land = legal_status_of_forest_land;
		this.legal_status_of_forest_land_other = legal_status_of_forest_land_other;
		this.total_forest_land = total_forest_land;
		this.total_non_forest_land = total_non_forest_land;
		this.total_area_of_kmls = total_area_of_kmls;
		this.legal_status_forest_land_area=legal_status_forest_land_area;
		this.canopy_density=canopy_density;
	}
	
	
	
}
