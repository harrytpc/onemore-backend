package com.onemore.repository;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.util.StringUtils;

import com.onemore.model.Event;
import com.onemore.vo.EventFilter;

public class EventRepositoryImpl implements EventRepositoryCustom{

	@PersistenceContext
    private EntityManager em;
	
	@Override
	public List<Event> searchByFilter(EventFilter eventFilter) {
		Map<String, Object> params = new HashMap<>();
		
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT e FROM Event e");
		sb.append(" where 1=1");
		
		if(!StringUtils.isEmpty(eventFilter.getName())){
			sb.append(" AND upper(e.name) like upper(:name)");
			params.put("name", '%' + eventFilter.getName() + '%');
		}
		
		if(eventFilter.getId() != null && eventFilter.getId() > 0L){
			sb.append(" AND e.id = :eventId ");
			params.put("eventId", eventFilter.getId());
		}
		
		if(eventFilter.getDate() != null){
			Calendar cal = Calendar.getInstance();
			cal.setTime(eventFilter.getDate());
			sb.append(" AND day(e.date) = :date and month(e.date) = :month and year(e.date) = :year ");
			params.put("date", cal.get(Calendar.DATE));
			params.put("month", cal.get(Calendar.MONTH)+1);
			params.put("year", cal.get(Calendar.YEAR));
		}
		
		if(eventFilter.getOnlyOwner() != null && eventFilter.getOnlyOwner() == true){
			sb.append(" AND e.owner.id = :loggerUserId");
			params.put("loggerUserId", eventFilter.getLoggedUser().getId());
		}
		
		if(eventFilter.getModality() != null && eventFilter.getModality().getId() != null){
			sb.append(" AND e.modality.id = :modalityId");
			params.put("modalityId", eventFilter.getModality().getId());
		}
		
		if(eventFilter.getState() != null && eventFilter.getState().getId() != null){
			sb.append(" AND e.city.state.id = :stateId");
			params.put("stateId", eventFilter.getState().getId());
		}
		
		if(eventFilter.getCity() != null && eventFilter.getCity().getId() != null){
			sb.append(" AND e.city.id = :cityId");
			params.put("cityId", eventFilter.getCity().getId());
		}
		
		sb.append(" order by e.dateCreation desc ");
		
		Query query = em.createQuery(sb.toString());
		for(Map.Entry<String, Object> entry : params.entrySet()) {
			query.setParameter(entry.getKey(), entry.getValue());
		}
		
	    return (List<Event>) query.getResultList();
	}

}
