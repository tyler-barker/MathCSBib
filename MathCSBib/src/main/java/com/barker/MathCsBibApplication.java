package com.barker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;

import com.barker.dao.AuthorDAO;
import com.barker.dao.PublicationDAO;
import com.barker.model.Author;
import com.barker.model.Publication;

@Configuration
@EnableAutoConfiguration
@ComponentScan("com.barker")
public class MathCsBibApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(MathCsBibApplication.class, args);
		
		PublicationDAO pubDAO = ctx.getBean(PublicationDAO.class);
		AuthorDAO authorDAO = ctx.getBean(AuthorDAO.class);
		
		Publication pub = new Publication();
		Author author = new Author();
		author.setFirstName("Tyler");
		author.setLastName("Barker");
		author.setMiddleInitial("C");
		pub.setTitle("A Monad for Randomized Algorithms");
		pub.getAuthors().add(author);
		Publication pub2 = new Publication();
		pub2.setTitle("Paper 2");
		pub2.getAuthors().add(author);
		
		authorDAO.save(author);
		pubDAO.save(pub);
		pubDAO.save(pub2);
		
		
	}
	
	@Bean
    public HibernateJpaSessionFactoryBean sessionFactory() {
        return new HibernateJpaSessionFactoryBean();
    }
}
