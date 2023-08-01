package com.backend.model.EcForm10NoIncreaseInPL;

import com.backend.audit.Auditable;
import com.backend.model.DocumentDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "ec_form10_authorization_hazardous_details", schema = "master")
public class EcForm10AuthorizationHazardousDetails extends Auditable<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ec_form10_basic_information_id", nullable=false)
    @JsonIgnore
    private EcForm10BasicInformation ecForm10BasicInformation;

    private String authorization_no;

    private Date date_hazardous;

    private Date valid_upto_date_hazardous;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "copy_authorization_id", nullable = true)
    private DocumentDetails copy_authorization;

    private Boolean is_active=true;

    private Boolean is_delete=false;
}
