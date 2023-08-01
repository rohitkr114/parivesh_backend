package com.backend.model.ForestClearanceFormB;

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

import org.apache.axis2.jaxws.description.xml.handler.TrueFalseType;

import com.backend.audit.Auditable;
import com.backend.model.DocumentDetails;
import com.backend.model.WildLifeClearance.WildLifeClearance;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "fc_form_b_river_valley_project", schema = "master")
public class FcFormBRiverValleyProject extends Auditable<Integer>{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	
	@Column(nullable = true)
	private String river_valley_project;
	
	private String involves_construction_of;
	@Column(name="river_name",nullable=false,length=300)
	private String river_name;
	
	@Column(name = "irrigation_project_configuration_json", nullable = true, length = 3000)
	private String irrigation_project_configuration_json;
	
	@Column(name = "irrigation_project_capacity_cultural_command", nullable = true)
	private Double irrigation_project_capacity_cultural_command;
	
	
	@Column(name = "irrigation_project_capacity_irrigable_command", nullable = true)
	private Double irrigation_project_capacity_irrigable_command;	
	
	@Column(name = "irrigation_project_capacity_type_of_irrigation", nullable = true, length = 100)
	private String irrigation_project_capacity_type_of_irrigation;
	
	@Column(name = "irrigation_project_capacity_type_of_irrigation_value", nullable = true, length = 100)
	private String irrigation_project_capacity_type_of_irrigation_value;
	
	@Column(name = "hep_project_configuration_json", nullable = true, length = 3000)
	private String hep_project_configuration_json;
	
	@Column(name = "hep_project_capacity_json", nullable = true, length = 3000)
	private String hep_project_capacity_json;
	
	@Column(nullable = true)
	private Double catchement_area;

	@Column(nullable = true)
	private Double average_rainfall;

	@Column(nullable = true)
	private Double water_availability;
	
	@Column(name = "muck_restoration_plan", nullable = true, length = 500)
	private String muck_restoration_plan;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "upload_muck_restoration_plan_id", nullable = true)
	private DocumentDetails upload_muck_restoration_plan;
	
	@Column(name = "schedule_1_spieces", nullable = true, length = 500)
	private String schedule_1_spieces;
	
	@Column(name = "brief_on_e_flow", nullable = true, length = 500)
	private String brief_on_e_flow;
	
	private String fish_pass_envisaged_provision;
	@Column(name="reasons",nullable=true,length=500)
	private String reasons;

	private Boolean is_active;

	private Boolean is_deleted;

	public FcFormBRiverValleyProject() {
		this.is_active=true;
		this.is_deleted=false;
	}
}
