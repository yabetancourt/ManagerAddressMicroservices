package com.yabetancourt.managerservice.repository;

import com.yabetancourt.managerservice.dto.CenterAuthorizationDto;
import com.yabetancourt.managerservice.entity.CenterAuthorization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CenterAuthorizationRepository extends JpaRepository<CenterAuthorization, Long> {

    Optional<CenterAuthorizationDto> findSimpleById(Long id);

    List<CenterAuthorizationDto> findAllBy();

}