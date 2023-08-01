package com.backend.model.Crz;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.backend.audit.Auditable;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name="crz_disposal",schema = "master")
public class CrzDisposal extends Auditable<Integer>{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(length = 1000,nullable = true)
	private String mode_of_disposal;
	
	@ManyToOne
	@JoinColumn(name="crz_basic_details_id", nullable=false)
	@JsonIgnore
	private CrzBasicDetails crzBasicDetails;
	
	private Boolean is_active;
	
	private Boolean is_deleted;

	public CrzDisposal() {
		this.is_active=true;
		this.is_deleted=false;
	}
	
	public CrzDisposal(Integer id,String mode_of_disposal) {
		this.id=id;
		this.mode_of_disposal=mode_of_disposal;
	}
}
