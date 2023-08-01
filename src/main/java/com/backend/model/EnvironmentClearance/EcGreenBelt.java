package com.backend.model.EnvironmentClearance;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.backend.audit.Auditable;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "ec_greenbelt_details", schema = "master")
public class EcGreenBelt extends Auditable<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer Id;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ec_partb_id", nullable = true)
	@JsonIgnore
	private EcPartB ecPartB;

	@Column(name = "area_green_belt_existing", nullable = true)
	private Double area_green_belt_existing;

	@Column(name = "width_green_belt_existing", nullable = true)
	private Double width_green_belt_existing;

	@Column(name = "percentage_total_area_covered_existing", nullable = true)
	private Double percentage_total_area_covered_existing;

	@Column(name = "details_species_proposed_plantation_existing", length = 200, nullable = true)
	private String details_species_proposed_plantation_existing;

	@Column(name = "Number_tree_saplings_planted_existing", nullable = true)
	private Integer Number_tree_saplings_planted_existing;

	@Column(name = "funds_allocated_plantation_existing", nullable = true)
	private Double funds_allocated_plantation_existing;

	@Column(name = "area_green_belt__proposed", nullable = true)
	private Double area_green_belt__proposed;

	@Column(name = "width_green_belt_proposed", nullable = true)
	private Double width_green_belt_proposed;

	@Column(name = "percentage_total_area_covered_proposed", nullable = true)
	private Double percentage_total_area_covered_proposed;

	@Column(name = "details_species_proposed_plantation_proposed", length = 200, nullable = true)
	private String details_species_proposed_plantation_proposed;

	@Column(name = "Number_tree_saplings_planted_proposed", nullable = true)
	private Integer Number_tree_saplings_planted_proposed;

	@Column(name = "funds_allocated_plantation_proposed", nullable = true)
	private Double funds_allocated_plantation_proposed;

	@Column(name = "newproposal_expansion_flag", length = 20, nullable = true)
	private String newproposal_expansion_flag;

	@Column(name = "is_active")
	private boolean is_active;

	@Column(name = "is_deleted")
	private boolean is_deleted;

	public boolean isIs_active() {
		return is_active;
	}

	public void setIs_active(boolean is_active) {
		this.is_active = is_active;
	}

	public boolean isIs_deleted() {
		return is_deleted;
	}

	public void setIs_deleted(boolean is_deleted) {
		this.is_deleted = is_deleted;
	}

	public EcGreenBelt() {
		this.is_active = true;
		this.is_deleted = false;
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public EcPartB getEcPartB() {
		return ecPartB;
	}

	public void setEcPartB(EcPartB ecPartB) {
		this.ecPartB = ecPartB;
	}

	public Double getArea_green_belt_existing() {
		return area_green_belt_existing;
	}

	public void setArea_green_belt_existing(Double area_green_belt_existing) {
		this.area_green_belt_existing = area_green_belt_existing;
	}

	public Double getWidth_green_belt_existing() {
		return width_green_belt_existing;
	}

	public void setWidth_green_belt_existing(Double width_green_belt_existing) {
		this.width_green_belt_existing = width_green_belt_existing;
	}

	public Double getPercentage_total_area_covered_existing() {
		return percentage_total_area_covered_existing;
	}

	public void setPercentage_total_area_covered_existing(Double percentage_total_area_covered_existing) {
		this.percentage_total_area_covered_existing = percentage_total_area_covered_existing;
	}

	public String getDetails_species_proposed_plantation_existing() {
		return details_species_proposed_plantation_existing;
	}

	public void setDetails_species_proposed_plantation_existing(String details_species_proposed_plantation_existing) {
		this.details_species_proposed_plantation_existing = details_species_proposed_plantation_existing;
	}

	public Integer getNumber_tree_saplings_planted_existing() {
		return Number_tree_saplings_planted_existing;
	}

	public void setNumber_tree_saplings_planted_existing(Integer number_tree_saplings_planted_existing) {
		Number_tree_saplings_planted_existing = number_tree_saplings_planted_existing;
	}

	public Double getFunds_allocated_plantation_existing() {
		return funds_allocated_plantation_existing;
	}

	public void setFunds_allocated_plantation_existing(Double funds_allocated_plantation_existing) {
		this.funds_allocated_plantation_existing = funds_allocated_plantation_existing;
	}

	public Double getArea_green_belt__proposed() {
		return area_green_belt__proposed;
	}

	public void setArea_green_belt__proposed(Double area_green_belt__proposed) {
		this.area_green_belt__proposed = area_green_belt__proposed;
	}

	public Double getWidth_green_belt_proposed() {
		return width_green_belt_proposed;
	}

	public void setWidth_green_belt_proposed(Double width_green_belt_proposed) {
		this.width_green_belt_proposed = width_green_belt_proposed;
	}

	public Double getPercentage_total_area_covered_proposed() {
		return percentage_total_area_covered_proposed;
	}

	public void setPercentage_total_area_covered_proposed(Double percentage_total_area_covered_proposed) {
		this.percentage_total_area_covered_proposed = percentage_total_area_covered_proposed;
	}

	public String getDetails_species_proposed_plantation_proposed() {
		return details_species_proposed_plantation_proposed;
	}

	public void setDetails_species_proposed_plantation_proposed(String details_species_proposed_plantation_proposed) {
		this.details_species_proposed_plantation_proposed = details_species_proposed_plantation_proposed;
	}

	public Integer getNumber_tree_saplings_planted_proposed() {
		return Number_tree_saplings_planted_proposed;
	}

	public void setNumber_tree_saplings_planted_proposed(Integer number_tree_saplings_planted_proposed) {
		Number_tree_saplings_planted_proposed = number_tree_saplings_planted_proposed;
	}

	public Double getFunds_allocated_plantation_proposed() {
		return funds_allocated_plantation_proposed;
	}

	public void setFunds_allocated_plantation_proposed(Double funds_allocated_plantation_proposed) {
		this.funds_allocated_plantation_proposed = funds_allocated_plantation_proposed;
	}

	public String getNewproposal_expansion_flag() {
		return newproposal_expansion_flag;
	}

	public void setNewproposal_expansion_flag(String newproposal_expansion_flag) {
		this.newproposal_expansion_flag = newproposal_expansion_flag;
	}

}
