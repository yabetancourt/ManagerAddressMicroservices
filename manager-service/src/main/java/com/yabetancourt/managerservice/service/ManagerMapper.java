package com.yabetancourt.managerservice.service;

import com.yabetancourt.managerservice.dto.*;
import com.yabetancourt.managerservice.entity.Manager;
import org.springframework.stereotype.Component;

@Component
public class ManagerMapper implements Mapper<Manager, ManagerRequest, ManagerSimpleResponse> {
    @Override
    public Manager toEntity(ManagerRequest request) {
        Manager manager = new Manager();
        manager.setId(request.getId());
        manager.setAddressId(request.getAddressId());
        manager.setEnabled(request.getEnabled());
        manager.setName(request.getName());
        manager.setNif(request.getNif());

        return manager;
    }

    @Override
    public ManagerSimpleResponse toResponse(Manager entity) {
        return new ManagerSimpleResponse(
                entity.getId(),
                entity.getName(),
                entity.getNif(),
                entity.getEnabled()
        );
    }

    public ManagerResponse toFullResponse(Manager entity, AddressResponse address) {
        return new ManagerResponse(
                entity.getId(),
                entity.getName(),
                entity.getNif(),
                entity.getEnabled(),
                address,
                entity.getAuthorizations().stream().map(
                        a -> new CenterAuthorizationDto(a.getAuthorization().getId(), a.getAuthorization().getAuthorizationNumber())
                ).toList()
        );
    }

}
