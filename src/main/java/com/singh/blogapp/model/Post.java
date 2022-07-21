package com.singh.blogapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "Post")
@Table
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String title;
    private String content;
    private LocalDate created_at;
    private LocalDate updated_at;
    private String username;

    public Post(String title, String content, LocalDate created_at, LocalDate updated_at, String username) {
        this.title = title;
        this.content = content;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.username = username;
    }
}