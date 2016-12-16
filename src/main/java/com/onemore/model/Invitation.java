package com.onemore.model;

import java.util.Date;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.onemore.converter.InvitationStatusEnumConverter;
import com.onemore.converter.InvitationTypeEnumConverter;

@Entity
public class Invitation {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JsonManagedReference
	private Event event;
	
	@Convert( converter=InvitationTypeEnumConverter.class )
	private InvitationTypeEnum type;
	
	@ManyToOne(fetch = FetchType.LAZY)
//	@JsonBackReference
	private User owner;
	
	@ManyToOne(fetch = FetchType.LAZY)
//	@JsonBackReference
	private User player;
	
//    @Temporal(TemporalType.DATE)
	private Date dateSent;

//    @Temporal(TemporalType.DATE)
	private Date dateResponse;
	
	@Convert( converter=InvitationStatusEnumConverter.class )
	private InvitationStatusEnum status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public InvitationTypeEnum getType() {
		return type;
	}

	public void setType(InvitationTypeEnum type) {
		this.type = type;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public User getPlayer() {
		return player;
	}

	public void setPlayer(User player) {
		this.player = player;
	}

	public Date getDateSent() {
		return dateSent;
	}

	public void setDateSent(Date dateSent) {
		this.dateSent = dateSent;
	}

	public Date getDateResponse() {
		return dateResponse;
	}

	public void setDateResponse(Date dateResponse) {
		this.dateResponse = dateResponse;
	}

	public InvitationStatusEnum getStatus() {
		return status;
	}

	public void setStatus(InvitationStatusEnum status) {
		this.status = status;
	}
	
}
