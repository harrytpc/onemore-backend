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
import com.onemore.vo.EventFilter;
import com.onemore.vo.InvitationFilter;
import com.onemore.vo.InvitationVO;

@Service
public class EventBO {

	@Autowired
	private EventRepository eventRepository;
	
	@Autowired
	private InvitationRepository invitationRepository;
	
	@Autowired
	private InvitationBO invitationBO;
	
	public Iterable<Event> findAll() {
        return eventRepository.findAll();
    }
	
	public Event insert(Event event, User loggedUser) {
		event.setOwner(loggedUser);
		event.setDateCreation(new Date());
//		if(!StringUtils.isEmpty(event.getDateStr())){
//			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
//			try {
//				event.setDate(sdf.parse(event.getDateStr()));
//			} catch (ParseException e) {
//				e.printStackTrace();
//			}
//		}
		
		
        return eventRepository.save(event);
    }
	
	public Event update(Event event) {
        return eventRepository.save(event);
    }
	
	public void remove(Long eventId) {
		Event event = eventRepository.findOne(eventId);
        eventRepository.delete(event);
    }
	
    public Event findEventById(Long eventId, User loggedUser) {
        Event event = eventRepository.findOne(eventId);
        event = addInformationsEvent(event, loggedUser);
        return event;
    }
    
    public List<Event> findEventByUserId(Long userId) {
//    	eventRepository.findEventsByUserOwnerId(userId);
//        return eventRepository.findEventsByUserOwnerId(userId);
    	return null;
    }
    
    public List<Event> searchByFilter(EventFilter eventFilter){
    	List<Event> eventList = eventRepository.searchByFilter(eventFilter);
    	for (Event event : eventList) {
			event = addInformationsEvent(event, eventFilter.getLoggedUser());
		}
    	
    	return eventList;
    }
    
    public Event addInformationsEvent(Event event, User loggedUser){
    	event.setAdmin(event.getOwner().getId() == loggedUser.getId());
		
		if(event.isAdmin()){
			event.setInvited(false);
			event.setConfirmed(false);
			event.setRequested(false);
		}else{ 
			
			InvitationFilter filter = new InvitationFilter(event.getId());
			filter.setPlayerId(loggedUser.getId());
			filter.setStatusList(InvitationStatusEnum.getInvitedsStatus());
			List<Invitation> resultInvited = invitationBO.getInvitationByFilter(filter);
			if(resultInvited != null && resultInvited.size() > 0){
				event.setInvited(true); 
				event.setInvitation(new InvitationVO(resultInvited.get(0).getId()));
			}
			
			filter.setStatusList(InvitationStatusEnum.getConfirmedStatus());
			List<Invitation> resultConfirmed = invitationBO.getInvitationByFilter(filter);
			if(resultConfirmed != null && resultConfirmed.size() > 0){
				event.setConfirmed(true); 
				event.setInvitation(new InvitationVO(resultConfirmed.get(0).getId()));
			}
			
			filter.setStatusList(InvitationStatusEnum.getPendingApprovalStatus());
			List<Invitation> resultPending = invitationBO.getInvitationByFilter(filter);
			if(resultPending != null && resultPending.size() > 0){
				event.setRequested(true); 
				event.setInvitation(new InvitationVO(resultPending.get(0).getId()));
			}
		}
		
		event.setInvitationsSummary(invitationBO.getSummaryInvitations(event));
    	
		return event;
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

    public Invitation addRequest(Long eventId, User user) {
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
		
		return invitation;
    }
    
    public List<Invitation> findInvitations(Long eventId){
    	Event event = eventRepository.findOne(eventId);
    	return invitationRepository.findByEvent(event);
    }
}
