package com.onemore.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.onemore.bo.CityBO;
import com.onemore.bo.StateBO;
import com.onemore.model.City;
import com.onemore.model.State;

@RestController
@RequestMapping("/states")
public class StateRest {

	@Autowired
	private StateBO stateBO;
	
	@Autowired
	private CityBO cityBO;
	
	@RequestMapping(method = RequestMethod.GET)
    public Iterable<State> searchAll(){
		return stateBO.findAll();
	}
	
	@RequestMapping(value = "/{stateId}", method = RequestMethod.GET)
    public State getStateById(@PathVariable Long stateId) {
		return stateBO.findStateById(stateId);
	}
	
	@RequestMapping(value = "/{stateId}/cities", method = RequestMethod.GET)
    public Iterable<City> getCitiesByStateById(@PathVariable Long stateId) {
		return cityBO.findCitiesByStateId(stateId);
	}
	
}