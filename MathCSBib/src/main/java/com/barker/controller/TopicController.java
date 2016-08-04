package com.barker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.barker.model.Topic;
import com.barker.repository.TopicRepository;

@Controller
@RequestMapping("/topics")
public class TopicController {
	
	@Autowired
	private TopicRepository topicRepo;
	
	@RequestMapping(method=RequestMethod.GET)
	public String getTopics(Model model) {
		model.addAttribute("topics", topicRepo.findAll());
		return "TopicsPage";
	}
	
	@RequestMapping("/{topicId}")
	public String getTopic(@PathVariable("topicId") Long topicId, Model model) {
		Topic topic = topicRepo.findOne(topicId);
		model.addAttribute("topic", topic);
		model.addAttribute("publications", topicRepo.findPublicationsByTopic(topic));
		return "TopicPage";
	}
	
}
