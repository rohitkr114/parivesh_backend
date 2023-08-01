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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.backend.audit.Auditable;
import com.backend.model.DocumentDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "fc_form_b_approval_details", schema = "master")
public class FcFormBApprovalDetails extends Auditable<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@OneToOne
	@JoinColumn(name = "fc_form_b_id", nullable = true)
	@JsonIgnore
	private FcFormBProjectDetails fcFormBProjectDetails;
	
	@Column(nullable = true)
	private String forest_land_returned;
	
	@Column(nullable = true)
	private int patch_count;
	
	@Column(nullable = true)
	private Double forest_area;
	
	@Column(nullable = true)
	private String lease_agency_name;
	
	@Column(nullable = true)
	private Boolean is_lease_transferred;
	
	@Column(nullable = true)
	private String firm_name;
	
	@Column(length = 1000, nullable = true)
	private String firm_address;
	
	@Column(nullable = true)
	private Date lease_transfer_date;
	
	@Column(nullable = true)
	private Boolean approval_transfer_lease;
	
	@Column(nullable = true, length = 1000)
	private String lease_agency_address;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "all_patch_kml", nullable = true)
	private DocumentDetails all_patch_kml;

	@Column(nullable = true, length = 1000)
	private String remarks;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "transfer_deed_copy", nullable = true)
	private DocumentDetails transfer_deed_copy;
	
	private Boolean is_active;

	private Boolean is_deleted;

	public FcFormBApprovalDetails() {
		this.is_active=true;
		this.is_deleted=false;
	}

	public FcFormBApprovalDetails(Integer id, String forest_land_returned, int patch_count, Double forest_area,
			String lease_agency_name, Boolean is_lease_transferred, String firm_name, String firm_address,
			Date lease_transfer_date, Boolean approval_transfer_lease, String lease_agency_address) {
		this.id = id;
		this.forest_land_returned = forest_land_returned;
		this.patch_count = patch_count;
		this.forest_area = forest_area;
		this.lease_agency_name = lease_agency_name;
		this.is_lease_transferred = is_lease_transferred;
		this.firm_name = firm_name;
		this.firm_address = firm_address;
		this.lease_transfer_date = lease_transfer_date;
		this.approval_transfer_lease = approval_transfer_lease;
		this.lease_agency_address = lease_agency_address;
	}

	public FcFormBApprovalDetails(Integer id) {
		this.id = id;
	}

	
}
