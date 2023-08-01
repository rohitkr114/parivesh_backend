package com.backend.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.backend.audit.Auditable;
import com.backend.constant.AppConstant;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode
@Table(name = "caf_kml", schema = "master")
//@JsonIgnoreProperties({ "created_by", "created_on", "updated_by", "updated_on" })
public class CafKML extends Auditable<Integer> implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "common_form_id", nullable = false)
	@JsonIgnore
	private CommonFormDetail commonFormDetail;

	/*
	 * @OneToMany(fetch = FetchType.LAZY ,cascade = CascadeType.ALL)
	@JoinColumn(name = "caf_kml", nullable = true)
	private List<CAFKMLPlots> cafKMLPlots = new ArrayList<>();
	*/
	
	@OneToMany(targetEntity =CAFKMLPlots.class,cascade = CascadeType.ALL)
	@JoinColumn(name = "caf_kml",referencedColumnName = "id")
	private List<CAFKMLPlots> cafKMLPlots = new ArrayList<>();

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "type", nullable = false)
	private AppConstant.kml_type type;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "caf_kml_id", nullable = true)
	private DocumentDetails caf_kml;

	@Column(nullable = true, length = 1000)
	private String remarks;
	
	@Column(name = "ne_extend", length = 100, nullable = true)
	private String ne_extend;

	@Column(name = "sw_extend", length = 100, nullable = true)
	private String sw_extend;

	@Column(nullable = false)
	private boolean is_active;

	@Column(nullable = false)
	private boolean is_deleted;
	
	@Column(name = "i_agree")
	private Boolean I_Agree;
	

	
	
	
	public CafKML() {

		this.is_active = true;
		this.is_deleted = false;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public CommonFormDetail getCommonFormDetail() {
		return commonFormDetail;
	}

	public void setCommonFormDetail(CommonFormDetail commonFormDetail) {
		this.commonFormDetail = commonFormDetail;
	}

	public List<CAFKMLPlots> getCafKMLPlots() {
		return cafKMLPlots;
	}

	public void setCafKMLPlots(List<CAFKMLPlots> cafKMLPlots) {
		this.cafKMLPlots = cafKMLPlots;
	}

	public AppConstant.kml_type getType() {
		return type;
	}

	public void setType(AppConstant.kml_type type) {
		this.type = type;
	}

	public String getNe_extend() {
		return ne_extend;
	}

	public void setNe_extend(String ne_extend) {
		this.ne_extend = ne_extend;
	}

	public String getSw_extend() {
		return sw_extend;
	}

	public void setSw_extend(String sw_extend) {
		this.sw_extend = sw_extend;
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

	public Boolean getI_Agree() {
		return I_Agree;
	}

	public void setI_Agree(Boolean i_Agree) {
		I_Agree = i_Agree;
	}

	public DocumentDetails getCaf_kml() {
		return caf_kml;
	}

	public void setCaf_kml(DocumentDetails caf_kml) {
		this.caf_kml = caf_kml;
	}

}