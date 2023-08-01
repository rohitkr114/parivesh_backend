package com.backend.model.FcFormDPartB;

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

import com.backend.audit.Auditable;
import com.backend.model.FcFormBPartB.FcFormBPartBBasicDetails;
import com.backend.model.FcFormBPartB.FcFormBPartBEnumerationDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "fc_form_d_part_b_tree_details", schema = "master")
public class FcFormDPartBTreeDetails extends Auditable<Integer> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fc_form_d_part_b_id", nullable = false)
	@JsonIgnore
	private FcFormDPartBBasicDetails fcFormDPartBBasicDetails;

	@Column(length = 100)
	private String type_of_field;

	@Column(length = 100)
	private String scientific_name;
	
	@Column(length = 100)
	private String scientific_name_other;

	@Column(length = 100)
	private String local_name;

	@OneToMany(targetEntity = FcFormDPartBEnumerationDetails.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "forestClearanceDEnumerationDetails_id", referencedColumnName = "id")
	private Set<FcFormDPartBEnumerationDetails> fcFormDPartBEnumerationDetails = new HashSet<>();

	private Boolean is_active;

	private Boolean is_deleted;

	public FcFormDPartBTreeDetails() {
		this.is_active = true;
		this.is_deleted = false;
	}

}
