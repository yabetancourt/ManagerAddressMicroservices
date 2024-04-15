package com.yabetancourt.managerservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public class ManagerRequest {

    @JsonIgnore
    private Long id;

    @NotBlank(message = "Name is mandatory")
    @Size(max = 128, message = "Name cannot exceed 128 characters")
    private String name;

    @NotBlank(message = "NIF is mandatory")
    @Size(min = 8, max = 12, message = "NIF is between 8 and 12 characters")
    private String nif;

    @NotNull
    private Long addressId;

    @NotNull
    private Boolean enabled;

    @NotEmpty
    private List<Long> authorizationIds;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getNif() {
        return nif;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public List<Long> getAuthorizationIds() {
        return authorizationIds;
    }

    public Long getAddressId() {
        return addressId;
    }

}
