package com.backend.repository.postgres;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.ClearanceAction;

public interface ClearanceActionRepository extends JpaRepository<ClearanceAction, Long> {

	@Query("Select ca from ClearanceAction ca where ca.applications.id = ?1")
	List<ClearanceAction> findAllByApplicationId(Integer application_id);

}
