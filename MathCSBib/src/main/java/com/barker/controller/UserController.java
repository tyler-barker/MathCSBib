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

import com.barker.model.User;
import com.barker.repository.PublicationRepository;
import com.barker.repository.UserRepository;
import com.barker.service.MailService;
import com.barker.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private PublicationRepository pubRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MailService mailService;
	
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
		if (!user.isMatchingPasswords()) {
			result.rejectValue("password", "error.mismatchedPasswords", "The passwords do not match");
			return "register";
		}
		if (!user.validateEmail()) {
			result.rejectValue("email", "error.invalidEmail", "The email address is not valid");
			return "register";
		}
		User registeredUser = userService.register(user);
		if (registeredUser != null) {
			mailService.sendWelcomeEmail(user.getUserName(), user.getEmail());
			return "registerSuccess";
		}
		else {
			result.rejectValue("username", "error.alreadyExists", "This username already exists.");
			return "register";
		}
	}
	
	@RequestMapping(value="/user", method=RequestMethod.GET)
	public String getUser(Model model, HttpServletRequest request) {
		User currentUser = userRepo.findOneByUserName(request.getUserPrincipal().getName());
		model.addAttribute("currentUser", currentUser);
		model.addAttribute("publications", currentUser.getFavorites());
		return "UserPage";
	}
	
	@RequestMapping(value="/user/favorite/{pubId}", method=RequestMethod.POST)
	public String addPublication(@PathVariable("pubId") Long pubId, Model model, HttpServletRequest request) {
		User currentUser = userRepo.findOneByUserName(request.getUserPrincipal().getName());
		currentUser.getFavorites().add(pubRepo.findOne(pubId));
		userRepo.save(currentUser);
		return "redirect:/publications/"+ Long.toString(pubId);
	}
}
