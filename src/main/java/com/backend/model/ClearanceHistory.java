package com.backend.model;

import static javax.persistence.TemporalType.TIMESTAMP;

import java.util.Date;

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
import javax.persistence.Temporal;

import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;

import com.backend.audit.Auditable;
import com.backend.model.ForestClearancePartB.ForestClearanceBAfforestationDetails;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Data;

@Data
@Entity
@Table(name = "clearance_history", schema = "master")
@Where(clause = "is_deleted='false'")
public class ClearanceHistory extends Auditable<Integer> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "proposal_no", nullable = true)
	private String proposal_no;
	
	@Column(name = "step", nullable = true)
	private String step;
	
	@Column(name = "action", nullable = true)
	private String action;
	
	@Column(name = "proponent_status", nullable = true)
	private String proponent_status;
	
	@Column(name = "department_status", nullable = true)
	private String department_status;
	
	@Column(name = "remarks", nullable = true, length = 1500)
	private String remarks;
	
	@Column(name = "department_remarks", nullable = true, length = 1500)
	private String department_remarks;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "proposal_id", nullable = true)
	private ProponentApplications proponentApplications;
	
	/*@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "role_id", nullable = true)
	private Role role;*/
	
	@Column(nullable = true)
	private String headline;
	
	@Column(name = "action_from", updatable = true)
	@Temporal(TIMESTAMP)
	protected Date action_from;
	
	@Column(name = "action_to", updatable = true)
	@Temporal(TIMESTAMP)
	protected Date action_to;
	
	@Column(name = "ip_address", nullable = true,length = 50)
	private String ip_address;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "clearance_action_id", nullable = true)
	private ClearanceAction clearanceAction;
	
	@Column(nullable = false)
	@JsonProperty(access = Access.READ_ONLY)
	private Boolean is_active;

	@Column(nullable = false)
	@JsonProperty(access = Access.READ_ONLY)
	private Boolean is_deleted;
	
	public ClearanceHistory() {
		this.is_active = true;
		this.is_deleted = false;
	}
}
