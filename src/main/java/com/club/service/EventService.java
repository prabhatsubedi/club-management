package com.club.service;

import java.util.List;

import com.club.entity.EventEntity;
import com.club.form.EventForm;


public interface EventService {

	public void save(EventForm eventForm);

	public List<EventEntity> getEventList();

	public EventEntity getEventDetailById(Integer eventId);

	public void update(EventEntity eventEntity);

}
