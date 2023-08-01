package com.backend.model.ForestClearanceFormCPartB;

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
@Table(name="fc_form_c_part_b_other_details",schema = "master")
public class FcFormCPartBOtherDetails extends Auditable<Integer>{


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fc_form_c_part_b_id", nullable = false)
	@JsonIgnore
	private FcFormCPartB fcFormCPartB;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "site_inspection_report_id", nullable = true)
	private DocumentDetails site_inspection_report;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "recommendation_file_copy_id", nullable = true)
	private DocumentDetails recommendation_file_copy;
	
	@Column(nullable = true)
	private String recomm_total_forest_land;
	
	@Column(nullable = true)
	private String recomm_area;
	
	@Column(length = 100,nullable = true)
	private String recommendation_of_dfo;
	
	@Column(length = 1000,nullable = true)
	private String recommendation_of_dfo_details;

	@Column(length = 1000,nullable = true)
	private String justification;

	private Boolean is_active;
	
	private Boolean is_deleted;
	

	FcFormCPartBOtherDetails(){
		this.is_active=true;
		this.is_deleted=false;
	}
}
