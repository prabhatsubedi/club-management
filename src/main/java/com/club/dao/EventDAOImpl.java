package com.club.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.club.common.HibernateUtil;
import com.club.entity.EventEntity;

@Repository
@Transactional
public class EventDAOImpl  extends HibernateUtil implements EventDAO {

	@Override
	public void save(EventEntity eventEntity) {
		getHibernateTemplate().save(eventEntity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EventEntity> getEventList() {
		List<EventEntity> eventEntities = new ArrayList<EventEntity>();
		Criteria criteria = getCurrentSession().createCriteria(EventEntity.class);
		eventEntities = criteria.list();
		return eventEntities;
	}

	@Override
	public EventEntity getEventDetailById(Integer eventId) {
		List<EventEntity> eventEntities = new ArrayList<EventEntity>();
		Criteria criteria = getCurrentSession().createCriteria(EventEntity.class);
		criteria.add(Restrictions.eq("id",eventId));
		eventEntities = criteria.list();
		return eventEntities.size() > 0 ? eventEntities.get(0) : null;
	}

	@Override
	public void update(EventEntity eventEntity) {
		getHibernateTemplate().update(eventEntity);
	}

}
