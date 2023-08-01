package com.backend.model.ForestClearanceFormC;

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
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name="fc_form_c_proposed_land_n",schema = "master")
public class FcFormCProposedLandN extends Auditable<Integer>{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(nullable = true)
	private Boolean is_proposal_seeking_prior_approval;
	
	@Column(nullable = true)
	private String status_of_proposal;
	
	@Column(name = "proposal_no", nullable = true)
	private String proposal_no;

	/* To the Add Row
	 * @Column(nullable = true) private String legal_status_of_forest_land;
	 * 
	 * @Column(nullable = true) private Double total_forest_land;
	 */
	
	/* To the Base Model
	 * 
	@Column(nullable = true)
	private Integer total_proposed_diversion_period;

	@Column(nullable = true, length = 500)
	private String legal_status_of_forest_land_other;
	
	@Column(nullable = true)
	private Double total_non_forest_land;

	@Column(nullable = true)
	private Double total_area_of_kmls;
	
	@Column(nullable = true)
	private Double legal_status_forest_land_area;

	*/
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fc_form_c_id", nullable = true)
	@JsonIgnore
	private FcFormC fcFormC;
	
	@Column(nullable = true)
	private Boolean is_active;

	@Column(nullable = true)
	private Boolean is_deleted;

	public FcFormCProposedLandN() {
		this.is_active=true;
		this.is_deleted=false;
	}

	public FcFormCProposedLandN(Integer id) {
		this.id=id;
	}

	public FcFormCProposedLandN(Integer id,Boolean is_proposal_seeking_prior_approval,String status_of_proposal,
			String proposal_no) {
		super();
		this.id = id;
		this.is_proposal_seeking_prior_approval=is_proposal_seeking_prior_approval;
		this.status_of_proposal=status_of_proposal;
		this.proposal_no=proposal_no;
	}

}
