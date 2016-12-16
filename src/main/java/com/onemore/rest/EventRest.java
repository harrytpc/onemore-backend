package com.onemore.rest;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.onemore.bo.EventBO;
import com.onemore.bo.InvitationBO;
import com.onemore.bo.UserBO;
import com.onemore.model.City;
import com.onemore.model.Event;
import com.onemore.model.Invitation;
import com.onemore.model.InvitationStatusEnum;
import com.onemore.model.Modality;
import com.onemore.model.State;
import com.onemore.model.User;
import com.onemore.vo.EventFilter;
import com.onemore.vo.InvitationFilter;

@RestController
@RequestMapping("/events")
public class EventRest {

	@Autowired
	private EventBO eventBO;
	
	@Autowired
	private UserBO userBO;
	
	@Autowired InvitationBO invitationBO;
	
	@RequestMapping(method = RequestMethod.GET)
    public Iterable<Event> searchEvent(
    		@RequestParam(value="name", defaultValue="") String name,
    		@RequestParam(value="id", defaultValue="") Long eventid,
    		@RequestParam(value="date", defaultValue="") String date,
    		@RequestParam(value="modalityId", defaultValue="") Long modalityId,
    		@RequestParam(value="stateId", defaultValue="") Long stateId,
    		@RequestParam(value="cityId", defaultValue="") Long cityId,
    		@RequestParam(value="userOwner", defaultValue="") Boolean onlyOwner,
    		@RequestHeader HttpHeaders headers
    		) {
		
		EventFilter eventFilter = new EventFilter();
		eventFilter.setName(name);
		eventFilter.setId(eventid);
		eventFilter.setLoggedUser(userBO.getLoggedUser(headers));
		eventFilter.setOnlyOwner(onlyOwner);
		
		eventFilter.setModality(new Modality());
		eventFilter.getModality().setId(modalityId);
		
		eventFilter.setState(new State());
		eventFilter.getState().setId(stateId);
		
		eventFilter.setCity(new City());
		eventFilter.getCity().setId(cityId);
		
		if(date != null && !date.trim().isEmpty()){
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			try {
				eventFilter.setDate(sdf.parse(date));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		Iterable<Event> eventList = eventBO.searchByFilter(eventFilter); 
        return eventList;
    }
	
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Event insertEvent(@RequestBody Event eventInsert,@RequestHeader HttpHeaders headers) {
		return eventBO.insert(eventInsert, userBO.getLoggedUser(headers));
	}
	
	@RequestMapping(value = "/{eventId}", method = RequestMethod.GET)
    public Event getEventById(@PathVariable Long eventId,@RequestHeader HttpHeaders headers) {
		return eventBO.findEventById(eventId, userBO.getLoggedUser(headers));
    }
	
	@RequestMapping(value = "/{eventId}", method = RequestMethod.PUT)
    public Event updateEvent(@PathVariable Long eventId, @RequestBody Event eventUpdate) {
		return eventBO.update(eventUpdate);
	}
	
	@RequestMapping(value = "/{eventId}", method = RequestMethod.DELETE)
    public String removeEvent(@PathVariable Long eventId) {
		eventBO.remove(eventId);
		return "";
	}
	
	@RequestMapping(value = "/{eventId}/request", method = RequestMethod.POST)
    public Invitation request(@PathVariable Long eventId, @RequestHeader HttpHeaders headers) {
		User user = userBO.getLoggedUser(headers);
        return eventBO.addRequest(eventId, user);
    }
	
	@RequestMapping(value = "/{eventId}/invite", method = RequestMethod.POST)
    public void invite(@PathVariable Long eventId, @RequestBody List<User> userList) {
        eventBO.addInvite(eventId, userList);
    }

	@RequestMapping(value = "/{eventId}/invitations", method = RequestMethod.GET)
    public List<Invitation> getInvitations(@PathVariable Long eventId) {
		return eventBO.findInvitations(eventId);
    }
	
	@RequestMapping(value = "/{eventId}/confirmeds", method = RequestMethod.GET)
    public List<Invitation> getConfirmeds(@PathVariable Long eventId) {
		InvitationFilter filter = new InvitationFilter(eventId);
		filter.setStatusList(InvitationStatusEnum.getConfirmedStatus());
		return invitationBO.getInvitationByFilter(filter);
    }
	
	@RequestMapping(value = "/{eventId}/inviteds", method = RequestMethod.GET)
    public List<Invitation> getInviteds(@PathVariable Long eventId) {
		InvitationFilter filter = new InvitationFilter(eventId); 
		filter.setStatusList(InvitationStatusEnum.getInvitedsStatus());
		return invitationBO.getInvitationByFilter(filter);
    }
	
	@RequestMapping(value = "/{eventId}/pendings", method = RequestMethod.GET)
    public List<Invitation> getPendingApproval(@PathVariable Long eventId) {
		InvitationFilter filter = new InvitationFilter(eventId);
		filter.setStatusList(InvitationStatusEnum.getPendingApprovalStatus());
		return invitationBO.getInvitationByFilter(filter);
    }
	
	@RequestMapping(value = "/{eventId}/declineds", method = RequestMethod.GET)
    public List<Invitation> getDeclined(@PathVariable Long eventId) {
		InvitationFilter filter = new InvitationFilter(eventId); 
		filter.setStatusList(InvitationStatusEnum.getDeclinedStatus());
		return invitationBO.getInvitationByFilter(filter);
    }
	
}
