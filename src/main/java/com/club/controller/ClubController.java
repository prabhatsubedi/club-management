package com.club.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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

import com.club.entity.ClubEntity;
import com.club.entity.PeopleEntity;
import com.club.form.ClubForm;
import com.club.service.ClubService;
import com.club.service.PeopleService;

@Controller
public class ClubController {

	@Autowired
	private ClubService clubService;
	
	@Autowired
	private PeopleService peopleService;
	
	@RequestMapping(value = "clubTable", method = RequestMethod.GET)
	public String getListOFClubDetail(Model model) {

		List<ClubEntity> clubEntities = clubService.getListOFclubs();

		model.addAttribute("clubEntities", clubEntities);

		return "clubTable";
	}
	
	@RequestMapping(value="clubForm")
	public String clubForm(HttpSession session,Model model){
		
		Integer clubid = (Integer)session.getAttribute("clubid");
		
		ClubEntity clubEntity = clubService.getClubDetailByClubId(clubid);

		if (clubEntity != null) {
			
			model.addAttribute("clubEntity", clubEntity);
		}
		return "clubForm";
	}
	
	@RequestMapping(value = "editClubDetail/{clubid}", method = RequestMethod.GET)
	public String getClubForm(Model model,@PathVariable("clubid") Integer clubid,HttpSession session) {
		
		session.setAttribute("clubid",clubid);
		
		return "redirect:/clubForm";
	}

	@RequestMapping(value = "updateClubDetail", method = RequestMethod.POST)
	public String updateClubDetails(ClubForm clubForm,HttpSession session) {
		
		Integer clubid = (Integer)session.getAttribute("clubid");
		
		clubForm.setId(clubid);
		
		clubService.updateClubDetail(clubForm);

		return "redirect:/clubTable";
	}

	@RequestMapping(value = "uploadClubCsv", method = RequestMethod.POST)
	public String uloadClubCsv(MultipartHttpServletRequest request) throws IOException {
		
		clubService.deleteAllClubs();
		
		Iterator<String> itr = request.getFileNames();
		MultipartFile mpf = request.getFile(itr.next());

		InputStream is = mpf.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,
				"UTF-8"));
		String line = "";
		ClubEntity clubEntity = new ClubEntity();

		int count = 0;
		while ((line = br.readLine()) != null) {

			line = line.replace("\"", "");
			System.out.println(line);
			count = count + 1;
			if (count == 1)
				continue;

			String[] columnArray = line.split(",");
			clubEntity.setName(columnArray[0]);
			clubEntity.setWebsite(columnArray[1]);
			clubEntity.setEmail(columnArray[2]);
			clubEntity.setPhone(columnArray[3]);
			clubEntity.setDescription(columnArray[4]);
			clubEntity.setDues(columnArray[5]);
			String activities = "";

			for (int i = 6; i < columnArray.length; i++) {
				if (i < columnArray.length - 1)
					activities += columnArray[i] + ",";
				else
					activities += columnArray[i];
			}
			clubEntity.setActivities(activities);
			clubService.save(clubEntity);

			System.out.println(columnArray[0]);
		}
		return "redirect:/clubTable";
	}
	
	@RequestMapping(value = "exportClubCSV", method = RequestMethod.GET)
	public void exportPeopleCSV(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		BufferedWriter writer = new BufferedWriter(response.getWriter());

		List<ClubEntity> clubEntities = clubService.getListOFclubs();
		String csvString = "";

		csvString += "name,website, email, phone,description, dues, activities\n";

		for (int i = 0; i < clubEntities.size(); i++) {
			ClubEntity clubEntity = clubEntities.get(i);
			csvString += clubEntity.getName() + "," + clubEntity.getWebsite()
					+ "," + clubEntity.getEmail() + "," + clubEntity.getPhone()+
					",\""
					+ clubEntity.getDescription() + "\","
					+ clubEntity.getDues() + ",\"" + clubEntity.getActivities()
					+ "\"\n";
		}

		response.setContentType("text/csv");
		String headerValue = String.format("attachment; filename=\"%s\"",
				"club.csv");
		response.setHeader("Content-Disposition", headerValue);

		writer.write(csvString);

		writer.flush();
		writer.close();
	}
	
	@RequestMapping(value = "searchClub" ,method = RequestMethod.GET)
	public String searchClub(Model model){
		
		List<ClubEntity> clubEntities = clubService.getListOFclubs();

		model.addAttribute("clubEntities", clubEntities);

		return "searchClub";
	}
	
	@RequestMapping(value = "viewClubDetails/{clubid}" ,method = RequestMethod.GET)
	public String viewClubDetails(Model model,@PathVariable("clubid")Integer clubid,HttpSession session){
		
		session.setAttribute("clubid",clubid);
		
		return "redirect:/viewClubDetails";
	}
	
	
	@RequestMapping(value = "viewClubDetails" ,method = RequestMethod.GET)
	public String viewClubDetailByClubId(Model model,HttpSession session){
		
		Integer clubid = (Integer)session.getAttribute("clubid");
		
		ClubEntity clubEntity = clubService.getClubDetailByClubId(clubid);
		
		String username = (String) session.getAttribute("userName");
		
		PeopleEntity peopleEntity = peopleService.getPeopleDetailByUsername(username);
		
		if(peopleEntity.getMember() != "" && peopleEntity.getMember() !=null){
			if(peopleEntity.getMember().toLowerCase().contains(clubEntity.getName().toLowerCase())){
				model.addAttribute("clubExist", "clubExist");
			}
		}	
		
		model.addAttribute("clubEntity",clubEntity);
		
		return "viewClubDetails";
	}
	
	@RequestMapping(value = "joinClub",method = RequestMethod.GET)
	public String joinClub(Model model,HttpSession session){
		
		Integer clubid = (Integer)session.getAttribute("clubid");
		
		String username = (String) session.getAttribute("userName");
		
		ClubEntity clubEntity = clubService.getClubDetailByClubId(clubid);
		
		PeopleEntity peopleEntity = peopleService.getPeopleDetailByUsername(username);
		
		String member = "";
		if(peopleEntity.getMember() != "" && peopleEntity.getMember() !=null){
			member= peopleEntity.getMember();
			if(member.equals("")){
				peopleEntity.setMember(clubEntity.getName());
			}else{
				peopleEntity.setMember(member +","+clubEntity.getName());
			}
		}
		
		if(member == "") {
			peopleEntity.setMember(clubEntity.getName());
		}
		peopleService.updatePeopleDetail(peopleEntity);
		
		return "redirect:/userDashboard";
		
	}
	
}
