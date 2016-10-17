package com.onemore.repository;

import org.springframework.data.repository.CrudRepository;

import com.onemore.model.City;
import com.onemore.model.State;

public interface CityRepository extends CrudRepository<City, Long>{

    City findById(Long userId);
    Iterable<City> findByState(State state);
	
}

