package com.backend.model.WildLifeClearance;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.backend.audit.Auditable;
import com.backend.model.DocumentDetails;
import com.backend.model.FcProposedDiversionsDetailsAudit;
import com.backend.model.ForestClearanceProposedDiversionsAudit;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "wl_division_land_detail_audit", schema = "master")
public class WLDivisionLandDetailAudit extends Auditable<Integer>{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(nullable = true)
	private String district;
	
	@Column(nullable = true)
	private String division;
	
	@Column(length=500)
	private String protected_area;
	
	@Column(nullable = true)
	private Double distance;
	
	@Column(nullable = true)
	private Double forest_protected_area;
	
	@Column(nullable = true)
	private Double non_forest_protected_area;
	
	@Column(nullable = true)
	private Double total_protected_area;
	
	@Column(nullable = true)
	private Double forest_esz_area;
	
	@Column(nullable = true)
	private Double non_forest_esz_area;
	
	@Column(nullable = true)
	private Double total_esz_area;
	
	@Column(nullable = true)
	private Double forest_protected_outside_area;
	
	@Column(nullable = true)
	private Double non_forest_protected_outside_area;
	
	@Column(nullable = true)
	private Double total_protected_outside_area;
	
	@Column(nullable = true)
	private Boolean is_active;
	
	@Column(nullable = true)
	private Boolean is_delete;
	//change on 29112022
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "wl_clearance_id", nullable = true)
//	@JsonIgnore
//	private WildLifeClearance wlclearance;
	
	//change on 29112022
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "wl_clearance_id", nullable = true)
	@JsonIgnore
	private WildLifeClearanceAudit wildLifeClearanceaudit;
	
	
	//added on 26112022
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "wl_proposed_land_Id", nullable = true)
	@JsonIgnore
	private WLProposedLandAudit wlProposedLandAudit;
	
	//end 26112022
	//added 03122022
		@Column(nullable = true)
		private Double area_proposed_inside_pa;
		
		@Column(nullable = true)
		private Double area_proposed_outside_pa;

	
		
}
