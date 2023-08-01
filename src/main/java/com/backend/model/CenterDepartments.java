package com.backend.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.backend.audit.Auditable;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Getter
@Setter
@Table(name="center_departments",schema="master")
public class CenterDepartments extends Auditable<Integer>{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name="dept_name",length = 100,nullable = false)
	private String dept_Name;
	
	@Column(name="is_active",nullable = false)
	private boolean is_active;
	
	@Column(name="is_deleted",nullable = false)
	private boolean is_deleted;
	
	/*
	@Column(name="created_by",nullable = false)
	private Integer created_by;
	
	@Column(name="created_on",nullable = false)
	private Date created_on;
	
	@Column(name="updated_by",nullable = false)
	private Integer updated_by;
	
	@Column(name="updated_on",nullable = false)
	private Date updated_on;*/
	
	public CenterDepartments() {
		this.is_active=true;
		this.is_deleted=false;
	}
	
}
