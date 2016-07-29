package com.barker.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.barker.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{
	User findOneByUserName(String userName);

}
