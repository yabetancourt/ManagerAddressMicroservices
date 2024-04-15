package com.yabetancourt.managerservice.service;

import com.yabetancourt.managerservice.dto.CenterAuthorizationDto;
import com.yabetancourt.managerservice.entity.CenterAuthorization;

public interface Mapper<E, R, T> {
    E toEntity(R request);
    T toResponse(E entity);
}
