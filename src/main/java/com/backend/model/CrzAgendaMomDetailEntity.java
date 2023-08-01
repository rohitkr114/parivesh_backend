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
public class CrzAgendaMomDetailEntity {

	private CrzAgendaMomDto crzAgendaMom;
	private CrzAgendaDetailsDto crzAgendaDetails;
	private List<CrzAgendaParticipantDto> crzAgendaParticipants;
	private List<CrzAgendaProposalsDto> crzAgendaProposals;
	private List<CrzAgendaOtherItemsDto> crzAgendaOtherItems;
	private List<CrzAgendaAttachmentMomDto> crzAgendaAttachmentsMom;
//	private CrzProposalConditionMappingEntity crzProposalConditionMappings;
}
