package com.barker.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Topic {

	@Id @GeneratedValue
	private Long topicId;
	private String name;
	
}
