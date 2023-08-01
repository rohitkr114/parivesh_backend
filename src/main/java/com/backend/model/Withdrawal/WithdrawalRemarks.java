package com.backend.model.Withdrawal;

import java.util.Date;

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
import com.backend.model.DocumentDetails;

import lombok.Data;

@Data
@Entity
@Table(name = "withdrawal_remarks",schema = "master")
public class WithdrawalRemarks extends Auditable<Integer>{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(nullable = true)
	private Integer withdrawal_id;

	@Column(nullable = true)
	private String remarks;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "document", nullable = true)
	private DocumentDetails document;
	
	@Column(nullable = true)
	private Date date;
	
	private Boolean is_delete;
	
	private Boolean is_active;
	
	public WithdrawalRemarks() {
		this.is_active=true;
		this.is_delete=false;
	}
}
