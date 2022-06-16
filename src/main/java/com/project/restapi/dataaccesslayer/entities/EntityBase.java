package com.project.restapi.dataaccesslayer.entities;

import javax.persistence.*;

@MappedSuperclass
public abstract class EntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public EntityBase(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
