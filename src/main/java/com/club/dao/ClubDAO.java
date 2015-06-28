package com.club.dao;

import java.util.List;

import com.club.entity.ClubEntity;

public interface ClubDAO {

	public ClubEntity getClubDetailByClubId(Integer clubid);

	public void updateClubDetail(ClubEntity clubEntity);

	public List<ClubEntity> getListOFClubEntity();

	public void save(ClubEntity clubEntity);

	public void deleteAllClubs();
		

}
