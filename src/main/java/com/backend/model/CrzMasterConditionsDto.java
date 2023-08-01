package com.backend.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.backend.crz.constant.CrzAppConstant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "crz_master_conditions", schema = "master")
@NoArgsConstructor
@AllArgsConstructor
public class CrzMasterConditionsDto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "header")
	private String header;
	
	@Column(name = "condition")
	private String condition;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "type", nullable = false)
	private CrzAppConstant.Condition_Type type;
	
//	@Column(name = "type", nullable = false)
//	private Integer type;

	@Column(name = "created_by")
	private Integer created_by;

	@Column(name = "created_on")
	private Date created_on;

	@Column(name = "updated_by")
	private Integer updated_by;

	@Column(name = "updated_on")
	private Date updated_on;

	@Column(name = "remarks")
	private String remarks;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "selected")
	private CrzAppConstant.Yes_No selected;
	
	@Column(name = "state_id")
	private Integer stateId;
	
}
