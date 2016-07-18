package com.barker.formatter;

import org.springframework.core.convert.converter.Converter;

import com.barker.model.Author;

public class AuthorStringConverter implements Converter<Author, String> {

	@Override
	public String convert(Author author) {
		return Long.toString(author.getAuthorId());
	}

}
