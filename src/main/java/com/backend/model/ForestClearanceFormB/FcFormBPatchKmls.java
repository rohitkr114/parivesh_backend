package com.backend.model.ForestClearanceFormB;

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

import com.backend.audit.Auditable;
import com.backend.model.DocumentDetails;
import com.backend.model.ForestClearanceFormC.FcFormC;
import com.backend.model.ForestClearanceFormC.FcFormCPatchKmlDetails;
import com.backend.model.ForestClearanceFormC.FcFormCPatchKmls;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "fc_form_b_patch_kmls", schema = "master")
public class FcFormBPatchKmls extends Auditable<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "fc_form_b_id", nullable = true)
	@JsonIgnore
	private FcFormBProjectDetails fcFormBProjectDetails;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "fc_form_b_patch_kml_id", nullable = false)
	private List<FcFormBPatchKmlDetails> fcFormBPatchKmlDetails = new ArrayList<>();

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

	public FcFormBPatchKmls() {
		this.is_active=true;
		this.is_deleted=false;
	}
}
