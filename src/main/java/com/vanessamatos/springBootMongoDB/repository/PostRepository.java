package com.vanessamatos.springBootMongoDB.repository;

import com.vanessamatos.springBootMongoDB.domain.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

}
