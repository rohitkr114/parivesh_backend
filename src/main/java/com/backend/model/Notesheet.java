package com.backend.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.backend.audit.Auditable;

import lombok.Data;

@Data
@Entity
@Table(name="notesheet",schema = "master")
public class Notesheet extends Auditable<Integer>{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name="note",length = 3000,nullable = true)
	private String note;
	
	@Column(nullable = true)
	private Long to_role_id;
	
	@Column(nullable = true)
	private Integer to_user_id;
	
	@Column(nullable = true)
	private Long from_role_id;
	
	@Column(nullable = true)
	private Integer from_user_id;
	
	@Column(nullable = true)
	private Integer app_history_id;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "copy_of_attachment_id", nullable = true)
    private DocumentDetails copy_of_attachment_letter;
	
	@Column(nullable = true)
	private Integer application_id;
	
	@Column(nullable = true, length=100)
	private String status;
	
	@Column(nullable = true)
	private Boolean is_active;
	
	@Column(nullable = true)
	private Boolean is_deleted;
	
	public Notesheet() {
		this.is_active=true;
		this.is_deleted=false;
	}

	
	
}
