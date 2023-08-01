package com.backend.model;

import com.backend.audit.Auditable;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="fc_recommended_area",schema="master")
public class FcRecommendedArea extends Auditable<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Integer fc_id;

    private Double area;


}
