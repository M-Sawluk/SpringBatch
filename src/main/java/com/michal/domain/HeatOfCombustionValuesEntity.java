package com.michal.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class HeatOfCombustionValuesEntity {
    @Id
    @GeneratedValue
    private Long id;

    private BigDecimal value;

    private Date data;

    @ManyToOne
    private HeatOfCombustionEntity heatOfCombustion;


    public HeatOfCombustionValuesEntity(BigDecimal value, Date data) {
        this.value = value;
        this.data = data;
    }
}
