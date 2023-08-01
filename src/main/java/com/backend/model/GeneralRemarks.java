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
@Table(name="general_remarks", schema="master")
public class GeneralRemarks extends Auditable<Integer> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private Integer ref_id;
	
	@Column(length=100)
	private String ref_type;
	
	private String remarks;
	
	private String user_role_name;
	
	private Boolean is_active=true;
	
	private Boolean is_deleted=false;
}
