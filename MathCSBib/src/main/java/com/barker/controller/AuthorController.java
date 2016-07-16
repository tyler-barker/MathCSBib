package com.barker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.barker.dao.AuthorDAO;
import com.barker.model.Author;

@Controller
@RequestMapping("/authors")
public class AuthorController {
	
	@Autowired
	private ApplicationContext ctx;
	
	@RequestMapping(value="/newAuthor", method=RequestMethod.GET)
	public String getNewAuthorForm(Model model) {
		model.addAttribute("newAuthor", new Author());
		return "NewAuthorForm";
	}
	
	@RequestMapping(value="/newAuthor", method=RequestMethod.POST)
	public String addAuthor(@ModelAttribute("newAuthor") Author newAuthor, Model model) {
		model.addAttribute("newAuthor", newAuthor);
		AuthorDAO authorDAO = ctx.getBean(AuthorDAO.class);
		authorDAO.save(newAuthor);
		return "AuthorAdded";
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String getAuthors(Model model) {
		return "AuthorsPage";
	}

}
