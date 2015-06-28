package com.club.service;

import com.club.form.RegistrationForm;

public interface RegistrationService {

	Boolean isUsernameAlreadyExist(String username);

	Boolean isEmailAlreadyExist(String email);

	void createUser(RegistrationForm registrationForm);

	boolean userLogin(String username, String password);

}
