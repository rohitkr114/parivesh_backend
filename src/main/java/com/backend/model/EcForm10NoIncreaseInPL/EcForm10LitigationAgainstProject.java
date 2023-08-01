package com.backend.model.EcForm10NoIncreaseInPL;

import com.backend.audit.Auditable;
import com.backend.model.DocumentDetails;
import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name = "ec_form10_litigation_against_project", schema = "master")
public class EcForm10LitigationAgainstProject extends Auditable<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "court_or_tribunal")
    private String court_or_tribunal;

    @Column(name = "name_of_specific_court")
    private String name_of_specific_court;

    @Column(name = "is_case_category")
    private String is_case_category;

    @Column(name = "is_status_of_court_case",length = 500)
    private String is_status_of_court_case;

    @Column(name = "order_or_direction_of_court",length = 500)
    private String order_or_direction_of_court;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "upload_court_order_id", nullable = true)
    private DocumentDetails upload_court_order_id;

}
