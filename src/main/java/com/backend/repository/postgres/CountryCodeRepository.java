package com.backend.repository.postgres;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.backend.model.CountryCode;


@Repository
public interface CountryCodeRepository extends JpaRepository<CountryCode, Integer>{

	@Query("SELECT new CountryCode(c.code,c.name) from CountryCode c where c.is_active='true' and c.is_deleted='false' order by name")
    List<CountryCode> getAllCountryCodes();
	
	/*@Query("SELECT c from CountryCode c where c.is_active='true' and c.is_deleted='false' and c.id=?1 order by name")
    CountryCode getCountryCodeByCode(Integer id);*/
	
}
