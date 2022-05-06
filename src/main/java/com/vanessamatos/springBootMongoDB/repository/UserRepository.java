package com.vanessamatos.springBootMongoDB.repository;

import com.vanessamatos.springBootMongoDB.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

}
