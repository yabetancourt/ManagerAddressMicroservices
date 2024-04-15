package com.yabetancourt.addressservice.service;

import com.yabetancourt.addressservice.dto.AddressRequest;
import com.yabetancourt.addressservice.dto.AddressResponse;
import com.yabetancourt.addressservice.entity.Address;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {

    public Address toEntity(AddressRequest request) {
        return new Address(request.getId(), request.getDirection(), request.isEnabled());
    }

    public AddressResponse toResponse(Address address) {
        return new AddressResponse(address.getId(), address.getDirection());
    }

}
