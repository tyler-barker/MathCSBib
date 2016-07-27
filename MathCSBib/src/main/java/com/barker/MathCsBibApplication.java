package com.barker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@SpringBootApplication
@EnableOAuth2Sso
public class MathCsBibApplication extends WebSecurityConfigurerAdapter {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(MathCsBibApplication.class, args);
		
/*		PublicationDAO pubDAO = ctx.getBean(PublicationDAO.class);
		AuthorDAO authorDAO = ctx.getBean(AuthorDAO.class);
		
		Publication pub = new Publication();
		Author author = new Author();
		author.setFirstName("Tyler");
		author.setLastName("Barker");
		author.setMiddleInitial("C");
		author.setUniversity("Tulane");
		author.setPicture("http://cs.tulane.edu/~tbarker/images-static/Me.jpg");
		authorDAO.save(author);
		
		Author author2 = new Author();
		author2.setFirstName("Michael");
		author2.setLastName("Mislove");
		author2.setUniversity("Tulane");
		author2.setPicture("http://www.cs.tulane.edu/~mwm/Michael_Misloves_Home_Page/Michael_Mislove_files/droppedImage.jpg");
		authorDAO.save(author2);
		
		pub.setTitle("A Monad for Randomized Algorithms");
		pub.setUrl("http://cs.tulane.edu/~tbarker/files/dissertation.pdf");
		pub.getAuthors().add(author);
		author.getPublications().add(pub);
		
		pubDAO.save(pub);
		authorDAO.save(author);
		authorDAO.save(author2);*/
		
	}
	
	@Bean
    public HibernateJpaSessionFactoryBean sessionFactory() {
        return new HibernateJpaSessionFactoryBean();
    }
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.antMatcher("/**")
			.authorizeRequests()
				.antMatchers("/", "/authors**", "/publications**", "/topics**", "/login**", "/webjars/**")
				.permitAll()
			.anyRequest()
				.authenticated();
	}

	
}
