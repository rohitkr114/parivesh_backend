package com.backend.dto.StandadCertificateDto;

import javax.persistence.Column;

public interface ConditionMasterDto {

	String getConditionId();

	String getConditiondiscription();

	String getConditionType();

	String getProjectCategory();
	
	String getIsActive();
}
