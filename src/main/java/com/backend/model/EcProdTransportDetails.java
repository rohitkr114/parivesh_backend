package com.backend.model;

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
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;

@Data
@Entity
@Table(name = "ec_prod_transport_details", schema = "master")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "product_id")
public class EcProdTransportDetails extends Auditable<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer product_id;

	@ManyToOne
	@JoinColumn(name = "ec_id")
	@JsonIgnore
	private EnvironmentClearence environmentClearence;

	@Column(name = "product_name", nullable = true, length = 200)
	private String product_name;

	@Column(name = "product_by", nullable = true, length = 200)
	private String product_by;

	@Column(name = "quantity", nullable = true, length = 500)
	private Double quantity;

	@Column(name = "unit", nullable = true)
	private String unit;

	@Column(name = "transport_mode", nullable = true)
	private String transport_mode;

	@Column(name = "remarks", nullable = true, length = 500)
	private String remarks;
	
	@Column(nullable = true)
	private String proposalNo;
	
	@Column(name="quantity_total",nullable = true)
	private String quantity_total;
	
	@Column(name="proposed_quantity",nullable = true,length = 50)
	private Double proposed_quantity;

	@Column(name="unit_other",nullable = true,length = 255)
	private String unit_other;
	
	@Column(nullable = false)
	private boolean isDelete;

	public EcProdTransportDetails() {
		this.isDelete = false;
	}

	public Integer getProduct_id() {
		return product_id;
	}

	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public String getProduct_by() {
		return product_by;
	}

	public void setProduct_by(String product_by) {
		this.product_by = product_by;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getTransport_mode() {
		return transport_mode;
	}

	public void setTransport_mode(String transport_mode) {
		this.transport_mode = transport_mode;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public boolean isDelete() {
		return isDelete;
	}

	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}

	public EnvironmentClearence getEnvironmentClearence() {
		return environmentClearence;
	}

	public void setEnvironmentClearence(EnvironmentClearence environmentClearence) {
		this.environmentClearence = environmentClearence;
	}

	public String getProposalNo() {
		return proposalNo;
	}

	public void setProposalNo(String proposalNo) {
		this.proposalNo = proposalNo;
	}

	public String getQuantity_total() {
		return quantity_total;
	}

	public void setQuantity_total(String quantity_total) {
		this.quantity_total = quantity_total;
	}

	public Double getProposed_quantity() {
		return proposed_quantity;
	}

	public void setProposed_quantity(Double proposed_quantity) {
		this.proposed_quantity = proposed_quantity;
	}

	public String getUnit_other() {
		return unit_other;
	}

	public void setUnit_other(String unit_other) {
		this.unit_other = unit_other;
	}
 
	
	
	
}
