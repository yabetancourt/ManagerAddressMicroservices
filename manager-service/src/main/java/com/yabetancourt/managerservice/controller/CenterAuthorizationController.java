package com.yabetancourt.managerservice.controller;

import com.yabetancourt.managerservice.dto.CenterAuthorizationDto;
import com.yabetancourt.managerservice.service.CenterAuthorizationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/center-authorization")
public class CenterAuthorizationController {

    private final CenterAuthorizationService centerAuthorizationService;

    public CenterAuthorizationController(CenterAuthorizationService centerAuthorizationService) {
        this.centerAuthorizationService = centerAuthorizationService;
    }

    @GetMapping
    public ResponseEntity<List<CenterAuthorizationDto>> findAll() {
        return ResponseEntity.ok(centerAuthorizationService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CenterAuthorizationDto> findById(@PathVariable Long id) {
        CenterAuthorizationDto centerAuthorization = centerAuthorizationService.findById(id);
        return ResponseEntity.ok(centerAuthorization);
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody @Valid CenterAuthorizationDto request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return buildErrorResponse(bindingResult);
        }
        CenterAuthorizationDto centerAuthorization = centerAuthorizationService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(centerAuthorization);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody @Valid CenterAuthorizationDto request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return buildErrorResponse(bindingResult);
        }
        request.setId(id);
        CenterAuthorizationDto centerAuthorization = centerAuthorizationService.save(request);
        return ResponseEntity.ok(centerAuthorization);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        centerAuthorizationService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private ResponseEntity<Object> buildErrorResponse(BindingResult bindingResult) {
        Map<String, String> errors = new HashMap<>();
        bindingResult.getFieldErrors().forEach(fieldError ->
                errors.put(fieldError.getField(), fieldError.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }
}
