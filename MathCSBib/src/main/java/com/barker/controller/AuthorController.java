package com.barker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.barker.dao.AuthorDAO;
import com.barker.model.Author;

@Controller
@RequestMapping("/authors")
public class AuthorController {
	
	@Autowired
	private AuthorDAO authorDAO;
	
	@RequestMapping(value="/newAuthor", method=RequestMethod.GET)
	public String getNewAuthorForm(Model model) {
		model.addAttribute("newAuthor", new Author());
		return "NewAuthorForm";
	}
	
	@RequestMapping(value="/newAuthor", method=RequestMethod.POST)
	public String addAuthor(@ModelAttribute("newAuthor") Author newAuthor, Model model) {
		model.addAttribute("newAuthor", newAuthor);
		authorDAO.save(newAuthor);
		return "AuthorAdded";
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String getAuthors(Model model) {
		model.addAttribute("authors", authorDAO.getAuthors());
		return "AuthorsPage";
	}
	
	@RequestMapping("/{authorId}")
	public String getAuthor(@PathVariable("authorId") long authorId, Model model) {
		Author author = authorDAO.getAuthor(authorId);
		model.addAttribute("author", author);
		model.addAttribute("publications", authorDAO.getPublicationsFromAuthor(authorId));
		return "AuthorPage";
	}
	
	@RequestMapping(value="/{authorId}/update", method=RequestMethod.GET)
	public String updateAuthor(@PathVariable("authorId") long authorId, Model model) {
		Author author = authorDAO.getAuthor(authorId);
		model.addAttribute("author", author);
		return "AuthorUpdatePage";
	}
	
	@RequestMapping(value="/{authorId}/update", method=RequestMethod.POST)
	public String updateComplete(@ModelAttribute("author") Author author, Model model) {
		authorDAO.update(author);
		return "redirect:/authors/"+ Long.toString(author.getAuthorId());
	}
	
	
	
	@RequestMapping(value="/{authorId}/delete", method=RequestMethod.POST)
	public String deleteAuthor(@PathVariable("authorId") long authorId, Model model) {
		authorDAO.delete(authorId);
		return "DeletedAuthorPage";
	}

}
