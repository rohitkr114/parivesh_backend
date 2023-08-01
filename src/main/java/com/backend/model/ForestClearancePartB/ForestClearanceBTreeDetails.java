package com.backend.model.ForestClearancePartB;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.backend.audit.Auditable;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name="forest_clearance_part_b_tree_details",schema = "master")
public class ForestClearanceBTreeDetails extends Auditable<Integer>{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fc_partb_id", nullable = true)
	@JsonIgnore
	private ForestClearanceBBasicDetails forestClearanceBBasicDetails;
	
	@Column(length = 100)
	private String type_of_field;

	@Column(length = 100)
	private String scientific_name;
	
	@Column(length = 100)
	private String local_name;
	
	@OneToMany(targetEntity = ForestClearanceBEnumerationDetails.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "forestClearanceBEnumerationDetails_id", referencedColumnName = "id")
	private Set<ForestClearanceBEnumerationDetails> forestClearanceBEnumerationDetails = new HashSet<>();

	private Boolean is_active;
	
	private Boolean is_deleted;
	
	public ForestClearanceBTreeDetails() {
		this.is_active=true;
		this.is_deleted=false;
	}
}
