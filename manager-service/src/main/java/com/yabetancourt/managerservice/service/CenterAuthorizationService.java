package com.yabetancourt.managerservice.service;

import com.yabetancourt.managerservice.dto.CenterAuthorizationDto;
import com.yabetancourt.managerservice.entity.CenterAuthorization;
import com.yabetancourt.managerservice.error.CenterAuthorizationNotFoundException;
import com.yabetancourt.managerservice.repository.CenterAuthorizationRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CenterAuthorizationService {

    private final CenterAuthorizationRepository repository;
    private final CenterAuthorizationMapper mapper;


    public CenterAuthorizationService(CenterAuthorizationRepository repository, CenterAuthorizationMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional
    public CenterAuthorizationDto save(CenterAuthorizationDto request) {
        CenterAuthorization centerAuthorization = mapper.toEntity(request);
        CenterAuthorization savedCenterAuthorization = repository.save(centerAuthorization);
        return mapper.toResponse(savedCenterAuthorization);
    }

    public CenterAuthorizationDto findById(Long id) {
        Optional<CenterAuthorizationDto> centerAuthorizationOptional = repository.findSimpleById(id);
        if (centerAuthorizationOptional.isEmpty()) {
            throw new CenterAuthorizationNotFoundException(id);
        }
        return centerAuthorizationOptional.get();
    }

    public List<CenterAuthorizationDto> findAll() {
        return repository.findAllBy();
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

}
