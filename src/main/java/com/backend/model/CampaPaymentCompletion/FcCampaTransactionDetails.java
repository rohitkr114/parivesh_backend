package com.backend.model.CampaPaymentCompletion;

import com.backend.audit.Auditable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "fc_campa_transaction_details", schema = "authority")
public class FcCampaTransactionDetails extends Auditable<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "fc_campa_payment_completion_id", nullable = false)
    @JsonIgnore
    private FcCampaPaymentCompletionDetails fcCampaPaymentCompletionDetails;

    @Column(nullable = true)
    private String transaction_id;

    @Column(nullable = true)
    private String transaction_id_to;

    private Boolean transaction_verified;

    private String transaction_status;

    private String payment_mode;

    private String transaction_date;

    @Column(nullable = true)
    private Double amount;

    private String bank_name;

    private String branch_name;

    private String account_name;

    private Boolean is_active;

    private Boolean is_deleted;

    private String ifsc_code;

    public FcCampaTransactionDetails() {
        this.is_active = true;
        this.is_deleted = false;
        this.transaction_verified = false;
    }

}
