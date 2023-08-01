package com.backend.model.EcForm8TransferOfTOR;

import com.backend.model.DocumentDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "ec_form8_additional_document", schema = "master")
public class EcForm8AdditionalDocument {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;


  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "ec_form_8_document_id", nullable = true)
  @JsonIgnore
  private EcForm8TransferOfTOR ecForm8TransferOfTOR;


  @OneToMany(targetEntity = ECForm8AdditionalDocuments.class, cascade = CascadeType.ALL)
  @JoinColumn(name = "ec_form8_additional_documents_id", referencedColumnName = "id")
  private Set<ECForm8AdditionalDocuments> additionalDocuments = new HashSet<>();
}
