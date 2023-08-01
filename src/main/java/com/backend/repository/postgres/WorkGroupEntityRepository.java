package com.backend.repository.postgres;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.model.WorkGroupEntity;

public interface WorkGroupEntityRepository extends JpaRepository<WorkGroupEntity, Long>{

}
