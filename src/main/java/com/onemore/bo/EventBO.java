package com.onemore.bo;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onemore.model.Event;
import com.onemore.model.Invitation;
import com.onemore.model.InvitationStatusEnum;
import com.onemore.model.InvitationTypeEnum;
import com.onemore.model.User;
import com.onemore.repository.EventRepository;
import com.onemore.repository.InvitationRepository;

@Service
public class EventBO {

	@Autowired
	private EventRepository eventRepository;
	
	@Autowired
	private InvitationRepository invitationRepository;
	
	public Iterable<Event> findAll() {
        return eventRepository.findAll();
    }
	
	public Event insert(Event event) {
        return eventRepository.save(event);
    }
	
	public Event update(Event event) {
        return eventRepository.save(event);
    }
	
	public void remove(Long eventId) {
		Event event = eventRepository.findOne(eventId);
        eventRepository.delete(event);
    }
	
    public Event findEventById(Long eventId) {
        return eventRepository.findOne(eventId);
    }
    
    public List<Event> findEventByUserId(Long userId) {
    	eventRepository.findEventsByUserOwnerId(userId);
        return eventRepository.findEventsByUserOwnerId(userId);
    }
    
    public void addInvite(Long eventId, List<User> userList) {
    	Event event = eventRepository.findOne(eventId);
    	for (User user : userList) {
    		Invitation invitation = new Invitation();
    		invitation.setEvent(event);
    		invitation.setOwner(event.getOwner());
    		invitation.setPlayer(user);
    		invitation.setDateSent(new Date());
    		invitation.setDateResponse(null);
    		invitation.setStatus(InvitationStatusEnum.INVITED);
    		invitation.setType(InvitationTypeEnum.OWNER_TO_USER);
    		
    		invitation = invitationRepository.save(invitation);
		}
    }

    public void addRequest(Long eventId, User user) {
    	Event event = eventRepository.findOne(eventId);
		Invitation invitation = new Invitation();
		invitation.setEvent(event);
		invitation.setOwner(event.getOwner());
		invitation.setPlayer(user);
		invitation.setDateSent(new Date());
		invitation.setDateResponse(null);
		invitation.setStatus(InvitationStatusEnum.REQUESTED);
		invitation.setType(InvitationTypeEnum.USER_TO_OWNER);
		
		invitation = invitationRepository.save(invitation);
    }
    
    public List<Invitation> findInvitations(Long eventId){
    	Event event = eventRepository.findOne(eventId);
    	return invitationRepository.findByEvent(event);
    }
}
