package com.barker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.barker.model.Publication;
import com.barker.model.Topic;

public interface TopicRepository extends JpaRepository<Topic, Long> {
	
	@Query("select p from Publication p where ?1 member p.topics")
	List<Publication> findPublicationsByTopic(Topic topic);

}
