package com.yabetancourt.managerservice.error;

public class CenterAuthorizationNotFoundException extends RuntimeException {

    public CenterAuthorizationNotFoundException(Long id) {
        super("Center Authorization with ID " + id + " not found");
    }
}
