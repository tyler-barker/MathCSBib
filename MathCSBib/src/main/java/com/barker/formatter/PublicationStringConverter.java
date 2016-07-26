package com.barker.formatter;

import org.springframework.core.convert.converter.Converter;

import com.barker.model.Publication;

public class PublicationStringConverter implements Converter<Publication, String> {

	@Override
	public String convert(Publication pub) {
		return Long.toString(pub.getPubId());
	}

}
