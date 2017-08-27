package com.michal.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class GasPointEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    public GasPointEntity(String name) {
        this.name = name;
    }

    public GasPointEntity() {
    }
}
