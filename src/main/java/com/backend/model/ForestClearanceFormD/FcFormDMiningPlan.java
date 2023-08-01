package com.backend.model.ForestClearanceFormD;

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
import com.backend.model.ForestClearanceFormC.FcFormC;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name="fc_form_d_mining_plan",schema = "master")
public class FcFormDMiningPlan extends Auditable<Integer>{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(nullable = true)
	private Boolean is_mining_approved;

	@Column(nullable = true)
    private String mining_nature;

	@Column(nullable = true)
    private Date date_of_approval;

	@Column(nullable = true,length = 50)
    private String approval_authority;

	@Column(nullable = true)
    private Integer life_of_mine;

    @Column(name="dpr_details",nullable = true,length=1000)
    private String dpr_details;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "dpr_copy_id", nullable = true)
	private DocumentDetails dpr_copy;
    
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "copy_approved_mining_plan_id", nullable = true)
	private DocumentDetails copy_approved_mining_plan;
    
    @OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fc_form_d_id", nullable = true)
	@JsonIgnore
	private FcFormD fcFormD;

    @Column(nullable = true)
    private Boolean is_active;
    
    @Column(nullable = true)
    private Boolean is_delete;
    
    public FcFormDMiningPlan() {
    	this.is_active=true;
    	this.is_delete=false;
    }

	public FcFormDMiningPlan(Integer id, Boolean is_mining_approved, String mining_nature, Date date_of_approval,
			String approval_authority, Integer life_of_mine, String dpr_details) {
		super();
		this.id = id;
		this.is_mining_approved = is_mining_approved;
		this.mining_nature = mining_nature;
		this.date_of_approval = date_of_approval;
		this.approval_authority = approval_authority;
		this.life_of_mine = life_of_mine;
		this.dpr_details = dpr_details;
	}
	
	public FcFormDMiningPlan(Integer id) {
		this.id=id;
	}
    
    
}
