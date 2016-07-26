package com.barker.formatter;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.format.Formatter;

import com.barker.model.Publication;

public class PublicationFormatter implements Formatter<Publication>{
	
	@Override
	public String print(Publication pub, Locale l) {
		return Long.toString(pub.getPubId());
	}

	@Override
	public Publication parse(String s, Locale l) throws ParseException {
		Publication pub = new Publication();
		pub.setPubId(Long.parseLong(s));
		return pub;
	}

}
