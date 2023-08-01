package com.backend.model.EnvironmentClearance;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="ec_construction_details",schema = "master")
public class ConstructionDetails {
   
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	
	@ManyToOne
	@JoinColumn(name="ec_part_b_id")
	@JsonIgnore
	private EcPartB ecPartB;
	
	
	private String details;
	
	private Integer number;
	
	private Double built_up_area;

	@Column(length = 500)
	private String remarks;
	
	private boolean is_deleted;
	
	public ConstructionDetails(){
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

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Double getBuilt_up_area() {
		return built_up_area;
	}

	public void setBuilt_up_area(Double built_up_area) {
		this.built_up_area = built_up_area;
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
