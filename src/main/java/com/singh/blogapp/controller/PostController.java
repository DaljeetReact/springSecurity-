package com.singh.blogapp.controller;

import com.singh.blogapp.model.Post;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/management/api/v1/post")
@PreAuthorize("hasAnyRole('Role_ADMIN','ROLE_ADMINTRANEE')")// roles authentication
//under pre authorities we can add hasRole(),hasAnyRole(),hasAuthority('permissions'),hasAnyAuthority('Permission')
public class PostController {

    @GetMapping("/all")
    @PreAuthorize("hasAnyAuthority('post:read')")
    public List<Post> fetchAllPost(){
        List<Post> data = List.of(
                new Post("hello","hello i am content", LocalDate.of(2022,9,17),LocalDate.of(2022,9,17),"singh")
        );
        return  data;
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('post:write')") //Permissions
    public void  CreatePost(@RequestBody Post post){
        System.out.println(post);
    }

    @DeleteMapping(path = "{postId}")
    @PreAuthorize("hasAnyAuthority('post:write')") //permissions
    public void DeletePost(@PathVariable("postId") Integer postId){
        System.out.println(postId);
    }

    @PutMapping(path = "{postId}")
    @PreAuthorize("hasAnyAuthority('post:write')") //Permissions
    public void UpdatePost(@PathVariable("postId") Integer postId, @RequestBody Post post){
        System.out.println(post);
    }
}
