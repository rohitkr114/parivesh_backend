package com.backend.model;

import com.backend.audit.Auditable;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="revoke_proposal",schema = "authority")
public class RevokeProposal extends Auditable<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "proposal_no",nullable = false)
    private String proposalNo;

    @Column(columnDefinition = "TEXT")
    private String reason;

    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "document_id", nullable = true)
    private DocumentDetails document;

    @Column(name = "is_reprocessed",nullable = true)
    private Boolean isReprocessed=false;

    @Column(name="is_active")
    private Boolean isActive=true;

    @Column(name="is_deleted")
    private Boolean isDeleted=false;


}
