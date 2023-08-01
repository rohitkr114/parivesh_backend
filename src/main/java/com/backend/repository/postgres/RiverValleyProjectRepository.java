package com.backend.repository.postgres;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.model.RiverValleyProject;

public interface RiverValleyProjectRepository extends JpaRepository<RiverValleyProject, Integer> {

	RiverValleyProject findRiverValleyProjectByCafId(Integer cafId);

}
