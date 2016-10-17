package com.onemore.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.onemore.bo.EventBO;
import com.onemore.model.Event;
import com.onemore.model.Invitation;
import com.onemore.model.User;

@RestController
@RequestMapping("/events")
public class EventRest {

	@Autowired
	private EventBO eventBO;
	
	@RequestMapping(method = RequestMethod.GET)
    public Iterable<Event> searchEvent(
    		@RequestParam(value="name", defaultValue="") String name,
    		@RequestParam(value="id", defaultValue="") Long eventid
    		) {
		Iterable<Event> eventList = eventBO.findAll(); 
        return eventList;
    }
	
	@RequestMapping(method = RequestMethod.POST)
	public Event insertEvent(@RequestBody Event eventInsert) {
		return eventBO.insert(eventInsert);
	}
	
	@RequestMapping(value = "/{eventId}", method = RequestMethod.GET)
    public Event getUserByUserId(@PathVariable Long eventId) {
		return eventBO.findEventById(eventId);
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
    public void request(@PathVariable Long eventId, @RequestBody User user) {
        eventBO.addRequest(eventId, user);
    }
	
	@RequestMapping(value = "/{eventId}/invite", method = RequestMethod.POST)
    public void invite(@PathVariable Long eventId, @RequestBody List<User> userList) {
        eventBO.addInvite(eventId, userList);
    }

	@RequestMapping(value = "/{eventId}/invitations", method = RequestMethod.GET)
    public List<Invitation> getInvitations(@PathVariable Long eventId) {
		return eventBO.findInvitations(eventId);
    }
}
