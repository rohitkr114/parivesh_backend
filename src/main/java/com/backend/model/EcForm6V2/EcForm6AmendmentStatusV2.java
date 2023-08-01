package com.backend.model.EcForm6V2;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.backend.audit.Auditable;

import lombok.Data;

@Data
@Entity
@Table(name="ec_form6_amendment_status_v2",schema="master")
public class EcForm6AmendmentStatusV2 extends Auditable<Integer> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name="amendment_sought_for",nullable=true)
	private String amendmentSoughtFor;
	
	@Column(name="approved_ec_reference",nullable=true)
	private String approvedEcReference;
	
	@Column(name="description_as_per_approved_ec",nullable=true,length = 1000)
	private String descriptionAsPerApprovedEc;
	
	@Column(name="amendment_required",nullable=true)
	private String amendmentRequired;
	
	@Column(name="remarks",nullable=true,length=2000)
	private String remarks;
	
	@Column(name="reason_other_specify",nullable=true,length=5000)
	private String reasonOthersSpecify;
	
	@Column(name="is_active")
	private Boolean isActive=true;
	
	@Column(name="is_deleted")
	private Boolean isDeleted=false;

}
