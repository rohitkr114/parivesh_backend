package com.backend.repository.postgres;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.model.ForestClearanceSubmergedArea;

public interface FcSubmergedAreaRepository extends JpaRepository<ForestClearanceSubmergedArea, Integer>{

}
