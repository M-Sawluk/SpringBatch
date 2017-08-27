package com.michal.repository;

import com.michal.domain.HeatOfCombustionType;
import com.michal.domain.HeatOfCombustionTypeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeatOfCombustionTypeEntRepo extends CrudRepository<HeatOfCombustionTypeEntity, Integer> {

    HeatOfCombustionTypeEntity findByName(HeatOfCombustionType heatOfCombustionType);
}
