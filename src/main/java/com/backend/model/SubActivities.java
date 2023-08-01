package com.backend.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import com.backend.audit.Auditable;

import lombok.Data;

@Entity
@Table(name = "sub_activities", schema = "master")
public class SubActivities extends Auditable<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_subact")
	@SequenceGenerator(name = "seq_subact", sequenceName = "subactivity_sequence", allocationSize = 1, initialValue = 1)
	private Integer id;

	/*@Column(nullable = false)
	private Integer activity_id;*/

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "activity_id", nullable = true)
	private Activities activities;
	
	/*
	 * @ManyToOne(cascade = CascadeType.ALL)
	 * 
	 * @JoinColumn(name = "activity_Id", nullable = true) private Activities
	 * activities;
	 */

	@Column(nullable = false,unique = true)
	private String name;

	@Column(nullable = false)
	private boolean is_active;

	@Column(nullable = false)
	private boolean is_deleted;

	@Column(nullable = true)
	private String description;
	
	@Column(name="code",length = 50,nullable = true)
	private String code;

//	@OneToMany(mappedBy = "subActivities", cascade = CascadeType.ALL)
//    List<EnvironmentClearanceProjectActivityDetails> environmentClearanceProjectActivityDetails = new ArrayList<>();

	public SubActivities() {
		this.is_active = true;
		this.is_deleted = false;
	}

	
	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	/*public Integer getActivity_id() {
		return activity_id;
	}

	public void setActivity_id(Integer activity_id) {
		this.activity_id = activity_id;
	}*/

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Activities getActivities() {
		return activities;
	}

	public void setActivities(Activities activities) {
		this.activities = activities;
	}

}
