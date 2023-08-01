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
@Table(name = "ec_proposed_land_use_expansion", schema = "master")
public class ProposedLandUseExpansion {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "ec_part_b_id")
	@JsonIgnore
	private EcPartB ecPartB;
	
	 private String description;
	
	 private Double existing_land_req;
	 
	 private Double proposed_land_req;
	 
	 private Double total_land_req;
	 
	 @Column(length = 300)
	 private String remarks;
	 
	 private boolean is_deleted;
	 
	 public ProposedLandUseExpansion(){
		 this.is_deleted = false;
	 }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	

	public EcPartB getEcPartB() {
		return ecPartB;
	}

	public void setEcPartB(EcPartB ecPartB) {
		this.ecPartB = ecPartB;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getExisting_land_req() {
		return existing_land_req;
	}

	public void setExisting_land_req(Double existing_land_req) {
		this.existing_land_req = existing_land_req;
	}

	public Double getProposed_land_req() {
		return proposed_land_req;
	}

	public void setProposed_land_req(Double proposed_land_req) {
		this.proposed_land_req = proposed_land_req;
	}

	public Double getTotal_land_req() {
		return total_land_req;
	}

	public void setTotal_land_req(Double total_land_req) {
		this.total_land_req = total_land_req;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public boolean isIs_deleted() {
		return is_deleted;
	}

	public void setIs_deleted(boolean is_deleted) {
		this.is_deleted = is_deleted;
	}
	 
	 
	 

}
