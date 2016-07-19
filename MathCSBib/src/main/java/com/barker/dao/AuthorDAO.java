package com.barker.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.barker.model.Author;
import com.barker.model.Publication;

@Repository
@Transactional
public class AuthorDAO {
	
	private final SessionFactory sf;

    @Autowired
    public AuthorDAO(SessionFactory sf) {
        this.sf=sf;
    }
	
	public void save(Author author) {
		sf.getCurrentSession().saveOrUpdate(author);
	}
	
	public Author getAuthor(long id) {
		Author author = (Author) sf.getCurrentSession().get(Author.class, id);
		return author;
	}
	
	@SuppressWarnings("unchecked")
	public List<Author> getAuthors() {
		return sf.getCurrentSession().createQuery("from Author").list();
	}
	
	public List<Publication> getPublicationsFromAuthor(long id) {
		Author author = (Author) sf.getCurrentSession().get(Author.class, id);
		return author.getPublications();
	}
	
	public void update(Author author) {
		sf.getCurrentSession().update(author);
	}
	
	public void delete(long authorId) {
		Session session = sf.getCurrentSession();
		Author author = (Author) session.get(Author.class, authorId);
		for (Publication pub : author.getPublications()) {
			pub.getAuthors().remove(author);
			session.update(pub);
		}
		author.setPublications(null);
		session.update(author);
		session.delete(author);
	}

}