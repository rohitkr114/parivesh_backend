package com.backend.model.EnvironmentClearance;

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
@Table(name = "ec_waste_dtls", schema = "master")
public class EcWasteDetails extends Auditable<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer Id;

	@ManyToOne
	@JoinColumn(name = "ec_partB_id")
	@JsonIgnore
	private EcPartB ecPartB;

	@Column(name = "waste_type", nullable = true, length = 50)
	private String waste_type;

	@Column(name = "name", nullable = true, length = 50)
	private String name;

	@Column(name = "source", nullable = true, length = 100)
	private String source;

	@Column(name = "quantity", nullable = true)
	private Double quantity;

	@Column(name = "mode_of_disposal", nullable = true, length =100)
	private String mode_of_disposal;

	@Column(name = "mode_of_transport", nullable = true, length = 100)
	private String mode_of_transport;
	
	@Column(name="others",nullable = true,length = 50)
	private String others;

	@Column(name = "is_active")
	private boolean is_active;

	@Column(name = "is_deleted")
	private boolean is_deleted;

	public EcWasteDetails() {
		this.is_active = true;
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

	public String getWaste_type() {
		return waste_type;
	}

	public void setWaste_type(String waste_type) {
		this.waste_type = waste_type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public String getMode_of_disposal() {
		return mode_of_disposal;
	}

	public void setMode_of_disposal(String mode_of_disposal) {
		this.mode_of_disposal = mode_of_disposal;
	}

	public String getMode_of_transport() {
		return mode_of_transport;
	}

	public void setMode_of_transport(String mode_of_transport) {
		this.mode_of_transport = mode_of_transport;
	}

	public boolean isIs_deleted() {
		return is_deleted;
	}

	public void setIs_deleted(boolean is_deleted) {
		this.is_deleted = is_deleted;
	}

	public String getOthers() {
		return others;
	}

	public void setOthers(String others) {
		this.others = others;
	}

	public boolean isIs_active() {
		return is_active;
	}

	public void setIs_active(boolean is_active) {
		this.is_active = is_active;
	}
	
	

}
