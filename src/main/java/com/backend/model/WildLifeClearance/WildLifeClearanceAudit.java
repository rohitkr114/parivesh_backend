package com.backend.model.WildLifeClearance;

import static javax.persistence.TemporalType.TIMESTAMP;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;

import com.backend.audit.Auditable;
import com.backend.model.ForestClearanceProposedDiversionsAudit;
import lombok.Data;

@Data
@Entity
@Table(name = "wl_clearance_audit", schema = "master")
public class WildLifeClearanceAudit {
	
	private Date proposal_created_on;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(nullable = true)
	private Integer wl_id;
		
	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable = false)
	@CreationTimestamp
	private Date eds_raised_on;
	
	@Column(nullable = true, length = 100)
	private String unique_clearance_no;
	
	@Column(nullable = true, length = 100)
	private String green_clearance_no;
	@Column(nullable = true, length = 100)
	private String category_of_project;
	// added on 17-01-2023
	@Column(nullable = true, length = 100)
	private String type_of_category_project;
	// added on 17-01-2023
	@Column(nullable = true, length = 100)
	private String sub_category_of_project;

	@Column(nullable = true)
	private String project_category_id;

	@Column(nullable = true, length = 500)
	private String project_category_others;

	@Column(name = "state")
	private Integer state;

	@Column(nullable = true, length = 100)
	private String category_of_area;

	@Column(nullable = true)
	private boolean is_any_prior_approval;

	@Column(name = "proposal_no",length = 50, nullable = false)
	private String proposal_no;

	  @OneToOne(mappedBy = "wildLifeClearanceaudit") WLProposedLandAudit wlProposedLandAudit;
	  
	  @OneToOne(mappedBy = "wildLifeClearanceaudit") WLOtherDetailsAudit wlOtherDetailsAudit;
	  
	  @OneToOne(mappedBy = "wildLifeClearanceaudit") WLEnclosuresAudit wlEnclosuresAudit;
	  
	  @OneToOne(mappedBy = "wildLifeClearanceaudit") WLUndertakingAudit wlUndertakingAudit;
	  
	  @OneToMany(mappedBy = "wildLifeClearanceaudit", cascade = CascadeType.ALL)
	  @Where(clause = "is_delete='false'") 
	  List<WLDivisionLandDetailAudit> wldivisionLandDetailsAudit = new ArrayList<>();
	  
	  @OneToMany(mappedBy = "wildLifeClearanceaudit", cascade = CascadeType.ALL)
	  @Where(clause = "is_delete='false'") 
	  List<WLComponentWiseDetailsAudit> wlComponentWiseDetailsAudit = new ArrayList<>();
	  
	  @OneToMany(mappedBy = "wildLifeClearanceaudit", cascade = CascadeType.ALL)
	  @Where(clause = "isDelete='false'") 
	  List<ForestClearanceProposedDiversionsAudit> forestClearanceProposedDiversionsAudit = new ArrayList<>();
	     

	// added on 25112022
	@Column(nullable = true)
	private Boolean for_investigation_survey_new;

	@Column(nullable = true, length = 100)
	private String investigation_purpose;

	/*
	 * @Column(name = "physical_disturbance_within_protected_area", nullable = true)
	 * private Boolean physical_disturbance_within_protected_area;
	 */

	@Column(nullable = true, length = 100)
	private String extent_of_physical_disturbance_new;

	// added on 17-01-2023
	@Column(nullable = true, length = 100)
	private String physical_disturbance_others;

	@Column(nullable = true)
	private Double no_of_trees_proposed_to_be_cut_new;

	@Column(nullable = true)
	private Integer treecutting_timerequired_new;

	@Column
	private Double earthwork_new;

	@Column
	private Integer earthwork_timerequired_new;

	@Column(nullable = true, length = 100)
	private String physical_disturbance_type;
	
	// Changes done by Divyanee for adding a new field on 18052023 [Start]
	@Column
	private Integer physical_disturbance_others_timerequired;
	// Changes done by Divyanee for adding a new field on 18052023 [End]
	
	@Column(nullable = true)
	private Boolean is_active;

	@Column(nullable = true)
	private Boolean is_delete;
	
	//added on 30112022
	@Column(nullable = true, length=100)
	private String division;

	public boolean isIs_any_prior_approval() {
		return is_any_prior_approval;
	}

	public void setIs_any_prior_approval(boolean is_any_prior_approval) {
		this.is_any_prior_approval = is_any_prior_approval;
	}
	
	// Changes done by Divyanee for adding a new field on 18052023 [Start]
	public Integer getPhysical_disturbance_others_timerequired() {
		return physical_disturbance_others_timerequired;
	}

	public void setPhysical_disturbance_others_timerequired(Integer physical_disturbance_others_timerequired) {
		this.physical_disturbance_others_timerequired = physical_disturbance_others_timerequired;
	}
	// Changes done by Divyanee for adding a new field on 18052023 [End]
	
}
