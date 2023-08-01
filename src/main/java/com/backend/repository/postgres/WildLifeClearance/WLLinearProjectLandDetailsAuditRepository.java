package com.backend.repository.postgres.WildLifeClearance;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.model.WildLifeClearance.WLLinearProjectLandDetails;
import com.backend.model.WildLifeClearance.WLLinearProjectLandDetailsAudit;

public interface WLLinearProjectLandDetailsAuditRepository extends JpaRepository<WLLinearProjectLandDetailsAudit, Integer>{

}
