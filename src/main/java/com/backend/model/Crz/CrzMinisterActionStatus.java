package com.backend.model.Crz;
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
@Table(name="crz_minister_action_status", schema = "master")
@NoArgsConstructor
@AllArgsConstructor
public class CrzMinisterActionStatus {

		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		@Column(name = "id")
		private Integer id;
		
		@Column(name = "status")
		private String status;
		

	}

