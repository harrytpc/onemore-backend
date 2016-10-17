package com.onemore.repository;

import com.onemore.model.User;

public interface UserRepositoryCustom {

	public User getUserByFilters(User userFilter);
	
}
