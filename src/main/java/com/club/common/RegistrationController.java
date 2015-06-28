package com.club.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.club.form.RegistrationForm;
import com.club.service.RegistrationService;

@Controller
public class RegistrationController {
	
	@Autowired
	private RegistrationService registrationService;
	
	@RequestMapping(value = "registration", method = RequestMethod.GET)
	public String registration(Model model) {
		
		return "registration";
	}
	
	@RequestMapping(value = "registration", method = RequestMethod.POST)
	public String getMainPage(Model model,RegistrationForm registrationForm,RedirectAttributes redirectAttributes) {
		
		Boolean isUsernameExist = registrationService.isUsernameAlreadyExist(registrationForm.getUsername());
		
		if(isUsernameExist){
			redirectAttributes.addFlashAttribute("error","UserName is already exist with us");
			return "redirect:/registration";
		}
		
		Boolean isEmailAddressAlreadyExist = registrationService.isEmailAlreadyExist(registrationForm.getEmail());
		
		if(isEmailAddressAlreadyExist){
			redirectAttributes.addFlashAttribute("error","Email is already exist with us");
			return "redirect:/registration";
		}
		
		registrationService.createUser(registrationForm);
		
		redirectAttributes.addFlashAttribute("success","Registration complete successfully..");
		
		return "redirect:/home";
	}
}
