package com.backend.model.certificate;

import com.backend.model.EcProdTransportDetails;
import com.backend.model.EnvironmentClearance.EcIndustryProposal;
import lombok.Data;

import java.util.List;

@Data
public class IndustryAndTransportDetails {
    List<EcIndustryProposal> ecIndustryProposal;
    List<EcProdTransportDetails> ecProdTransportDetails;
}
