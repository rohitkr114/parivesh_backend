package com.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CertificateConditionDTO {
	
	private String proposalNo ;
	private String conditionId;
	private String conditionType;	
	private String conditiondescription;
}
