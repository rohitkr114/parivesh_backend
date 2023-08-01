package com.backend.NSWS.external.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
public class AuthenticationResponse {

    private final String jwt;
    private final String name;
    private final List<String> permissions;
    private final List<String> roleName;
    private final String expiryTime;
    private final List<String> workGroupName;
    private String refreshToken;
    private Boolean isFirstAttempt;
    private String username;
    private final List<MenuDto> menus;
    private long sessionValidityTime = 50000l;
    private String userType;
    Integer stateid;
    private final List<String> officetype_abbreviation;
    private final List<RoleNameAbbreviationDto> roleNameAbbreviationDtos;
    Long userid;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MenuDto {

        private String displayname;

        private String menuCollapse;

        private String icon;

        private Integer order;

        private String urlpath;

        private List<MenuDto> childmenu;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class RoleNameAbbreviationDto {

        private String roleName;
        private Long roleId;
        private Long officeId;
        private String officeName;
        private String officeTypeAbbreviation;
        private Long officeTypeId;
        private String officeTypeName;
        private Long WorkgroupId;
        private String WorkgroupName;
        private String roleAbbreviation;
        private Long sectorId;
        private String sectorCode;
        private String SectorName;
    }
}
