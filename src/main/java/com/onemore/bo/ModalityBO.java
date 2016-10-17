package com.onemore.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onemore.model.Modality;
import com.onemore.repository.ModalityRepository;

@Service
public class ModalityBO {

	@Autowired
	private ModalityRepository modalityRepository;
	
	
	public Iterable<Modality> findAll() {
        return modalityRepository.findAll();
    }
	
    public Modality findModalityById(Long modalityId) {
        return modalityRepository.findOne(modalityId);
    }
}
