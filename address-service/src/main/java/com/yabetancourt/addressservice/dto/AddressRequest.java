package com.yabetancourt.addressservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class AddressRequest {

    @JsonIgnore
    private Long id;

    @NotBlank(message = "Direction is mandatory")
    @Size(max = 500, message = "Direction cannot exceed 500 characters")
    private String direction;

    @NotNull
    private Boolean enabled;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getDirection() {
        return direction;
    }

    public Boolean isEnabled() {
        return enabled;
    }

}
