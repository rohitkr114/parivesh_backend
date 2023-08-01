package com.backend.model.EnvironmentClearance;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.backend.audit.Auditable;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="ec_proposed_project_land_details",schema="master")
public class EcProposedProjectLandDetails extends Auditable<Integer> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer Id;
	
	@Column(name="description_activity",length=50)
	private String description_activity;
	
	@Column(name="existing_land_required")
	private Integer existing_land_required;
	
	@Column(name="proposed_land")
	private Integer proposed_land;
	
    @Column(name="total_land")
	private Integer total_land;
    
    @Column(name="remarks",length=100)
	private String remarks;
    
    @Column(nullable = false)
	private boolean is_active;
    
    @Column(nullable = false)
	private boolean is_deleted;
	
	@ManyToOne
	@JoinColumn(name="ec_partB_id")
	@JsonIgnore
	private EcPartB ecPartB;

	
	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getDescription_activity() {
		return description_activity;
	}

	public void setDescription_activity(String description_activity) {
		this.description_activity = description_activity;
	}

	public Integer getExisting_land_required() {
		return existing_land_required;
	}

	public void setExisting_land_required(Integer existing_land_required) {
		this.existing_land_required = existing_land_required;
	}

	public Integer getProposed_land() {
		return proposed_land;
	}

	public void setProposed_land(Integer proposed_land) {
		this.proposed_land = proposed_land;
	}

	public Integer getTotal_land() {
		return total_land;
	}

	public void setTotal_land(Integer total_land) {
		this.total_land = total_land;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
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

	public EcPartB getEcPartB() {
		return ecPartB;
	}

	public void setEcPartB(EcPartB ecPartB) {
		this.ecPartB = ecPartB;
	}
	
	public EcProposedProjectLandDetails() {
		this.is_active=true;
		this.is_deleted=false;
	}
	
  
	
}
