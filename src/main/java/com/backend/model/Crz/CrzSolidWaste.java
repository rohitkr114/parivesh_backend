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
@Table(name="crz_solid_waste",schema = "master")
public class CrzSolidWaste extends Auditable<Integer>{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="crz_basic_details_id", nullable=false)
	@JsonIgnore
	private CrzBasicDetails crzBasicDetails;
	
	@Column(nullable = true)
	private String type_of_waste;

	@Column(name = "quantity_of_solid_waste_generated", nullable = true)
	private Double quantity_of_solid_waste_generated;

	@Column(nullable = true)
	private String mode_of_disposal;
	
	private Boolean is_active;
	
	private Boolean is_deleted;
	
	public CrzSolidWaste() {
		this.is_active=true;
		this.is_deleted=false;
	}
	
	public CrzSolidWaste(Integer id,String type_of_waste,Double quantity_of_solid_waste_generated,String mode_of_disposal) {
		this.id=id;
		this.type_of_waste=type_of_waste;
		this.quantity_of_solid_waste_generated=quantity_of_solid_waste_generated;
		this.mode_of_disposal=mode_of_disposal;
	}
}
