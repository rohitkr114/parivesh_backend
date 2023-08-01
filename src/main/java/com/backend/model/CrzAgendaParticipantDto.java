package com.backend.model;

import java.io.Serializable;
import java.sql.Date;

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
@Table(name = "crz_agenda_participant", schema = "master")
@NoArgsConstructor
@AllArgsConstructor
public class CrzAgendaParticipantDto implements Serializable {
	private static final long serialVersionUID = 122L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "user_Type")
	private String user_type;

	@Column(name = "designation")
	private String designation;

	@Column(name = "designation_Id")
	private Integer designation_id;

	@Column(name = "ministry")
	private String ministry;

	@Column(name = "ministry_Id")
	private Integer ministry_id;

	@Column(name = "email_Id")
	private String email_id;

	@Column(name = "mobile_No")
	private Long mobile_no;

	@Column(name = "status")
	private String status;

	@Column(name = "status_Code")
	private Integer status_code;

	@Column(name = "address")
	private String address;

	@Column(name = "location")
	private String location;

	@Column(name = "created_By")
	private Integer created_by;

	@Column(name = "created_On")
	private Date created_on;

	@Column(name = "updated_By")
	private Integer updated_by;

	@Column(name = "updated_On")
	private Date updated_on;

	@Column(name = "remarks")
	private String remarks;

	@Column(name = "agenda_Id")
	private Integer agenda_id;

//	@JsonBackReference
//	@ManyToOne(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
//    @JoinColumn(name = "fk_agenda_id", referencedColumnName = "agenda_id", insertable = false, updatable = false)
//	@JsonIgnore
//    private CrzAgendaDetailsDto crzAgendaDetailsDto;

//	@ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "agenda_id", referencedColumnName = "code", nullable = false)
//	@OnDelete(action = OnDeleteAction.CASCADE)
//    @JsonIgnore
//    private CrzAgendaDetailsDto crzAgendaDetailsDto;
}
