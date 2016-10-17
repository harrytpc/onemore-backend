package com.onemore.bo;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onemore.model.City;
import com.onemore.model.User;
import com.onemore.repository.CityRepository;
import com.onemore.repository.UserRepository;
import com.onemore.vo.UserInsertVO;

@Service
public class UserBO {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CityRepository cityRepository;
	
	
    public User getUserById(Long userId) {
        return userRepository.findById(userId);
    }
    
    public Iterable<User> getUserByFilter(User filter) {
        return userRepository.findAll();
    }
    
    public User insert(UserInsertVO userInsert){
    	User user = new User();
    	
    	try {
    		user.setUsername(userInsert.getUsername());
    		user.setName(userInsert.getName());
    		user.setEmail(userInsert.getEmail());
    		
    		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    		user.setBirthDate(sdf.parse(userInsert.getBirthDate()));
    		
    		City city = cityRepository.findOne(userInsert.getCityId());
    		user.setCity(city);
    		user.setPassword(userInsert.getPassword());
    		
    		user = userRepository.save(user);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
    	
    	return user;
    }
    
    public User update(User userUpdate){
    	return userRepository.save(userUpdate);
    }
    
    public List<User> findContactsByUserId(Long userId){
    	return userRepository.findContactsByUserId(userId);
    }
    
    public User getByFilter(){
    	return userRepository.getUserByFilters(null);
//    	return null;
    }
	
}
