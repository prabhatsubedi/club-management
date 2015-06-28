package com.club.dao;

import java.util.List;

import com.club.entity.EventEntity;

public interface EventDAO {

	public void save(EventEntity eventEntity);

	public List<EventEntity> getEventList();

	public EventEntity getEventDetailById(Integer eventId);

	public void update(EventEntity eventEntity);

}
