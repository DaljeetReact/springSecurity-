package com.singh.blogapp.controller;

import com.singh.blogapp.model.Post;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/management/api/v1/post")
public class PostController {

    @GetMapping("/all")
    public List<Post> fetchAllPost(){
        List<Post> data = List.of(
                new Post("hello","hello i am content", LocalDate.of(2022,9,17),LocalDate.of(2022,9,17),"singh")
        );
        return  data;
    }

    @PostMapping
    public void  CreatePost(@RequestBody Post post){
        System.out.println(post);
    }

    @DeleteMapping(path = "{postId}")
    public void DeletePost(@PathVariable("postId") Integer postId){
        System.out.println(postId);
    }

    @PutMapping(path = "{postId}")
    public void UpdatePost(@PathVariable("postId") Integer postId, @RequestBody Post post){
        System.out.println(post);
    }
}
