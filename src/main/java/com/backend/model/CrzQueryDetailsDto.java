package com.backend.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.backend.crz.constant.CrzAppConstant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "crz_query_details", schema = "master")
@NoArgsConstructor
@AllArgsConstructor
public class CrzQueryDetailsDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name ="id")
	private Integer id;
	
	@NotNull
	@Column(name = "application_id")
	private Integer application_id;
	
	@NotNull
	@Column(name = "query")
	private String query;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "type")
	private CrzAppConstant.Query_Type type;
	
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
	
	private transient List<CrzResponseDetailsDto> crzResponseDetailsDto;
	
	private transient CrzAttachmentsDetail crzQueryDetailsAttachment;
}
