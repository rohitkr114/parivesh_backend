package com.backend.model.FcFormDPartB;

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
@Table(name = "fc_form_d_part_b_enumeration_details", schema = "master")
public class FcFormDPartBEnumerationDetails extends Auditable<Integer>  {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(length = 50)
	private String girth;

	@Column(length = 12)
	private Integer number_of_trees;

	@Column(length = 50, nullable = true)
	private String girth_frl_2;

	@Column(length = 12, nullable = true)
	private Integer number_of_trees_frl_2;

	@Column(length = 50, nullable = true)
	private String girth_frl_4;

	@Column(length = 12, nullable = true)
	private Integer number_of_trees_frl_4;
	private Boolean is_active;

	private Boolean is_deleted;

	public FcFormDPartBEnumerationDetails() {
		this.is_active = true;
		this.is_deleted = false;
	}


}
	