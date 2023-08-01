package com.backend.model.ProposalWithdraw;

import com.backend.audit.Auditable;
import com.backend.model.DocumentDetails;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "proposal_withdraw_request", schema = "master")
public class ProposalWithdrawRequest extends Auditable<Integer> {
    
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "proposal_no", nullable = false)
    private String proposal_no;

    @Column(name = "application_id", nullable = false)
    private Integer application_id;

    @Column(name = "reason", nullable = false)
    private String reason;

    @Column(name = "status", nullable = false)
    private String status;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "document_id", nullable = true)
	private DocumentDetails document;

    @Column(name = "is_active", nullable = true)
    private Boolean is_active;

    @Column(name = "is_delete", nullable = true)
    private Boolean is_delete;

    private ProposalWithdrawRequest() {
        this.is_active = true;
        this.is_delete = false;
    }

}
