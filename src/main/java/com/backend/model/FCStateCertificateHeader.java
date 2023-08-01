package com.backend.model;

import com.backend.audit.Auditable;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "fc_state_certificate_header", schema = "master")
public class FCStateCertificateHeader extends Auditable<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = true)
    private Integer state_code;

    @Column(nullable = true)
    private String header_details;

    @Column(nullable = true)
    private Boolean is_active = true;

    @Column(nullable = true)
    private Boolean is_delete = false;

}
