package com.club.service;

import java.util.List;

import com.club.entity.PeopleEntity;
import com.club.form.PeopleForm;

public interface PeopleService {
	
	public PeopleEntity getPeopleDetailByPeopleId(Integer peopleId);

	public void updatePeopleDetail(PeopleForm peopleForm);

	public List<PeopleEntity> getListOfPeoples();

	public void save(PeopleEntity peopleEntity);

	public void deleteAllPeple();

	public PeopleEntity getPeopleDetailByUsername(String username);

	public void updatePeopleDetail(PeopleEntity peopleEntity);
}
