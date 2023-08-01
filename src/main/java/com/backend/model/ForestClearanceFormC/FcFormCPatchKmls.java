package com.backend.model.ForestClearanceFormC;

import java.util.ArrayList;
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

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Where;

import com.backend.audit.Auditable;
import com.backend.model.DocumentDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "fc_form_c_patch_kmls",schema = "master")
public class FcFormCPatchKmls extends Auditable<Integer>{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fc_form_c_id", nullable = false)
	@JsonIgnore
	private FcFormC fcFormC;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "fc_form_c_patch_kml_id", nullable = false)
	private Set<FcFormCPatchKmlDetails> fcFormCPatchKmlDetails = new HashSet<>();

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "patch_doc_kml_id", nullable = true)
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
	
	public FcFormCPatchKmls() {
		this.is_active=true;
		this.is_deleted=false;
	}
}
