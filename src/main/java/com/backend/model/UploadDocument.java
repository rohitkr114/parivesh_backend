//package com.backend.model;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.Table;
//
//import com.backend.auditFc.AuditWithId;
//
//
//
//@Entity
//@Table(name = "upload_document", schema = "dms")
//public class UploadDocument extends AuditWithId {
//
//	@Column(name = "ref_id")
//	private String refId;
//	@Column(name = "ref_type")
//	private String refType;
//	@Column(name = "doc_name")
//	private String docName;
//	@Column(name = "doc_type_mapping_id")
//	private Long docTypeMappingId;
//	@Column(name = "name")
//	private String name;
//	@Column(name = "uploaded_name")
//	private String uploadedName;
//	@Column(name = "uuid")
//	private String uuid;
//	@Column(name = "path")
//	private String path;
//	@Column(name = "version")
//	private String version;
//
//	public String getRefId() {
//		return refId;
//	}
//
//	public void setRefId(String refId) {
//		this.refId = refId;
//	}
//
//	public String getRefType() {
//		return refType;
//	}
//
//	public void setRefType(String refType) {
//		this.refType = refType;
//	}
//
//	public String getDocName() {
//		return docName;
//	}
//
//	public void setDocName(String docName) {
//		this.docName = docName;
//	}
//
//	public Long getDocTypeMappingId() {
//		return docTypeMappingId;
//	}
//
//	public void setDocTypeMappingId(Long docTypeMappingId) {
//		this.docTypeMappingId = docTypeMappingId;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public String getUploadedName() {
//		return uploadedName;
//	}
//
//	public void setUploadedName(String uploadedName) {
//		this.uploadedName = uploadedName;
//	}
//
//	public String getUuid() {
//		return uuid;
//	}
//
//	public void setUuid(String uuid) {
//		this.uuid = uuid;
//	}
//
//	public String getPath() {
//		return path;
//	}
//
//	public String getVersion() {
//		return version;
//	}
//
//	public void setVersion(String version) {
//		this.version = version;
//	}
//
//	public void setPath(String path) {
//		this.path = path;
//	}
//
//}
