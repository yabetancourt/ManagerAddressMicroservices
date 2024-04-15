package com.yabetancourt.managerservice.dto;

import java.util.List;

public record ManagerResponse(Long id, String name, String nif, Boolean enabled, AddressResponse address, List<CenterAuthorizationDto> centerAuthorization) {
}
