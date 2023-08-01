package com.backend.model.EcForm6Part2;

import java.io.Serializable;

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
import com.backend.model.CommonFormDetail;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ec_form6_impl_product_amendment_required_approved", schema = "master")
public class EcForm6ProductAmendmentRequiredApproved extends Auditable<Integer> implements Serializable {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "amendment_required_approved_ec_ref", length = 100, nullable = true)
	private String reference_approved_ec;
	
	@Column(name = "amendment_required_approved_ec_desc", length = 100, nullable = true)
	private String desc_per_approved;
	
	@Column(name = "amendment_required_approved_ec_desc_proposal", length = 100, nullable = true)
	private String desc_proposel;
	
	@Column(name = "amendment_required_approved_ec_remark", length = 100, nullable = true)
	private String remark_other_amendment;
	
}
