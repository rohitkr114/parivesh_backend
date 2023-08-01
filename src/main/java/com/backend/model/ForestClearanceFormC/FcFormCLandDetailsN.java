package com.backend.model.ForestClearanceFormC;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.backend.model.DocumentDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name="fc_form_c_land_details_n",schema = "master")
public class FcFormCLandDetailsN {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(nullable=true) 
	private Double surface_sampling_of_temporary_change;
	
	@Column(nullable=true) 
	private Double pitting_of_temporary_change;
	
	@Column(nullable=true) 
	private Double drilling_of_boreholes_of_temporary_change;
	
	@Column(nullable=true) 
	private Double construction_of_roads_temporary_change;
	
	@Column(nullable=true) 
	private Double other_activity_temporary_change;
	
	@Column(nullable=true) 
	private Double total_temporary_change;
	
	@Column(nullable=true) 
	private Double drilling_of_boreholes_of_permanent_change;
	
	@Column(nullable=true) 
	private Double construction_of_roads_permanent_change;
	
	@Column(nullable=true) 
	private Double other_activity_permanent_change;
	
	@Column(nullable=true) 
	private Double total_permanent_change;
	
	@Column(nullable=true) 
	private String name_equipment;
	
	@Column(nullable=true) 
	private String mode_of_traction;
	
	@Column(nullable=true) 
	private Double size;
	
	@Column(nullable=true) 
	private Double estimated_deployment;
	
	@Column(nullable=true) 
	private Double maximum_noise;
	
	@Column(nullable=true) 
	private Integer no_of_person;
	
	@Column(nullable=true) 
	private Double duration_of_person;

	@Column(nullable=true,length = 500) 
	private String details_of_sample;
	
	@Column(nullable=true) 
	private Double quantity_proposed_sample;
	
	@Column(nullable=true) 
	private String estimated_accuracy_level_mineral_reserve;
	
	@Column(nullable=true) 
	private String estimated_confidence_mineral_reserve;
	
	@Column(nullable=true) 
	private Double estimated_accuracy_level_boreholes;
	
	@Column(nullable=true) 
	private Double estimated_confidence_boreholes;
	
	@Column(name = "proposal_no",nullable = true)
	private String proposal_no;
	
	@Column(nullable=true) 
	private Date date_of_approval;
	
	@Column(nullable=true) 
	private Double permitted_forest_land_area;
	
	@Column(nullable=true) 
	private Date validity_from;
	
	@Column(nullable=true) 
	private Date validity_to;
	
//	@Column(nullable=true) 
//	private String compliance_status;
//	
//	@Column(nullable=true) 
//	private String compliance_condition;
//	
	@Column(nullable=true) 
	private String details_of_violation;
	
	@Column(nullable = true)
	private Boolean is_active;
	
	@Column(nullable = true)
	private Boolean is_delete;
	
	/* For Old
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "justification_of_extension_copy_id", nullable = true)
	private DocumentDetails justification_for_extension_copy;
	*/
	
	@Column(name = "justification_of_extension_copy_id", nullable = true,length = 1000)
	private String justification_for_extension_copy;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "details_of_existing_path_copy_id", nullable = true)
	private DocumentDetails details_of_existing_path_copy;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "note_containing_details_copy_id", nullable = true)
	private DocumentDetails note_containing_details_copy;
	
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fc_form_c_id", nullable = true)
	@JsonIgnore
	private FcFormC fcFormC;
	
	public FcFormCLandDetailsN() {
		this.is_active=true;
		this.is_delete=false;
	}

	public FcFormCLandDetailsN(Integer id) {
		this.id=id;
	}
	
	public FcFormCLandDetailsN(Integer id, Double surface_sampling_of_temporary_change,
			Double pitting_of_temporary_change, Double drilling_of_boreholes_of_temporary_change,
			Double construction_of_roads_temporary_change, Double other_activity_temporary_change,
			Double total_temporary_change, Double drilling_of_boreholes_of_permanent_change,
			Double construction_of_roads_permanent_change, Double other_activity_permanent_change,
			Double total_permanent_change, String name_equipment, String mode_of_traction, Double size,
			Double estimated_deployment, Double maximum_noise, Integer no_of_person, Double duration_of_person,
			String details_of_sample, Double quantity_proposed_sample, String estimated_accuracy_level_mineral_reserve,
			String estimated_confidence_mineral_reserve, Double estimated_accuracy_level_boreholes,
			Double estimated_confidence_boreholes, String proposal_no, Date date_of_approval,
			Double permitted_forest_land_area, Date validity_from, Date validity_to,String details_of_violation,String justification_for_extension_copy) {
		super();
		this.id = id;
		this.surface_sampling_of_temporary_change = surface_sampling_of_temporary_change;
		this.pitting_of_temporary_change = pitting_of_temporary_change;
		this.drilling_of_boreholes_of_temporary_change = drilling_of_boreholes_of_temporary_change;
		this.construction_of_roads_temporary_change = construction_of_roads_temporary_change;
		this.other_activity_temporary_change = other_activity_temporary_change;
		this.total_temporary_change = total_temporary_change;
		this.drilling_of_boreholes_of_permanent_change = drilling_of_boreholes_of_permanent_change;
		this.construction_of_roads_permanent_change = construction_of_roads_permanent_change;
		this.other_activity_permanent_change = other_activity_permanent_change;
		this.total_permanent_change = total_permanent_change;
		this.name_equipment = name_equipment;
		this.mode_of_traction = mode_of_traction;
		this.size = size;
		this.estimated_deployment = estimated_deployment;
		this.maximum_noise = maximum_noise;
		this.no_of_person = no_of_person;
		this.duration_of_person = duration_of_person;
		this.details_of_sample = details_of_sample;
		this.quantity_proposed_sample = quantity_proposed_sample;
		this.estimated_accuracy_level_mineral_reserve = estimated_accuracy_level_mineral_reserve;
		this.estimated_confidence_mineral_reserve = estimated_confidence_mineral_reserve;
		this.estimated_accuracy_level_boreholes = estimated_accuracy_level_boreholes;
		this.estimated_confidence_boreholes = estimated_confidence_boreholes;
		this.proposal_no = proposal_no;
		this.date_of_approval = date_of_approval;
		this.permitted_forest_land_area = permitted_forest_land_area;
		this.validity_from = validity_from;
		this.validity_to = validity_to;
		this.details_of_violation=details_of_violation;
		this.justification_for_extension_copy=justification_for_extension_copy;
	}
}
