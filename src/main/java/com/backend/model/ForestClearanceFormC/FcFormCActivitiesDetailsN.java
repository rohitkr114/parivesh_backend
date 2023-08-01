package com.backend.model.ForestClearanceFormC;

import java.util.ArrayList;
import java.util.List;

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

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name="fc_form_c_activity_details_n",schema = "master")
public class FcFormCActivitiesDetailsN {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
//	@Column(nullable = true)
//	private String surface_Sampling;
//
//	@Column(nullable = true)
//	private Double area;
	
	@OneToMany(mappedBy = "fcFormC", cascade = CascadeType.ALL)
	@Fetch(FetchMode.SELECT)
	@Where(clause = "is_delete='false'")
	List<FcFormCProposedDiversions> fcFormCProposedDiversions = new ArrayList<>();
	
	
	@Column(nullable = true)
	private Integer no_of_pits;

	@Column(nullable = true)
	private Double width_of_pit;

	@Column(nullable = true)
	private Double depth_of_pit;

	@Column(nullable = true)
	private Double length_of_pit;

	@Column(nullable = true)
	private Double volume_of_excavation;

	@Column(nullable = true)
	private Double surface_area;

	@Column(nullable = true)
	private Integer no_of_drills;

	@Column(nullable = true)
	private Double diameter;

	@Column(nullable = true)
	private Double depth_of_boreholes;

	@Column(nullable = true)
	private Double forest_area_temporary_change;

	@Column(nullable = true)
	private Double forest_area_permanent_change;

	@Column(nullable = true)
	private Double meterage_of_boreholes;

	@Column(nullable = true)
	private Double length_temporary_change;

	@Column(nullable = true)
	private Double width_in_temporary_change;

	@Column(nullable = true)
	private Double area_in_temporary_change;

	@Column(nullable = true)
	private Double length_permanent_change;

	@Column(nullable = true)
	private Double width_in_permanent_change;

	@Column(nullable = true)
	private Double area_in_permanent_change;

	@Column(nullable = true)
	private Boolean is_activity_involved_on_temporary_change;

	@Column(nullable = true)
	private String activity_on_temporary_change;

	@Column(nullable = true)
	private Double activity_area_on_temporary_change;

	@Column(nullable = true)
	private Boolean is_activity_involved_on_permanent_change;

	@Column(nullable = true)
	private String activity_on_permanent_change;

	@Column(nullable = true)
	private Double activity_area_on_permanent_change;
	
	@Column(nullable = true)
	private Boolean is_active;
	
	@Column(nullable = true)
	private Boolean is_delete;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fc_form_c_id", nullable = true)
	@JsonIgnore
	private FcFormC fcFormC;
	
	public FcFormCActivitiesDetailsN() {
		this.is_active=true;
		this.is_delete=false;
	}

	public FcFormCActivitiesDetailsN(Integer id) {
		this.id=id;
	}
	
	public FcFormCActivitiesDetailsN(Integer id,Integer no_of_pits,
			Double width_of_pit, Double depth_of_pit, Double length_of_pit, Double volume_of_excavation,
			Double surface_area, Integer no_of_drills, Double diameter, Double depth_of_boreholes,
			Double forest_area_temporary_change, Double forest_area_permanent_change, Double meterage_of_boreholes,
			Double length_temporary_change, Double width_in_temporary_change, Double area_in_temporary_change,
			Double length_permanent_change, Double width_in_permanent_change, Double area_in_permanent_change,
			Boolean is_activity_involved_on_temporary_change, String activity_on_temporary_change,
			Double activity_area_on_temporary_change, Boolean is_activity_involved_on_permanent_change,
			String activity_on_permanent_change, Double activity_area_on_permanent_change) {
		super();
		this.id = id;
		this.no_of_pits = no_of_pits;
		this.width_of_pit = width_of_pit;
		this.depth_of_pit = depth_of_pit;
		this.length_of_pit = length_of_pit;
		this.volume_of_excavation = volume_of_excavation;
		this.surface_area = surface_area;
		this.no_of_drills = no_of_drills;
		this.diameter = diameter;
		this.depth_of_boreholes = depth_of_boreholes;
		this.forest_area_temporary_change = forest_area_temporary_change;
		this.forest_area_permanent_change = forest_area_permanent_change;
		this.meterage_of_boreholes = meterage_of_boreholes;
		this.length_temporary_change = length_temporary_change;
		this.width_in_temporary_change = width_in_temporary_change;
		this.area_in_temporary_change = area_in_temporary_change;
		this.length_permanent_change = length_permanent_change;
		this.width_in_permanent_change = width_in_permanent_change;
		this.area_in_permanent_change = area_in_permanent_change;
		this.is_activity_involved_on_temporary_change = is_activity_involved_on_temporary_change;
		this.activity_on_temporary_change = activity_on_temporary_change;
		this.activity_area_on_temporary_change = activity_area_on_temporary_change;
		this.is_activity_involved_on_permanent_change = is_activity_involved_on_permanent_change;
		this.activity_on_permanent_change = activity_on_permanent_change;
		this.activity_area_on_permanent_change = activity_area_on_permanent_change;
	}
}
