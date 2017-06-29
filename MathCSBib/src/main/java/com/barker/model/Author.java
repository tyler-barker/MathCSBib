package com.barker.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Author {

	private String firstName;
	private String lastName;
	private String middleInitial;
	@Id @GeneratedValue
	private Long authorId;
	
	@ManyToMany
	@JsonManagedReference
	private Set<Publication> publications = new HashSet<Publication>();
	private String picture;
	private String university;
	
}
