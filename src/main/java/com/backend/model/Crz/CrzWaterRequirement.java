package com.backend.model.Crz;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.backend.audit.Auditable;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name="crz_water_requirement",schema = "master")
public class CrzWaterRequirement extends Auditable<Integer>{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="crz_basic_details_id", nullable=false)
	@JsonIgnore
	private CrzBasicDetails crzBasicDetails;
	
	@Column(name = "source", nullable = true)
	private String source;

	@Column(name = "quantity", nullable = true)
	private Double quantity;
	
	private Boolean is_active;
	
	private Boolean is_deleted;
	
	public CrzWaterRequirement() {
		this.is_active=true;
		this.is_deleted=false;
	}
	
	public CrzWaterRequirement(Integer id,String source,Double quantity) {
		this.id=id;
		this.source=source;
		this.quantity=quantity;
	}
}
