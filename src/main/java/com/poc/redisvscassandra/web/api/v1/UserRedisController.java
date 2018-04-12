package com.poc.redisvscassandra.web.api.v1;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.datastax.driver.core.utils.UUIDs;
import com.poc.redisvscassandra.model.entity.User;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/apiredis/v1")
public class UserRedisController {
    
    @Autowired
    private ReactiveRedisOperations<String, User> template;
    
    @GetMapping("/users")
    public Flux<User> getAllUsers() {
        System.out.println("Get all users...");
        return template.<String, User>opsForHash().values("users");
    }
    
    @PostMapping("/users/create")
    public Mono<User> createCustomer(@Valid @RequestBody User user) {
        System.out.println("Create user: " + user.getName() + "...");
        user.setId(UUIDs.timeBased());
        return template.<String, User>opsForHash().put("users", user.getId().toString(), user)
                .log()
                .map(p -> user);
    }
    
    @GetMapping("/user/{id}")
    public Mono<User> findById(@PathVariable String userId) {
        return template.<String, User>opsForHash().get("users", userId);
    }
    

}
