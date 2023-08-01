package com.backend.model.EnvironmentClearance;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "ec_current_land_use", schema = "master")
public class CurrentLandUse {
    
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "ec_part_b_id")
	@JsonIgnore
	private EcPartB ecPartB;
	
	
	
	private String land_use_name;
	private Double land_use_area;
	
	@Column(length = 300)
	private String land_use_remarks;
	private boolean is_deleted;
	public CurrentLandUse(){
		this.is_deleted = false;
		
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getLand_use_name() {
		return land_use_name;
	}

	public void setLand_use_name(String land_use_name) {
		this.land_use_name = land_use_name;
	}

	public Double getLand_use_area() {
		return land_use_area;
	}

	public void setLand_use_area(Double land_use_area) {
		this.land_use_area = land_use_area;
	}

	public String getLand_use_remarks() {
		return land_use_remarks;
	}

	public void setLand_use_remarks(String land_use_remarks) {
		this.land_use_remarks = land_use_remarks;
	}

	public boolean isIs_deleted() {
		return is_deleted;
	}

	public void setIs_deleted(boolean is_deleted) {
		this.is_deleted = is_deleted;
	}

	public EcPartB getEcPartB() {
		return ecPartB;
	}

	public void setEcPartB(EcPartB ecPartB) {
		this.ecPartB = ecPartB;
	}
    
	
	
	
	
	
}
