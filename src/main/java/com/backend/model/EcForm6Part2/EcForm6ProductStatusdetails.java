package com.backend.model.EcForm6Part2;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "ec_form6_impl_product_status_detail", schema = "master")
public class EcForm6ProductStatusdetails extends Auditable<Integer> implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;


	@Column(name = "project_impl_status_unit_granted_ec", length = 100, nullable = true)
	private String ec_granted;

	@Column(name = "project_impl_status_unit_granted_cte", length = 100, nullable = true)
	private String cte_granted;

	@Column(name = "project_impl_status_unit_granted_cto", length = 100, nullable = true)
	private String cto_granted;

	@Column(name = "project_impl_status_unimpl_unit_details", length = 100, nullable = true)
	private String unimplemented_details;

	@Column(name = "project_impl_status_remarks", length = 100, nullable = true)
	private String remarks;

	
}
