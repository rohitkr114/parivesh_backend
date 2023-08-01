package com.backend.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.backend.audit.Auditable;

import lombok.Data;

@Data
@Entity
@Table(name="eds",schema = "master")
public class EDS extends Auditable<Integer>{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private Integer proposal_id;
	
	@Column(length = 80)
	private String proposal_no;
	
	@Column(nullable = true)
	private String remarks;
	
	@Lob
	private String field_mappings;
	
	@Column(length = 100)
	private String status;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "clearance_history_id", nullable = true)
	private ClearanceHistory clearanceHistory;
	
	private Boolean is_active;
	
	private Boolean is_deleted;
	
	public EDS(){
		this.is_active=true;
		this.is_deleted=false;
	}
	
}
