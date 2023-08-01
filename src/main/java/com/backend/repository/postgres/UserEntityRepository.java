package com.backend.repository.postgres;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.ProjectProponentEntity;
import com.backend.model.ProjectProponentGovernment;
import com.google.common.base.Optional;

public interface UserEntityRepository extends JpaRepository<ProjectProponentEntity, Integer>{

	@Query(value = "Select p from ProjectProponentEntity p where pan_no = cast(?1 as text)")
	public Optional<ProjectProponentEntity> findRecordByPan(String pan_no);
	
	@Query(value = "Select p from ProjectProponentEntity p where cin_no = cast(?1 as text)")
	public Optional<ProjectProponentEntity> findRecordByCIN(String cin_no);
	
	@Query(value = "Select p from ProjectProponentEntity p where email = cast(?1 as text)")
	public Optional<ProjectProponentEntity> findRecordByEmail(String email);
	
	@Query(value = "Select p from ProjectProponentEntity p where cin_no = cast(?1 as text)")
	public Optional<ProjectProponentEntity> findRecordByCin(String cin_no);
	
	@Query(value = "Select p from ProjectProponentEntity p where name_of_Entity = cast(?1 as text) and state is null")
	public Optional<ProjectProponentEntity> findRecordByEntity(String entity);
	
	@Query(value = "Select p from ProjectProponentEntity p where name_of_Entity = cast(?1 as text) and state = cast(?2 as text)")
	public Optional<ProjectProponentEntity> findRecordByEntityAndState(String entity, String state);
}
