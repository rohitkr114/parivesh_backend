package com.backend.model;

import java.io.Serializable;
import java.sql.Date;

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
@Table(name = "crz_proposal_condition_mapping", schema = "master")
@NoArgsConstructor
@AllArgsConstructor
public class CrzProposalConditionMappingDto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "proposal_id", nullable = false)
	private Integer proposal_id;
	
	@Column(name = "mom_id")
	private Integer mom_id;
	
	@Column(name = "header")
	private String header;
	
	@Column(name = "condition")
	private String condition;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "type", nullable = false)
	private CrzAppConstant.Condition_Type type;

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
	
	@Enumerated(EnumType.ORDINAL)
	private transient CrzAppConstant.Condition_Status condition_status;
	
	@Column(name = "master_id")
	private String master_id;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "selected")
	private CrzAppConstant.Yes_No selected;
}
