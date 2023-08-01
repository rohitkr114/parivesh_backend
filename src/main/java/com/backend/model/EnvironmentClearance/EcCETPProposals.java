package com.backend.model.EnvironmentClearance;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "ec_cetp_proposals", schema = "master")
public class EcCETPProposals {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer id;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ec_partb_id", nullable = true)
	@JsonIgnore
	private EcPartB ecPartB;

	@Column(name = "project_components", nullable = true, length = 200)
	private String project_components;

	private String other_details;

	@Column(name = "nearest_operational_cetp", nullable = true, length = 200)
	private String nearest_operational_cetp;

	@Column(name = "waste_dtls_to_be_handled", nullable = true, length = 200)
	private String waste_dtls_to_be_handled;

	@Column(name = "member_units_details", nullable = true, length = 200)
	private String member_units_details;

	@Column(name = "mode_of_trnsprt_to_cetp", nullable = true, length = 200)
	private String mode_of_trnsprt_to_cetp;

	@Column(name = "lnd_area_distirbution", nullable = true, length = 50)
	private String lnd_area_distirbution;

	private boolean is_deleted;

	EcCETPProposals() {

		this.is_deleted = false;
	}

	public EcPartB getEcPartB() {
		return ecPartB;
	}

	public void setEcPartB(EcPartB ecPartB) {
		this.ecPartB = ecPartB;
	}

	public String getProject_components() {
		return project_components;
	}

	public void setProject_components(String project_components) {
		this.project_components = project_components;
	}

	public String getOther_details() {
		return other_details;
	}

	public void setOther_details(String other_details) {
		this.other_details = other_details;
	}

	public String getNearest_operational_cetp() {
		return nearest_operational_cetp;
	}

	public void setNearest_operational_cetp(String nearest_operational_cetp) {
		this.nearest_operational_cetp = nearest_operational_cetp;
	}

	public String getWaste_dtls_to_be_handled() {
		return waste_dtls_to_be_handled;
	}

	public void setWaste_dtls_to_be_handled(String waste_dtls_to_be_handled) {
		this.waste_dtls_to_be_handled = waste_dtls_to_be_handled;
	}

	public String getMember_units_details() {
		return member_units_details;
	}

	public void setMember_units_details(String member_units_details) {
		this.member_units_details = member_units_details;
	}

	public String getMode_of_trnsprt_to_cetp() {
		return mode_of_trnsprt_to_cetp;
	}

	public void setMode_of_trnsprt_to_cetp(String mode_of_trnsprt_to_cetp) {
		this.mode_of_trnsprt_to_cetp = mode_of_trnsprt_to_cetp;
	}

	public String getLnd_area_distirbution() {
		return lnd_area_distirbution;
	}

	public void setLnd_area_distirbution(String lnd_area_distirbution) {
		this.lnd_area_distirbution = lnd_area_distirbution;
	}

	public boolean isIs_deleted() {
		return is_deleted;
	}

	public void setIs_deleted(boolean is_deleted) {
		this.is_deleted = is_deleted;
	}

}
