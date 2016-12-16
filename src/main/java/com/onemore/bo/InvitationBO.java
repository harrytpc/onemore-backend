package com.onemore.bo;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onemore.model.Event;
import com.onemore.model.Invitation;
import com.onemore.model.InvitationStatusEnum;
import com.onemore.model.InvitationTypeEnum;
import com.onemore.model.User;
import com.onemore.repository.InvitationRepository;
import com.onemore.vo.InvitationFilter;
import com.onemore.vo.InvitationsSummaryVO;

@Service
public class InvitationBO {

	@Autowired
	private InvitationRepository invitationRepository;
	
	public InvitationsSummaryVO getSummaryInvitations(Event event){
		InvitationsSummaryVO summary = new InvitationsSummaryVO();
		
		InvitationFilter filter = new InvitationFilter(event.getId());
		
		filter.setStatusList(InvitationStatusEnum.getConfirmedStatus());
		summary.setConfirmed(getInvitationByFilter(filter).size());
		
		filter.setStatusList(InvitationStatusEnum.getPendingApprovalStatus());
		summary.setPendingApproval(getInvitationByFilter(filter).size());
		
		filter.setStatusList(InvitationStatusEnum.getInvitedsStatus());
		summary.setPendingInvites(getInvitationByFilter(filter).size());
		
		filter.setStatusList(InvitationStatusEnum.getDeclinedStatus());
		summary.setDeclined(getInvitationByFilter(filter).size());
		
		int seat = (event.getMaxParticipants() == null ? 0 : event.getMaxParticipants()) - summary.getConfirmed();
		summary.setSeat(seat);
		
    	return summary;
    }
	
	public List<Invitation> getInvitationByFilter(InvitationFilter filter){
		return invitationRepository.getInvitationByFilter(filter);
	}
	
	public void remove(Long invitationId) {
//		Invitation invitation = invitationRepository.findOne(invitationId);
		invitationRepository.delete(invitationId);
    }
	
	
	public Invitation response(Long invitationId, boolean accepted, User loggerUser){
		Invitation invitation = invitationRepository.findOne(invitationId);
		
		if(invitation.getType().equals(InvitationTypeEnum.USER_TO_OWNER)){
			if(loggerUser.getId() == invitation.getOwner().getId()){ // administrador respondendo
				invitation.setDateResponse(new Date());
				invitation.setStatus(accepted ? InvitationStatusEnum.ACCEPTED_BY_OWNER : InvitationStatusEnum.DECLINED_BY_OWNER);
			}else{
				System.out.println("Erro: forbidden");
				//TODO: tratar acesso proibido
				return null;
			}
		} else{
			if(loggerUser.getId() == invitation.getPlayer().getId()){ // jogador respondendo
				invitation.setDateResponse(new Date());
				invitation.setStatus(accepted ? InvitationStatusEnum.ACCEPTED_BY_USER : InvitationStatusEnum.DECLINED_BY_USER);
			}else{
				System.out.println("Erro: forbidden");
				//TODO: tratar acesso proibido
				return null;
			}
		}
		return invitationRepository.save(invitation);	
		
	}
}
