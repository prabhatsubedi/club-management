package com.club.dao;

import java.util.List;

import com.club.entity.PeopleEntity;

public interface PeopleDAO {
	
	public PeopleEntity getPeopleDetailByPeopleId(Integer peopleId);

	public void updatePeopleDetail(PeopleEntity peopleEntity);

	public List<PeopleEntity> getListOfPeoples();

	public void save(PeopleEntity peopleEntity);

	public void deleteAllPeople();

	public PeopleEntity getPeopleDetailByUsername(String username);

}
