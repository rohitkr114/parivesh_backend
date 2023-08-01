package com.backend.model.ForestClearanceE;

import java.util.Date;
import java.util.List;

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

import com.backend.model.DocumentDetails;
import com.backend.model.ForestClearanceFormC.FcFormCProposedLand;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name="fc_form_e_patch_details",schema = "master")
public class FcFormEPatchDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(nullable = true)
	private Double area_of_patch;
	
	@Column(nullable = true)
	private Date date_of_return;
	
	@Column(nullable = true)
	private String authority_of_forest_land;

	@Column(nullable = true)
	private String reason_for_return_to_authority;
	
	@Column(nullable = true)
	private String status_of_reclamation;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "map_patch_copy_id", nullable = true)
	private DocumentDetails map_of_patch_copy;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "map_of_patch_gps_copy_id", nullable = true)
	private DocumentDetails map_of_patch_gps_copy;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "documentary_proof_copy_id", nullable = true)
	private DocumentDetails documentary_proof_copy;

	@Column(nullable = true, length = 1000)
	private String remarks;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fc_form_e_id", nullable = true)
	@JsonIgnore
	private FcFormE fcFormE;
	
	@Column(nullable = true)
	private Boolean is_active;
	
	@Column(nullable = true)
	private Boolean is_delete;
	
	public FcFormEPatchDetails() {
		this.is_active=true;
		this.is_delete=false;
	}

	public FcFormEPatchDetails(Integer id) {
		this.id = id;
	}

}
