package com.backend.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class CrzProponentApplicationEntity implements Serializable {

	private Integer id;
	private String moefccFileNumber;
	private String last_remarks;
	private Integer proposal_id;
	private boolean roleIsMS;
	
}
