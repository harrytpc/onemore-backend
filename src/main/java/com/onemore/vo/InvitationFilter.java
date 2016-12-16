package com.onemore.vo;

import java.util.List;

import com.onemore.model.InvitationStatusEnum;
import com.onemore.model.InvitationTypeEnum;

public class InvitationFilter {

	private List<InvitationStatusEnum> statusList;
	private Long ownerId;
	private Long playerId;
	private Long eventId;
	private InvitationTypeEnum type;
	private Long invitationId;
	
	public InvitationFilter(Long eventId) {
		this.eventId = eventId;
	}
	
	public List<InvitationStatusEnum> getStatusList() {
		return statusList;
	}
	
	public void setStatusList(List<InvitationStatusEnum> statusList) {
		this.statusList = statusList;
	}
	public Long getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}
	public Long getPlayerId() {
		return playerId;
	}
	public void setPlayerId(Long playerId) {
		this.playerId = playerId;
	}
	public Long getEventId() {
		return eventId;
	}
	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}
	public InvitationTypeEnum getType() {
		return type;
	}
	public void setType(InvitationTypeEnum type) {
		this.type = type;
	}
	public Long getInvitationId() {
		return invitationId;
	}
	public void setInvitationId(Long invitationId) {
		this.invitationId = invitationId;
	}
	
	
}
