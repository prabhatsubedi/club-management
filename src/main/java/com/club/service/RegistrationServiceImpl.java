package com.club.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.club.dao.PeopleDAO;
import com.club.dao.RegistrationDAO;
import com.club.entity.LoginEntity;
import com.club.entity.PeopleEntity;
import com.club.form.RegistrationForm;


@Service
public class RegistrationServiceImpl implements RegistrationService {

	@Autowired
	private RegistrationDAO registrationDAO;
	
	@Autowired
	private PeopleDAO peopleDAO;
		
	@Override
	public Boolean isUsernameAlreadyExist(String username) {
		return registrationDAO.isUsernameAlreadyExist(username);
	}

	@Override
	public Boolean isEmailAlreadyExist(String email) {
		return registrationDAO.isEmailAlreadyExist(email);
	}

	@Override
	public void createUser(RegistrationForm registrationForm) {
		LoginEntity loginEntity = new LoginEntity();
		loginEntity.setEmail(registrationForm.getEmail());
		loginEntity.setPassword(registrationForm.getPassword());
		loginEntity.setUsername(registrationForm.getUsername());
		registrationDAO.createLoginEntity(loginEntity);
		
		PeopleEntity peopleEntity = new PeopleEntity();
		peopleEntity.setName(registrationForm.getUsername());
		peopleEntity.setDob(registrationForm.getDob());
		peopleEntity.setIntrests(registrationForm.getIntrests());
		
		if(registrationForm.getMember() == null){
			registrationForm.setMember("");
		}else{
			peopleEntity.setMember(registrationForm.getMember());
		}
		peopleEntity.setPhone(registrationForm.getPhone());
		peopleDAO.save(peopleEntity);
	}
	
	@Override
	public boolean userLogin(String username,String password) {
		boolean isSuc = registrationDAO.loginUser(username, password);
		if(isSuc){
			return true;
		}else{
			return false;
		}
	}

}
