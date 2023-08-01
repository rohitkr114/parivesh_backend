package com.backend.repository.postgres.WildLifeClearance;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.model.WildLifeClearance.WLEnclosures;
import com.backend.model.WildLifeClearance.WLEnclosuresAudit;

public interface WLEnclosuresAuditRepository extends JpaRepository<WLEnclosuresAudit, Integer>{

}
