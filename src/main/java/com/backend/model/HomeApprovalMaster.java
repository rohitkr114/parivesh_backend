package com.backend.model;

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
@Table(name = "approval_master", schema = "master")
public class HomeApprovalMaster extends Auditable<Integer> {

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

	@Column(name = "image_url", length = 200)
	private String image_url;

	@Column(name = "more_detail_url", length = 200)
	private String more_detail_url;

	@Column(name = "subject", length = 255)
	private String subject;

	@Column(name = "description", length = 500)
	private String description;

	@Column(name = "order_no")
	private Integer order_no;

	@Column(name = "enabled", nullable = false)
	private Boolean enabled;

	public HomeApprovalMaster() {
	}
}
