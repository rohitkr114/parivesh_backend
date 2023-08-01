package com.backend.model;

import java.util.Date;
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

import org.hibernate.annotations.Where;

import com.backend.audit.Auditable;
import com.backend.model.Crz.CrzLocationOfProject;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "mining_mineral_oil_proposal", schema = "master")
public class MiningMineralOilProposal extends Auditable<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fc_id", nullable = true)
	@JsonIgnore
	private ForestClearance forestClearance;
	
	@Column(nullable = true)
	private Date mineral_oil_validity_from;

	@Column(nullable = true)
	private Date mineral_oil_validity_to;

	@Column(nullable = true, length = 100)
	private String approving_authority_name;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "copy_approved_mineral_oil_plan_id", nullable = true)
	private DocumentDetails copy_approved_mineral_oil_plan;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "copy_map_outer_boundary_mineral_oil_id", nullable = true)
	private DocumentDetails copy_map_outer_boundary_mineral_oil;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "copy_detailed_land_use_plan_mineral_oil_id", nullable = true)
	private DocumentDetails copy_detailed_land_use_plan_mineral_oil;

	@Column(nullable = true)
	private Boolean is_mineral_reserves_exploration;

	@Column(nullable = true)
	private String mineral_oil_authority_designation;

	@Column(nullable = true)
	private Date mineral_oil_grant_date;

	@Column(nullable = true)
	private Date mineral_oil_extension_validity_from;

	@Column(nullable = true)
	private Date mineral_oil_extension_validity_to;

	@Column(nullable = true)
	private Boolean is_previously_drilled;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "fc_wlc_rofr_certification_id", nullable = true)
	private DocumentDetails fc_wlc_rofr_certification;

	@Column(nullable = true)
	private Double drill_cutting_excavation;

	@Column(nullable = true, length = 1000)
	private String excavation_other_information;

	@Column(nullable = true)
	private Integer mineral_oil_reservoir_total_life_year;
	
	@Column(nullable = true)
	private Integer mineral_oil_reservoir_total_life_month;

	@Column(nullable = true)
	private Integer mineral_oil_reservoir_approved_plan_year;
	
	@Column(nullable = true)
	private Integer mineral_oil_reservoir_approved_plan_month;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "copy_mineral_oil_reservoir_life_id", nullable = true)
	private DocumentDetails copy_mineral_oil_reservoir_life;

	@Column(nullable = true, length=1000)
	private String mineral_oil_reservoir_other_information;

	@Column(nullable = true, length=50)
	private String mineral_oil_extraction_phase;

	@Column(nullable = true, length=1000)
	private String extraction_mineral_oil_other_information;

	@Column(nullable = true)
	private Boolean is_type_of_blasting_adopted;

	@Column(nullable = true,length=500)
	private String blasting_type;

	@Column(nullable = true,length=1000)
	private String blast_vibration_mitigation;

	@Column(nullable = true,length=1000)
	private String blasting_adopted_other_information;

	@Column(nullable = true)
	private Boolean is_proposal_install_production;

	@Column(nullable = true)
	private Double capacity_same_mtpa_mmscmd;

	@Column(nullable = true,length=1000)
	private String production_facility_other_information;

	@Column(nullable = true,length=1000)
	private String total_drill_cutting_excavated;

	@Column(nullable = true,length=1000)
	private String drill_cuttings_other_information;

	@Column(nullable = true,length=1000)
	private String waste_estimated_volume;

	@Column(nullable = true,length=1000)
	private String waste_pit_other_information;

	@Column(nullable = true,length =1000)
	private String mode_of_transportation_seperator;

	@Column(nullable = true,length =1000)
	private String mode_of_transportation_loading_point;

	@Column(nullable = true,length =1000)
	private String mode_of_transportation_consumer;

	@Column(nullable = true,length =1000)
	private String transportation_extracted_other_information;

	@Column(nullable = true)
	private Double plantation_area_after_cessation;

	@Column(nullable = true)
	private Double public_use_after_cessation;

	@Column(nullable = true)
	private Double other_use_after_cessation;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "fc_development_plan_mineral_oil_extraction_id", nullable = true)
	private DocumentDetails fc_development_plan_mineral_oil_extraction;

	@Column(nullable = true)
	private Boolean is_proposal_involves_expansion;

	@Column(nullable = true)
	private String commencement_extraction_year;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "copy_mineral_oil_exploration_licence_id", nullable = true)
    private DocumentDetails copy_mineral_oil_exploration_licence;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "copy_mineral_oil_transportation_note_id", nullable = true)
    private DocumentDetails copy_mineral_oil_transportation_note;

    @Column(nullable = true,length = 50)
    private String status_of_approval_mineral_oil;

	@OneToMany(targetEntity = MiningMineralOilProductionDetail.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "mining_mineral_oil_proposal_id", referencedColumnName = "id")
	@Where(clause = "is_deleted ='false'")
	private List<MiningMineralOilProductionDetail> miningMineralOilProductionDetail;
	
	@OneToMany(targetEntity = MiningMineralOilEstimatedReserve.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "mining_mineral_oil_proposal_id", referencedColumnName = "id")
	@Where(clause = "is_deleted ='false'")
	private List<MiningMineralOilEstimatedReserve> miningMineralOilEstimatedReserve;
	
	@OneToMany(targetEntity = MiningMineralOilExtracted.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "mining_mineral_oil_proposal_id", referencedColumnName = "id")
	@Where(clause = "is_deleted ='false'")
	private List<MiningMineralOilExtracted> miningMineralOilExtracted;
	
	@OneToMany(targetEntity = MiningMineralOilReserves.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "mining_mineral_oil_proposal_id", referencedColumnName = "id")
	@Where(clause = "is_deleted ='false'")
	private List<MiningMineralOilReserves> miningMineralOilReserve;

	@Column(nullable = true)
	private Boolean is_active;

	@Column(nullable = true)
	private Boolean is_deleted;

	public MiningMineralOilProposal() {
		this.is_active = true;
		this.is_deleted = false;
	}
}
