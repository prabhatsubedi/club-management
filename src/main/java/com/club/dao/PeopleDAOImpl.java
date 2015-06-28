package com.club.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.club.common.HibernateUtil;
import com.club.entity.PeopleEntity;

@SuppressWarnings("unchecked")
@Transactional
@Repository
public class PeopleDAOImpl extends HibernateUtil implements PeopleDAO{

	@Override
	public PeopleEntity getPeopleDetailByPeopleId(Integer peopleId) {
		
		List<PeopleEntity> peopleEntities = new ArrayList<PeopleEntity>();
		Criteria criteria = getCurrentSession().createCriteria(PeopleEntity.class);
		criteria.add(Restrictions.eq("id",peopleId));
		peopleEntities = criteria.list();
		
		return peopleEntities.size() > 0 ? peopleEntities.get(0) : null;
	}

	@Override
	public void updatePeopleDetail(PeopleEntity peopleEntity) {
		getHibernateTemplate().update(peopleEntity);
	}

	@Override
	public List<PeopleEntity> getListOfPeoples() {
		
		List<PeopleEntity> peopleEntities = new ArrayList<PeopleEntity>();
		Criteria criteria = getCurrentSession().createCriteria(PeopleEntity.class);
		peopleEntities = criteria.list();
		return peopleEntities;
	}

	@Override
	public void save(PeopleEntity peopleEntity) {
		getHibernateTemplate().save(peopleEntity);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void deleteAllPeople() {
		getSession().createSQLQuery("truncate table people").executeUpdate();
	}

	@Override
	public PeopleEntity getPeopleDetailByUsername(String username) {
		List<PeopleEntity> peopleEntities = new ArrayList<PeopleEntity>();
		Criteria criteria = getCurrentSession().createCriteria(PeopleEntity.class);
		criteria.add(Restrictions.eq("name",username));
		peopleEntities = criteria.list();
		
		return peopleEntities.size() > 0 ? peopleEntities.get(0) : null;
	}
	
}
