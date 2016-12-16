package com.onemore.repository;

import java.util.List;

import com.onemore.model.Event;
import com.onemore.vo.EventFilter;

public interface EventRepositoryCustom {

	public List<Event> searchByFilter(EventFilter eventFilter);
	
}
