//package com.backend.auditFc;
//
//import static javax.persistence.TemporalType.TIMESTAMP;
//
//import java.util.Date;
//
//import javax.persistence.Column;
//import javax.persistence.EntityListeners;
//import javax.persistence.MappedSuperclass;
//import javax.persistence.Temporal;
//
//import org.springframework.data.annotation.CreatedBy;
//import org.springframework.data.annotation.CreatedDate;
//import org.springframework.data.annotation.LastModifiedBy;
//import org.springframework.data.annotation.LastModifiedDate;
//import org.springframework.data.annotation.Version;
//import org.springframework.data.jpa.domain.support.AuditingEntityListener;
//
//@MappedSuperclass
//@EntityListeners(AuditingEntityListener.class)
//public abstract class Auditable<T> {
//
//	@Column(name = "created_on", updatable = false)
//	@Temporal(TIMESTAMP)
//	@CreatedDate
//	protected Date created_on;
//
//	@Column(name = "updated_on")
//	@LastModifiedDate
//	@Temporal(TIMESTAMP)
//	protected Date updated_on;
//
//	@CreatedBy
//	@Column(name = "created_by", updatable = false)
//	protected T created_by;
//
//	@LastModifiedBy
//	@Column(name = "updated_by")
//	protected T updated_by;
//
//	@Version
//	protected Integer vers;
//
//}
