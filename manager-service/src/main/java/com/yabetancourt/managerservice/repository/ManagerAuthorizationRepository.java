package com.yabetancourt.managerservice.repository;

import com.yabetancourt.managerservice.entity.ManagerAuthorization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerAuthorizationRepository extends JpaRepository<ManagerAuthorization, ManagerAuthorization.Id> {
    void deleteByManagerId(Long id);
}