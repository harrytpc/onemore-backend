package com.onemore.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onemore.model.City;
import com.onemore.model.State;
import com.onemore.repository.CityRepository;
import com.onemore.repository.StateRepository;

@Service
public class CityBO {

	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private StateRepository stateRepository;
	
	public Iterable<City> findAll() {
        return cityRepository.findAll();
    }
	
    public City findCityById(Long cityId) {
        return cityRepository.findOne(cityId);
    }
    
    public Iterable<City> findCitiesByStateId(Long stateId) {
        State state = stateRepository.findOne(stateId); 
    	return cityRepository.findByState(state);
    }
}
