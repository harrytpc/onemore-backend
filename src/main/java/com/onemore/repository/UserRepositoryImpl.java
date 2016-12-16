package com.onemore.repository;

import com.onemore.model.User;

public class UserRepositoryImpl implements UserRepositoryCustom{

	@Override
	public User getUserByFilters(User userFilter) {
		User user1 = new User();
		user1.setFirstName("teste");
		
		return user1;
	}
	

}
