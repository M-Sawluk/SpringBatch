package com.michal.repository;

import com.michal.domain.GasPointEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface GasPointRepo extends CrudRepository<GasPointEntity, Long> {

    GasPointEntity findByName(String name);
}
