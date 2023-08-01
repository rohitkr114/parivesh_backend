package com.backend.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class RlsDto implements Serializable {

    String clause;

    Integer dataset;
}
