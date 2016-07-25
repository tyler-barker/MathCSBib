package com.barker.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.barker.dao.AuthorDAO;
import com.barker.dao.PublicationDAO;
import com.barker.model.Author;
import com.barker.model.Publication;

@Controller
@RequestMapping("/publications")
public class PublicationController {
	
	@Autowired
	private ApplicationContext ctx;
	
	@RequestMapping(value="/newPublication", method=RequestMethod.GET)
	public String getNewPublicationForm(Model model) {
		model.addAttribute("newPublication", new Publication());
		AuthorDAO authorDAO = ctx.getBean(AuthorDAO.class);
		model.addAttribute("authorList", authorDAO.getAuthors());
		//model.addAttribute("addedAuthors", new ArrayList<Long>());
		//model.addAttribute("firstAuthorId", 0);
		return "NewPublicationForm";
	}
	
	@RequestMapping(value="/newPublication", method=RequestMethod.POST)
	public String addAuthor(@ModelAttribute("newPublication") Publication newPublication, 
							Model model) {
		model.addAttribute("newPublication", newPublication);
		PublicationDAO pubDAO = ctx.getBean(PublicationDAO.class);
		pubDAO.save(newPublication);
		for (Author author: newPublication.getAuthors()) {
			pubDAO.addAuthor(newPublication.getPubId(), author.getAuthorId());
		}
		return "PublicationAdded";
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String getPublications(Model model) {
		PublicationDAO pubDAO = ctx.getBean(PublicationDAO.class);
		model.addAttribute("publications", pubDAO.getPublications());
		return "PublicationsPage";
	}
	
	@RequestMapping(value="/{pubId}", method=RequestMethod.GET)
	public String getPublication(@PathVariable("pubId") long pubId, Model model) {
		PublicationDAO pubDAO = ctx.getBean(PublicationDAO.class);
		Publication pub = pubDAO.getPublication(pubId);
		model.addAttribute("publication", pub);
		List<Author> authors = pubDAO.getAuthorsFromPublication(pubId);
		model.addAttribute("authors", authors);
		if (authors.size() > 1)
			model.addAttribute("authorText", "Authors");
		else
			model.addAttribute("authorText", "Author");
		return "PublicationPage";
	}
	
	@RequestMapping(value="/{pubId}/update", method=RequestMethod.GET)
	public String updatePublication(@PathVariable("pubId") long pubId, Model model) {
		PublicationDAO pubDAO = ctx.getBean(PublicationDAO.class);
		AuthorDAO authorDAO = ctx.getBean(AuthorDAO.class);
		model.addAttribute("authorList", authorDAO.getAuthors());
		Publication publication = pubDAO.getPublication(pubId);
		model.addAttribute("publication", publication);
		return "PublicationUpdatePage";
	}
	
	@RequestMapping(value="/{pubId}/update", method=RequestMethod.POST)
	public String updateComplete(@ModelAttribute("publication") Publication pub,
								 Model model) {
		PublicationDAO pubDAO = ctx.getBean(PublicationDAO.class);
		pubDAO.update(pub);
		return "redirect:/publications/"+ Long.toString(pub.getPubId());
	}
	
	@RequestMapping(value="/{pubId}/delete", method=RequestMethod.POST)
	public String deletePublication(@PathVariable("pubId") long pubId, Model model) {
		PublicationDAO pubDAO = ctx.getBean(PublicationDAO.class);
		pubDAO.delete(pubId);
		return "DeletedPublicationPage";
	}
	
}
