package com.backend.model.EcForm8TransferOfTOR;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Entity
@Table(name = "ec_form8_threshold", schema = "master")
public class EcForm8Threshold {

  @Id
  @GeneratedValue
  private Integer id;

  @Column(name = "threshold")
  private Integer threshold;

  @Column(name = "unit")
  private String unit;

}
