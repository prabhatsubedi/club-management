package com.club.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.club.dao.EventDAO;
import com.club.entity.EventEntity;
import com.club.form.EventForm;

@Service
public class EventServiceImpl implements EventService {
	
	@Autowired
	private EventDAO eventDAO;
	
	@Override
	public void save(EventForm eventForm) {
		
		EventEntity eventEntity = new EventEntity();
		eventEntity.setEventName(eventForm.getEventName());
		eventEntity.setDate(eventForm.getDate());
		eventEntity.setPlace(eventForm.getPlace());
		eventEntity.setDescription(eventForm.getDescription());
		eventDAO.save(eventEntity);
	}

	@Override
	public List<EventEntity> getEventList() {
		return eventDAO.getEventList();
	}

	@Override
	public EventEntity getEventDetailById(Integer eventId) {
		return eventDAO.getEventDetailById(eventId);
	}

	@Override
	public void update(EventEntity eventEntity) {
		eventDAO.update(eventEntity);
	}

	
}
