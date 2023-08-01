package com.backend.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.backend.audit.Auditable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@Entity
@Table(name="recent_master",schema="master")
public class HomeRecentMaster extends Auditable<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getImage_url() {
		return image_url;
	}

	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}

	public String getMore_detail_url() {
		return more_detail_url;
	}

	public void setMore_detail_url(String more_detail_url) {
		this.more_detail_url = more_detail_url;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getEvent_date() {
		return event_date;
	}

	public void setEvent_date(Date event_date) {
		this.event_date = event_date;
	}

	public Integer getOrder_no() {
		return order_no;
	}

	public void setOrder_no(Integer order_no) {
		this.order_no = order_no;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Integer getCreated_by() {
		return created_by;
	}

	public void setCreated_by(Integer created_by) {
		this.created_by = created_by;
	}

	public Integer getUpdated_by() {
		return updated_by;
	}

	public void setUpdated_by(Integer updated_by) {
		this.updated_by = updated_by;
	}

	public Date getCreated_on() {
		return created_on;
	}

	public void setCreated_on(Date created_on) {
		this.created_on = created_on;
	}

	public Date getUpdated_on() {
		return updated_on;
	}

	public void setUpdated_on(Date updated_on) {
		this.updated_on = updated_on;
	}

	@Column(name="image_url",length=200)
	private String image_url;
	
	@Column(name="more_detail_url",length=200)
	private String more_detail_url;
	
	@Column(name="subject",length=255)
	private String subject;
	
	@Column(name="description",length=500)
	private String description;
	
	@Column(name="type")
	private String type;
	
	@Column(name="event_date")
	private Date event_date;
	
	@Column(name="order_no")
	private Integer order_no;
	
	@Column(name="enabled",nullable=false)
	private Boolean enabled;
	
	@Column(name="created_by")
	private Integer created_by;
	
	@Column(name="updated_by")
	private Integer updated_by;
	
	@Column(name="created_on")
	private Date created_on;
	
	@Column(name="updated_on")
	private Date updated_on;
	
	public HomeRecentMaster() {
		this.created_by = 1;
		this.updated_by = 1;
		this.created_on = new Date();
		this.updated_on = new Date();
	}
}
