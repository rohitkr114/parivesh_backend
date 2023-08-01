package com.backend.model.EcForm9TransferOfEC;

import com.backend.audit.Auditable;
import com.backend.model.DocumentDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name = "ec_form9_litigation_against_project", schema = "master")
public class EcForm9LitigationAgainstProject extends Auditable<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "court_or_tribunal")
    private String court_or_tribunal;

    @Column(name = "court_or_tribunal_others")
    private String court_or_tribunal_others;

    @Column(name = "name_of_specific_court")
    private String name_of_specific_court;

    @Column(name = "is_case_category")
    private String is_case_category;

    @Column(name = "is_case_category_others")
    private String is_case_category_others;

    @Column(name = "is_status_of_court_case")
    private String is_status_of_court_case;

    @Column(name = "order_or_direction_of_court")
    private String order_or_direction_of_court;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "upload_court_order_id", nullable = true)
    private DocumentDetails upload_court_order_id;










}
