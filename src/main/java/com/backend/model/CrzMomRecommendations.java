package com.backend.model;

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
@Entity
@Table(name="crz_mom_recommendation", schema = "master")
@NoArgsConstructor
@AllArgsConstructor
public class CrzMomRecommendations {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	
	@Column (name= "status_id")
	private Integer status_id;
	
	@Column(name = "label_name")
	private String label_name;
	
	@Column(name = "field_name")
	private String field_name;

}
