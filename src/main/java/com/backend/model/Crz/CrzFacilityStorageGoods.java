package com.backend.model.Crz;

import java.util.Date;

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
@Table(name = "crz_facility_storage_goods", schema = "master")
public class CrzFacilityStorageGoods extends Auditable<Integer> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name = "name_of_product", nullable = true)
	private String name_of_product;
	
	@Column(name = "type_of_product", nullable = true)
	private String type_of_product;
	
	@Column(name = "please_specify_product", nullable = true)
	private String please_specify_product;
	
	@Column(name = "no_of_tanks_for_storage", nullable = true)
	private Integer no_of_tanks_for_storage;
	
	@Column(name = "capacity_of_tanks", nullable = true)
	private Integer capacity_of_tanks;
	
	@Column(name = "end_use_of_the_product", nullable = true)
	private String end_use_of_the_product;
	
	@Column(name = "is_active")
	private Boolean is_active;

	@Column(name = "is_deleted")
	private Boolean is_deleted;
	
	public CrzFacilityStorageGoods() {
		this.is_active = true;
		this.is_deleted = false;
	}

}
