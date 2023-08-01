package com.backend.model.EcForm10NoIncreaseInPL;

import com.backend.audit.Auditable;
import com.backend.model.DocumentDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "ec_form10_consent_under_air_details", schema = "master")
public class EcForm10ConsentUnderAirDetails extends Auditable<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ec_form10_basic_information_id", nullable=false)
    @JsonIgnore
    private EcForm10BasicInformation ecForm10BasicInformation;

    private String consent_no;

    private Date date;

    private Date valid_upto;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "copy_consent_order_id", nullable = true)
    private DocumentDetails copy_consent_order;

    private Boolean is_active=true;

    private Boolean is_delete=false;

}
