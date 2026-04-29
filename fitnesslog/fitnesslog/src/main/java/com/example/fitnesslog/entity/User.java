package com.example.fitnesslog.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "users")
@Data
public class User {

    @Id
    private String id;

    @Indexed(unique = true)
    private String email;

    private String password;
}
