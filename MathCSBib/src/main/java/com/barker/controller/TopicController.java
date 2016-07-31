package com.barker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.barker.dao.PublicationDAO;
import com.barker.model.Topic;

@Controller
@RequestMapping("/topics")
public class TopicController {
	
	@Autowired
	private ApplicationContext ctx;
	
	@RequestMapping(method=RequestMethod.GET)
	public String getTopics(Model model) {
		PublicationDAO pubDAO = ctx.getBean(PublicationDAO.class);
		model.addAttribute("topics", pubDAO.getTopics());
		return "TopicsPage";
	}
	
	@RequestMapping("/{topicId}")
	public String getTopic(@PathVariable("topicId") long topicId, Model model) {
		PublicationDAO pubDAO = ctx.getBean(PublicationDAO.class);
		Topic topic = pubDAO.getTopic(topicId);
		model.addAttribute("topic", topic);
		model.addAttribute("publications", pubDAO.getPublicationsByTopic(topicId));
		return "TopicPage";
	}
	
}
