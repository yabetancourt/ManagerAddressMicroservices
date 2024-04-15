package com.yabetancourt.managerservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CenterAuthorizationDto {

    @JsonIgnore
    private Long id;

    @NotBlank(message = "Authorization number is mandatory")
    @Size(max = 256, message = "Direction cannot exceed 256 characters")
    private String authorizationNumber;

    public CenterAuthorizationDto(Long id, String authorizationNumber) {
        this.id = id;
        this.authorizationNumber = authorizationNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthorizationNumber() {
        return authorizationNumber;
    }

    public void setAuthorizationNumber(String authorizationNumber) {
        this.authorizationNumber = authorizationNumber;
    }
}
