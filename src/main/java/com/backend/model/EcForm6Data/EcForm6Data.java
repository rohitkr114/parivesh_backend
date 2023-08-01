package com.backend.model.EcForm6Data;

import com.backend.model.EcForm6Part1.EcForm6BasicDetails;
import com.backend.model.EcForm6Part2.EcForm6Productdetails;
import com.backend.model.EcForm6Part3.EcForm6EiaConsultantDetails;
import com.backend.model.EcForm6Part5.EcForm6Undertaking;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EcForm6Data {

	private EcForm6BasicDetails ecForm6BasicDetails;
	private EcForm6Productdetails ecForm6Productdetails;
	private EcForm6EiaConsultantDetails ecForm6EiaConsultantDetails;
	private EcForm6Undertaking ecForm6Undertaking;
	//private AdditionalInformation additionalInformation;
}
