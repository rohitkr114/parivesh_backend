package com.backend.repository.postgres;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.model.EcStatus;

public interface EcImplementationStatusRepository extends JpaRepository<EcStatus, Integer> {

}
