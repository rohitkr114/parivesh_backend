package com.backend.model.EcPartC;

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

import org.hibernate.annotations.Where;

import com.backend.audit.Auditable;
import com.backend.model.DocumentDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "ec_partc_other_details", schema = "master")
public class EcOtherDetails extends Auditable<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ec_id", nullable = false)
	@JsonIgnore
	private EcPartC ecPartC;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "air_quality_impact_id", nullable = true)
	@Where(clause = "is_deleted='false'")
	private Set<EcAirQualityImpacts> ecAirQualityImpacts = new HashSet<>();

	@Column(name = "environment_mgmt_funds", nullable = true)
	private Double environment_mgmt_funds;

	@Column(name = "corporate_environment_funds", nullable = true)
	private Double corporate_environment_funds;

	@Column(name = "environment_mgmt_plan_funds", nullable = true)
	private Double environment_mgmt_plan_funds;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "summary_allocation_id", nullable = true)
	@Where(clause = "is_deleted='false'")
	private Set<EcSummaryAllocations> ecSummaryAllocations = new HashSet<>();

	@Column(name = "total_capital_cost", nullable = true)
	private Integer total_capital_cost;

	@Column(name = "total_recurring_cost", nullable = true)
	private Integer total_recurring_cost;

	@Column(name = "land_acquisition_status", nullable = true, length = 50)
	private String land_acquisition_status;

	@Column(name = "acquired_land", nullable = true)
	private Double acquired_land;

	@Column(name = "land_to_be_acquired", nullable = true)
	private Double land_to_be_acquired;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "parameter_monitor_id", nullable = true)
	@Where(clause = "is_deleted='false'")
	private Set<EcParameterMonitor> ecParameterMonitors = new HashSet<>();

	@Column(name = "is_environmental_cell_proposed", nullable = true)
	private Boolean is_environmental_cell_proposed;

	@Column(name = "env_organisational_structure", nullable = true, length = 200)
	private String env_organisational_structure;

	@Column(name = "details_of_responsibilities", nullable = true, length = 200)
	private String details_of_responsibilities;

	@Column(name = "procedure_to_report", nullable = true, length = 200)
	private String procedure_to_report;

	@Column(name = "env_cell_reason", nullable = true, length = 500)
	private String env_cell_reason;

	@Column(name = "procedure_to_review", nullable = true, length = 200)
	private String procedure_to_review;

	private String is_compliance_report;

	@Column(nullable = true, length = 500)
	private String compliance_date_of_site;

	@Column(nullable = true, length = 500)
	private String compliance_final_observation;

	@Column(nullable = true, length = 500)
	private String compliance_reason;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "compliance_report_id", nullable = true)
	private DocumentDetails compliance_report;
	
	@Column(name = "identification_no", nullable = true)
	private String identification_no;

	public EcOtherDetails(Integer id, Double environment_mgmt_funds, Double corporate_environment_funds,
			Double environment_mgmt_plan_funds, Integer total_capital_cost, Integer total_recurring_cost,
			String land_acquisition_status, Double acquired_land, Double land_to_be_acquired,
			Boolean is_environmental_cell_proposed, String env_organisational_structure,
			String details_of_responsibilities, String procedure_to_report, String env_cell_reason,
			String procedure_to_review, String is_compliance_report, String compliance_date_of_site,
			String compliance_final_observation, String compliance_reason) {
		this.id = id;
		this.environment_mgmt_funds = environment_mgmt_funds;
		this.corporate_environment_funds = corporate_environment_funds;
		this.environment_mgmt_plan_funds = environment_mgmt_plan_funds;
		this.total_capital_cost = total_capital_cost;
		this.total_recurring_cost = total_recurring_cost;
		this.land_acquisition_status = land_acquisition_status;
		this.acquired_land = acquired_land;
		this.land_to_be_acquired = land_to_be_acquired;
		this.is_environmental_cell_proposed = is_environmental_cell_proposed;
		this.env_organisational_structure = env_organisational_structure;
		this.details_of_responsibilities = details_of_responsibilities;
		this.procedure_to_report = procedure_to_report;
		this.env_cell_reason = env_cell_reason;
		this.procedure_to_review = procedure_to_review;
		this.is_compliance_report = is_compliance_report;
		this.compliance_date_of_site = compliance_date_of_site;
		this.compliance_final_observation = compliance_final_observation;
		this.compliance_reason = compliance_reason;
	}

}
