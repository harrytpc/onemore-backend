package com.onemore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
 
import com.onemore.model.User;

public interface UserRepository extends CrudRepository<User, Long>, UserRepositoryCustom{

    User findById(Long userId);
    
    User findByKeycloakId(String keycloakId);
    
    @Query("select u from User u where u.id not in (select r.followed.id from Relationship r where r.following.id = :userIdFollowing) and u.id != :userIdFollowing ")
	public List<User> findNotFollowing(@Param("userIdFollowing") Long userIdFollowing);
    
}

