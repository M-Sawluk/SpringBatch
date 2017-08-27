package com.michal.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class MeasureUnitVersionEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String alias;

    public MeasureUnitVersionEntity() {
    }

    public MeasureUnitVersionEntity(String alias) {
        this.alias = alias;
    }
}
