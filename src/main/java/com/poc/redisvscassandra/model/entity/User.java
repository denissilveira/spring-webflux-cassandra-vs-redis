package com.poc.redisvscassandra.model.entity;

import java.io.Serializable;
import java.util.UUID;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table("users")
public class User implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @PrimaryKey
    private UUID id;
    
    private String name;
    private int age;
    
}