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
@Table(name="activity_action_mapping",schema = "master")
public class ActivityActionMapping extends Auditable<Integer>{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(nullable = true)
	private Integer activity_id;
	
	@Column(nullable = true)
	private Integer application_id;
	
	@Column(nullable = true,length = 10)
	private String item_no;
	
	@Column(nullable = true)
	private Boolean is_active;
	
	@Column(nullable = true)
	private Boolean is_deleted;
	
	public ActivityActionMapping() {
		this.is_active=true;
		this.is_deleted=false;
	}

}
