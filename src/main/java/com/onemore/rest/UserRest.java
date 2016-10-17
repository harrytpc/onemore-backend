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
import com.onemore.bo.UserBO;
import com.onemore.model.Event;
import com.onemore.model.User;
import com.onemore.vo.LoginVO;
import com.onemore.vo.UserInsertVO;

@RestController
@RequestMapping("/users")
public class UserRest {

	@Autowired
	private UserBO userBO;
	
	@Autowired
	private EventBO eventBO;
	
	@RequestMapping(method = RequestMethod.GET)
    public Iterable<User> searchUser(
    		@RequestParam(value="name", defaultValue="") String name,
    		@RequestParam(value="id", defaultValue="") Long userid
    		) {
		Iterable<User> userList = userBO.getUserByFilter(null); 
        return userList;
    }
	
	@RequestMapping(method = RequestMethod.POST)
	public User insertUser(@RequestBody UserInsertVO userInsert) {
		return userBO.insert(userInsert);
	}
	
	@RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public User getUserByUserId(@PathVariable Long userId) {
		return userBO.getUserById(userId);
    }
	
	@RequestMapping(value = "/{userId}", method = RequestMethod.PUT)
    public User updateUser(@RequestBody User userUpdate) {
		return userBO.update(userUpdate);
	}
	
	@RequestMapping(value = "/{userId}/following", method = RequestMethod.GET)
    public List<User> getContactsByUserId(@PathVariable Long userId) {
        return userBO.findContactsByUserId(userId);
    }
	
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
