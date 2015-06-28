package com.club.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.club.entity.EventEntity;
import com.club.entity.PeopleEntity;
import com.club.form.EventForm;
import com.club.form.PeopleForm;
import com.club.service.EventService;
import com.club.service.PeopleService;

@Controller
public class EventController {
	
	@Autowired
	private EventService eventService;
	
	@Autowired
	private PeopleService peopleService;
	
	@RequestMapping(value = "/createEvent",method = RequestMethod.GET)
	private String createEvent() {
		return "createEvent";
	}
	
	@RequestMapping(value = "/createEvent" , method = RequestMethod.POST)
	private String createEvent(EventForm eventForm,Model model) {
		eventService.save(eventForm);
		model.addAttribute("eventSucc","Event is created successfully");
		return "createEvent" ;
	}
	
	@RequestMapping(value = "/eventList",method = RequestMethod.GET)
	public String eventList(Model model) {
		List<EventEntity> entities = eventService.getEventList();
		model.addAttribute("eventList",entities);
		return "events";
	}
	
	@RequestMapping(value = "/viewEventDetail/{eventId}", method = RequestMethod.GET)
	public String eventDetail(Model model,@PathVariable("eventId") Integer eventId,HttpSession session) {
		
		session.setAttribute("eventId",eventId);
		
		return "redirect:/viewEventDetail";
	}

	@RequestMapping(value = "/viewEventDetail", method = RequestMethod.GET)
	public String getClubForm(Model model,HttpSession session) {
		
		int eventId = (int) session.getAttribute("eventId");
		
		String username = (String)session.getAttribute("userName");
		
		PeopleEntity peopleEntity = peopleService.getPeopleDetailByUsername(username);
		
		EventEntity eventEntity = eventService.getEventDetailById(eventId) ;
		
		model.addAttribute("eventEntity",eventEntity);
		
		model.addAttribute("join","join");
		
		for(PeopleEntity entity : eventEntity.getPeopleEntities()) {
			if(entity.getId() == peopleEntity.getId()) {
				model.addAttribute("join",null);
				break;
			}
		}
		
		return "viewEventDetail";
	}
	
	@RequestMapping(value = "/joinEvent/{eventId}", method = RequestMethod.GET)
	public String joinEvent(Model model,@PathVariable("eventId") Integer eventId,HttpSession session) {
		
		session.setAttribute("eventId",eventId);
		
		return "redirect:/joinEvent";
	}

	@RequestMapping(value = "/joinEvent", method = RequestMethod.GET)
	public String joinUserInEvent(Model model,HttpSession session) {
		
		int eventId = (int) session.getAttribute("eventId");
		EventEntity eventEntity = eventService.getEventDetailById(eventId) ;
		
		String username = (String)session.getAttribute("userName");
		
		PeopleEntity peopleEntity = peopleService.getPeopleDetailByUsername(username);
		
		List<PeopleEntity> peopleEntities = eventEntity.getPeopleEntities();
		peopleEntities.add(peopleEntity);
		
		eventEntity.setPeopleEntities(peopleEntities);
		
		eventService.update(eventEntity);
		
		return "redirect:/viewEventDetail";
	}
	
	
}
