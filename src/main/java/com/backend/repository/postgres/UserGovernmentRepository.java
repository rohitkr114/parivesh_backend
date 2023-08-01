package com.backend.repository.postgres;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.ProjectProponentGovernment;
import com.google.common.base.Optional;

public interface UserGovernmentRepository extends JpaRepository<ProjectProponentGovernment, Integer>{

	@Query(value = "Select p from ProjectProponentGovernment p where pan_no = cast(?1 as text)")
	public Optional<ProjectProponentGovernment> findRecordByPan(String pan_no);
	
	@Query(value = "Select p from ProjectProponentGovernment p where cin_no = cast(?1 as text)")
	public Optional<ProjectProponentGovernment> findRecordByCIN(String cin);
	
	@Query(value = "Select p from ProjectProponentGovernment p where email = cast(?1 as text)")
	public Optional<ProjectProponentGovernment> findRecordByEmail(String email);
	
	@Query(value = "Select p from ProjectProponentGovernment p where cin_no = cast(?1 as text)")
	public Optional<ProjectProponentGovernment> findRecordByCin(String cin_no);
	
	@Query(value = "Select p from ProjectProponentGovernment p where name_of_Entity = cast(?1 as text) and state is null")
	public Optional<ProjectProponentGovernment> findRecordByEntity(String entity);
	
	@Query(value = "Select p from ProjectProponentGovernment p where name_of_Entity = cast(?1 as text) and state = cast(?2 as text)")
	public Optional<ProjectProponentGovernment> findRecordByEntityAndState(String entity, String state);
}
