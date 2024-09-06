package com.example.demo.service;

import com.example.demo.dto.DemoUser;
import com.example.demo.exception.DemoServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DemoService {

    private final List<DemoUser> users = new ArrayList<>();

    public DemoService() {
        users.add(new DemoUser(1, "Alice", "alice@example.com"));
        users.add(new DemoUser(2, "Bob", "bob@example.com"));
        users.add(new DemoUser(3, "Charlie", "charlie@example.com"));
    }


    public List<DemoUser> getUsers() {
        return users;
    }

    public Optional<DemoUser> findUserById(long id) {
       return users.stream().filter(user -> user.getId() == id).findAny();
    }

    public void createUser(DemoUser demoUser) throws DemoServiceException {
        Optional<DemoUser> existingUser = findUserById(demoUser.getId());

        if (existingUser.isEmpty()) {
            users.add(demoUser);
        } else {
            throw new DemoServiceException("User with this ID already exists!", HttpStatus.BAD_REQUEST);
        }

    }
public void updateUserById(long id, DemoUser demoUser) throws DemoServiceException {
    Optional<DemoUser> demoUserToUpdate = findUserById(id);

    if (demoUserToUpdate.isPresent()) {
        DemoUser userToUpdate = demoUserToUpdate.get();
        userToUpdate.setName(demoUser.getName());
        userToUpdate.setEmail(demoUser.getEmail());
    } else {
        throw new DemoServiceException("User not found!", HttpStatus.NOT_FOUND);
    }
}
public void deleteUserById(long id) throws DemoServiceException {
    Optional<DemoUser> userToDelete = findUserById(id);

    if (userToDelete.isPresent()) {
        users.removeIf(user -> user.getId() == id);
    } else {
        throw new DemoServiceException("User not found!", HttpStatus.NOT_FOUND);
    }
}

}
