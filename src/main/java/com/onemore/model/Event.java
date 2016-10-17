package com.onemore.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Event {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String name;
	
	@ManyToOne
	private User owner;
	
	@ManyToOne
	private Modality modality;
	
	@OneToMany
	@JsonBackReference
	private List<Invitation> invitations;
	
	private Date date;
	
	@ManyToOne
	private City city;
	private String local;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
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
	public City getCity() {
		return city;
	}
	public void setCity(City city) {
		this.city = city;
	}
	public String getLocal() {
		return local;
	}
	public void setLocal(String local) {
		this.local = local;
	}
	public List<Invitation> getInvitations() {
		return invitations;
	}
	public void setInvitations(List<Invitation> invitations) {
		this.invitations = invitations;
	}
	
}
