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
@Table(name = "windmills", schema = "master")
public class WindMills extends Auditable<Integer> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name="capacity_of_wind_mills",nullable=true)
	private Double capacity_of_wind_mills;
	
	@Column(name="transmission_lines",nullable=true,length=100)
	private String transmission_lines;
	
	@Column(name="diameter_of_windmill",nullable=true)
	private Double diameter_of_windmill;
	
	@Column(name="length_of_blade",nullable=true)
	private Double length_of_blade;
	
	@Column(name="speed_of_rotation",nullable=true)
	private Double speed_of_rotation;
	
	@Column(name="height_of_structure_wind_mills",nullable=true)
	private Double height_of_structure_wind_mills;
	
	@Column(name="is_active", nullable=true)
	private Boolean is_active;
	
	@Column(name="is_deleted", nullable=true)
	private Boolean is_deleted;
	
	public WindMills() {
		this.is_active=true;
		this.is_deleted=false;
	}

}
