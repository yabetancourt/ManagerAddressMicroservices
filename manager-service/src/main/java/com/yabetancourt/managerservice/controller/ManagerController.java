package com.yabetancourt.managerservice.controller;

import com.yabetancourt.managerservice.dto.ManagerRequest;
import com.yabetancourt.managerservice.dto.ManagerResponse;
import com.yabetancourt.managerservice.service.ManagerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/manager")
public class ManagerController {

    private final ManagerService managerService;

    public ManagerController(ManagerService managerService) {
        this.managerService = managerService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ManagerResponse> findById(@PathVariable Long id) {
        ManagerResponse manager = managerService.findById(id);
        return ResponseEntity.ok(manager);
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody @Valid ManagerRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return buildErrorResponse(bindingResult);
        }
        var manager = managerService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(manager);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody @Valid ManagerRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return buildErrorResponse(bindingResult);
        }
        request.setId(id);
        var manager = managerService.update(request);
        return ResponseEntity.ok(manager);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        managerService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private ResponseEntity<Object> buildErrorResponse(BindingResult bindingResult) {
        Map<String, String> errors = new HashMap<>();
        bindingResult.getFieldErrors().forEach(fieldError ->
                errors.put(fieldError.getField(), fieldError.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }
}
