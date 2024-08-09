package com.nicson.workshopmongo.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicson.workshopmongo.domains.Post;
import com.nicson.workshopmongo.repositories.PostRepository;
import com.nicson.workshopmongo.services.exceptions.ObjectNotFoundException;

@Service
public class PostService {

    @Autowired
    private PostRepository repository;

    public Post findById(String id) {
        try {
            Optional<Post> post = repository.findById(id);
            return post.get();
        } catch (NoSuchElementException e) {
            throw new ObjectNotFoundException("O post não foi encontrado!");
        }
    }

    public List<Post> findByTitle(String text) {
        return repository.findByTitleContainingIgnoreCase(text);
    }
}
