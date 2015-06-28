package com.club.dao;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.club.common.HibernateUtil;
import com.club.entity.LoginEntity;

@SuppressWarnings("unchecked")
@Transactional
@Repository
public class RegistrationDAOImpl  extends HibernateUtil implements RegistrationDAO{

	@Override
	public void createLoginEntity(LoginEntity loginEntity) {
		getHibernateTemplate().save(loginEntity);
	}
	
	@Override
	public boolean loginUser(String username, String password) {
		boolean userStatus=false;
		List<LoginEntity> loginEntities=getCurrentSession().createCriteria(LoginEntity.class).add(Restrictions.and(Restrictions.eq("username", username),Restrictions.eq("password", password))).list();
		if(loginEntities.size()!=0){
			userStatus = true;
		}
		return userStatus;
		
	}

	@Override
	public boolean isUsernameAlreadyExist(String username) {
		boolean userStatus=false;
		List<LoginEntity> loginEntities=getCurrentSession().createCriteria(LoginEntity.class).add(Restrictions.eq("username", username)).list();
		if(loginEntities.size()!=0){
			userStatus = true;
		}
		return userStatus;
	}

	@Override
	public boolean isEmailAlreadyExist(String email) {
		boolean userStatus=false;
		List<LoginEntity> loginEntities=getCurrentSession().createCriteria(LoginEntity.class).add(Restrictions.eq("email", email)).list();
		if(loginEntities.size()!=0){
			userStatus = true;
		}
		return userStatus;
	}



}
