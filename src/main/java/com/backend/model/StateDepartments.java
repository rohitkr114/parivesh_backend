package com.backend.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Data
@Entity
@Table(name="state_departments",schema="master")
public class StateDepartments implements Serializable{
	
	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name="dept_name",length = 100,nullable = false)
	private String Dept_Name;
	
	private Integer state_code;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JsonBackReference
	@JoinColumn(name = "state_code",referencedColumnName="code", insertable = false, updatable = false)
	private State state;
	
	@Column(name="is_active",nullable = false)
	private boolean is_active;
	
	@Column(name="is_deleted",nullable = false)
	private boolean is_deleted;
	
	@Column(name="created_by",nullable = false)
	private Long created_by;
	
	@Column(name="created_on",nullable = false)
	private Date created_on;
	
	@Column(name="updated_by",nullable = false)
	private Long updated_by;
	
	@Column(name="updated_on",nullable = false)
	private Date updated_on;

	public StateDepartments() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StateDepartments(int id, String dept_Name, Integer state_code, boolean is_active, boolean is_deleted,
			Long created_by, Date created_on, Long updated_by, Date updated_on) {
		super();
		this.id = id;
		Dept_Name = dept_Name;
		this.state_code = state_code;
		this.is_active = is_active;
		this.is_deleted = is_deleted;
		this.created_by = created_by;
		this.created_on = created_on;
		this.updated_by = updated_by;
		this.updated_on = updated_on;
	}

	public Integer getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDept_Name() {
		return Dept_Name;
	}

	public void setDept_Name(String dept_Name) {
		Dept_Name = dept_Name;
	}

	public Integer getState_code() {
		return state_code;
	}

	public void setState_code(Integer state_code) {
		this.state_code = state_code;
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

	public Long getCreated_by() {
		return created_by;
	}

	public void setCreated_by(Long created_by) {
		this.created_by = created_by;
	}

	public Date getCreated_on() {
		return created_on;
	}

	public void setCreated_on(Date created_on) {
		this.created_on = created_on;
	}

	public Long getUpdated_by() {
		return updated_by;
	}

	public void setUpdated_by(Long updated_by) {
		this.updated_by = updated_by;
	}

	public Date getUpdated_on() {
		return updated_on;
	}

	public void setUpdated_on(Date updated_on) {
		this.updated_on = updated_on;
	}
	
	

}
