package com.barker.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Publication {

	private String title;
	@Id @GeneratedValue
	private Long pubId;
	@ManyToMany(mappedBy="publications")
	private Set<Author> authors = new HashSet<Author>();
	@ManyToMany
	private Set<Topic> topics = new HashSet<Topic>();
	private String url;
	
	
	public Long getPubId() {
		return pubId;
	}
	public void setPubId(Long pubId) {
		this.pubId = pubId;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Set<Author> getAuthors() {
		return authors;
	}
	public void setAuthors(Set<Author> authors) {
		this.authors = authors;
	}
	public Set<Topic> getTopics() {
		return topics;
	}
	public void setTopics(Set<Topic> topics) {
		this.topics = topics;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
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
