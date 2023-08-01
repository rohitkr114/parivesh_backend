package com.backend.model.EnvironmentClearance;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name="ec_cropping_pattern",schema = "master")
public class EcCroppingPattern {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer Id;

	@ManyToOne
	@JoinColumn(name = "ec_part_b_id")
	@JsonIgnore
	private EcPartB ecPartB;
	
	
	private String crop;
	
	private Double existing_area;
	
	private Double existing_productivity;
	
	private Double proposed_area;
	
	private Double proposed_productivity;
	
	private Double total_production;
	
	@Column(name = "is_deleted", nullable = true, length = 100)
	private boolean is_deleted;
	
	EcCroppingPattern(){
		
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

	public String getCrop() {
		return crop;
	}

	public void setCrop(String crop) {
		this.crop = crop;
	}

	public Double getExisting_area() {
		return existing_area;
	}

	public void setExisting_area(Double existing_area) {
		this.existing_area = existing_area;
	}

	public Double getExisting_productivity() {
		return existing_productivity;
	}

	public void setExisting_productivity(Double existing_productivity) {
		this.existing_productivity = existing_productivity;
	}

	public Double getProposed_area() {
		return proposed_area;
	}

	public void setProposed_area(Double proposed_area) {
		this.proposed_area = proposed_area;
	}

	public Double getProposed_productivity() {
		return proposed_productivity;
	}

	public void setProposed_productivity(Double proposed_productivity) {
		this.proposed_productivity = proposed_productivity;
	}

	public Double getTotal_production() {
		return total_production;
	}

	public void setTotal_production(Double total_production) {
		this.total_production = total_production;
	}

	public boolean isIs_deleted() {
		return is_deleted;
	}

	public void setIs_deleted(boolean is_deleted) {
		this.is_deleted = is_deleted;
	}
	
	
	
}
