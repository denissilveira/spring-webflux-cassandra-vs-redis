package com.poc.redisvscassandra.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.poc.redisvscassandra.model.entity.User;

import reactor.core.publisher.Flux;

@Repository
public interface UserRedisRepository extends CrudRepository<User, UUID> {

    Flux<User> findByAge(final int age);

    Flux<User> findByName(final String name);

}