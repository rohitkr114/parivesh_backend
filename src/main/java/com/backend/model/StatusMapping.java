package com.backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "status_mapping", schema = "process_engine")
public class StatusMapping {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name = "display_status",nullable = false)
	private String displayStatus;
	
	@Column(name = "pending_status",nullable = false)
	private String pendingStatus;
	
	@Column(name = "workgroup_id")
	private Integer workgroupId;
	
}
