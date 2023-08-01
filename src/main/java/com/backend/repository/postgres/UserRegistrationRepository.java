package com.backend.repository.postgres;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import com.backend.model.User;
import com.google.common.base.Optional;

@NoRepositoryBean
public interface UserRegistrationRepository<T extends User> extends JpaRepository<T, Integer> {

	@Query(value = "Select p from #{#entityName} p where pan_no = cast(?1 as text)")
	public Optional<T> findRecordByPan(String pan_no);
	
	@Query(value = "Select p from #{#entityName} p where email = cast(?1 as text)")
	public Optional<T> findRecordByEmail(String email);
	
	@Query(value = "Select p from #{#entityName} p where cin_no = cast(?1 as text)")
	public Optional<T> findRecordByCin(String cin_no);
	
	@Query(value = "Select p from #{#entityName} p where name_of_Entity = cast(?1 as text) and state is null")
	public Optional<T> findRecordByEntity(String entity);
	
	@Query(value = "Select p from #{#entityName} p where name_of_Entity = cast(?1 as text) and state = cast(?2 as text)")
	public Optional<T> findRecordByEntityAndState(String entity, String state);
	
}
