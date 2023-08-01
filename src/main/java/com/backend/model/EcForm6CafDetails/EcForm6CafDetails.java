package com.backend.model.EcForm6CafDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;


@Data
@Entity
@Table(name="ec_form6_caf_details", schema="master")
public class EcForm6CafDetails {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="parent_caf_id", nullable=false)
	Integer parentCaf;
	
	@Column(name="new_caf_id", nullable=false)
	Integer newCaf;
	
	@Column(name="status", nullable=false)
	Integer status;  //0= draft,1=summited
	
	@Column(name="ecId", nullable=false)
	Integer ecId;  //0= draft,1=summited
	
}
