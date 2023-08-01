package com.backend.repository.postgres.WildLifeClearance;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.model.WildLifeClearance.WLUndertaking;
import com.backend.model.WildLifeClearance.WLUndertakingAudit;

public interface WLUndertakingAuditRepository extends JpaRepository<WLUndertakingAudit, Integer>{

}
