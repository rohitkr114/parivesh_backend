package com.backend.model.EcForm8TransferOfTOR;


import com.backend.model.DocumentDetails;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ec_form8_additional_documents", schema = "master")
public class ECForm8AdditionalDocuments {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "document_name", nullable = true)
    private String document_name;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "doc_upload")
    private DocumentDetails upload_additional_information;
}
