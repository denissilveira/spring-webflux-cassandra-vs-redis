package com.poc.redisvscassandra.web.api.v1;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.datastax.driver.core.utils.UUIDs;
import com.poc.redisvscassandra.model.entity.User;
import com.poc.redisvscassandra.repository.UserRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/api/v1")
public class UserController {
    
    @Autowired
    private UserRepository repository;
    
    @GetMapping("/users")
    public Flux<User> getAllUsers() {
        System.out.println("Get all users...");
        return repository.findAll();
    }
    
    @PostMapping("/users/create")
    public Mono<User> createCustomer(@Valid @RequestBody User user) {
        System.out.println("Create user: " + user.getName() + "...");
        user.setId(UUIDs.timeBased());
        return repository.save(user);
    }
    
    @GetMapping("/user/{id}")
    public Mono<User> findById(@PathVariable String userId) {
        return repository.findById(UUID.fromString(userId));
    }
    
    @GetMapping("/users/findbyage")
    public Flux<User> findByAge(@RequestParam int age) {
        return repository.findByAge(age);
    }
    
    @GetMapping("/users/findbyname")
    public Flux<User> findByName(@RequestParam String name) {
        return repository.findByName(name);
    }

}
