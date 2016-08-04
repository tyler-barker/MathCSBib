package com.barker.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.barker.model.User;
import com.barker.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository repo;

	@Autowired
	private HttpSession httpSession;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = repo.findOneByUserName(username);

		if (user == null)
			throw new UsernameNotFoundException(username);
		httpSession.setAttribute("CURRENT_USER", user);
		List<GrantedAuthority> auth = AuthorityUtils.commaSeparatedStringToAuthorityList(user.getRole());

		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), auth);
	}

	public User register(User user) {
		System.out.println("Registering User");
		System.out.println(user.getUserName());
		System.out.println(user.getPassword());
		user.setPassword(encodeUserPassword(user.getPassword()));
		System.out.println(user.getPassword());
		if (this.repo.findOneByUserName(user.getUserName()) == null) {
			this.repo.save(user);
			return user;
		}

		return null;

	}

	public static String encodeUserPassword(String password) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder.encode(password);
	}

	public static boolean matchesPassword(String rawPassword, String encodedPassword) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder.matches(rawPassword, encodedPassword);
	}

}
