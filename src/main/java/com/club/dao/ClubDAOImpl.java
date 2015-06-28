package com.club.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.club.common.HibernateUtil;
import com.club.entity.ClubEntity;

@SuppressWarnings("unchecked")
@Transactional
@Repository
public class ClubDAOImpl extends HibernateUtil implements ClubDAO{

	@Override
	public ClubEntity getClubDetailByClubId(Integer clubid) {
		
		List<ClubEntity> clubEntities = new ArrayList<ClubEntity>();
		Criteria criteria = getCurrentSession().createCriteria(ClubEntity.class);
		criteria.add(Restrictions.eq("id",clubid));
		clubEntities = criteria.list();
		
		return clubEntities.size() > 0 ? clubEntities.get(0) : null;
	}

	@Override
	public void updateClubDetail(ClubEntity clubEntity) {
		getHibernateTemplate().update(clubEntity);
	}

	@Override
	public List<ClubEntity> getListOFClubEntity() {
		List<ClubEntity> clubEntities = new ArrayList<ClubEntity>();
		Criteria criteria = getCurrentSession().createCriteria(ClubEntity.class);
		clubEntities = criteria.list();
		return clubEntities;
	}

	@Override
	public void save(ClubEntity clubEntity) {
		getHibernateTemplate().save(clubEntity);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void deleteAllClubs() {
		getSession().createSQLQuery("truncate table club").executeUpdate();
	}

}
