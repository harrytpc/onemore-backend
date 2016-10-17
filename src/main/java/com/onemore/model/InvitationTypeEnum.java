package com.onemore.model;

public enum InvitationTypeEnum {
	
	OWNER_TO_USER(1, "Administrador convidou usuario"),
	USER_TO_OWNER(2, "Usuario solicitou participar");
	
	private final Integer code;
	private final String description;

	private InvitationTypeEnum(Integer code, String description) {
		this.code = code;
		this.description = description;
	}

	
	public Integer getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

	public static InvitationTypeEnum fromCode(Integer code){
		for (InvitationTypeEnum status : InvitationTypeEnum.values()) {
			if(status.getCode() == code){
				return status;
			}
		}
		return null;
	}
}
