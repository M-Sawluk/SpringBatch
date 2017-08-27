package com.michal.repository;

import com.michal.domain.GasPointEntity;
import com.michal.domain.HeatOfCombustionEntity;
import com.michal.domain.HeatOfCombustionType;
import com.michal.domain.HeatOfCombustionTypeEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;


public interface HeatOfCombustionRepo extends CrudRepository<HeatOfCombustionEntity, Integer> {

    HeatOfCombustionEntity findByGasPointAndDateFromLessThanEqualAndDateToGreaterThanEqualAndHeatOfCombustionType(GasPointEntity gasPointEntity, Date date, Date date1, HeatOfCombustionTypeEntity heatOfCombustionTypeEntity);

}
