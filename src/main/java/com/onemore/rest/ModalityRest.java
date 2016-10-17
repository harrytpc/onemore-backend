package com.onemore.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.onemore.bo.ModalityBO;
import com.onemore.model.Modality;

@RestController
@RequestMapping("/modalities")
public class ModalityRest {

	@Autowired
	private ModalityBO modalityBO;
	
	@RequestMapping(method = RequestMethod.GET)
    public Iterable<Modality> searchAll(){
		return modalityBO.findAll();
	}
	
	@RequestMapping(value = "/{modalityId}", method = RequestMethod.GET)
    public Modality getModalityById(@PathVariable Long modalityId) {
		return modalityBO.findModalityById(modalityId);
	}
	
}