package com.backend.model.EcForm10NoIncreaseInPL;

import com.backend.model.DocumentDetails;
import com.backend.model.EcForm9TransferOfEC.EcForm9GovernmentOrder;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * It consists of fields from point 1.2.3.1.11
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "ec_form10_emission_generation", schema = "master")
public class EcForm10EmissionGeneration {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	/*
	 * @OneToOne(fetch = FetchType.LAZY)
	 * 
	 * @JoinColumn(name = "ec_form10_project_details_id", nullable = true)
	 * 
	 * @JsonIgnore private EcForm10ProjectDetails ecForm10ProjectDetails;
	 */
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ec_form10_basic_information_id", nullable = true)
	@JsonIgnore
	private EcForm10BasicInformation ecForm10BasicInformation;

	@OneToMany(targetEntity = EcForm10DetailsOfEmissionGenerationQuantity.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "ec_form10_details_of_emission_generation_quantity_id", referencedColumnName = "id")
	private Set<EcForm10DetailsOfEmissionGenerationQuantity> from_stack_options = new HashSet<>();

	@OneToMany(targetEntity = EcForm10DetailsOfEmissionGenerationFromFugitiveSources.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "ec_form10_details_of_emission_generation_from_fugitive_sources_id", referencedColumnName = "id")
	private Set<EcForm10DetailsOfEmissionGenerationFromFugitiveSources> from_fugitive_options = new HashSet<>();

	@OneToMany(targetEntity = EcForm10DetailsOfEmissionGenerationFromOtherSources.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "ec_form10_details_of_emission_generation_from_other_sources_id", referencedColumnName = "id")
	private Set<EcForm10DetailsOfEmissionGenerationFromOtherSources> from_other_options = new HashSet<>();

	@OneToMany(targetEntity = EcForm10DetailsOfEmissionGenerationQuality.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "ec_form10_details_of_emission_generation_quality_id", referencedColumnName = "id")
	private Set<EcForm10DetailsOfEmissionGenerationQuality> from_quality_options = new HashSet<>();

	@OneToMany(targetEntity = EcForm10DetailsOfEmissionGenerationTotalLoad.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "ec_form10_details_of_emission_generation_total_load_id", referencedColumnName = "id")
	private Set<EcForm10DetailsOfEmissionGenerationTotalLoad> from_emission_options = new HashSet<>();

	@Column(name = "proposal_for_switching_over_cleaner_fuel", nullable = true)
	private Boolean switching;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "report_on_proposal_for_switching", nullable = true)
	private DocumentDetails switching_upload;

	@Column(name = "proposal_for_the_upgradation", nullable = true)
	private Boolean is_apcm;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "report_on_proposal_for_upgradation", nullable = true)
	private DocumentDetails apcm_upload;

	@Column(name = "proposal_for_the_installation", nullable = true)
	private Boolean is_installation;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "report_on_proposal_for_installation", nullable = true)
	private DocumentDetails report;
}
