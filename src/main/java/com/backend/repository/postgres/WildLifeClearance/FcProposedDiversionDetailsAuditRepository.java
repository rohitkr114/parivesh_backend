package com.backend.repository.postgres.WildLifeClearance;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.model.FcProposedDiversionsDetailsAudit;

public interface FcProposedDiversionDetailsAuditRepository extends JpaRepository<FcProposedDiversionsDetailsAudit, Integer> {

}
