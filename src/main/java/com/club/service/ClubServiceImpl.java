package com.club.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.club.dao.ClubDAO;
import com.club.entity.ClubEntity;
import com.club.form.ClubForm;

@Service
public class ClubServiceImpl implements ClubService{
	
	@Autowired
	private ClubDAO clubDAO;
	
	@Override
	public ClubEntity getClubDetailByClubId(Integer clubid) {
		return clubDAO.getClubDetailByClubId(clubid);
	}

	@Override
	public void updateClubDetail(ClubForm clubForm) {
		
		ClubEntity clubEntity = clubDAO.getClubDetailByClubId(clubForm.getId());
		
		clubEntity.setName(clubForm.getName());
		clubEntity.setWebsite(clubForm.getWebsite());
		clubEntity.setPhone(clubForm.getPhone());
		clubEntity.setEmail(clubForm.getEmail());
		clubEntity.setActivities(clubForm.getActivities());
		clubEntity.setDues(clubForm.getDues());
		clubEntity.setDescription(clubForm.getDescription());
		
		clubDAO.updateClubDetail(clubEntity);
	}

	@Override
	public List<String> getlistOFClubsByName() {
		List<String> clubnames = new ArrayList<String>();
		List<ClubEntity> clubEntities = clubDAO.getListOFClubEntity();
		for(ClubEntity clubEntity : clubEntities){
			String clubname = clubEntity.getName();
			clubnames.add(clubname);
		}
		return clubnames;
	}

	@Override
	public List<ClubEntity> getListOFclubs() {
		return clubDAO.getListOFClubEntity();
	}

	@Override
	public void save(ClubEntity clubEntity) {
		clubDAO.save(clubEntity);
	}

	@Override
	public void deleteAllClubs() {
		clubDAO.deleteAllClubs();
	}
	
	
}
