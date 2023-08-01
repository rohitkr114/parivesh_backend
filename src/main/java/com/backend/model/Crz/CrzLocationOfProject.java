package com.backend.model.Crz;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.backend.audit.Auditable;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name="crz_location_of_project",schema = "master")
public class CrzLocationOfProject extends Auditable<Integer>{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(nullable = true)
	private String project_location;
	
	@Column(nullable = true)
	private String type_of_projects;
	
	private Boolean is_active;
	
	private Boolean is_deleted;
	
	public CrzLocationOfProject() {
		this.is_active=true;
		this.is_deleted=false;
	}

}
