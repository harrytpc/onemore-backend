package com.onemore.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class User {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String username;
	
	private String name;
	
	private String email;
	
	private Date birthDate;
    
	@ManyToOne(fetch = FetchType.EAGER)
//	@JsonBackReference
	private City city;
	
    private String password;
	
//	@OneToMany(fetch = FetchType.EAGER)
//	private List<Relationship> relationships;

	public User() {
	}
	public User(String nome) {
		name = nome;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public City getCity() {
		return city;
	}
	public void setCity(City city) {
		this.city = city;
	}
//	public List<Relationship> getRelationships() {
//		return relationships;
//	}
//	public void setRelationships(List<Relationship> relationships) {
//		this.relationships = relationships;
//	}
	
}
