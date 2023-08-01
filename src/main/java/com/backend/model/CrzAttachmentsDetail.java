package com.backend.model;

import java.io.Serializable;
import java.sql.Date;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "crz_attachments_detail", schema = "master")
@NoArgsConstructor
@AllArgsConstructor
public class CrzAttachmentsDetail implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name ="id")
	private Integer id;
	
	@Column(name = "doc_name")
	private String docName;
	
	@Column(name = "doc_type_mapping_id")
	private Integer docTypeMappingId;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "path")
	private String path;
	
	@Column(name = "ref_id")
	private Integer ref_id;
	
	@Column(name = "foreign_reference_key")
	private Integer foreign_reference_key;
	
	@Column(name = "ref_type")
	private String refType;
	
	@Column(name = "uploaded_name")
	private String uploadedName;
	
	@Column(name = "uuid")
	private String uuid;
	
	@Column(name = "version")
	private String version;
	
	@Column(name = "created_by")
	private Integer created_by;

	@Column(name = "created_on")
	private Date created_on;

	@Column(name = "updated_by")
	private Integer updated_by;

	@Column(name = "updated_on")
	private Date updated_on;
	
	
}
