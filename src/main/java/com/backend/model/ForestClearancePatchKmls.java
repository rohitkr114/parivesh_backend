package com.backend.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;

@Data
@Entity
@Table(name = "forest_clearance_patch_kmls", schema = "master")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class ForestClearancePatchKmls extends Auditable<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	/*
	 * @ManyToOne(fetch = FetchType.LAZY)
	 * 
	 * @JoinColumn(name = "fc_Id" , nullable = false)
	 * 
	 * @JsonBackReference(value = "fc_reference") private ForestClearance
	 * forestClearance;
	 */

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fc_Id", nullable = false)
	@JsonIgnore
	private ForestClearance forestClearance;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "fc_patch_kml_id", nullable = false)
	private Set<ForestClearancePatchKmlDetails> forestClearancePatchKmlDetails = new HashSet<>();

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "patch_kml_id", nullable = true)
	private DocumentDetails patch_kml;

	@Column(nullable = true, length = 1000)
	private String remarks;
	
	@Column(name = "ne_extend", length = 100)
	private String ne_extend;

	@Column(name = "sw_extend", length = 100)
	private String sw_extend;


	@Column(name = "is_active")
	private boolean is_active;

	@Column(name = "is_deleted")
	private boolean is_deleted;

	ForestClearancePatchKmls() {
		this.is_active = true;
		this.is_deleted = false;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ForestClearance getForestClearance() {
		return forestClearance;
	}

	public void setForestClearance(ForestClearance forestClearance) {
		this.forestClearance = forestClearance;
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

	public DocumentDetails getPatch_kml() {
		return patch_kml;
	}

	public void setPatch_kml(DocumentDetails patch_kml) {
		this.patch_kml = patch_kml;
	}
  
	
}
