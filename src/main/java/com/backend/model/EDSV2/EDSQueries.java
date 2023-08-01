package com.backend.model.EDSV2;

import javax.persistence.*;

import com.backend.audit.Auditable;
import com.backend.model.DocumentDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="eds_queries", schema="authority")
public class EDSQueries extends Auditable<Integer> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(length=1000)
	private String query;
	
	@Column(nullable=true)
	private String description;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "query_document_id", nullable = true)
	private DocumentDetails query_document;
	
	@Column(nullable=true)
	private String response;

	private String document_name;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "response_document_id", nullable = true)
	private DocumentDetails response_document;
	
	
//	@ManyToOne
//	@JoinColumn(name="eds_id",nullable=true)
//	@JsonIgnore
//	private EDSFormV2 edsFormV2;
	
	@Column(length=100)
	private String status;

	@Column(nullable=true)
	private Integer eds_to_role_id;

	@Column(nullable=true)
	private Integer eds_to_office_id;

	@Transient
	private String eds_raised_by;
	
	private Boolean is_active=true;

	private Boolean is_deleted=false;

	public EDSQueries(Integer id, String query, String description, String response, String status, Integer eds_to_role_id,Integer eds_to_office_id, String eds_raised_by) {
		
		this.id = id;
		this.query = query;
		this.description = description;
		this.response = response;
		this.status = status;
		this.eds_to_role_id = eds_to_role_id;
		this.eds_to_office_id = eds_to_office_id;
		this.eds_raised_by = eds_raised_by;
	}
	
	
	
	
	

}
