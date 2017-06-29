package com.barker.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.barker.model.Author;
import com.barker.repository.AuthorRepository;

@RestController
@RequestMapping("/api/authors")
public class AuthorRestController {
	
	@Autowired
	private AuthorRepository authorRepo;
	
	@RequestMapping(method=RequestMethod.GET)
	List<Author> listAuthors() {
		return authorRepo.findAll();
	}
	
	@RequestMapping(value="/{authorId}", method=RequestMethod.GET)
	Author getAuthor(@PathVariable("authorId") Long authorId) {
		return authorRepo.findOne(authorId);
	}

}
