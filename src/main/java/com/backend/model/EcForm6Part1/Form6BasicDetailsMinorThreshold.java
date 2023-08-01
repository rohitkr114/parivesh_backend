package com.backend.model.EcForm6Part1;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ec_form6_basic_detail_minor_threshold", schema = "master")
public class Form6BasicDetailsMinorThreshold {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="threshold")
	private String threshold;
	
	@Column(name="unit")
	private String unit;
	
	@Column(name="activityId")
	private Integer activityId;

}
