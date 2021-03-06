package com.springBoot.fifa;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import domain.Stadium;
import domain.Wedstrijd;
import service.StadiumDao;
import service.WedstrijdDao;

@Controller
@SessionAttributes("stadium")
@RequestMapping("fifa/*")
public class StadiumController {

	@Autowired
	private WedstrijdDao wedstrijdDao;
	
	@GetMapping()
	public String showStadiumPage(HttpSession session, @ModelAttribute Wedstrijd wedstrijd, BindingResult result, 
			Model model, HttpServletRequest request) {
		Stadium stadium;
		if (session.getAttribute("stadium") != null) {
		stadium = (Stadium) session.getAttribute("stadium");
		session.setAttribute("stadiumUrlName", stadium.getName().replaceAll("\\s", "").replace("Stadium", ""));
		session.setAttribute("wedstrijden",wedstrijdDao.getAllWedstrijdenByStadiumId(stadium.getStadium_id()));
		}
		return "stadiumView";
	}
	
}
