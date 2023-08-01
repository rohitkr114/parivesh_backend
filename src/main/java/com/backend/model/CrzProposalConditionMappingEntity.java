package com.backend.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class CrzProposalConditionMappingEntity {

	private List<CrzProposalConditionMappingDto> specialConditions;
	private List<CrzProposalConditionMappingDto> generalConditions;
}