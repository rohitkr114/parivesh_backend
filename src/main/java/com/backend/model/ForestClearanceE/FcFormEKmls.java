package com.backend.model.ForestClearanceE;

import java.util.HashSet;
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
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "fc_form_e_kmls", schema = "master")
public class FcFormEKmls extends Auditable<Integer>{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "fc_form_e_id", nullable = true)
	@JsonIgnore
	private FcFormE fcFormE;

	@Column(nullable = true)
	private String division;

	@Column(nullable = true)
	private Integer division_id;
	
	@OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinColumn(name = "kml_patch_copy", nullable = true)
	private DocumentDetails kml_patch_copy;
	
	@Column(nullable = true, length = 1000)
	private String remarks;
	
	@OneToMany(targetEntity = FcFormEApprovalDetails.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "fc_form_e_kmls_id", referencedColumnName = "id",nullable = true)
	private Set<FcFormEApprovalDetails> fcFormEApprovalDetails = new HashSet<>();
	
	@Column(nullable = true)
	private Boolean is_active;
	
	@Column(nullable = true)
	private Boolean is_delete;
	
	public FcFormEKmls() {
		this.is_active=true;
		this.is_delete=false;
	}

	public FcFormEKmls(int id) {
		this.id = id;
	}
	
}
