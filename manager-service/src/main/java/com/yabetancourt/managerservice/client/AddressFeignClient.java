package com.yabetancourt.managerservice.client;

import com.yabetancourt.managerservice.dto.AddressResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("address-service")
public interface AddressFeignClient {

    @GetMapping("/address/{id}")
    AddressResponse getAddressById(@PathVariable Long id);

    @DeleteMapping("/address/{id}")
    void deleteById(@PathVariable Long id);

    @GetMapping("/address/exists/{id}")
    Boolean existsById(@PathVariable Long id);

}
