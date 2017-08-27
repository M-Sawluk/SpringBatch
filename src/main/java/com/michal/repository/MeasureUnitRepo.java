package com.michal.repository;

import com.michal.domain.MeasureUnitVersionEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeasureUnitRepo extends CrudRepository<MeasureUnitVersionEntity, Long> {

    MeasureUnitVersionEntity findByAlias(String alias);

}
