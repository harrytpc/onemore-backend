package com.onemore.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.onemore.model.User;
import com.onemore.repository.UserRepository;
import com.onemore.util.TokenUtil;
import com.onemore.vo.UserCheckVO;

@Service
public class UserBO {

	@Autowired
	private UserRepository userRepository;
	
//	@Autowired
//	private CityRepository cityRepository;
	
    public User getUserById(Long userId) {
        return userRepository.findById(userId);
    }
    
    public Iterable<User> getUserByFilter(User filter) {
        return userRepository.findAll();
    }
    
    public User getLoggedUser(HttpHeaders headers){
//    	String username = (String) TokenUtil.getClaim(headers, "preferred_username");
//		System.out.println(username);
		 
		String keycloakId = (String) TokenUtil.getClaim(headers, "sub");
		return userRepository.findByKeycloakId(keycloakId);
    	
    }
    
//    public User insert(UserInsertVO userInsert){
//    	User user = new User();
//    	
//    	try {
//    		user.setUsername(userInsert.getUsername());
//    		user.setFirstName(userInsert.getName());
//    		user.setEmail(userInsert.getEmail());
//    		
//    		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
//    		user.setBirthDate(sdf.parse(userInsert.getBirthDate()));
//    		
//    		City city = cityRepository.findOne(userInsert.getCityId());
//    		user.setCity(city);
//    		user.setPassword(userInsert.getPassword());
//    		
//    		user = userRepository.save(user);
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//    	
//    	return user;
//    }
    
    public User check(HttpHeaders headers, UserCheckVO userCheck){
    	User userDB = getLoggedUser(headers);
    	if(userDB == null){ //user not exists
    		User newUser = new User();
    		newUser.setKeycloakId(userCheck.getId());
    		newUser.setFirstName(userCheck.getFirstName());
    		newUser.setLastName(userCheck.getLastName());
    		newUser.setEmail(userCheck.getEmail());
    		newUser.setUsername(userCheck.getUsername());
    		return userRepository.save(newUser);
    	}else{
    		userDB.setFirstName(userCheck.getFirstName());
    		userDB.setLastName(userCheck.getLastName());
    		userDB.setEmail(userCheck.getEmail());
    		userDB.setUsername(userCheck.getUsername());
    		return userRepository.save(userDB);
    	}
    }
    
    public User update(User userUpdate){
    	return userRepository.save(userUpdate);
    }
    
//    public List<User> findContactsByUserId(HttpHeaders headers){
//    	User loggedUser = getLoggedUser(headers);
//    	return userRepository.findContactsByUserId(loggedUser.getId());
//    }
    
    public User getByFilter(){
    	return userRepository.getUserByFilters(null);
//    	return null;
    }
	
    public List<User> findNotFollowing (User loggedUser){
    	return userRepository.findNotFollowing(loggedUser.getId());
    }
}
