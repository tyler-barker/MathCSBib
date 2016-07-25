package com.barker.dao;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.barker.model.Author;
import com.barker.model.Publication;
import com.barker.model.Topic;

@Repository
@Transactional
public class PublicationDAO {
	
	private final SessionFactory sf;

    @Autowired
    public PublicationDAO(SessionFactory sf) {
        this.sf=sf;
    }
	
	public void save(Publication pub) {
		sf.getCurrentSession().saveOrUpdate(pub);
	}

	public Publication getPublication(long id) {
		Publication pub = (Publication) sf.getCurrentSession().get(Publication.class, id);
		return pub;
	}
	
	@SuppressWarnings("unchecked")
	public Set<Publication> getPublications() {
		return new HashSet<Publication>(sf.getCurrentSession().createQuery("from Publication").list());
	}
	
	public Set<Author> getAuthorsFromPublication(long id) {
		Publication publication = (Publication) sf.getCurrentSession().get(Publication.class, id);
		return publication.getAuthors();
	}
	
	public void addAuthor(long pubId, long authorId) {
		Publication pub = (Publication) sf.getCurrentSession().get(Publication.class, pubId);
		Author author = (Author) sf.getCurrentSession().get(Author.class, authorId);
		pub.getAuthors().add(author);
		author.getPublications().add(pub);
		sf.getCurrentSession().update(pub);
		sf.getCurrentSession().update(author);
	}
	
	public void update(Publication pub) {
		sf.getCurrentSession().update(pub);
	}
	
	public void delete(long pubId) {
		Session session = sf.getCurrentSession();
		Publication pub = (Publication) session.get(Publication.class, pubId);
		for (Author author : pub.getAuthors()) {
			author.getPublications().remove(pub);
			session.update(author);
		}
		pub.setAuthors(null);
		session.update(pub);
		session.delete(pub);
	}
	
	public void addTopic(long pubId, Topic topic) {
		Session session = sf.getCurrentSession();
		Publication pub = (Publication) session.get(Publication.class, pubId);
		pub.getTopics().add(topic);
		session.update(pub);
	}
	
	@SuppressWarnings("unchecked")
	public Set<Topic> getTopics() {
		return new HashSet<Topic>(sf.getCurrentSession().createQuery("from Topic").list());
	}
	
	public Topic findTopicByName(String name) {
		for (Topic topic : getTopics()) {
			if (topic.getName().equalsIgnoreCase(name))
				return topic;
		}
		return null;
	}
	
	public void saveTopic(Topic topic) {
		sf.getCurrentSession().saveOrUpdate(topic);
	}

}