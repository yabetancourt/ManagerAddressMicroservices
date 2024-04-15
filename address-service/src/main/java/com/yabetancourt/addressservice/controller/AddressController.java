package com.yabetancourt.addressservice.controller;

import com.yabetancourt.addressservice.dto.AddressRequest;
import com.yabetancourt.addressservice.dto.AddressResponse;
import com.yabetancourt.addressservice.service.AddressService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/address")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping
    public ResponseEntity<List<AddressResponse>> findAll() {
        return ResponseEntity.ok(addressService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressResponse> findById(@PathVariable Long id) {
        AddressResponse address = addressService.findById(id);
        return ResponseEntity.ok(address);
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody @Valid AddressRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return buildErrorResponse(bindingResult);
        }
        AddressResponse address = addressService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(address);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody @Valid AddressRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return buildErrorResponse(bindingResult);
        }
        request.setId(id);
        AddressResponse address = addressService.save(request);
        return ResponseEntity.ok(address);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        addressService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("exists/{id}")
    private ResponseEntity<Boolean> existsById(@PathVariable Long id) {
        return ResponseEntity.ok(addressService.existsById(id));
    }

    private ResponseEntity<Object> buildErrorResponse(BindingResult bindingResult) {
        Map<String, String> errors = new HashMap<>();
        bindingResult.getFieldErrors().forEach(fieldError ->
                errors.put(fieldError.getField(), fieldError.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }
}
