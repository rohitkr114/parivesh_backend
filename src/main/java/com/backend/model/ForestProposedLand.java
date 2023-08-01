package com.backend.model;

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
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;

@Data
@Entity
@Table(name = "fc_proposed_land", schema = "master")
@JsonIgnoreProperties({ "created_by", "created_on", "updated_by", "updated_on" })
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class ForestProposedLand extends Auditable<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(nullable = false)
	private Double total_proposed_diversion_area;

	@Column(nullable = true)
	private Double total_non_forestland_area_required;

	@Column(nullable = false)
	private Integer total_proposed_diversion_period;

	@Column(nullable = true)
	private String legal_status_of_forest_land;
	@Column(nullable = true, length = 500)
	private String legal_status_of_forest_land_other;
	@Column(nullable = false)
	private double total_forest_land;

	@Column(nullable = false)
	private double total_non_forest_land;

	@Column(nullable = true)
	private Double total_area_of_kmls;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fc_id", nullable = false)
//	@JsonBackReference(value = "fc_reference")
	@JsonIgnore
	private ForestClearance forestClearance;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "geo_referenced_map_id", nullable = true)
	private DocumentDetails geo_referenced_map;

	@OneToMany(targetEntity = ForestClearanceLegalStatus.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "fc_proposed_land_id", referencedColumnName = "id")
	@Where(clause = "is_deleted ='false'")
	private Set<ForestClearanceLegalStatus> forestClearanceLegalStatus = new HashSet<>();

	@Column(nullable = true, length = 1000)
	private String remarks;

	public ForestProposedLand() {
	}

	public String getLegal_status_of_forest_land_other() {
		return legal_status_of_forest_land_other;
	}

	public void setLegal_status_of_forest_land_other(String legal_status_of_forest_land_other) {
		this.legal_status_of_forest_land_other = legal_status_of_forest_land_other;
	}

	public Double getTotal_area_of_kmls() {
		return total_area_of_kmls;
	}

	public void setTotal_area_of_kmls(Double total_area_of_kmls) {
		this.total_area_of_kmls = total_area_of_kmls;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLegal_status_of_forest_land() {
		return legal_status_of_forest_land;
	}

	public void setLegal_status_of_forest_land(String legal_status_of_forest_land) {
		this.legal_status_of_forest_land = legal_status_of_forest_land;
	}

	public Double getTotal_proposed_diversion_area() {
		return total_proposed_diversion_area;
	}

	public void setTotal_proposed_diversion_area(double total_proposed_diversion_area) {
		this.total_proposed_diversion_area = total_proposed_diversion_area;
	}

	public Integer getTotal_proposed_diversion_period() {
		return total_proposed_diversion_period;
	}

	public void setTotal_proposed_diversion_period(Integer total_proposed_diversion_period) {
		this.total_proposed_diversion_period = total_proposed_diversion_period;
	}

	public double getTotal_forest_land() {
		return total_forest_land;
	}

	public void setTotal_forest_land(double total_forest_land) {
		this.total_forest_land = total_forest_land;
	}

	public double getTotal_non_forest_land() {
		return total_non_forest_land;
	}

	public void setTotal_non_forest_land(double total_non_forest_land) {
		this.total_non_forest_land = total_non_forest_land;
	}

	public ForestClearance getForestClearance() {
		return forestClearance;
	}

	public void setForestClearance(ForestClearance forestClearance) {
		this.forestClearance = forestClearance;
	}

	public DocumentDetails getGeo_referenced_map() {
		return geo_referenced_map;
	}

	public void setGeo_referenced_map(DocumentDetails geo_referenced_map) {
		this.geo_referenced_map = geo_referenced_map;
	}

}
