package com.onemore.vo;

import java.util.Date;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.onemore.model.City;
import com.onemore.model.Invitation;
import com.onemore.model.Modality;
import com.onemore.model.User;

public class EventInsertVO {

	private String name;
	private User owner;
	private Modality modality;
	
	@OneToMany
	private List<Invitation> invitations;
	
	private Date date;
	
	@ManyToOne
	private City city;
	private String local;
	
}
