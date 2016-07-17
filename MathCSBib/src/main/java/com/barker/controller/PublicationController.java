package com.barker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.barker.dao.PublicationDAO;
import com.barker.model.Publication;

@Controller
@RequestMapping("/publications")
public class PublicationController {
	
	@Autowired
	private ApplicationContext ctx;
	
	@RequestMapping(value="/newPublication", method=RequestMethod.GET)
	public String getNewPublicationForm(Model model) {
		model.addAttribute("newPublication", new Publication());
		return "NewPublicationForm";
	}
	
	@RequestMapping(value="/newPublication", method=RequestMethod.POST)
	public String addAuthor(@ModelAttribute("newPublication") Publication newPublication, Model model) {
		model.addAttribute("newPublication", newPublication);
		PublicationDAO pubDAO = ctx.getBean(PublicationDAO.class);
		pubDAO.save(newPublication);
		return "PublicationAdded";
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String getPublications(Model model) {
		PublicationDAO pubDAO = ctx.getBean(PublicationDAO.class);
		model.addAttribute("publications", pubDAO.getPublications());
		return "PublicationsPage";
	}
	
	@RequestMapping("/{pubId}")
	public String getPublication(@PathVariable("pubId") long pubId, Model model) {
		PublicationDAO pubDAO = ctx.getBean(PublicationDAO.class);
		model.addAttribute("publication", pubDAO.getPublication(pubId));
		return "PublicationPage";
	}
	
}
