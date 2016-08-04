package com.barker.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.barker.model.Author;
import com.barker.model.Publication;
import com.barker.model.Topic;
import com.barker.repository.AuthorRepository;
import com.barker.repository.PublicationRepository;
import com.barker.repository.TopicRepository;

@Controller
@RequestMapping("/publications")
public class PublicationController {
	
	@Autowired
	private PublicationRepository pubRepo;
	
	@Autowired
	private AuthorRepository authorRepo;
	
	@Autowired
	private TopicRepository topicRepo;
	
	@RequestMapping(value="/newPublication", method=RequestMethod.GET)
	public String getNewPublicationForm(Model model) {
		model.addAttribute("newPublication", new Publication());
		model.addAttribute("authorList", authorRepo.findAll());
		//model.addAttribute("addedAuthors", new ArrayList<Long>());
		//model.addAttribute("firstAuthorId", 0);
		return "NewPublicationForm";
	}
	
	@RequestMapping(value="/newPublication", method=RequestMethod.POST)
	public String addPublication(@ModelAttribute("newPublication") Publication newPublication, 
							Model model) {
		Publication publicationToAdd = new Publication();
		publicationToAdd.setTitle(newPublication.getTitle());
		publicationToAdd.setUrl(newPublication.getUrl());
		for (Author author : newPublication.getAuthors()) {
			Author pubAuthor = authorRepo.findOne(author.getAuthorId());
			publicationToAdd.addAuthor(pubAuthor);
		}
		model.addAttribute("newPublication", publicationToAdd);
		
		pubRepo.save(publicationToAdd);
		return "PublicationAdded";
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String getPublications(Model model) {
		model.addAttribute("publications", pubRepo.findAll());
		return "PublicationsPage";
	}
	
	@RequestMapping(value="/{pubId}", method=RequestMethod.GET)
	public String getPublication(@PathVariable("pubId") Long pubId, Model model) {
		Publication pub = pubRepo.findOne(pubId);
		model.addAttribute("publication", pub);
		Set<Author> authors = pub.getAuthors();
		model.addAttribute("authors", authors);
		if (authors.size() > 1)
			model.addAttribute("authorText", "Authors");
		else
			model.addAttribute("authorText", "Author");
		model.addAttribute("topic", new Topic());
		return "PublicationPage";
	}
	
	@RequestMapping(value="/{pubId}/update", method=RequestMethod.GET)
	public String updatePublication(@PathVariable("pubId") Long pubId, Model model) {
		model.addAttribute("authorList", authorRepo.findAll());
		Publication publication = pubRepo.findOne(pubId);
		model.addAttribute("publication", publication);
		return "PublicationUpdatePage";
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String updateComplete(@ModelAttribute("publication") Publication pub,
								 Model model) {
		Publication updatedPub = pubRepo.findOne(pub.getPubId());
		updatedPub.setTitle(pub.getTitle());
		updatedPub.setUrl(pub.getUrl());
		for (Author author : updatedPub.getAuthors()) {
			if (!pub.getAuthors().contains(author))
				author.getPublications().remove(updatedPub);
		}
		for (Author author : pub.getAuthors()) {
			Author pubAuthor = authorRepo.findOne(author.getAuthorId());
			pubAuthor.getPublications().add(updatedPub);
		}
		updatedPub.setAuthors(pub.getAuthors());
		pubRepo.save(updatedPub);
		return "redirect:/publications/"+ Long.toString(pub.getPubId());
	}
	
	@RequestMapping(value="/{pubId}/delete", method=RequestMethod.POST)
	public String deletePublication(@PathVariable("pubId")Long pubId, Model model) {
		Publication pub = pubRepo.findOne(pubId);
		for (Author author : pub.getAuthors())
			author.getPublications().remove(pub);
		pub.setAuthors(null);
		pubRepo.delete(pub);
		return "DeletedPublicationPage";
	}
	
	@RequestMapping(value="/{pubId}/topics", method=RequestMethod.POST)
	public String addTopic(@PathVariable("pubId") Long pubId, 
						   @ModelAttribute("topic") Topic newTopic,
						   Model model) {
		topicRepo.save(newTopic);
		Publication pub = pubRepo.findOne(pubId);
		pub.getTopics().add(newTopic);
		pubRepo.save(pub);
		return "redirect:/publications/"+ Long.toString(pubId);
	}
	
}
