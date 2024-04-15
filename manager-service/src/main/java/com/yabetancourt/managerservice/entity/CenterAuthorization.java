package com.yabetancourt.managerservice.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Entity
@Table(name = "center_authorization")
@EntityListeners(AuditingEntityListener.class)
public class CenterAuthorization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false, unique = true)
    private String authorizationNumber;

    @Column(nullable = false)
    @Version
    private final Long version = 0L;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Date createdDate;

    @LastModifiedDate
    private Date lastModifiedDate;

    public CenterAuthorization() {
    }

    public CenterAuthorization(Long id) {
        this.id = id;
    }

    public CenterAuthorization(Long id, String authorizationNumber) {
        this.id = id;
        this.authorizationNumber = authorizationNumber;
    }

    public Long getId() {
        return id;
    }

    public String getAuthorizationNumber() {
        return authorizationNumber;
    }

    public Long getVersion() {
        return version;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

}