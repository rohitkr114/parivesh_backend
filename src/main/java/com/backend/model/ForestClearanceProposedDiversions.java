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
import com.backend.model.WildLifeClearance.WildLifeClearance;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "forest_clearance_proposed_diversions", schema = "master")
public class ForestClearanceProposedDiversions extends Auditable<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fc_Id", nullable = true)
	@JsonIgnore
	private ForestClearance forestClearance;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "wl_Id", nullable = true)
	@JsonIgnore
	private WildLifeClearance wildLifeClearance;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "fc_diversion_detail_id", nullable = true)
	private List<FcProposedDiversionsDetails> fcProposedDiversionsDetails = new ArrayList<>();

	/*
	 * @OneToMany(mappedBy = "forestClearanceProposedDiversions", cascade =
	 * CascadeType.ALL)
	 * 
	 * @Where(clause = "is_deleted='false'")
	 * List<ForestClearanceDivisionPatchDetails> ForestClearanceDivisionPatchDetails
	 * = new ArrayList<>();
	 */

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "fc_proposed_diversion_id", nullable = true)
	@Where(clause = "is_deleted='false'")
	private Set<ForestClearanceDivisionPatchDetails> ForestClearanceDivisionPatchDetails;

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
	private WLProposedLand wlProposedLand;

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

	public Integer getId() {
		return id;
	}

	public Double getTotal_forest_land_for_division() {
		return total_forest_land_for_division;
	}

	public void setTotal_forest_land_for_division(Double total_forest_land_for_division) {
		this.total_forest_land_for_division = total_forest_land_for_division;
	}

	public Double getTotal_non_forest_land_for_division() {
		return total_non_forest_land_for_division;
	}

	public void setTotal_non_forest_land_for_division(Double total_non_forest_land_for_division) {
		this.total_non_forest_land_for_division = total_non_forest_land_for_division;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ForestClearance getForestClearance() {
		return forestClearance;
	}

	public void setForestClearance(ForestClearance forestClearance) {
		this.forestClearance = forestClearance;
	}

	public List<FcProposedDiversionsDetails> getFcProposedDiversionsDetails() {
		return fcProposedDiversionsDetails;
	}

	public void setFcProposedDiversionsDetails(List<FcProposedDiversionsDetails> fcProposedDiversionsDetails) {
		this.fcProposedDiversionsDetails = fcProposedDiversionsDetails;
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public Integer getTotal_patches() {
		return total_patches;
	}

	public void setTotal_patches(Integer total_patches) {
		this.total_patches = total_patches;
	}

	public DocumentDetails getKml() {
		return kml;
	}

	public void setKml(DocumentDetails kml) {
		this.kml = kml;
	}

	public DocumentDetails getDiversion_map_copy() {
		return diversion_map_copy;
	}

	public void setDiversion_map_copy(DocumentDetails diversion_map_copy) {
		this.diversion_map_copy = diversion_map_copy;
	}

	public Double getProtected_area() {
		return protected_area;
	}

	public void setProtected_area(Double protected_area) {
		this.protected_area = protected_area;
	}

	public Double getCore_zone_area() {
		return core_zone_area;
	}

	public void setCore_zone_area(Double core_zone_area) {
		this.core_zone_area = core_zone_area;
	}

	public Double getBuffer_zone_area() {
		return buffer_zone_area;
	}

	public void setBuffer_zone_area(Double buffer_zone_area) {
		this.buffer_zone_area = buffer_zone_area;
	}

	public boolean isDelete() {
		return isDelete;
	}

	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}

	public WildLifeClearance getWildLifeClearance() {
		return wildLifeClearance;
	}

	public void setWildLifeClearance(WildLifeClearance wildLifeClearance) {
		this.wildLifeClearance = wildLifeClearance;
	}

	public WLProposedLand getWlProposedLand() {
		return wlProposedLand;
	}

	public void setWlProposedLand(WLProposedLand wlProposedLand) {
		this.wlProposedLand = wlProposedLand;
	}

	ForestClearanceProposedDiversions() {
		this.isDelete = false;

	}

	public Integer getDivision_id() {
		return division_id;
	}

	public void setDivision_id(Integer division_id) {
		this.division_id = division_id;
	}

	public Set<ForestClearanceDivisionPatchDetails> getForestClearanceDivisionPatchDetails() {
		return ForestClearanceDivisionPatchDetails;
	}

	public void setForestClearanceDivisionPatchDetails(
			Set<ForestClearanceDivisionPatchDetails> forestClearanceDivisionPatchDetails) {
		ForestClearanceDivisionPatchDetails = forestClearanceDivisionPatchDetails;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}
