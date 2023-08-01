package com.backend.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.backend.audit.Auditable;
import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Data
@Entity
@Table(name = "caf_government_orders", schema = "master")
public class CafGovernmentOrders extends Auditable<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(length = 500)
	private String detail_of_order;

//	private String order_copy;
//	private String order_copy_version;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "government_order_copy_id", nullable = true)
	private DocumentDetails government_order_copy;

	@Column(nullable = false)
	private boolean is_active;

	@Column(nullable = false)
	private boolean is_deleted;

	public CafGovernmentOrders() {
		this.is_active = true;
		this.is_deleted = false;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDetail_of_order() {
		return detail_of_order;
	}

	public void setDetail_of_order(String detail_of_order) {
		this.detail_of_order = detail_of_order;
	}

	public boolean isIs_active() {
		return is_active;
	}

	public void setIs_active(boolean is_active) {
		this.is_active = is_active;
	}

	public boolean isIs_deleted() {
		return is_deleted;
	}

	public void setIs_deleted(boolean is_deleted) {
		this.is_deleted = is_deleted;
	}

	public DocumentDetails getGovernment_order_copy() {
		return government_order_copy;
	}

	public void setGovernment_order_copy(DocumentDetails government_order_copy) {
		this.government_order_copy = government_order_copy;
	}
	
	
	
}
