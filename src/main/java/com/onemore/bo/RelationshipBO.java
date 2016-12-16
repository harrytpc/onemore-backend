package com.onemore.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onemore.model.Relationship;
import com.onemore.model.User;
import com.onemore.repository.RelationshipRepository;

@Service
public class RelationshipBO {

	@Autowired
	private RelationshipRepository relationshipRepository;
	
	@Autowired
	private UserBO userBO;
	
	public void follow(User loggedUser, User userFollowed) {
		User followedBD = userBO.getUserById(userFollowed.getId());
		
		Relationship relationship = new Relationship();
		relationship.setFollowing(loggedUser);
		relationship.setFollowed(followedBD);
		
		relationshipRepository.save(relationship);
	}
	
	public void unfollow(User loggedUser, Long userIdFollowed) {
		List<Relationship> relationshipList = relationshipRepository.findFollowingAndFollowed(loggedUser.getId(), userIdFollowed);
		if(relationshipList != null && relationshipList.size() > 0){
			relationshipRepository.delete(relationshipList);
		}
	}
	
	public List<Relationship> findFollowing(User loggedUser){
		List<Relationship> relationshipList = relationshipRepository.findFollowing(loggedUser.getId());
//		List<Relationship> relationshipList = null;
		return relationshipList;
	}
	
//	public List<Relationship> findNotFollowed(User loggedUser){
//		List<Relationship> relationshipList = relationshipRepository.findNotFollowed(loggedUser.getId());
//		return relationshipList;
//	}
	
	

}
