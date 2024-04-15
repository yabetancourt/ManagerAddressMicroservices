package com.yabetancourt.addressservice.error;

public class AddressNotFoundException extends RuntimeException {

    public AddressNotFoundException(Long id) {
        super("Address with ID " + id + " not found");
    }
}
