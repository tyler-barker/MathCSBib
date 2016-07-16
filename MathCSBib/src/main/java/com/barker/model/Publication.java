package com.barker.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Publication {

	private String title;
	@Id @GeneratedValue
	private long pubId;
	@ManyToMany
	private List<Author> authors = new ArrayList<Author>();
	@ElementCollection
	private List<String> topics = new ArrayList<String>();
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
	public List<Author> getAuthors() {
		return authors;
	}
	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}
	public List<String> getTopics() {
		return topics;
	}
	public void setTopics(List<String> topics) {
		this.topics = topics;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}
