package com.onemore.model;

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
}
