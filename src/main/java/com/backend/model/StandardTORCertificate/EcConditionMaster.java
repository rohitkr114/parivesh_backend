package com.backend.model.StandardTORCertificate;

import com.backend.audit.Auditable;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "ec_condition_master", schema = "ua")
public class EcConditionMaster extends Auditable<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "isactive")
    private boolean isActive;
    @Column(name = "condition_heading")
    private String conditionHeading;
    @Column(name = "condition")
    private String condition;
    @Column(name = "heading")
    private String heading;
    @Column(name = "sector_name")
    private String sectorName;
    @Column(name = "type_proposal")
    private String typeProposal;
    @Column(name = "activity_id")
    private Integer activityId;
    @Column(name = "ec_condition_id")
    private Long ecConditionId;
    @Column(name = "Activity name")
    private String ActivityName;
    @Column(name = "Sector Name")
    private String sector_name;
    @Column(name = "conditions")
    private String conditions;
    @Column(name = "folder_dir", length = 100000, nullable = true)
    private String folderDir;
    @Column(name = "pdfFilePath", length = 100000, nullable = true)
    private String filePath;
    @Column(name = "bar_code_url", length = 100000, nullable = true)
    private String barCodeUrl;
}
