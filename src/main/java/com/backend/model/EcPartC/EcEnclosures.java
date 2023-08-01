package com.backend.model.EcPartC;

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

import com.backend.audit.Auditable;
import com.backend.model.DocumentDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "ec_partc_enclosures", schema = "master")
public class EcEnclosures extends Auditable<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ec_id", nullable = false)
	@JsonIgnore
	private EcPartC ecPartC;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "eia_final_copy_id", nullable = true)
	private DocumentDetails eia_final_copy;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "feasibility_summary_report_id", nullable = true)
	private DocumentDetails feasibility_summary_report;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "approved_mining_plan_copy_id", nullable = true)
	private DocumentDetails approved_mining_plan_copy;

	@Column(name = "approved_mining_plan_url", nullable = true)
	private String approved_mining_plan_url;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "district_survey_report_id", nullable = true)
	private DocumentDetails district_survey_report;

	@Column(name = "district_survey_report_url", nullable = true)
	private String district_survey_report_url;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "replenishment_study_report_id", nullable = true)
	private DocumentDetails replenishment_study_report;

	@Column(name = "replenishment_study_report_url", nullable = true)
	private String replenishment_study_report_url;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "final_layout_copy_id", nullable = true)
	private DocumentDetails final_layout_copy;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "additional_upload_copy_id", nullable = true)
	private DocumentDetails additional_upload_copy;

	public EcEnclosures(Integer id, String approved_mining_plan_url, String district_survey_report_url,
			String replenishment_study_report_url) {
		this.id = id;
		this.approved_mining_plan_url = approved_mining_plan_url;
		this.district_survey_report_url = district_survey_report_url;
		this.replenishment_study_report_url = replenishment_study_report_url;
	}

}
