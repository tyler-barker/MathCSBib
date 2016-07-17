package com.barker.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.barker.model.Author;

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

}