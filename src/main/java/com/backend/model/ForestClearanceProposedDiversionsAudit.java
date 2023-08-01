package com.backend.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import com.backend.audit.Auditable;
import com.backend.model.EnvironmentClearance.MiningMineralsMined;
import com.backend.model.WildLifeClearance.WLOtherDetails;
import com.backend.model.WildLifeClearance.WLProposedLand;
import com.backend.model.WildLifeClearance.WLProposedLandAudit;
import com.backend.model.WildLifeClearance.WildLifeClearance;
import com.backend.model.WildLifeClearance.WildLifeClearanceAudit;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
@Data
@Entity
@Table(name = "forest_clearance_proposed_diversions_audit", schema = "master")
public class ForestClearanceProposedDiversionsAudit extends Auditable<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "wl_Id", nullable = true)
	@JsonIgnore
	private WildLifeClearanceAudit wildLifeClearanceaudit;

		
	@OneToMany(mappedBy="forestClearanceProposedDiversionsAudit",cascade=CascadeType.ALL) 
	private List<FcProposedDiversionsDetailsAudit> FcProposedDiversionsDetailsAudit;
		

	@Column(nullable = true, length = 50)
	private String division;

	@Column(nullable = true, length = 50)
	private Integer total_patches;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "kml_id", nullable = true)
	private DocumentDetails kml;

	@Column(nullable = true, length = 1000)
	private String remarks;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "diversion_map_copy_id", nullable = true)
	private DocumentDetails diversion_map_copy;

	/*
	 * FOR Mapping with WL Proposed Land
	 */

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "wl_proposed_land_Id", nullable = true)
	@JsonIgnore
	private WLProposedLandAudit wlProposedLandAudit;

	@Column(name = "total_forest_land_for_division", nullable = true)
	private Double total_forest_land_for_division;

	@Column(name = "total_non_forest_land_for_division", nullable = true)
	private Double total_non_forest_land_for_division;

	@Column(name = "protected_area", nullable = true)
	private Double protected_area;

	@Column(name = "core_zone_area", nullable = true)
	private Double core_zone_area;

	@Column(name = "buffer_zone_area", nullable = true)
	private Double buffer_zone_area;

	@Column(nullable = true)
	private Integer division_id;

	@Column(nullable = false)
	private boolean isDelete;

	
}
