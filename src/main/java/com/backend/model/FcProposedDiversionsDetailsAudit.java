package com.backend.model;

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
import com.backend.model.WildLifeClearance.WildLifeClearanceAudit;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;

@Data
@Entity
@Table(name = "fc_proposed_diversions_details_audit", schema = "master")
public class FcProposedDiversionsDetailsAudit extends Auditable<Integer> {

	public String getToposheet_no() {
		return toposheet_no;
	}
	

	public void setToposheet_no(String toposheet_no) {
		this.toposheet_no = toposheet_no;
	}
	
	
	
	  @ManyToOne
	  @JoinColumn(name="fc_diversion_detail_id", nullable=false)
	  @JsonIgnore private ForestClearanceProposedDiversionsAudit forestClearanceProposedDiversionsAudit;
	 
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
		
	@Column(nullable = true, length = 50)
	private String toposheet_no;

	@Column(name = "state", nullable = true, length = 50)
	private String state;

	@Column(nullable = true)
	private Integer state_code;

	@Column(nullable = false, length = 50)
	private String district;

	@Column(nullable = true)
	private Integer district_code;

	@Column(nullable = false, length = 50)
	private String village;

	@Column(nullable = true)
	private Integer village_code;

	@Column(nullable = true)
	private String range;
	
	@Column(nullable=true)
	private String unit;

	@Column(nullable = true)
	private String forest_area_length;

	@Column(nullable = true)
	private String forest_area_width;

	@Column(nullable = true)
	private String forest_area_total;

	@Column(nullable = true)
	private String non_forest_area_length;

	@Column(nullable = true)
	private String non_forest_area_width;

	@Column(nullable = true)
	private String non_forest_area_total;
	@Column(nullable = true)
	private String non_linear_forest_area;
	@Column(nullable = true)
	private String non_linear_non_forest_area;

	@Column(name = "sub_district", nullable = true, length = 50)
	private String sub_district;

	@Column(nullable = true)
	private Integer sub_district_code;

	/*
	 * @Column(name = "manual_entry", nullable = true) private Boolean manual_entry;
	 */
	@Column(nullable = false)
	private boolean isDelete;

	
}
