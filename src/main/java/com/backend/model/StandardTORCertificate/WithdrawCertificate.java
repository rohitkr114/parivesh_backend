package com.backend.model.StandardTORCertificate;

import com.backend.audit.Auditable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "withdraw_certificate", schema = "master")
public class WithdrawCertificate extends Auditable<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "application_id", nullable = false)
    private Integer application_id;

    @Column(name = "proposal_no")
    private String proposal_No;

    @Column(name = "status", length = 100, nullable = true)
    private String status;

    @Transient
    @Length(max = 10000000)
    String htmlContent;


    @Column(name = "withdraw_request_date")
    private String withdrawRequestDate;

    @Column(name = "version")
    private Integer version;

    @Column(name = "folder_dir", length = 100000, nullable = true)
    private String folderDir;

    @Column(name = "pdfFilePath", length = 100000, nullable = true)
    private String filePath;

    @Column(name = "bar_code_url", length = 100000, nullable = true)
    private String barCodeUrl;

    @Column(name = "copy_to", columnDefinition = "text", nullable = true)
    private String copyTo;

    @Column(name = "companyname", length = 1000, nullable = true)
    private String companyname;

    @Column(name = "fileNo", length = 100, nullable = true)
    private String fileNo;

    @Column(name = "issuingAuthority", length = 1000, nullable = true)
    private String issuingAuthority;

    @Column(name = "locationOfProject", length = 1000, nullable = true)
    private String locationOfProject;

    @Column(name = "nameOfOrganization", length = 1000, nullable = true)
    private String nameOfOrganization;

    @Column(name = "nameOfProject", length = 1000, nullable = true)
    private String nameOfProject;

    @Column(name = "proponent", length = 1000, nullable = true)
    private String proponent;

    @Column(name = "proponentEmail", length = 1000, nullable = true)
    private String proponentEmail;

    @Column(name = "registeredAddress", length = 1000, nullable = true)
    private String registeredAddress;

    @Column(name = "subject", columnDefinition = "text", nullable = true)
    private String subject;

    @Column(name = "content", columnDefinition = "text", nullable = true)
    private String content;

    @Column(name = "is_active", nullable = true)
    private Boolean is_active;

    @Column(name = "is_delete", nullable = true)
    private Boolean is_delete;

}
