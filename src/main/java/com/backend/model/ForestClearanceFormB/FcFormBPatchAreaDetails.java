package com.backend.model.ForestClearanceFormB;

import java.util.Date;

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
import com.backend.model.DocumentDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "fc_form_b_patch_area_details", schema = "master")
public class FcFormBPatchAreaDetails extends Auditable<Integer> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "fc_form_b_id", nullable = true)
	@JsonIgnore
	private FcFormBProjectDetails fcFormBProjectDetails;
	
	@Column(nullable = true)
	private Double area;
	
//	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//	@JoinColumn(name = "patch_kml", nullable = true)
//	private DocumentDetails patch_kml;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "gps_patch", nullable = true)
	private DocumentDetails gps_patch;

	@Column(nullable = true, length = 1000)
	private String remarks;
	
	@Column(nullable = true)
	private Date return_date;
	
	@Column(nullable = true)
	private String authority_name;
	
	@Column(nullable = true)
	private String authority_name_other;
	
	@Column(nullable = true)
	private String land_status;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "documentary_proof_copy", nullable = true)
	private DocumentDetails documentary_proof_copy;
	
	private Boolean is_active;

	private Boolean is_deleted;

	public FcFormBPatchAreaDetails() {
		this.is_active=true;
		this.is_deleted=false;
	}

	public FcFormBPatchAreaDetails(Integer id, Double area, Date return_date, String authority_name,
			String authority_name_other, String land_status) {
		this.id = id;
		this.area = area;
		this.return_date = return_date;
		this.authority_name = authority_name;
		this.authority_name_other = authority_name_other;
		this.land_status = land_status;
	}

	public FcFormBPatchAreaDetails(Integer id, Double area, 
//			DocumentDetails patch_kml, 
			DocumentDetails gps_patch,
			Date return_date, 
			String authority_name, 
			String authority_name_other, 
			String land_status,
			DocumentDetails documentary_proof_copy) {
		this.id = id;
		this.area = area;
//		this.patch_kml = patch_kml;
		this.gps_patch = gps_patch;
		this.return_date = return_date;
		this.authority_name = authority_name;
		this.authority_name_other = authority_name_other;
		this.land_status = land_status;
		this.documentary_proof_copy = documentary_proof_copy;
	}
	
	
	
	
}
