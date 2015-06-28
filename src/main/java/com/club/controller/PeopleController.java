package com.club.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.club.entity.PeopleEntity;
import com.club.form.PeopleForm;
import com.club.service.ClubService;
import com.club.service.PeopleService;

@Controller
public class PeopleController {

	@Autowired
	private PeopleService peopleService;

	@Autowired
	private ClubService clubService;

	@RequestMapping(value = "peopleTable", method = RequestMethod.GET)
	public String getListOfPeopleDetail(Model model) {

		List<PeopleEntity> peopleEntities = peopleService.getListOfPeoples();

		model.addAttribute("peopleEntities", peopleEntities);

		return "peopleTable";
	}
	
	@RequestMapping(value="peopleForm")
	public String clubForm(HttpSession session,Model model){
		
		Integer peopleid = (Integer)session.getAttribute("peopleid");
		
		List<String> clubnames = clubService.getlistOFClubsByName();
		PeopleEntity peopleEntity = peopleService.getPeopleDetailByPeopleId(peopleid);
		
		if(peopleEntity !=null && peopleEntity.getMember()!= null){
			List<HashMap<String, Object>> results = new ArrayList<HashMap<String, Object>>();
			
			for (int i = 0; i < clubnames.size(); i++) {
				HashMap<String, Object> value = new HashMap<>();
				if(peopleEntity.getMember().toLowerCase().contains(clubnames.get(i).toLowerCase())) {
					value.put("checkbox", true);
				} else {
					value.put("checkbox", false);
				}
				value.put("clubname", clubnames.get(i));
				results.add(value);
			}
			model.addAttribute("results", results);
		}
		model.addAttribute("peopleEntity", peopleEntity);
		return "peopleForm";
	}

	@RequestMapping(value = "editPeopleDetail/{peopleid}", method = RequestMethod.GET)
	public String getPeopleForm(Model model,@PathVariable("peopleid") Integer peopleid,HttpSession session) {
		
		session.setAttribute("peopleid", peopleid);

		return "redirect:/peopleForm";
	}

	@RequestMapping(value = "updatePeopleDetail", method = RequestMethod.POST)
	public String updateClubDetails(PeopleForm peopleForm,HttpSession session) {
		
		Integer peopleid = (Integer)session.getAttribute("peopleid");
		
		peopleForm.setId(peopleid);
		
		peopleService.updatePeopleDetail(peopleForm);

		return "redirect:/peopleTable";
	}

	@RequestMapping(value = "uploadPeopleCsv", method = RequestMethod.POST)
	public String uloadClubCsv(MultipartHttpServletRequest request) throws IOException, ParseException {
		
		peopleService.deleteAllPeple();

		Iterator<String> itr = request.getFileNames();
		MultipartFile mpf = request.getFile(itr.next());

		InputStream is = mpf.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		String line = "";
		PeopleEntity peopleEntity = new PeopleEntity();
		

		int count = 0;
		while ((line = br.readLine()) != null) {

			count = count +1 ;
			if(count == 1)
				continue;
			
			line = line.replace("\"", "");
			String[] columnArray = line.split(",");
			peopleEntity.setName(columnArray[0]);
			peopleEntity.setDob(columnArray[1]);
			peopleEntity.setPhone(columnArray[2]);
			
			String interests = "";
			String members = "";
			
			for (int i = 3; i < columnArray.length; i++) {
				
				if(columnArray[i].contains("club"))
					members += columnArray[i]+",";
				else
					interests += columnArray[i]+",";
			}
			
			members = members.substring(0, members.length()-1);
			interests = interests.substring(0, interests.length()-1);
			peopleEntity.setIntrests(interests);
			peopleEntity.setMember(members);
			peopleService.save(peopleEntity);
			System.out.println(columnArray[0]);
		}
		return "redirect:/peopleTable";
	}
	
	
	@RequestMapping(value = "exportPeopleCSV", method = RequestMethod.GET)
	public void exportPeopleCSV(HttpServletRequest request, HttpServletResponse response) throws IOException {

		 BufferedWriter writer = new BufferedWriter(response.getWriter());
		 List<PeopleEntity> peopleEntities = peopleService.getListOfPeoples();
	   	 String csvString = "";	
		 
	   	 csvString += "name,dob,phone,interests,member\n";
			
			for (int i = 0; i < peopleEntities.size(); i++) {
				PeopleEntity peopleEntity = peopleEntities.get(i);
				csvString += peopleEntity.getName()+","+peopleEntity.getDob()+","+peopleEntity.getPhone()+",\""+
						peopleEntity.getIntrests()+"\",\""+peopleEntity.getMember()+"\"\n";
			}
			
		response.setContentType("text/csv");
	    String headerValue = String.format("attachment; filename=\"%s\"","people.csv");
	    response.setHeader("Content-Disposition", headerValue);
	      
	    writer.write(csvString);
					
		writer.flush(); 
		writer.close();
	}
	
}
