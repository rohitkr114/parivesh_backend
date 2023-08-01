package com.backend.dto.StandadCertificateDto;

import lombok.Data;

import java.util.List;

@Data
public class GeneralConditionDetails {
    private String heading;
    private List<ConditionHeading> conditionHeading;

    @Data
    public static class ConditionHeading {
        private String headingCondition;
        private List<String> conditions;
    }


}


