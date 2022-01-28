package com.ycsum.elkdemo.controller;

import com.ycsum.elkdemo.data.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    private final List<User> users = Arrays.asList(
            new User(1, "test1"),
            new User(2, "test2"),
            new User(3, "test3")
    );

    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable Integer id) {
        Optional<User> userOptional = users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();

        if (userOptional.isPresent()) {
            logger.info("[{}] Get user with id: {}", "/user/:id", id );
            return userOptional.get();
        }

        logger.error("[{}] Get user with id: {} not found!", "/user/:id", id);
        return null;
    }

    @PostMapping("/user")
    public User saveUser(@RequestBody User user) {
        logger.info("saved User");
        return user;
    }
}
