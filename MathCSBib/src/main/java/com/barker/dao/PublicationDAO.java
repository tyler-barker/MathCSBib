package com.barker.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.barker.model.Author;
import com.barker.model.Publication;

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
	public List<Publication> getPublications() {
		return sf.getCurrentSession().createQuery("from Publication").list();
	}
	
	public List<Author> getAuthorsFromPublication(long id) {
		Publication publication = (Publication) sf.getCurrentSession().get(Publication.class, id);
		return publication.getAuthors();
	}

}