package com.onemore.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.onemore.bo.InvitationBO;
import com.onemore.bo.UserBO;
import com.onemore.model.Invitation;

@RestController
@RequestMapping("/invitations")
public class InvitationRest {
	
	@Autowired
	private InvitationBO invitationBO;
	
	@Autowired
	private UserBO userBO;
	
	@RequestMapping(value = "/{invitationId}", method = RequestMethod.DELETE)
    public String removeInvitation(@PathVariable Long invitationId, @RequestHeader HttpHeaders headers) {
		invitationBO.remove(invitationId);
		return ""; 
	}
	
	@RequestMapping(value = "/{invitationId}/accept", method = RequestMethod.POST)
    public Invitation acceptInvitation(@PathVariable Long invitationId, @RequestHeader HttpHeaders headers) {
		
		Invitation invitation = invitationBO.response(invitationId, true, userBO.getLoggedUser(headers));
		
		return invitation; 
	}
	
	@RequestMapping(value = "/{invitationId}/deny", method = RequestMethod.POST)
    public Invitation denyInvitation(@PathVariable Long invitationId, @RequestHeader HttpHeaders headers) {
		Invitation invitation = invitationBO.response(invitationId, false, userBO.getLoggedUser(headers));
		return invitation; 
	}
	
}
