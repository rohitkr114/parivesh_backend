package com.backend.model.ForestClearanceFormB;

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
import com.backend.model.WildLifeClearance.WildLifeClearance;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "fc_form_b_airport_proposal", schema = "master")
public class FcFormBAirportProposal extends Auditable<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer Id;

	@Column(length = 1000, nullable = true)
	private String project_components;

	@Column(length = 50, nullable = true)
	private String passenger_capacity;

	@Column(length = 1000, nullable = true)
	private String aircraft_compatibility;

	@Column(nullable = true)
	private Double total_built_up_area;

	@Column(length = 1000, nullable = true)
	private String any_other_details;

	@Column(length = 1000, nullable = true)
	private String nearest_airport_details;

	@Column(length = 1000, nullable = true)
	private String near_by_habitation;

	@Column(length = 1000, nullable = true)
	private String connectivity_to_proposed_project;

	@Column(length = 1000, nullable = true)
	private String parking_provision;

	private Boolean is_active;

	private Boolean is_deleted;

	public FcFormBAirportProposal() {
		this.is_active=true;
		this.is_deleted=false;
	}
}
