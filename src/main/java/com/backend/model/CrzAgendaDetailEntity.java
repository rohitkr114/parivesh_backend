package com.backend.model;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class CrzAgendaDetailEntity {
 
	private CrzAgendaDetailsDto crzAgendaDetails;
	private List<CrzAgendaParticipantDto> crzAgendaParticipants;
	private List<CrzAgendaProposalsDto> crzAgendaProposals;
	private List<CrzAgendaOtherItemsDto> crzAgendaOtherItems;
	private List<CrzAgendaAttachmentDto> crzAgendaAttachments;
	
}
