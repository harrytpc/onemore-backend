package com.onemore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.onemore.model.Relationship;

public interface RelationshipRepository extends CrudRepository<Relationship, Long>{
	
	@Query("select r from Relationship r where r.following.id = :userIdFollowing and r.followed.id = :userIdFollowed")
	public List<Relationship> findFollowingAndFollowed(@Param("userIdFollowing") Long userIdFollowing, @Param("userIdFollowed") Long userIdFollowed);
	
	@Query("select r from Relationship r where r.following.id = :userId")
	public List<Relationship> findFollowing(@Param("userId") Long userId);
}

