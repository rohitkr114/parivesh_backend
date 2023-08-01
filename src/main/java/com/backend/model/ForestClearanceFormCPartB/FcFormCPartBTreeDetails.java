package com.backend.model.ForestClearanceFormCPartB;

import java.util.HashSet;
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

import org.hibernate.annotations.Where;

import com.backend.audit.Auditable;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name="fc_form_c_part_b_tree_details",schema = "master")
public class FcFormCPartBTreeDetails extends Auditable<Integer>{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fc_form_c_part_b_id", nullable = false)
	@JsonIgnore
	private FcFormCPartB fcFormCPartB;
	
	@Column(length = 100,nullable = true)
	private String type_of_field;

	@Column(length = 100,nullable = true)
	private String scientific_name;
	
	@Column(length = 100,nullable = true)
	private String local_name;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "fcFormCPartBEnumerationDetails_id", nullable = true)
	@Where(clause = "is_deleted ='false'")
	private Set<FcFormCPartBEnumerationDetails> fcFormCPartBEnumerationDetails = new HashSet<>();

	private Boolean is_active;
	
	private Boolean is_deleted;
	
	public FcFormCPartBTreeDetails() {
		this.is_active=true;
		this.is_deleted=false;
	}
}
