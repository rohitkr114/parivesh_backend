
package com.backend.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "crz_mom_chairman_queries", schema = "master")
@NoArgsConstructor
@AllArgsConstructor
public class CrzMomChairmanQueriesDto implements Serializable {

	private static final long serialVersionUID = 123L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;

	@NotNull(message = "Mom id should not be null")
	@Column(name = "mom_id")
	private Integer mom_id;

	@Column(name = "query")
	private String query;

	@Column(name = "response")
	private String response;

	@Column(name = "response_document_id")
	private Integer response_document_id;

	@Column(name = "query_type")
	private String query_type;

	@Column(name = "created_by")
	private Integer created_by;

	@Column(name = "created_on")
	private Date created_on;

	@Column(name = "updated_by")
	private Integer updated_by;

	@Column(name = "updated_on")
	private Date updated_on;

	@Column(name = "remarks")
	private String remarks;
}