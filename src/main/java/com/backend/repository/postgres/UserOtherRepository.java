package com.backend.repository.postgres;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.ProjectProponentOther;
import com.google.common.base.Optional;

public interface UserOtherRepository extends JpaRepository<ProjectProponentOther, Integer>{

	@Query(value = "Select p from ProjectProponentOther p where pan_no = cast(?1 as text)")
	public Optional<ProjectProponentOther> findRecordByPan(String pan_no);
	
	@Query(value = "Select p from ProjectProponentOther p where email = cast(?1 as text)")
	public Optional<ProjectProponentOther> findRecordByEmail(String email);
	
	@Query(value = "Select p from ProjectProponentOther p where cin_no = cast(?1 as text)")
	public Optional<ProjectProponentOther> findRecordByCin(String cin_no);
	
	@Query(value = "Select p from ProjectProponentOther p where name_of_Entity = cast(?1 as text) and state is null")
	public Optional<ProjectProponentOther> findRecordByEntity(String entity);
	
	@Query(value = "Select p from ProjectProponentOther p where name_of_Entity = cast(?1 as text) and state = cast(?2 as text)")
	public Optional<ProjectProponentOther> findRecordByEntityAndState(String entity, String state);
}
