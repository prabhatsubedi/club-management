package com.club.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.club.dao.PeopleDAO;
import com.club.entity.PeopleEntity;
import com.club.form.PeopleForm;

@Service
public class PeopleServiceImpl implements PeopleService {

	@Autowired
	private PeopleDAO peopleDAO;
	
	@Override
	public PeopleEntity getPeopleDetailByPeopleId(Integer peopleId) {
		return peopleDAO.getPeopleDetailByPeopleId(peopleId);
	}

	@Override
	public void updatePeopleDetail(PeopleForm peopleForm) {
	
		PeopleEntity peopleEntity = peopleDAO.getPeopleDetailByPeopleId(peopleForm.getId());
		
		peopleEntity.setDob(peopleForm.getDob());
		peopleEntity.setName(peopleForm.getName());
		peopleEntity.setPhone(peopleForm.getPhone());
		peopleEntity.setIntrests(peopleForm.getIntrests());
		peopleEntity.setMember(peopleForm.getMember());
		peopleDAO.updatePeopleDetail(peopleEntity);
	}

	@Override
	public List<PeopleEntity> getListOfPeoples() {
		return peopleDAO.getListOfPeoples();
	}

	@Override
	public void save(PeopleEntity peopleEntity) {
		peopleDAO.save(peopleEntity);
	}

	@Override
	public void deleteAllPeple() {
		peopleDAO.deleteAllPeople();
	}

	@Override
	public PeopleEntity getPeopleDetailByUsername(String username) {
		return peopleDAO.getPeopleDetailByUsername(username);
	}

	@Override
	public void updatePeopleDetail(PeopleEntity peopleEntity) {
		peopleDAO.updatePeopleDetail(peopleEntity);
	}

}
