package com.yabetancourt.managerservice.service;

import com.yabetancourt.managerservice.client.AddressFeignClient;
import com.yabetancourt.managerservice.dto.*;
import com.yabetancourt.managerservice.entity.CenterAuthorization;
import com.yabetancourt.managerservice.entity.Manager;
import com.yabetancourt.managerservice.entity.ManagerAuthorization;
import com.yabetancourt.managerservice.error.AddressNotFoundException;
import com.yabetancourt.managerservice.error.ManagerNotFoundException;
import com.yabetancourt.managerservice.repository.CenterAuthorizationRepository;
import com.yabetancourt.managerservice.repository.ManagerAuthorizationRepository;
import com.yabetancourt.managerservice.repository.ManagerRepository;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagerService {

    private final ManagerRepository repository;
    private final ManagerAuthorizationRepository authorizationRepository;
    private final CenterAuthorizationRepository centerAuthorizationRepository;
    private final AddressFeignClient addressClient;
    private final ManagerMapper mapper;

    public ManagerService(ManagerRepository repository, ManagerAuthorizationRepository authorizationRepository, CenterAuthorizationRepository centerAuthorizationRepository, AddressFeignClient addressClient, ManagerMapper mapper) {
        this.repository = repository;
        this.authorizationRepository = authorizationRepository;
        this.centerAuthorizationRepository = centerAuthorizationRepository;
        this.addressClient = addressClient;
        this.mapper = mapper;
    }

    @Transactional
    public ManagerSimpleResponse update(ManagerRequest request) {
        authorizationRepository.deleteByManagerId(request.getId());
        return save(request);
    }

    @Transactional
    public ManagerSimpleResponse save(ManagerRequest request) {

        checkIfExistsAddress(request);

        Manager manager = mapper.toEntity(request);
        Manager savedManager = repository.save(manager);
        List<ManagerAuthorization> authorizations = request.getAuthorizationIds()
                .stream()
                .filter(centerAuthorizationRepository::existsById)
                .map(id -> new ManagerAuthorization(savedManager, new CenterAuthorization(id)))
                .toList();

        authorizationRepository.saveAll(authorizations);

        return mapper.toResponse(savedManager);
    }

    @CircuitBreaker(name = "managerService")
    @Bulkhead(name= "bulkheadManagerService")
    @Retry(name = "retryManagerService")
    private void checkIfExistsAddress(ManagerRequest request) {
        if (!addressClient.existsById(request.getAddressId())) {
            throw new AddressNotFoundException(request.getAddressId());
        }
    }

    public ManagerResponse findById(Long id) {
        var managerOptional = repository.findById(id);
        if (managerOptional.isEmpty()) {
            throw new ManagerNotFoundException(id);
        }
        var manager = managerOptional.get();

        AddressResponse address = getAddressById(manager);

        return mapper.toFullResponse(manager, address);
    }

    @CircuitBreaker(name = "managerService", fallbackMethod = "buildFallbackFindManager")
    @Bulkhead(name= "bulkheadManagerService", fallbackMethod = "buildFallbackFindManager")
    @Retry(name = "retryManagerService", fallbackMethod = "buildFallbackFindManager")
    private AddressResponse getAddressById(Manager manager) {
        return addressClient.getAddressById(manager.getAddressId());
    }

    private AddressResponse buildFallbackFindManager(Manager manager, Throwable t) {
        return new AddressResponse(0L, "Address not accessible: " + t.getMessage());
    }

    @Transactional
    public void deleteById(Long id) {
        authorizationRepository.deleteByManagerId(id);
        repository.deleteById(id);
    }

}
