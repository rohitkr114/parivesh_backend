package com.backend.model.EcForm6Part1;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ec_form6_basic_detail_minor_activity", schema = "master")
public class Form6BasicDetailsMinorActivity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
    
	
	@Column(name="subActivityId")
	private String subActivityId;
	
	@Column(name="activity_type")
	private String activity_type;
	
	@OnDelete(action = OnDeleteAction.CASCADE)
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)	
	@JoinColumn(name="activityId", referencedColumnName = "id")  // reference column : local , name="foreign column
	List<Form6BasicDetailsMinorThreshold> threshold; 
	
	
	@Column(name="basic_detail_id")
	private Integer basic_detail_id;

}
