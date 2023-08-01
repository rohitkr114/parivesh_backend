package com.backend.model;

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
import com.backend.model.EnvironmentClearance.EcAirportProposal;
import com.backend.model.EnvironmentClearance.EcPartB;
import com.backend.model.WildLifeClearance.WildLifeClearance;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "airport_proposal", schema = "master")
public class AirportProposal extends Auditable<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer Id;

	@Column(length = 50)
	private Integer cafId;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ec_partb_id", nullable = true)
	@JsonIgnore
	private EcPartB ecPartB;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fc_id", nullable = true)
	@JsonIgnore
	private ForestClearance forestClearance;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "wlc_id", nullable = true)
	@JsonIgnore
	private WildLifeClearance wildLifeClearance;

	@Column(length = 200)
	private String projectComponents;

	@Column(length = 50)
	private String passengerCapacity;

	@Column(length = 200)
	private String aircraftCompatibility;

	@Column(length = 50)
	private String totalBuiltUpArea;

	@Column(length = 300)
	private String anyOtherDetails;

	@Column(length = 300)
	private String nearestAirportDetails;

	@Column(length = 300)
	private String nearByHabitation;

	@Column(length = 300)
	private String connectivityToProposedProject;

	@Column(length = 300)
	private String parkingProvision;

	private Boolean isDeleted;

	private Boolean isActive;

	public AirportProposal(EcAirportProposal ecAirportProposal) {
		super();
		this.cafId = ecAirportProposal.getEcPartB().getEnvironmentClearence().getCommonFormDetail().getId();
		this.ecPartB = ecAirportProposal.getEcPartB();
		this.projectComponents = ecAirportProposal.getProject_components();
		this.passengerCapacity = ecAirportProposal.getPassenger_capacity();
		this.aircraftCompatibility = ecAirportProposal.getAircraft_compatibility();
		this.totalBuiltUpArea = ecAirportProposal.getTotal_built_up_area();
		this.anyOtherDetails = ecAirportProposal.getAny_other_details();
		this.nearestAirportDetails = ecAirportProposal.getNearest_airport_details();
		this.nearByHabitation = ecAirportProposal.getNear_by_habitation();
		this.connectivityToProposedProject = ecAirportProposal.getConnectivity_to_proposed_project();
		this.parkingProvision = ecAirportProposal.getParking_provision();
		this.isDeleted = ecAirportProposal.isIs_deleted();
	}

	public AirportProposal(ForestClearanceAirportProposal fcAirportProposal) {
		super();
		this.cafId = fcAirportProposal.getForestClearance().getCommonFormDetail().getId();
		this.forestClearance = fcAirportProposal.getForestClearance();
		this.wildLifeClearance = fcAirportProposal.getWildLifeClearance();
		this.projectComponents = fcAirportProposal.getProject_components();
		this.passengerCapacity = fcAirportProposal.getPassenger_capacity();
		this.aircraftCompatibility = fcAirportProposal.getAircraft_compatibility();
		this.totalBuiltUpArea = fcAirportProposal.getTotal_built_up_area().toString();
		this.anyOtherDetails = fcAirportProposal.getAny_other_details();
		this.nearestAirportDetails = fcAirportProposal.getNear_by_habitation();
		this.nearByHabitation = fcAirportProposal.getNear_by_habitation();
		this.connectivityToProposedProject = fcAirportProposal.getConnectivity_to_proposed_project();
		this.parkingProvision = fcAirportProposal.getParking_provision();
		this.isDeleted = fcAirportProposal.isIs_deleted();
		this.isActive = fcAirportProposal.isIs_active();
	}

}
