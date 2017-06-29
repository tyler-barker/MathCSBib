package com.barker.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.barker.model.Publication;
import com.barker.repository.PublicationRepository;

@RestController
@RequestMapping("/api/publications")
public class PublicationRestController {
	
	@Autowired
	private PublicationRepository pubRepo;
	
	@RequestMapping(method = RequestMethod.GET)
	List<Publication> listPublications() {
		return pubRepo.findAll();
	}
	
	@RequestMapping(value="/{pubId}", method=RequestMethod.GET)
	Publication getPublication(@PathVariable("pubId") Long pubId) {
		return pubRepo.findOne(pubId);
	}

}
