package com.onemore.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onemore.model.Country;
import com.onemore.model.State;
import com.onemore.repository.CountryRepository;
import com.onemore.repository.StateRepository;

@Service
public class StateBO {

	@Autowired
	private StateRepository stateRepository;
	
	@Autowired
	private CountryRepository countryRepository;
	
	public Iterable<State> findAll() {
        return stateRepository.findAll();
    }
	
    public State findStateById(Long id) {
        return stateRepository.findOne(id);
    }
    
    public Iterable<State> findStatesByCountryId(Long countryId) {
        Country country = countryRepository.findOne(countryId); 
    	return stateRepository.findByCountry(country);
    }
}
