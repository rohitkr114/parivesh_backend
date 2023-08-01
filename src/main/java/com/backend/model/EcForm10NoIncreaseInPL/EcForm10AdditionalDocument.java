package com.backend.model.EcForm10NoIncreaseInPL;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "ec_form10_additional_document", schema = "master")
public class EcForm10AdditionalDocument {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	/*
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ec_form_10_document_id", nullable = true)
	@JsonIgnore
	private EcForm10ProjectDetails ecForm10ProjectDetails;
	*/
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ec_form10_basic_information_id", nullable = true)
	@JsonIgnore
	private EcForm10BasicInformation ecForm10BasicInformation;

	@OneToMany(targetEntity = ECForm10AdditionalDocuments.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "ec_form10_additional_documents_id", referencedColumnName = "id")
	private Set<ECForm10AdditionalDocuments> additionalDocuments = new HashSet<>();
}
