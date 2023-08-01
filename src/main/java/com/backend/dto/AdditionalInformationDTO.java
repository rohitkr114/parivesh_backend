package com.backend.dto;

import com.backend.model.AdditionalInformation;
import lombok.Data;

@Data
public class AdditionalInformationDTO {

    private AdditionalInformation additionalInformation;
    private UsernameDTO username;


}
