package com.onemore.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Relationship {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	private User following;
	
	@ManyToOne
	private User followed;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public User getFollowing() {
		return following;
	}
	public void setFollowing(User following) {
		this.following = following;
	}
	public User getFollowed() {
		return followed;
	}
	public void setFollowed(User followed) {
		this.followed = followed;
	}
}
