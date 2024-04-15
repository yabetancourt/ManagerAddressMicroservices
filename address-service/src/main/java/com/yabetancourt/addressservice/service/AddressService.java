package com.yabetancourt.addressservice.service;

import com.yabetancourt.addressservice.dto.AddressRequest;
import com.yabetancourt.addressservice.dto.AddressResponse;
import com.yabetancourt.addressservice.entity.Address;
import com.yabetancourt.addressservice.error.AddressNotFoundException;
import com.yabetancourt.addressservice.repository.AddressRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    private final AddressRepository repository;
    private final AddressMapper mapper;

    public AddressService(AddressRepository repository, AddressMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional
    public AddressResponse save(AddressRequest request) {
        Address address = mapper.toEntity(request);
        Address savedAddress = repository.save(address);
        return mapper.toResponse(savedAddress);
    }

    public AddressResponse findById(Long id) {
        Optional<AddressResponse> addressOptional = repository.findSimpleById(id);
        if (addressOptional.isEmpty()) {
            throw new AddressNotFoundException(id);
        }
        return addressOptional.get();
    }

    public List<AddressResponse> findAll() {
        return repository.findAllBy();
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public Boolean existsById(Long id) {
        return repository.existsById(id);
    }

}
