package com.backend.crz.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.model.CrzMasterStatusDto;

public interface CrzMasterStatusRepository extends JpaRepository<CrzMasterStatusDto, Integer> {

}
