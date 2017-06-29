package com.barker.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Publication {

	private String title;
	@Id @GeneratedValue
	private Long pubId;
	@ManyToMany(mappedBy="publications")
	@JsonBackReference
	private Set<Author> authors = new HashSet<Author>();
	@ManyToMany
	private Set<Topic> topics = new HashSet<Topic>();
	private String url;
	
	public void addAuthor(Author author) {
		this.authors.add(author);
		author.getPublications().add(this);
	}
	
	public void removeAuthor(Author author) {
		this.authors.remove(author);
		author.getPublications().remove(this);
	}
	
	public boolean hasAuthor(Author author) {
		return authors.contains(author);
	}
	
}
