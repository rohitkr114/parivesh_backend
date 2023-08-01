package com.backend.model.EcForm12;

import com.backend.audit.Auditable;
import com.backend.model.DocumentDetails;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ec_form_12_additional_infor_details", schema = "master")
public class EcForm12AdditionalInforDetails extends Auditable<Integer>{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(nullable = true)
	private String document_name;
	
	@OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinColumn(name = "additional_info_copy_id", nullable = true)
	private DocumentDetails additional_info_copy;

}
