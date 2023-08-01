package com.backend.model.EnvironmentClearance;

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
@Table(name = "major_project_requirement", schema = "master")
public class MajorProjectRequirement extends Auditable<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(nullable = true, length = 100)
	private String project_requirement_details;

	@Column(nullable = true)
	private Integer idx;

	@Column(nullable = true, length = 50)
	private Double project_requirement_existing;

	@Column(nullable = true, length = 50)
	private Double project_requirement_expansion;

	@Column(name = "is_active")
	private boolean is_active;

	@Column(name = "is_deleted")
	private boolean is_deleted;

	public MajorProjectRequirement() {
		this.is_active = true;
		this.is_deleted = false;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProject_requirement_details() {
		return project_requirement_details;
	}

	public void setProject_requirement_details(String project_requirement_details) {
		this.project_requirement_details = project_requirement_details;
	}

	public Double getProject_requirement_existing() {
		return project_requirement_existing;
	}

	public void setProject_requirement_existing(Double project_requirement_existing) {
		this.project_requirement_existing = project_requirement_existing;
	}

	public Double getProject_requirement_expansion() {
		return project_requirement_expansion;
	}

	public void setProject_requirement_expansion(Double project_requirement_expansion) {
		this.project_requirement_expansion = project_requirement_expansion;
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
