package com.onemore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.onemore.model.Event;
import com.onemore.model.Invitation;
import com.onemore.model.InvitationStatusEnum;

public interface InvitationRepository extends CrudRepository<Invitation, Long>, InvitationRepositoryCustom{

	
	@Query("select i from Invitation i where i.status = :status and i.event.id = :eventId")
	public List<Invitation> findByEventAndStatus(@Param("status") InvitationStatusEnum status, @Param("eventId") Long eventId);
	
	@Query("select i from Invitation i where i.event.id = :eventId")
	public List<Invitation> findByEventId(@Param("eventId") Long eventId);
	
	public List<Invitation> findByEvent(@Param("event") Event event);
	
}
