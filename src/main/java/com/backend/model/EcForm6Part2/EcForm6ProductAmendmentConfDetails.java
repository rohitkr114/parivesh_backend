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
@Table(name = "ec_form6_impl_product_amendment_conf_details", schema = "master")
public class EcForm6ProductAmendmentConfDetails extends Auditable<Integer> implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
		
	@Column(name = "amendment_conf_details_plant", length = 100, nullable = true)
	private String plant_equipment_facility;
	
	@Column(name = "amendment_conf_details_ext_conf", length = 100, nullable = true)
	private String exesting_configuration;
	
	@Column(name = "amendment_conf_details_plant_prop_conf", length = 100, nullable = true)
	private String proposed_configuration;
	
	@Column(name = "amendment_conf_details_plant_final_conf", length = 100, nullable = true)
	private String final_configuration;
	
	@Column(name = "amendment_conf_details_plant_remark", length = 100, nullable = true)
	private String unit_other_remark;
}
