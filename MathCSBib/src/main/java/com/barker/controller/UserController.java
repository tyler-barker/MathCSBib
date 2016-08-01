package com.barker.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.barker.dao.PublicationDAO;
import com.barker.dao.UserRepository;
import com.barker.model.User;
import com.barker.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private PublicationDAO pubDAO;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private UserService userService;
	
	@Autowired
    protected AuthenticationManager authenticationManager;
	
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String register(User user) {
		return "register";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String registerPost(@Valid User user, BindingResult result) {
		if (result.hasErrors())
			return "register";
		User registeredUser = userService.register(user);
		if (registeredUser != null)
			return "registerSuccess";
		else {
			result.rejectValue("username", "error.alreadyExists", "This username already exists.");
			return "register";
		}
	}
	
	@RequestMapping(value="/user", method=RequestMethod.GET)
	public String getUser(Model model, HttpServletRequest request) {
		User currentUser = userRepo.findOneByUserName(request.getUserPrincipal().getName());
		model.addAttribute("currentUser", currentUser);
		model.addAttribute("publications", pubDAO.getFavoritePublications(currentUser.getId()));
		return "UserPage";
	}
	
	@RequestMapping(value="/user/favorite/{pubId}", method=RequestMethod.POST)
	public String addPublication(@PathVariable("pubId") long pubId, Model model, HttpServletRequest request) {
		User currentUser = userRepo.findOneByUserName(request.getUserPrincipal().getName());
		pubDAO.favoritePublication(pubId, currentUser.getId());
		return "redirect:/publications/"+ Long.toString(pubId);
	}
}
