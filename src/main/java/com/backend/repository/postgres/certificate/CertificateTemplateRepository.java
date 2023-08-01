package com.backend.repository.postgres.certificate;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.backend.model.certificate.CertificateTemplate;

public interface CertificateTemplateRepository extends JpaRepository<CertificateTemplate, Integer> {

	@Modifying
	@Query(value = "update CertificateTemplate set is_delete='true' , is_active='false'  where id=?1", nativeQuery = true)
	Integer deleteCertificateTemplate(Integer id);

	@Query(value = " SELECT * from master.certificate_template "
			+ " where application_id=COALESCE(CAST(:application_id AS VARCHAR) ,application_id) "
			+ " and category=COALESCE(CAST(:category AS VARCHAR),category) "
			+ " and sub_category=COALESCE(CAST(:sub_category AS VARCHAR),sub_category); ", nativeQuery = true)
	List<CertificateTemplate> getCertificateTemplate(@Param(value = "application_id") String application_id,
			@Param(value = "category") String category, @Param(value = "sub_category") String sub_category);

}
