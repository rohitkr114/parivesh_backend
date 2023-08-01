package com.backend.repository.postgres.CrzTransfer;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.model.CrzTransfer.CrzTransferDetails;

public interface CrzTransferDetailsRepository extends JpaRepository<CrzTransferDetails, Integer>{

}
