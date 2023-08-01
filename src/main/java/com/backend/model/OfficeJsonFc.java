package com.backend.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class OfficeJsonFc {

	@Id
	private Integer proposal_id;
	private Integer div_office_id;
	private String div_office_name;
	private String div_office_type;
	private Long cir_office_id;
	private String cir_office_name;
	private String cir_office_type;
	private Long state_office_id;
	private String state_office_name;
	private String state_office_type;
	private Long to_office_id;
	private String to_office_name;
	private String to_office_type;
	private Long aig_office_id;
	private String aig_office_name;
	private String aig_office_type;
	private String moefcc_office_id;
	private String moefcc_office_name;
	private String moefcc_office_type;
	private String dis_office_id;
	private String dis_office_name;
	private String dis_office_type;
	private Long iro_to_office_id;
	private String iro_to_office_name;
	private String iro_to_office_type;
	private Long aig_to_office_id;
	private String aig_to_office_name;
	private String aig_to_office_type;
	private Long dig_to_office_id;
	private String dig_to_office_name;
	private String dig_to_office_type;
	private Long iro_office_id;
	private String iro_office_name;
	private String iro_office_type;

}
