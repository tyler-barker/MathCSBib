package com.barker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/publications")
public class PublicationController {
	
	@RequestMapping(method = RequestMethod.GET)
	public String getPublications(Model model) {
		return "PublicationsPage";
	}
	
//	@RequestMapping("/{pubId}")
//	public String getPublication(@PathVariable("pubId") String pubId, Model model) {
//		for (Publication paper : Database.getPublications()) {
//			if (paper.getPubId().equalsIgnoreCase(pubId)) {
//				model.addAttribute("publication", paper);
//				dao.save(paper);
//				return "PublicationPage";
//			}
//		}
//		return "PublicationPage";
//	}
	
}
