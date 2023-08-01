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
@Table(name="ec_irrigation_project_capacity_village",schema = "master")
public class EcIrrigationProjectCapacityVillage {
       
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer Id;

	@ManyToOne
	@JoinColumn(name = "ec_part_b_id")
	@JsonIgnore
	private EcPartB ecPartB;
	
	private Integer state;
	
	private Integer district;
	
	private Integer village;
	
	private Double area_benefited;
	
	@Column(name = "is_deleted", nullable = true, length = 100)
	private boolean is_deleted;
   
	
	EcIrrigationProjectCapacityVillage(){
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
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getDistrict() {
		return district;
	}

	public void setDistrict(Integer district) {
		this.district = district;
	}

	public Integer getVillage() {
		return village;
	}

	public void setVillage(Integer village) {
		this.village = village;
	}

	public Double getArea_benefited() {
		return area_benefited;
	}

	public void setArea_benefited(Double area_benefited) {
		this.area_benefited = area_benefited;
	}

	public boolean isIs_deleted() {
		return is_deleted;
	}

	public void setIs_deleted(boolean is_deleted) {
		this.is_deleted = is_deleted;
	}
	
	
	
}
