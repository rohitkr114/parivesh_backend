package com.backend.model.EnvironmentClearance;

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
@Table(name = "ec_pollutant_dtls", schema = "master")
public class EcPollutantDetails extends Auditable<Integer> {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer Id;

	@ManyToOne
	@JoinColumn(name = "ec_partB_id")
	@JsonIgnore
	private EcPartB ecPartB;

	@Column(name = "details_particulars", nullable = false, length = 50)
	private String details_particulars;

	@Column(name = "detail_type", nullable = false, length = 50)
	private String detail_type;

	@Column(name = "capacity", nullable = false, length = 50)
	private String capacity;

	@Column(name = "present_expansion_flag", nullable = false, length = 50)
	private String present_expansion_flag;

	@Column(name = "remarks_reason", nullable = false, length = 100)
	private String remarks_reason;

	@Column(name = "is_active")
	private boolean is_active;

	@Column(name = "is_deleted")
	private boolean is_deleted;

	public EcPollutantDetails() {
		this.is_active = true;
		this.is_deleted = false;
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public EcPartB getEcPartB() {
		return ecPartB;
	}

	public void setEcPartB(EcPartB ecPartB) {
		this.ecPartB = ecPartB;
	}

	public String getDetails_particulars() {
		return details_particulars;
	}

	public void setDetails_particulars(String details_particulars) {
		this.details_particulars = details_particulars;
	}

	public String getDetail_type() {
		return detail_type;
	}

	public void setDetail_type(String detail_type) {
		this.detail_type = detail_type;
	}

	public String getCapacity() {
		return capacity;
	}

	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}

	public String getPresent_expansion_flag() {
		return present_expansion_flag;
	}

	public void setPresent_expansion_flag(String present_expansion_flag) {
		this.present_expansion_flag = present_expansion_flag;
	}

	public String getRemarks_reason() {
		return remarks_reason;
	}

	public void setRemarks_reason(String remarks_reason) {
		this.remarks_reason = remarks_reason;
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
   
	
}
