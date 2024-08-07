package com.nicson.workshopmongo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.nicson.workshopmongo.domains.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

}
