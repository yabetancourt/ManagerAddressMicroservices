package com.yabetancourt.managerservice.service;

import com.yabetancourt.managerservice.dto.CenterAuthorizationDto;
import com.yabetancourt.managerservice.entity.CenterAuthorization;
import org.springframework.stereotype.Component;

@Component
public class CenterAuthorizationMapper implements Mapper<CenterAuthorization, CenterAuthorizationDto, CenterAuthorizationDto>{

    public CenterAuthorization toEntity(CenterAuthorizationDto request) {
        return new CenterAuthorization(request.getId(), request.getAuthorizationNumber());
    }

    public CenterAuthorizationDto toResponse(CenterAuthorization entity) {
        return new CenterAuthorizationDto(entity.getId(), entity.getAuthorizationNumber());
    }

}