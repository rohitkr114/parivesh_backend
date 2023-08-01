package com.backend.dto;

import java.util.List;

import lombok.Data;

@Data
public class DocDetailsDTO {
	
	private List<DocumentDetailsDTO> documentDetails;
	
	private List<AgendaDocumentDetailsDTO> agendaDetails;
	
	private List<MomDocumentDetailsDTO> momDetails;
 
	private List<CafDocDetailsDTO> cafDetails;
}
