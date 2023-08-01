package com.backend.model.FcFormBPartB;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.backend.audit.Auditable;
import com.backend.model.DocumentDetails;
import com.backend.model.ForestClearancePartB.ForestClearanceBBasicDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "fc_form_b_part_b_component_details", schema = "master")
public class FcFormBPartBComponentDetails extends Auditable<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fc_form_b_part_b_id", nullable = false)
	@JsonIgnore
	private FcFormBPartBBasicDetails fcFormBPartBBasicDetails;

	@Column(length = 25)
	private String component;

	private Double forest_area_proposed;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "revised_map_id", nullable = true)
	private DocumentDetails documentDetails_revised_map;

	@Column(nullable = false)
	private boolean is_active;

	@Column(nullable = false)
	private boolean is_deleted;

	FcFormBPartBComponentDetails() {
		this.is_active = true;
		this.is_deleted = false;
	}
}
