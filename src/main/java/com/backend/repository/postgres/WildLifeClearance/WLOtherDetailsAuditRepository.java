package com.backend.repository.postgres.WildLifeClearance;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.model.WildLifeClearance.WLOtherDetails;
import com.backend.model.WildLifeClearance.WLOtherDetailsAudit;

public interface WLOtherDetailsAuditRepository extends JpaRepository<WLOtherDetailsAudit, Integer>{

}
