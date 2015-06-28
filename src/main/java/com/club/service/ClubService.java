package com.club.service;

import java.util.List;

import com.club.entity.ClubEntity;
import com.club.form.ClubForm;

public interface ClubService {
		
	public ClubEntity getClubDetailByClubId(Integer clubid);

	public void updateClubDetail(ClubForm clubForm);

	public List<String> getlistOFClubsByName();

	public List<ClubEntity> getListOFclubs();

	public void save(ClubEntity clubEntity);

	public void deleteAllClubs();
	
}
