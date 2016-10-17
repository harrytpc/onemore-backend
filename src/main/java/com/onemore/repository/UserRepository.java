package com.onemore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.onemore.model.User;

public interface UserRepository extends CrudRepository<User, Long>, UserRepositoryCustom{

    User findById(Long userId);

    @Query("select r.followed from Relationship r where r.following.id = :userId")
	public List<User> findContactsByUserId(@Param("userId") Long userId);
    
}

