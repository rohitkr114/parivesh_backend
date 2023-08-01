package com.backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.backend.audit.Auditable;

@Entity(name="cms")
@Table(name = "cms", schema = "master")
public class CMS extends Auditable<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "title", length = 50, nullable = true)
	private String title;

	@Column(name = "abbr", length = 100, nullable = true)
	private String abbr;

	@Column(name="description",length=1000,nullable = true)
	private String description;

	@Column(name = "is_active", nullable = true)
	private Boolean is_active;

	@Column(name = "is_delete", nullable = true)
	private Boolean is_delete;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAbbr() {
		return abbr;
	}

	public void setAbbr(String abbr) {
		this.abbr = abbr;
	}


	public Boolean getIs_active() {
		return is_active;
	}

	public void setIs_active(Boolean is_active) {
		this.is_active = is_active;
	}

	public Boolean getIs_delete() {
		return is_delete;
	}

	public void setIs_delete(Boolean is_delete) {
		this.is_delete = is_delete;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public CMS() {
		this.is_active = true;
		this.is_delete = false;
	}
}
