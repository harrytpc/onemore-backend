package com.onemore.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.onemore.bo.CountryBO;
import com.onemore.bo.StateBO;
import com.onemore.model.Country;
import com.onemore.model.State;


@RestController
@RequestMapping("/countries")
public class CountryRest {

	@Autowired
	private CountryBO countryBO;
	
	@Autowired
	private StateBO stateBO;
	
	@RequestMapping(method = RequestMethod.GET)
    public Iterable<Country> searchAll(){
		return countryBO.findAll();
	}
	
	@RequestMapping(value = "/{countryId}", method = RequestMethod.GET)
    public Country getCountryById(@PathVariable Long countryId) {
		return countryBO.findCountryById(countryId);
	}
	
	@RequestMapping(value = "/{countryId}/cities", method = RequestMethod.GET)
    public Iterable<State> getStatesByCountryById(@PathVariable Long countryId) {
		return stateBO.findStatesByCountryId(countryId);
	}
	
}