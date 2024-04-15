package com.yabetancourt.managerservice.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "manager")
@EntityListeners(AuditingEntityListener.class)
@NamedEntityGraph(
        name = "ManagerWithAuthorizations",
        attributeNodes = {
                @NamedAttributeNode(value = "authorizations", subgraph = "center"),
        },
        subgraphs = @NamedSubgraph(
                name = "center",
                attributeNodes = {
                        @NamedAttributeNode(value = "authorization")
                })
)
public class Manager {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false, length = 128)
    private String name;

    @Column(nullable = false, unique = true, length = 16)
    private String nif;

    @Column(name = "address_id", nullable = false)
    private Long addressId;

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

    @OneToMany(mappedBy = "manager", fetch = FetchType.LAZY)
    private List<ManagerAuthorization> authorizations;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getNif() {
        return nif;
    }

    public Long getAddressId() {
        return addressId;
    }

    public Boolean getEnabled() {
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

    public List<ManagerAuthorization> getAuthorizations() {
        return authorizations;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

}