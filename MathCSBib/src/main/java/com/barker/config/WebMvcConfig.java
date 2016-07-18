package com.barker.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.barker.formatter.AuthorFormatter;
import com.barker.formatter.AuthorStringConverter;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter{
	
	@Override
	public void addFormatters(FormatterRegistry formatterRegistry) {
        formatterRegistry.addFormatter(new AuthorFormatter());
        formatterRegistry.addConverter(new AuthorStringConverter());
    }

}
