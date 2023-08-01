package com.backend.model.ForestClearanceFormC;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.backend.audit.Auditable;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "fc_form_c_patch_kml_details", schema = "master")
public class FcFormCPatchKmlDetails extends Auditable<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "topoSheet_no", nullable = true, length = 50)
	private String topoSheet_no;

	@Column(name = "state", nullable = true, length = 50)
	private String state;

	@Column(name = "district", nullable = true, length = 50)
	private String district;

	@Column(name = "division", nullable = true, length = 50)
	private String division;

	@Column(nullable = true)
	private Integer division_id;

	@Column(name = "village", nullable = true, length = 50)
	private String village;

	@Column(name = "sub_district", nullable = true, length = 50)
	private String sub_district;

	@Column(name = "range", nullable = true, length = 50)
	private String range;

	@Column(name = "present_owner", nullable = true, length = 50)
	private String present_owner;

	@Column(name = "area")
	private String area;

	@Column(nullable = true, columnDefinition = "Boolean default false")
	private Boolean delete;

	@Column(nullable = true, columnDefinition = "Boolean default false")
	private Boolean manual_entry;

	@Column(name = "is_active")
	private boolean is_active;

	@Column(name = "is_deleted")
	private boolean is_deleted;

	public FcFormCPatchKmlDetails() {
		this.is_active = true;
		this.is_deleted = false;
	}

}
