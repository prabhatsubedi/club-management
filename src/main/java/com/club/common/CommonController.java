package com.club.common;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.club.entity.PeopleEntity;
import com.club.service.ClubService;
import com.club.service.PeopleService;
import com.club.service.RegistrationService;


@Controller
public class CommonController {
	
	@Autowired
	private ClubService clubService;
	
	@Autowired
	private RegistrationService registrationService;
	
	@Autowired
	private PeopleService peopleService;
	
	@RequestMapping(value = "home", method = RequestMethod.GET)
	public String getMainPage(Model model) {
		
		List<String> clubnames = clubService.getlistOFClubsByName();
		model.addAttribute("clubnames", clubnames);
		return "home";
	}
	
	@RequestMapping(value = "adminDashBord",method =  RequestMethod.POST)
	public String getAdminHome(@RequestParam(value="username")String username,
			@RequestParam(value="password")String passwprd,Model model,HttpSession session,RedirectAttributes redirectAttributes){
		
		boolean isLoginSuc =  registrationService.userLogin(username,passwprd);
		if(isLoginSuc){
			session.setAttribute("userName",username);
			return "redirect:/userDashboard";
		}else if(username.equals("admin") && passwprd.equals("admin")){
			session.setAttribute("userName",username);
			return "adminDashboard";
		}else{
			redirectAttributes.addFlashAttribute("error","Username or password incorrect");
			return "redirect:/home";
		}
	}
	
	@RequestMapping(value = "adminDashBord",method =  RequestMethod.GET)
	public String adminDashBord(){
		return "adminDashboard";
	}
	
	@RequestMapping(value="logout", method=RequestMethod.GET)
	public String Logout(HttpSession session){
		session.removeAttribute("userName");
		session.invalidate();
		return "redirect:/home";
	}
	
	@RequestMapping(value = "userDashboard",method =  RequestMethod.GET)
	public String userDashBord(HttpSession session,Model model){
		
		String username = (String)session.getAttribute("userName");
		
		PeopleEntity peopleEntity = peopleService.getPeopleDetailByUsername(username);
		
		model.addAttribute("peopleEntity",peopleEntity);
		
		return "userDashboard";
	}
	
	@RequestMapping("Error404") 
	public String errorPage() {
		return "error404";
	}
	
}
