package com.backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.backend.audit.Auditable;

import lombok.Data;

@Data
@Entity
@Table(name="expansion_form_data", schema="master")
public class ExpansionFormData extends Auditable<Integer> {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(name="application_id", nullable=true)	
	private Integer applicationId;
	
	@Column(name="step", nullable=true)
	private Integer step;
	
	@Lob
	@Column(name="attributes", nullable=true)
	private String attributes;
	
}
