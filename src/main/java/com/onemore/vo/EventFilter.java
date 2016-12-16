package com.onemore.vo;

import java.util.Date;

import com.onemore.model.City;
import com.onemore.model.Modality;
import com.onemore.model.State;
import com.onemore.model.User;

public class EventFilter {

	private String name;
	private Long id;
	private Date date;
	private Boolean onlyOwner;
	private User loggedUser;
	private State state;
	private City city;
	private Modality modality;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Boolean getOnlyOwner() {
		return onlyOwner;
	}
	public void setOnlyOwner(Boolean onlyOwner) {
		this.onlyOwner = onlyOwner;
	}
	public User getLoggedUser() {
		return loggedUser;
	}
	public void setLoggedUser(User loggedUser) {
		this.loggedUser = loggedUser;
	}
	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}
	public City getCity() {
		return city;
	}
	public void setCity(City city) {
		this.city = city;
	}
	public Modality getModality() {
		return modality;
	}
	public void setModality(Modality modality) {
		this.modality = modality;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
}
