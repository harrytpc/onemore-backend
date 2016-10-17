package com.onemore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.onemore.model.Event;

public interface EventRepository extends CrudRepository<Event, Long>{

	@Query("select e from Event e where e.owner.id = :userId")
	public List<Event> findEventsByUserOwnerId(@Param("userId") Long userId);
}

