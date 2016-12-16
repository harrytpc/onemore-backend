package com.onemore.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.onemore.vo.InvitationVO;
import com.onemore.vo.InvitationsSummaryVO;

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
	
//	@OneToMany
//	@JsonBackReference
//	private List<Invitation> invitations;
	
	private Date dateCreation;
	
	private Date date;
	
	private Integer maxParticipants;
	
	@Transient
	private String dateStr;
	
//	@Transient
//	private String timeStr;
	
	@ManyToOne
	private City city;
	private String local;
	
	@Transient
	private boolean admin;
	@Transient
	private boolean invited;
	@Transient
	private boolean confirmed;
	@Transient
	private boolean requested;
//	@Transient
//	private int confirmeds;
//	@Transient
//	private int seats;
	@Transient
	private InvitationsSummaryVO invitationsSummary;
	
	@Transient
	private InvitationVO invitation;
	
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
//	public List<Invitation> getInvitations() {
//		return invitations;
//	}
//	public void setInvitations(List<Invitation> invitations) {
//		this.invitations = invitations;
//	}
	public String getDateStr() {
		return dateStr;
	}
	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}
	public boolean isAdmin() {
		return admin;
	}
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	public Integer getMaxParticipants() {
		return maxParticipants;
	}
	public void setMaxParticipants(Integer maxParticipants) {
		this.maxParticipants = maxParticipants;
	}
	public Date getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}
//	public String getTimeStr() {
//		return timeStr;
//	}
//	public void setTimeStr(String timeStr) {
//		this.timeStr = timeStr;
//	}
	public boolean isInvited() {
		return invited;
	}
	public void setInvited(boolean invited) {
		this.invited = invited;
	}
	public boolean isConfirmed() {
		return confirmed;
	}
	public void setConfirmed(boolean confirmed) {
		this.confirmed = confirmed;
	}
	public boolean isRequested() {
		return requested;
	}
	public void setRequested(boolean requested) {
		this.requested = requested;
	}
	public InvitationsSummaryVO getInvitationsSummary() {
		return invitationsSummary;
	}
	public void setInvitationsSummary(InvitationsSummaryVO invitationsSummary) {
		this.invitationsSummary = invitationsSummary;
	}
	public InvitationVO getInvitation() {
		return invitation;
	}
	public void setInvitation(InvitationVO invitation) {
		this.invitation = invitation;
	}
}
