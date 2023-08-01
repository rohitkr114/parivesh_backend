package com.backend.model.EcFactsheet;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.backend.audit.Auditable;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name="ec_factsheet_public_hearing_details", schema="master")
public class EcfactsheetPublichearingdetails extends Auditable<Integer> {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(name="date", nullable=true, length=100)
	private Date date;

	@Column(name="state", nullable=true, length=100)
	private String state;

	@Column(name="state_id", nullable=true, length=100)
	private String state_id;
	
	@Column(name="district", nullable=true, length=100)
	private String district;

	@Column(name="district_id", nullable=true, length=100)
	private String district_id;
	
	@Column(name="issue_raised", nullable=true, length=100)
	private String issue_raised;
	
	@ManyToOne
	@JoinColumn(name="ec_factsheet_id", nullable=false)
	@JsonIgnore
	private EcFactsheet ecFactsheet; 
	
	@Column(name = "is_active")
	private Boolean is_active;

	@Column(name = "is_deleted")
	private Boolean is_deleted;
	
	public EcfactsheetPublichearingdetails() {
		this.is_active = true;
		this.is_deleted = false;
	}

}
