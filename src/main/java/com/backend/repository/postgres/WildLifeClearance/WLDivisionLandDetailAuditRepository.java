package com.backend.repository.postgres.WildLifeClearance;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.model.WildLifeClearance.WLDivisionLandDetail;
import com.backend.model.WildLifeClearance.WLDivisionLandDetailAudit;

public interface WLDivisionLandDetailAuditRepository extends JpaRepository<WLDivisionLandDetailAudit, Integer>{

}
