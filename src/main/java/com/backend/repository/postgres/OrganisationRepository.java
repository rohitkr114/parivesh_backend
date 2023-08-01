package com.backend.repository.postgres;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import com.backend.model.Organisation;

@Repository
public interface OrganisationRepository extends JpaRepository<Organisation, Integer>{

	@Query("select e from Organisation e where e.email LIKE %?1%")
	Organisation getOrganisationByEmail(String email);
	
	@Query("select e from Organisation e where e.email=?1")
	Organisation getOrganisationByEmailForMigration(String email);
}
