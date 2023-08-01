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
public class EcSubmergedArea {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer Id;
	
	
	@ManyToOne
	@JoinColumn(name = "ec_part_b_id")
	@JsonIgnore
	private EcPartB ecPartB;
	
	
	private String area_submerged;
	
	private Double area;

	@Column(name = "is_deleted", nullable = true, length = 100)
	private boolean is_deleted;
	
	
	EcSubmergedArea(){
		this.is_deleted = false;
	}
	
	
	public boolean isIs_deleted() {
		return is_deleted;
	}


	public void setIs_deleted(boolean is_deleted) {
		this.is_deleted = is_deleted;
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


	public String getArea_submerged() {
		return area_submerged;
	}

	public void setArea_submerged(String area_submerged) {
		this.area_submerged = area_submerged;
	}

	public Double getArea() {
		return area;
	}

	public void setArea(Double area) {
		this.area = area;
	}
	
	

}
