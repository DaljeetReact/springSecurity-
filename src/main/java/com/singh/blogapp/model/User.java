package com.singh.blogapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;


@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "User")
@Table
@ToString
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String  username;
    private String password;

    public User(String email, String username, String password) {
    }
}
