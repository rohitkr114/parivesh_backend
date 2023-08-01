package com.backend.dto;

public interface PaymentNotificationDto {

    Integer getCreated_by();

    String getTransaction_id();

    String getTransaction_date();

    String getAmount();

    String getBank_name();

    String getBranch_name();

    String getAccount_name();

    String getPayment_mode();

    String getProposal_no();
}
