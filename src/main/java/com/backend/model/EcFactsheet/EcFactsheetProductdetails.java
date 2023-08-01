package com.backend.model.EcFactsheet;

import java.util.Date;

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
@Table(name="ec_factsheet_product_details", schema="master")
public class EcFactsheetProductdetails extends Auditable<Integer> {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(name="product_name", nullable=true, length=100)
	private String product_name;
	
	@Column(name="capacity", nullable=true, length=50)
	private String capacity;

	@Column(name="capacity_to", nullable=true, length=50)
	private String capacity_to;

	@Column(name="capacity_unit", nullable=true, length=50)
	private String capacity_unit;
	
	@Column(name="total_capacity", nullable=true, length=50)
	private String total_capacity;
	
//	@ManyToOne
//	@JoinColumn(name="ec_factsheet_id", nullable=true)
//	@JsonIgnore
//	private EcFactsheet ecFactsheet;
	
	@Column(name = "is_active")
	private Boolean is_active;

	@Column(name = "is_deleted")
	private Boolean is_deleted;
	
	public EcFactsheetProductdetails() {
		this.is_active = true;
		this.is_deleted = false;
	}
	
	
}
