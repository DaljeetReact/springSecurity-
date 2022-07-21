package com.singh.blogapp.controller;


import com.singh.blogapp.dto.RegisterRequest;
import com.singh.blogapp.model.Post;
import com.singh.blogapp.model.User;
import com.singh.blogapp.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity signup(@RequestBody RegisterRequest registerRequest){
         this.authService.signup(registerRequest);
        return  new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/post")
    public List<Post> fetchAllPost(){
        List<Post> data = List.of(
                new Post("hello","hello i am content",LocalDate.of(2022,9,17),LocalDate.of(2022,9,17),"singh")
        );

        return  data;
    }

    @GetMapping("/users")

    public List<User> users(){
        return  this.authService.AllUsers();
    }

}
