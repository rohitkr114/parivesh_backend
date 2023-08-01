package com.backend.dto;

import lombok.Data;

import java.util.List;

@Data
public class GuestTokenDto {

    LoginApiUserDto user;

    List<ResourcesDto> resources;

    List<RlsDto> rls;

    public GuestTokenDto(LoginApiUserDto loginApiUserDto ,List<ResourcesDto> resources, List<RlsDto> rls) {
        this.user=loginApiUserDto;
        this.resources = resources;
        this.rls = rls;
    }
}
