package com.apuliadigitalmakers.gestionaleautosalone.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity
public class Departments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    @Column(name = "created", updatable = false)
    private LocalDateTime created;

    private String updated;

    @Column(name = "deleted")
    private LocalDateTime deleted;

    @PrePersist
    protected void onCreate() {
        created = LocalDateTime.now();
    }

    public void softDelete() {
        this.deleted = LocalDateTime.now();
    }



    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public LocalDateTime getDeleted() {
        return deleted;
    }

    public void setDeleted(LocalDateTime deleted) {
        this.deleted = deleted;
    }
}
