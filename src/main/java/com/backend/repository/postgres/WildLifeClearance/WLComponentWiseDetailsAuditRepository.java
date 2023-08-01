package com.backend.repository.postgres.WildLifeClearance;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.model.WildLifeClearance.WLComponentWiseDetails;
import com.backend.model.WildLifeClearance.WLComponentWiseDetailsAudit;

public interface WLComponentWiseDetailsAuditRepository extends JpaRepository<WLComponentWiseDetailsAudit, Integer>{

}
