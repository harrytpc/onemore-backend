package com.onemore.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.onemore.model.Invitation;
import com.onemore.vo.InvitationFilter;

public class InvitationRepositoryImpl implements InvitationRepositoryCustom{
	
	@PersistenceContext
    private EntityManager em;

	public List<Invitation> getInvitationByFilter(InvitationFilter filter){
		StringBuffer sb = new StringBuffer();
		Map<String, Object> params = new HashMap<>();
		
		sb.append("SELECT i FROM Invitation i join fetch i.player where 1 = 1");
		
		if(filter.getInvitationId() != null && filter.getInvitationId() > 0L){
			sb.append(" AND i.id = :invitationId");
			params.put("invitationId", filter.getInvitationId());
		}
		
		if(filter.getStatusList() != null && filter.getStatusList().size() > 0){
			sb.append(" AND i.status in (:statusList)");
			params.put("statusList", filter.getStatusList());
		}
		
		if(filter.getEventId() != null && filter.getEventId() > 0L){
			sb.append(" AND i.event.id = :eventId");
			params.put("eventId", filter.getEventId());
		}
		
		if(filter.getType() != null){
			sb.append(" AND i.type = :type");
			params.put("type", filter.getType());
		}
		
		if(filter.getOwnerId() != null && filter.getOwnerId() > 0L){
			sb.append(" AND i.owner.id = :ownerId ");
			params.put("ownerId", filter.getOwnerId());
		}
		
		if(filter.getPlayerId() != null && filter.getPlayerId() > 0L){
			sb.append(" AND i.player.id = :playerId ");
			params.put("playerId", filter.getPlayerId());
		}
		
		Query query = em.createQuery(sb.toString());
		for(Map.Entry<String, Object> entry : params.entrySet()) {
			query.setParameter(entry.getKey(), entry.getValue());
		}
		
		return query.getResultList();
	}
}
