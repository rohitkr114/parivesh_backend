package com.backend.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.backend.audit.Auditable;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;

@Data
@Entity
@Table(name = "forest_maps", schema = "master")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class ForestClearanceMaps extends Auditable<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fc_Id", nullable = false)
//	@JsonBackReference(value = "fc_reference")
	@JsonIgnore
	private ForestClearance forestClearance;

	@Column(nullable = false)
	private Integer totalPatch;

	@Column(nullable = false)
	private Double patchArea;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "kmlFile_id", nullable = true)
	private DocumentDetails kmlFile;

	@Column(nullable = true, length = 1000)
	private String remarks;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "scanCopy_id", nullable = true)
	private DocumentDetails scanCopy;

	@Column(nullable = false)
	private boolean isDelete;

	ForestClearanceMaps() {
		this.isDelete = false;

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

	public Integer getTotalPatch() {
		return totalPatch;
	}

	public void setTotalPatch(Integer totalPatch) {
		this.totalPatch = totalPatch;
	}

	public Double getPatchArea() {
		return patchArea;
	}

	public void setPatchArea(Double patchArea) {
		this.patchArea = patchArea;
	}

	public boolean isDelete() {
		return isDelete;
	}

	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}

	public DocumentDetails getKmlFile() {
		return kmlFile;
	}

	public void setKmlFile(DocumentDetails kmlFile) {
		this.kmlFile = kmlFile;
	}

	public DocumentDetails getScanCopy() {
		return scanCopy;
	}

	public void setScanCopy(DocumentDetails scanCopy) {
		this.scanCopy = scanCopy;
	}
    
}
