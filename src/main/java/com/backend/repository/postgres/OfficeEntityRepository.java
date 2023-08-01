package com.backend.repository.postgres;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.model.OfficeEntity;

public interface OfficeEntityRepository extends JpaRepository<OfficeEntity, Long>{

}
