package com.onemore.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.onemore.bo.RelationshipBO;
import com.onemore.bo.UserBO;
import com.onemore.model.Relationship;
import com.onemore.model.User;

@RestController
@RequestMapping("/relationships")
public class RelationshipRest {

	@Autowired
	private RelationshipBO relationshipBO;
	
	@Autowired
	private UserBO userBO;
	
	
	@RequestMapping(method = RequestMethod.GET)
    public Iterable<Relationship> getRelationships(
    		@RequestParam(value="following", defaultValue="true") Boolean following,
    		@RequestHeader HttpHeaders headers) {
		
		if(following){
			User loggedUser = userBO.getLoggedUser(headers);
			Iterable<Relationship> relationshipList = relationshipBO.findFollowing(loggedUser); 
			return relationshipList;
		}
//		else{
//			User loggedUser = userBO.getLoggedUser(headers);
//			Iterable<Relationship> relationshipList = relationshipBO.findNotFollowed(loggedUser); 
//			return relationshipList;
//		}
		return null;
    }
	
	@RequestMapping(value = "/follow", method = RequestMethod.POST)
    public void follow(@RequestBody Relationship relationship,
    							@RequestHeader HttpHeaders headers) {
		
		User loggedUser = userBO.getLoggedUser(headers);
		relationshipBO.follow(loggedUser,relationship.getFollowed());
		
    }
	
	@RequestMapping(value = "/unfollow", method = RequestMethod.DELETE)
    public void unfollow(@RequestParam(value="userIdFollowed") Long userIdFollowed,
    				@RequestHeader HttpHeaders headers) {
		
		User loggedUser = userBO.getLoggedUser(headers);
		relationshipBO.unfollow(loggedUser,userIdFollowed);
		
    }
}
