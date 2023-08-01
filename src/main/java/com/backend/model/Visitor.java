package com.backend.model;

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
@Table(name="visitor",schema = "master")
public class Visitor extends Auditable<Integer>{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer Id;
	
	@Column(name="total",nullable = true)
	private Integer Total;
	
	@Column(name="ip_address",length = 50,nullable = true)
	private String ip_address;
	
	@Column(name="is_active")
	private Boolean is_active;
	
	@Column(name="is_deleted")
	private Boolean is_deleted;
	
	
}
