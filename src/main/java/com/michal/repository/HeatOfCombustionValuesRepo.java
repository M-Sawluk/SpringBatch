package com.michal.repository;

import com.michal.domain.HeatOfCombustionValuesEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface  HeatOfCombustionValuesRepo  extends CrudRepository<HeatOfCombustionValuesEntity, Integer>{

    HeatOfCombustionValuesEntity findByDataAndHeatOfCombustion(Date date, HeatOfCombustionValuesEntity heatOfCombustionValuesEntity);
}
