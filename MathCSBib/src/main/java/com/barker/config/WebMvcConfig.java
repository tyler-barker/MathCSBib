package com.barker.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.barker.formatter.AuthorFormatter;
import com.barker.formatter.AuthorStringConverter;
import com.barker.formatter.PublicationFormatter;
import com.barker.formatter.PublicationStringConverter;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter{
	
	@Override
	public void addFormatters(FormatterRegistry formatterRegistry) {
        formatterRegistry.addFormatter(new AuthorFormatter());
        formatterRegistry.addFormatter(new PublicationFormatter());
        formatterRegistry.addConverter(new AuthorStringConverter());
        formatterRegistry.addConverter(new PublicationStringConverter());
    }

}
