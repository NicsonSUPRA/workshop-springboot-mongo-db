package com.nicson.workshopmongo.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicson.workshopmongo.domains.User;
import com.nicson.workshopmongo.dto.UserDTO;
import com.nicson.workshopmongo.repositories.UserRepository;
import com.nicson.workshopmongo.services.exceptions.ObjectNotFoundException;

@Service
public class UserServices {

    @Autowired
    private UserRepository repository;

    public List<User> findAll() {
        return repository.findAll();
    }

    public User findById(String id) {
        try {
            Optional<User> user = repository.findById(id);
            return user.get();
        } catch (NoSuchElementException e) {
            throw new ObjectNotFoundException("Objeto não encontrado");
        }
    }

    public User insert(User user) {
        return repository.insert(user);
    }

    public void delete(String id) {
        findById(id);
        repository.deleteById(id);
    }

    public User update(User user) {
        Optional<User> newUser = repository.findById(user.getId());
        updateData(newUser.get(), user);
        return repository.save(newUser.get());
    }

    private void updateData(User newUser, User user) {
        newUser.setName(user.getName());
        newUser.setEmail(user.getEmail());
    }

    public User fromDTO(UserDTO userDTO) {
        return new User(userDTO.getId(), userDTO.getName(), userDTO.getEmail());
    }
}
