package com.backend.model.WildLifeClearance;

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
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

//@Data
@Entity
@Table(name = "wl_division_land_detail", schema = "master")
public class WLDivisionLandDetail extends Auditable<Integer>{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	// addded on 15-03-2023
	@Column(nullable = true)
	private String division_name;

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
	private WildLifeClearance wlclearance;
	
	
	//added on 26112022
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "wl_proposed_land_Id", nullable = true)
	@JsonIgnore
	private WLProposedLand wlProposedLand;
	
	//end 26112022
	//added 03122022
		@Column(nullable = true)
		private Double area_proposed_inside_pa;
		
		@Column(nullable = true)
		private Double area_proposed_outside_pa;

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getDistrict() {
			return district;
		}

		public void setDistrict(String district) {
			this.district = district;
		}

		public String getDivision() {
			return division;
		}

		public void setDivision(String division) {
			this.division = division;
		}

		public String getProtected_area() {
			return protected_area;
		}

		public void setProtected_area(String protected_area) {
			this.protected_area = protected_area;
		}

		public Double getDistance() {
			return distance;
		}

		public void setDistance(Double distance) {
			this.distance = distance;
		}

		public Double getForest_protected_area() {
			return forest_protected_area;
		}

		public void setForest_protected_area(Double forest_protected_area) {
			this.forest_protected_area = forest_protected_area;
		}

		public Double getNon_forest_protected_area() {
			return non_forest_protected_area;
		}

		public void setNon_forest_protected_area(Double non_forest_protected_area) {
			this.non_forest_protected_area = non_forest_protected_area;
		}

		public Double getTotal_protected_area() {
			return total_protected_area;
		}

		public void setTotal_protected_area(Double total_protected_area) {
			this.total_protected_area = total_protected_area;
		}

		public Double getForest_esz_area() {
			return forest_esz_area;
		}

		public void setForest_esz_area(Double forest_esz_area) {
			this.forest_esz_area = forest_esz_area;
		}

		public Double getNon_forest_esz_area() {
			return non_forest_esz_area;
		}

		public void setNon_forest_esz_area(Double non_forest_esz_area) {
			this.non_forest_esz_area = non_forest_esz_area;
		}

		public Double getTotal_esz_area() {
			return total_esz_area;
		}

		public void setTotal_esz_area(Double total_esz_area) {
			this.total_esz_area = total_esz_area;
		}

		public Double getForest_protected_outside_area() {
			return forest_protected_outside_area;
		}

		public void setForest_protected_outside_area(Double forest_protected_outside_area) {
			this.forest_protected_outside_area = forest_protected_outside_area;
		}

		public Double getNon_forest_protected_outside_area() {
			return non_forest_protected_outside_area;
		}

		public void setNon_forest_protected_outside_area(Double non_forest_protected_outside_area) {
			this.non_forest_protected_outside_area = non_forest_protected_outside_area;
		}

		public Double getTotal_protected_outside_area() {
			return total_protected_outside_area;
		}

		public void setTotal_protected_outside_area(Double total_protected_outside_area) {
			this.total_protected_outside_area = total_protected_outside_area;
		}

		public Boolean getIs_active() {
			return is_active;
		}

		public void setIs_active(Boolean is_active) {
			this.is_active = is_active;
		}

		public Boolean getIs_delete() {
			return is_delete;
		}

		public void setIs_delete(Boolean is_delete) {
			this.is_delete = is_delete;
		}

		public WildLifeClearance getWlclearance() {
			return wlclearance;
		}

		public void setWlclearance(WildLifeClearance wlclearance) {
			this.wlclearance = wlclearance;
		}

		public WLProposedLand getWlProposedLand() {
			return wlProposedLand;
		}

		public void setWlProposedLand(WLProposedLand wlProposedLand) {
			this.wlProposedLand = wlProposedLand;
		}

		public Double getArea_proposed_inside_pa() {
			return area_proposed_inside_pa;
		}

		public void setArea_proposed_inside_pa(Double area_proposed_inside_pa) {
			this.area_proposed_inside_pa = area_proposed_inside_pa;
		}

		public Double getArea_proposed_outside_pa() {
			return area_proposed_outside_pa;
		}

		public void setArea_proposed_outside_pa(Double area_proposed_outside_pa) {
			this.area_proposed_outside_pa = area_proposed_outside_pa;
		}
		
		public String getDivision_name() {
			return division_name;
		}

		public void setDivision_name(String division_name) {
			this.division_name = division_name;
		}

		public WLDivisionLandDetail() {
			this.is_active = true;
	        this.is_delete = false;
		}

		
		//added 03122022
	
	
}
