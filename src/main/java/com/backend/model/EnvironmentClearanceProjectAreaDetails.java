package com.backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.backend.audit.Auditable;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;

@Data
@Entity
@Table(name = "ec_project_area_dtls", schema = "master")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class EnvironmentClearanceProjectAreaDetails extends Auditable<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer id;

	@ManyToOne
	@JoinColumn(name = "ec_id", nullable = true)
	@JsonIgnore
	private EnvironmentClearence environmentClearence;

	@Column(nullable = false)
	private String area_category;

	@Column(length = 100)
	private String name;

	@Column(length = 50)
	private Double shortest_distance;

	@Column(length = 500)
	private String remarks;

	@Column(length = 500)
	private String reason_thereof;

	@Column(nullable = false)
	private boolean isDelete;
	
	private String proposalNo;

	public EnvironmentClearanceProjectAreaDetails() {
		this.isDelete = false;

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getArea_category() {
		return area_category;
	}

	public void setArea_category(String area_category) {
		this.area_category = area_category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getShortest_distance() {
		return shortest_distance;
	}

	public void setShortest_distance(Double shortest_distance) {
		this.shortest_distance = shortest_distance;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getReason_thereof() {
		return reason_thereof;
	}

	public void setReason_thereof(String reason_thereof) {
		this.reason_thereof = reason_thereof;
	}

	public boolean isDelete() {
		return isDelete;
	}

	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}

	public String getProposalNo() {
		return proposalNo;
	}

	public void setProposalNo(String proposalNo) {
		this.proposalNo = proposalNo;
	}

}
