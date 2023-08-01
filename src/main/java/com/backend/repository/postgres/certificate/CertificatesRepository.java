package com.backend.repository.postgres.certificate;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.model.certificate.Certificates;

public interface CertificatesRepository extends JpaRepository<Certificates, Integer> {

}
