package com.barker.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Publication {

	private String title;
	@Id @GeneratedValue
	private long pubId;
	@ManyToMany(mappedBy="publications", fetch = FetchType.EAGER)
	private Set<Author> authors = new HashSet<Author>();
	@ManyToMany(fetch = FetchType.EAGER)
	private Set<Topic> topics = new HashSet<Topic>();
	private String url;
	
	
	public long getPubId() {
		return pubId;
	}
	public void setPubId(long pubId) {
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
	
	public boolean hasAuthor(Author author) {
		return authors.contains(author);
	}
	
}
