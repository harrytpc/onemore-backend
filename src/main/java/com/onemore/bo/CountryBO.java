package com.onemore.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onemore.model.Country;
import com.onemore.repository.CountryRepository;

@Service
public class CountryBO {

	@Autowired
	private CountryRepository countryRepository;
	
	public Iterable<Country> findAll() {
        return countryRepository.findAll();
    }
	
    public Country findCountryById(Long id) {
        return countryRepository.findOne(id);
    }
}
