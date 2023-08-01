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
@Table(name="demolition_details",schema = "master")
public class DemolitionDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer Id;
	
	@ManyToOne
	@JoinColumn(name="ec_part_b_id")
	@JsonIgnore
	private EcPartB ecPartB;
	
	@Column(length = 500)
	private String structured_details_proposed;
	
	private Integer number;
	
	private Double built_up_area;
	
	@Column(length = 300)
	private String remarks;
	
	private boolean is_deleted;
	
	public DemolitionDetails(){
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

	public String getStructured_details_proposed() {
		return structured_details_proposed;
	}

	public void setStructured_details_proposed(String structured_details_proposed) {
		this.structured_details_proposed = structured_details_proposed;
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
