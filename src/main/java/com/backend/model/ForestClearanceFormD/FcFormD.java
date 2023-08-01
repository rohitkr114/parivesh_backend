package com.backend.model.ForestClearanceFormD;

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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Where;

import com.backend.audit.Auditable;
import com.backend.model.Clearence;
import com.backend.model.CommonFormDetail;
import com.backend.model.ForestClearanceProposedDiversions;
import com.backend.model.ProponentApplications;
import com.backend.model.ForestClearanceFormCPartB.FcFormCPartBDistrictWise;
import com.backend.model.ForestClearanceFormCPartB.FcFormCPartBLegalStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Data;

@Data
@Entity
@Table(name = "fc_form_d",schema = "master")
public class FcFormD extends Auditable<Integer> implements Clearence{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(length = 255)
	private String project_category;

	@Column(nullable = true)
	private Integer state_code;
	
	@Column(nullable = true,length = 50)
	private String state;

	@Column(length = 1000)
	private String proposal_justification;

	@Column(length = 50)
	private String proposal_no;
	
	@OneToMany(mappedBy = "fcFormD", cascade = CascadeType.ALL)
	@Fetch(FetchMode.SELECT)
	@Where(clause = "is_deleted='false'")
	List<FcFormDProposedDiversion> fcFormDProposedDiversions = new ArrayList<>();
	
	/*
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "fcFormD_id", nullable = true)
	@Where(clause = "is_deleted ='false'")
	private Set<FcFormDLegalStatus> fcFormDLegalStatus = new HashSet<>();
	*/

	@OneToMany(mappedBy = "fcFormD")
	@Where(clause = "is_deleted=false")
	private List<FcFormDLegalStatus> fcFormDLegalStatus=new ArrayList<FcFormDLegalStatus>();
	
	@OneToMany(mappedBy = "fcFormCPartB")
	@Where(clause = "is_deleted ='false'")
	private List<FcFormCPartBDistrictWise> fcFormCPartBDistrictWises = new ArrayList<>();

	@OneToOne(mappedBy = "fcFormD")
	private FcFormDMiningPlan fcFormDMiningPlan;

	@OneToOne(mappedBy = "fcFormD")
	private FcFormDUndertaking fcFormDUndertaking;
	
	@OneToOne(mappedBy = "fcFormD")
	private FcFormDProposedLand fcFormDProposedLand;

	@OneToOne(targetEntity = CommonFormDetail.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "caf_id", nullable = true)
	private CommonFormDetail commonFormDetail;

	@Transient
	@JsonProperty(access = Access.READ_ONLY)
	private ProponentApplications proponentApplications;

	@Column(nullable = true)
	private Integer parent_id;

	private Boolean is_active;

	private Boolean is_delete;

	public FcFormD() {
		this.is_active = true;
		this.is_delete = false;
	}
	
	public FcFormD(Integer id,String proposal_no) {
		this.id=id;
		this.proposal_no=proposal_no;
	}

	public FcFormD(Integer id, String project_category, Integer state_code,String state,String proposal_justification,
			String proposal_no) {
		this.id = id;
		this.project_category = project_category;
		this.state_code = state_code;
		this.state=state;
		this.proposal_justification = proposal_justification;
		this.proposal_no = proposal_no;
	}
	
	

}
