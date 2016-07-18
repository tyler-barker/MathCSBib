package com.barker.formatter;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.format.Formatter;

import com.barker.model.Author;

public class AuthorFormatter implements Formatter<Author>{
	
	@Override
	public String print(Author aut, Locale l) {
		return Long.toString(aut.getAuthorId());
	}

	@Override
	public Author parse(String s, Locale l) throws ParseException {
		//AuthorDAO authorDAO = ctx.getBean(AuthorDAO.class);
		//return authorDAO.getAuthor(Long.parseLong(s));
		Author author = new Author();
		author.setAuthorId(Long.parseLong(s));
		return author;
	}

}
