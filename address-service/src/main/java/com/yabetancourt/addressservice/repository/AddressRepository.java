package com.yabetancourt.addressservice.repository;

import com.yabetancourt.addressservice.dto.AddressResponse;
import com.yabetancourt.addressservice.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    Optional<AddressResponse> findSimpleById(Long id);
    List<AddressResponse> findAllBy();

}