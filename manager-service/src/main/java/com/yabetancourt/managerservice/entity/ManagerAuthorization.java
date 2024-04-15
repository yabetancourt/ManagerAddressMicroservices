package com.yabetancourt.managerservice.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.Date;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class ManagerAuthorization {

    @EmbeddedId
    protected Id id = new Id();

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id", insertable = false, updatable = false)
    protected Manager manager;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "center_authorization_id", insertable = false, updatable = false)
    protected CenterAuthorization authorization;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Date createdDate;

    public ManagerAuthorization() {
    }

    public ManagerAuthorization(Manager manager, CenterAuthorization authorization) {
        this.id.authorizationId = authorization.getId();
        this.id.managerId = manager.getId();
        this.manager = manager;
        this.authorization = authorization;
    }

    public CenterAuthorization getAuthorization() {
        return authorization;
    }

    public Id getId() {
        return id;
    }

    @Embeddable
    public static class Id implements Serializable {

        @Column(name = "manager_id")
        protected Long managerId;

        @Column(name = "center_authorization_id")
        protected Long authorizationId;

        public Id() {
        }

        public Id(Long managerId, Long authorizationId) {
            this.managerId = managerId;
            this.authorizationId = authorizationId;
        }

        public boolean equals(Object o) {
            if (o instanceof Id that) {
                return this.managerId.equals(that.managerId)
                        && this.authorizationId.equals(that.authorizationId);
            }
            return false;
        }

        public int hashCode() {
            return managerId.hashCode() + authorizationId.hashCode();
        }

    }


}
