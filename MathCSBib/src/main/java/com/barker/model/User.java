package com.barker.model;

import java.util.HashSet;
import java.util.Set;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="users")
public class User {
	
	@Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@NotNull
	@Size(min=3, max=50, message="Username must be between 3 and 50 characters.")
	@Column(unique=true)
	private String userName;
	
	@NotNull
	@Column(unique=true)
	private String email;
	
	@NotNull
	@Size(min=6, max=100, message="Password must be between 6 and 100 characters.")
	private String password;
	
	@Transient
	private String confirmPassword;
	
	private String role = "ROLE_USER";
	
	@ManyToMany(fetch = FetchType.EAGER)
	private Set<Publication> favorites = new HashSet<Publication>();
	
	public boolean isMatchingPasswords() {
		return this.password.equals(this.confirmPassword);
	}
	
	public boolean validateEmail() {
       boolean isValid = false;
        try {
            // Create InternetAddress object and validated the supplied
            // address which is this case is an email address.
            InternetAddress internetAddress = new InternetAddress(this.email);
            internetAddress.validate();
            isValid = true;
        } catch (AddressException e) {
            e.printStackTrace();
        }
        return isValid;
	}

}
