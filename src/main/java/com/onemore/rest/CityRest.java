package com.onemore.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.onemore.bo.CityBO;
import com.onemore.model.City;

@RestController
@RequestMapping("/cities")
public class CityRest {

	@Autowired
	private CityBO cityService;
	
	@RequestMapping(method = RequestMethod.GET)
    public Iterable<City> searchAll(){
		return cityService.findAll();
	}
	
	@RequestMapping(value = "/{cityId}", method = RequestMethod.GET)
    public City getCityById(@PathVariable Long cityId) {
		return cityService.findCityById(cityId);
	}
	
}