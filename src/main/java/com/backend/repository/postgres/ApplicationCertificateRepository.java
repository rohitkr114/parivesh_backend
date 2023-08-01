package com.backend.repository.postgres;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.backend.model.ApplicationCertificate;

public interface ApplicationCertificateRepository extends JpaRepository<ApplicationCertificate,Integer> {
	
	@Query("select ac from ApplicationCertificate ac where ac.application_id=:application_id and ac.certificate_type=:certificate_type ")
	public Optional<ApplicationCertificate> getCertificateByIds(@Param(value = "application_id") Integer  application_id,@Param(value = "certificate_type") String certificate_type);
}
