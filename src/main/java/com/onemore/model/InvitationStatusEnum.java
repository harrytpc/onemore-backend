package com.onemore.model;

import java.util.ArrayList;
import java.util.List;

public enum InvitationStatusEnum {
	
	INVITED(1, "Convidado"),
	REQUESTED(2, "Aguardando aprovação"),
	ACCEPTED_BY_OWNER(3, "Confirmado pelo administrador"),
	ACCEPTED_BY_USER(4, "Confirmado pelo convidado"),
	DECLINED_BY_OWNER(5, "Negado pelo administrador"),
	DECLINED_BY_USER(6, "Negado pelo convidado");
	
	private final Integer code;
	private final String description;

	private InvitationStatusEnum(Integer code, String description) {
		this.code = code;
		this.description = description;
	}

	
	public Integer getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

	public static InvitationStatusEnum fromCode(Integer code){
		for (InvitationStatusEnum status : InvitationStatusEnum.values()) {
			if(status.getCode() == code){
				return status;
			}
		}
		return null;
	}
	
	public static List<InvitationStatusEnum> getConfirmedStatus(){
		List<InvitationStatusEnum> confirmedList = new ArrayList<InvitationStatusEnum>();
		confirmedList.add(InvitationStatusEnum.ACCEPTED_BY_OWNER);
		confirmedList.add(InvitationStatusEnum.ACCEPTED_BY_USER);
		return confirmedList;
	}
	
	public static List<InvitationStatusEnum> getPendingApprovalStatus(){
		List<InvitationStatusEnum> pendingList = new ArrayList<InvitationStatusEnum>();
		pendingList.add(InvitationStatusEnum.REQUESTED);
		return pendingList;
	}
	
	public static List<InvitationStatusEnum> getInvitedsStatus(){
		List<InvitationStatusEnum> pendingList = new ArrayList<InvitationStatusEnum>();
		pendingList.add(InvitationStatusEnum.INVITED);
		return pendingList;
	}
	
	public static List<InvitationStatusEnum> getDeclinedStatus(){
		List<InvitationStatusEnum> declinedList = new ArrayList<InvitationStatusEnum>();
		declinedList.add(InvitationStatusEnum.DECLINED_BY_OWNER);
		declinedList.add(InvitationStatusEnum.DECLINED_BY_USER);
		
		return declinedList;
	}
}
