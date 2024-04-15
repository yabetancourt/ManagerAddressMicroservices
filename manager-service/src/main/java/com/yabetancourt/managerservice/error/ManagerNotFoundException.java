package com.yabetancourt.managerservice.error;

public class ManagerNotFoundException extends RuntimeException {
    public ManagerNotFoundException(Long id) {
        super("Manager with ID " + id + " not found");
    }
}

