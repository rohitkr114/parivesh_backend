package com.backend.model;

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
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;

@Data
@Entity
@Table(name = "fc_aforestation_detail", schema = "master")
@JsonIgnoreProperties({ "created_by", "created_on", "updated_by", "updated_on" })
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class FcAforestationDetails extends Auditable<Integer> {



	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(nullable = true)
	private String comp_afforestation_type;

	@Column(nullable=true)
	private Boolean is_applicable_compensatory_afforestation;
	
	@Column(nullable=true)
	private Boolean is_non_forest_land;
	
	@Column(nullable = true)
	private Integer total_patches;

	@Column(nullable = true)
	private Integer total_districts_involved_in_ca;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "ca_map_copy_id", nullable = true)
	private DocumentDetails ca_map_copy;

	@Column(nullable = true)
	private boolean is_ua_land_smaller_from_proposed;
	
	@Column(nullable = true)
	private Boolean is_nfl_free;
	
	@Column(nullable = true)
	private Boolean present_owner_nfl;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "copy_of_mou_id", nullable = true)
	private DocumentDetails copy_of_mou;
	
	@Column(nullable = true,length=1000)
	private String ua_land_smaller_reason;

	@Column(nullable = true)
	private double ua_land_area;

	@Column(nullable = true)
	private Double trees_to_be_cut;

	@Column(nullable = true)
	private Double trees_to_be_planted;

	@Column(nullable = true)
	private Double trees_compensation_ratio;
	@Column(nullable = true)
	private Double ca_land;
	@Column(nullable = true)
	private String trees_plantation_land_type;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "identified_land_for_compensatory_afforestaion", nullable = true)
	private DocumentDetails identified_land_for_compensatory_afforestaion;

	@Column(nullable = true, length = 1000)
	private String remarks;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fc_id", nullable = false)
//	@JsonBackReference(value = "fc_reference")
	@JsonIgnore
	private ForestClearance forestClearance;
	
	public DocumentDetails getIdentified_land_for_compensatory_afforestaion() {
		return identified_land_for_compensatory_afforestaion;
	}

	public void setIdentified_land_for_compensatory_afforestaion(DocumentDetails identified_land_for_compensatory_afforestaion) {
		this.identified_land_for_compensatory_afforestaion = identified_land_for_compensatory_afforestaion;
	}
	public FcAforestationDetails() {

	}

	public boolean isIs_ua_land_smaller_from_proposed() {
		return is_ua_land_smaller_from_proposed;
	}

	public void setIs_ua_land_smaller_from_proposed(boolean is_ua_land_smaller_from_proposed) {
		this.is_ua_land_smaller_from_proposed = is_ua_land_smaller_from_proposed;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public Double getCa_land() {
		return ca_land;
	}

	public void setCa_land(Double ca_land) {
		this.ca_land = ca_land;
	}
	public String getComp_afforestation_type() {
		return comp_afforestation_type;
	}

	public void setComp_afforestation_type(String comp_afforestation_type) {
		this.comp_afforestation_type = comp_afforestation_type;
	}

	public Integer getTotal_patches() {
		return total_patches;
	}

	public void setTotal_patches(Integer total_patches) {
		this.total_patches = total_patches;
	}

	public Integer getTotal_districts_involved_in_ca() {
		return total_districts_involved_in_ca;
	}

	public void setTotal_districts_involved_in_ca(Integer total_districts_involved_in_ca) {
		this.total_districts_involved_in_ca = total_districts_involved_in_ca;
	}

	public String getUa_land_smaller_reason() {
		return ua_land_smaller_reason;
	}

	public void setUa_land_smaller_reason(String ua_land_smaller_reason) {
		this.ua_land_smaller_reason = ua_land_smaller_reason;
	}

	public double getUa_land_area() {
		return ua_land_area;
	}

	public void setUa_land_area(double ua_land_area) {
		this.ua_land_area = ua_land_area;
	}

	public Double getTrees_compensation_ratio() {
		return trees_compensation_ratio;
	}

	public void setTrees_compensation_ratio(Double trees_compensation_ratio) {
		this.trees_compensation_ratio = trees_compensation_ratio;
	}

	public String getTrees_plantation_land_type() {
		return trees_plantation_land_type;
	}

	public void setTrees_plantation_land_type(String trees_plantation_land_type) {
		this.trees_plantation_land_type = trees_plantation_land_type;
	}

	public ForestClearance getForestClearance() {
		return forestClearance;
	}

	public void setForestClearance(ForestClearance forestClearance) {
		this.forestClearance = forestClearance;
	}

	public DocumentDetails getCa_map_copy() {
		return ca_map_copy;
	}

	public void setCa_map_copy(DocumentDetails ca_map_copy) {
		this.ca_map_copy = ca_map_copy;
	}

	public boolean isIs_applicable_compensatory_afforestation() {
		return is_applicable_compensatory_afforestation;
	}

	public void setIs_applicable_compensatory_afforestation(boolean is_applicable_compensatory_afforestation) {
		this.is_applicable_compensatory_afforestation = is_applicable_compensatory_afforestation;
	}

	public boolean isIs_non_forest_land() {
		return is_non_forest_land;
	}

	public void setIs_non_forest_land(boolean is_non_forest_land) {
		this.is_non_forest_land = is_non_forest_land;
	}

	
	
	
}
