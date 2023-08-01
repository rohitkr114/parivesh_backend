package com.backend.model.EcForm6V2;

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
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name="ec_form_6_unit_details_v2",schema="master")
public class EcForm6UnitDetailsV2 extends Auditable<Integer> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(length=255)
	private String ec;
	
	@Column(length=255)
	private String cte;
	
	@Column(length=255)
	private String cto;
	
	@Column(name="unimplemented_units",length=255,nullable=true)
	private String unimplementedUnits;
	
	@Column(length=1000,nullable=true)
	private String remarks;
	
	@Column(name="is_active")
	private Boolean isActive=true;
	
	@Column(name="is_deleted")
	private Boolean isDeleted=false;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ec_form6_id", nullable = false)
	@JsonIgnore
	private EcForm6ProjectDetailsV2 ecForm6;
}
