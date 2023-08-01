package com.backend.repository.postgres.Withdrawal;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.model.Withdrawal.WithdrawalRemarks;

public interface WithdrawalRespository extends JpaRepository<WithdrawalRemarks, Integer>{

}
