package com.yabetancourt.managerservice.repository;

import com.yabetancourt.managerservice.entity.Manager;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Long> {

    @Override
    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, value = "ManagerWithAuthorizations")
    Optional<Manager> findById(Long aLong);

}