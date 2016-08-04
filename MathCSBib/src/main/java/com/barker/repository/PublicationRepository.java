package com.barker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.barker.model.Publication;

public interface PublicationRepository extends JpaRepository<Publication, Long>{

}
