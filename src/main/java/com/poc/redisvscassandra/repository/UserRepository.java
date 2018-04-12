package com.poc.redisvscassandra.repository;

import java.util.UUID;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.poc.redisvscassandra.model.entity.User;

import reactor.core.publisher.Flux;

public interface UserRepository extends ReactiveCrudRepository<User, UUID> {

    Flux<User> findByAge(final int age);
    
    Flux<User> findByName(final String name);
}