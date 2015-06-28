package com.club.dao;

import com.club.entity.LoginEntity;

public interface RegistrationDAO {

	public void createLoginEntity(LoginEntity loginEntity);

	public boolean loginUser(String username, String password);

	public boolean isUsernameAlreadyExist(String username);

	public boolean isEmailAlreadyExist(String email);

}
