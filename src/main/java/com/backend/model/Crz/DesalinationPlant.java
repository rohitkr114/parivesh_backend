package com.backend.model.Crz;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.backend.audit.Auditable;

import lombok.Data;

@Data
@Entity
@Table(name = "desalination_plant", schema = "master")
public class DesalinationPlant extends Auditable<Integer> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name="capacity_of_desalination", nullable=true)
	private Double capacity_of_desalination;
	
	@Column(name="total_brine_generation", nullable=true)
	private Double total_brine_generation;
	
	@Column(name="temperature_of_effluent_above_ambient_disposal", nullable=true)
	private Double temperature_of_effluent_above_ambient_disposal;
	
	@Column(name="ambient_temperature_at_disposal_point", nullable=true)
	private Double ambient_temperature_at_disposal_point;
	
	@Column(name="ambient_salinity", nullable=true)
	private Double ambient_salinity;
	
	@Column(name="is_active", nullable=true)
	private Boolean is_active;
	
	@Column(name="is_deleted", nullable=true)
	private Boolean is_deleted;
	
	public DesalinationPlant() {
		this.is_active=true;
		this.is_deleted=false;
	}
	
	
}
