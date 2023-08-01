package com.backend.model.EcForm8TransferOfTOR;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.backend.model.DocumentDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;


@Data
@Entity
@Table(name = "ec_form8_document_attached", schema = "master")
public class EcForm8DocumentAttached {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "ec_form_8_document_id", nullable = true)
  @JsonIgnore
  private EcForm8TransferOfTOR ecForm8TransferOfTOR;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "objection_from_transferor_copy")
  private DocumentDetails objection_from_transferor_copy ;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "undertaking_by_transferee_copy")
  private DocumentDetails undertaking_by_transferee_copy ;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "transfer_from_competent_copy")
  private DocumentDetails transfer_from_competent_copy ;

}
