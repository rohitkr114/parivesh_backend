
package com.backend.dto;


public interface DocumentDetailsDTO {
	
	 Integer getdoc_detail_id();
	
	 Integer getdd_created_by();
	
	 String getdd_created_on();
	
	 Integer getdd_updated_by();
	
	 String getdd_updated_on();
	
	 String getdd_uuid(); 
	
	 Integer getdd_document_mapping_id();
	
	 String getdd_document_name();
	
	 Boolean getdd_is_active();
	
	 Boolean getdd_is_deleted();
	
	 String getdd_proposal_no();
	
	 String getdd_ref_id();
	
	 String getdd_type();
	
	 String getdd_version();
	
	 Integer getdd_vers();
	
	 Integer getdd_document_id();
	 
	 Boolean getIs_authority();

}
