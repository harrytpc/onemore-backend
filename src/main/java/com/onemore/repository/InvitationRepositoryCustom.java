package com.onemore.repository;

import java.util.List;

import com.onemore.model.Invitation;
import com.onemore.vo.InvitationFilter;

public interface InvitationRepositoryCustom {

//	public Integer getInvitationByStatus(List<InvitationStatusEnum> statusList);
	public List<Invitation> getInvitationByFilter(InvitationFilter filter);
	
}
