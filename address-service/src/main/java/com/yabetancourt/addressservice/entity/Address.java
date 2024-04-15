package com.yabetancourt.addressservice.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Entity
@Table(name = "address")
@EntityListeners(AuditingEntityListener.class)
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false, length = 512)
    private String direction;

    @Column(nullable = false)
    private Boolean enabled = Boolean.TRUE;

    @Column(nullable = false)
    @Version
    private Long version = 0L;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Date createdDate;

    @LastModifiedDate
    private Date lastModifiedDate;

    public Address() {
    }

    public Address(Long id, String direction, Boolean enabled) {
        this.id = id;
        this.direction = direction;
        this.enabled = enabled;
    }

    public Long getId() {
        return id;
    }

    public String getDirection() {
        return direction;
    }

    public Boolean isEnabled() {
        return enabled;
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