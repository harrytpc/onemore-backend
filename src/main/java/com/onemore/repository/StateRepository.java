package com.onemore.repository;

import org.springframework.data.repository.CrudRepository;

import com.onemore.model.Country;
import com.onemore.model.State;

public interface StateRepository extends CrudRepository<State, Long>{

    State findById(Long stateId);
    Iterable<State> findByCountry(Country state);
	
}

