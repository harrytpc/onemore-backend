package com.onemore.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.onemore.bo.EventBO;
import com.onemore.bo.UserBO;
import com.onemore.model.Event;
import com.onemore.model.User;
import com.onemore.vo.UserCheckVO;

@RestController
@RequestMapping("/users")
public class UserRest {

	@Autowired
	private UserBO userBO;
	
	@Autowired
	private EventBO eventBO;
	
	@RequestMapping(method = RequestMethod.GET)
    public List<User> searchUser(
    		@RequestParam(value="notFollowing", defaultValue="false") Boolean notFollowing,
    		@RequestParam(value="id", defaultValue="") Long userid,
    		@RequestHeader HttpHeaders headers
    		) {
		
		if(notFollowing){
			return userBO.findNotFollowing(userBO.getLoggedUser(headers));
		}
//		else{
//			return userBO.getUserByFilter(null); 
//		}
        return null;
    }

	@RequestMapping(value = "/check", method = RequestMethod.POST)
	public User checkUser(@RequestBody UserCheckVO userCheck,
						  @RequestHeader HttpHeaders headers) {
		
		return userBO.check(headers, userCheck);
	}
	
//	@RequestMapping(method = RequestMethod.POST)
//	public User insertUser(@RequestBody UserInsertVO userInsert) {
//		return userBO.insert(userInsert);
//	}
	
	@RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public User getUserByUserId(@PathVariable Long userId) {
		return userBO.getUserById(userId);
    }
	
//	@RequestMapping(value = "/{userId}", method = RequestMethod.PUT)
//    public User updateUser(@RequestBody User userUpdate) {
//		return userBO.update(userUpdate);
//	}
	
//	@RequestMapping(value = "/following", method = RequestMethod.GET)
//    public List<User> getContactsByUserId(@RequestHeader HttpHeaders headers) {
//        return userBO.findContactsByUserId(headers);
//    }
	
	@RequestMapping(value = "/{userId}/events", method = RequestMethod.GET)
    public List<Event> getEventsByUserId(@PathVariable Long userId) {
        return eventBO.findEventByUserId(userId);
    }
	
//	@RequestMapping(method = RequestMethod.POST)
//	public User login(@RequestBody LoginVO loginVO) {
////		return userBO.insert(userInsert);
//		return null;
//	}
	
}
